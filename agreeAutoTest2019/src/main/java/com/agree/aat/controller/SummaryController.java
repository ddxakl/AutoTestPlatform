/**
 * 
 */
package com.agree.aat.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agree.framework.constant.ApplicationKeyConst;
import com.agree.system.entity.ExecuteCase;
import com.agree.system.entity.ExecuteItem;
import com.agree.system.entity.SystemUser;
import com.agree.system.service.IExecuteCaseService;
import com.agree.system.service.IExecuteItemService;
import com.agree.util.ClassUtils;
import com.agree.util.CommUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 报表统计
 * 代码不规范，维护两行泪！
 * @author xp
 * @date 2019年10月17日
 * 
 */
@RestController
@RequestMapping("/Summary")
public class SummaryController {
	
	private static final Logger log=LogManager.getLogger(SummaryController.class);
	@Autowired
	IExecuteItemService executeItemService;
	@Autowired
	IExecuteCaseService executeCaseService;
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = { "/uploadFiles" }, method = { RequestMethod.POST })
	public String uploadFiles(HttpServletRequest files,Model model) {
		String fileList = files.getParameter("fileList");
//		String casetype = files.getParameter("casetype");
		String caseId =  "C"+System.currentTimeMillis();
		JSONArray scriptInfos = JSONArray.fromObject(fileList);
		
		for(int i=0;i<scriptInfos.size();i++) {
			
			ExecuteItem executeItem = new ExecuteItem();
			
			JSONObject jsonItem = JSONObject.fromObject(scriptInfos.get(i));
			
			SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
		
			executeItem.setItemid("I"+System.currentTimeMillis());
			executeItem.setItemindex((long)(i+1));
			executeItem.setTradecode(jsonItem.getString("tradeCode"));
			executeItem.setItemvalue(jsonItem.getString("value"));
			executeItem.setItemname(jsonItem.getString("itemName"));
			executeItem.setItemcode(jsonItem.getString("id"));
			if(jsonItem.has("pageCode")) {
				executeItem.setPagecode(jsonItem.getString("pageCode"));
			}
			executeItem.setItemtype(jsonItem.getString("controlType"));
			executeItem.setCreatedate(dataFormat.format(new Date()));
			executeItem.setAddvalue(jsonItem.getString("valueAdd")==null?"":jsonItem.getString("valueAdd"));
			executeItem.setCaseid(caseId);
			executeItem.setIsscreen("N");
			executeItem.setItemevent(jsonItem.getString("eventName"));
			executeItemService.insertSelective(executeItem);
			
		}
			model.addAttribute("caseId", caseId);
			
		return caseId;
	}
	
	@RequestMapping(value= {"/createCase"},method= {RequestMethod.POST})
	public void createCase(HttpServletRequest request) {
		ExecuteCase executeCase = new ExecuteCase();
		executeCase.setCaseid(request.getParameter("caseId"));
		executeCase.setCasebelong(request.getParameter("belong"));
		executeCase.setCasedesc(request.getParameter("caseDesc"));
		executeCase.setTradecodes(request.getParameter("tradecodes"));
		executeCase.setCasename(request.getParameter("caseName"));
		executeCase.setCaseidentifier(request.getParameter("caseIdentifier"));
		executeCase.setCasetype(request.getParameter("scriptType"));
		executeCase.setAbversion(request.getParameter("abversion"));
		executeCase.setBatchcase("F");
		SystemUser user = (SystemUser)session.getAttribute(ApplicationKeyConst.USER_INFO);
		executeCase.setUserid(user.getUsername());
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		executeCase.setCreatedate(dateFormat.format(date));
		if("ST".equals(request.getParameter("scriptType"))) {
			String rootPath = this.getClass().getResource("/").getPath().replaceAll("^\\/", ""); 
	        rootPath= rootPath.replace("WEB-INF/classes/", "");
	        String rootPathParent = new File(rootPath).getParentFile().getAbsolutePath(); //webapps
	        String realPath = rootPathParent+ApplicationKeyConst.AT_ASSET_PATH+ request.getParameter("caseId") + "/script";
	        File file = new File(realPath);
			File[] tempList = file.listFiles();
			log.info("脚本名称："+tempList[0].getName());
			executeCase.setScriptclass(ClassUtils.loadByteArray(realPath+File.separator+tempList[0].getName()));
			
		}
		executeCaseService.insertSelective(executeCase);
	}

	@RequestMapping(value= {"/updateCase"},method= {RequestMethod.POST})
	public void updateCase(HttpServletRequest request) {
		ExecuteCase executeCase = new ExecuteCase();
		executeCase.setCaseid(request.getParameter("caseid"));
		executeCase.setCasebelong(request.getParameter("casebelong"));
		executeCase.setCasedesc(request.getParameter("casedesc"));
		executeCase.setTradecodes(request.getParameter("tradecodes"));
		executeCase.setCasename(request.getParameter("casename"));
		executeCase.setCaseidentifier(request.getParameter("caseidentifier"));
		executeCaseService.updateByPrimaryKeySelective(executeCase);
	}
	
}
