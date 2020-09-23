package com.agree.aat.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.agree.aat.service.IScriptCaseService;
import com.agree.framework.base.ServiceReturn;
import com.agree.framework.constant.ApplicationKeyConst;
import com.agree.framework.constant.ResultCodeEnum;
import com.agree.system.entity.ExecuteCase;
import com.agree.system.service.IExecuteCaseService;
import com.agree.system.service.IExecuteItemService;
import com.agree.system.service.ISystemUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONObject;

/**
 * 脚本案例Controller类
 * @author xrp09
 *
 */
@RestController
@RequestMapping("/script")
public class ScriptCaseController {
	
	
	private static final Logger log=LogManager.getLogger(ScriptCaseController.class);
	@Autowired
	IExecuteCaseService executeCaseService;
	@Autowired
	IExecuteItemService executeItemService;
	@Autowired
	ISystemUserService systemUserService;
	@Autowired
	IScriptCaseService scriptcaseService;
	
	
	@RequestMapping(value = "/getCaseList", method = RequestMethod.GET)
    public Map<String, Object> getRecord(HttpServletRequest request) {
		ServiceReturn result = null;
        Map<String, Object> map = new HashMap<String, Object>();
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize"));// 每页显示的size
        Integer draw = Integer.valueOf(request.getParameter("draw") == null ? "0" : request.getParameter("draw"));// 记录操作的次数 每次加1
        Integer page = Integer.valueOf(request.getParameter("page") == null ? "0" : request.getParameter("page"));//
        String casebelong = request.getParameter("casebelong");
        String casename = request.getParameter("casename");
        String abversion = request.getParameter("abversion");
        String tradecodes = request.getParameter("tradecodes");
        Map executeCase = new HashMap<>();
        executeCase.put("casebelong", casebelong);
        executeCase.put("casename", casename);
        executeCase.put("abversion", abversion);
        executeCase.put("casetype", "ST");
        executeCase.put("tradecodes", tradecodes);
        PageHelper.startPage(page, pageSize);
		List<ExecuteCase> executeCaseList = executeCaseService.getAllExecuteCase(executeCase);
        final PageInfo<ExecuteCase> pageInfo = new PageInfo<>(executeCaseList);
        map.put("draw", draw);
        map.put("total", pageInfo.getTotal());//数据总条数
        map.put("pageData", executeCaseList);//数据集合
		try {
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
			result.setData(executeCaseList);
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error("查询录制案例出错："+e.getMessage());
		}
        return map;
    }
	
	/**
     * <B>方法名称：删除案例</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @return
     */
    @RequestMapping(value = "/removes", method = RequestMethod.POST)
    public String removeByCaseid(HttpServletRequest request) {
        String caseid = request.getParameter("caseid");
        if (!"".equals(caseid) && null != caseid) {
        	executeCaseService.removeByCaseid(caseid);
		}else {
			log.error("caseid为空!");
		}
        return "";
    }
    
    
    /**
     * <B>方法名称：上传脚本</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @return
     */
    @RequestMapping("/upscript")
    @ResponseBody
    public ServiceReturn upscript(@RequestParam("upscript") CommonsMultipartFile upscript, HttpServletRequest request) {
    	String caseId = "C"+System.currentTimeMillis();
        //原文件名
        String orgName = upscript.getOriginalFilename();
        //八位时间
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(c.getTime());
        today = today.replaceAll("-", "");
        //uuid+时间戳
//        String alone = getAlone();
        String rootPath = this.getClass().getResource("/").getPath().replaceAll("^\\/", ""); 
        rootPath = rootPath.replace("WEB-INF/classes/", "");//webapps/agreeAutoTest2019/
        String rootPathParent = new File(rootPath).getParentFile().getAbsolutePath(); //webapps
        String pPath = request.getSession().getServletContext().getRealPath(File.separator + "temp" + File.separator + today + File.separator + caseId);
        String realPath = rootPathParent+ApplicationKeyConst.AT_ASSET_PATH + caseId + "/script";
        new java.io.File(realPath).mkdir();
        String path = realPath + File.separator + orgName;
        File newFile = new File(path);
        if (!newFile.exists()) {
            newFile.mkdirs();
        }
        try {
            upscript.transferTo(newFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject json = new JSONObject();
        json.put("parentpath", pPath);
        json.put("realPath", realPath);
        json.put("scriptfile", path);
        json.put("scriptname", orgName);
        json.put("caseId", caseId);
        ServiceReturn result;
        result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
        result.setJson(json);
        
        return result;
    }
    
    
    
    /**
     * 获得一个独一无二的标识
     *
     * @return String UUID
     */
    public static String getAlone() {
        String uuid = UUID.randomUUID().toString();
        long currentTimeMillis = System.currentTimeMillis();
        //去掉“-”符号
        String replaceAll = uuid.replaceAll("-", "");
        String tempId = replaceAll + currentTimeMillis;
        return tempId;
    }
    
    
	
	@RequestMapping(value = "/executescript", method = RequestMethod.POST)
	@ResponseBody
    public ServiceReturn executescript(HttpServletRequest request) {
		String caseid = request.getParameter("caseid");
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", "69");
		map.put("caseid",caseid);
		ServiceReturn result = null;
		try {
			
			scriptcaseService.executeStCase(map);
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error("查询提测出错："+e.getMessage());
		}
	    return result;
    }

}
