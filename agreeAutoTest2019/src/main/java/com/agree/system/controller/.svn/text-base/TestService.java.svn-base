/**
 * 
 */
package com.agree.system.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.agree.aat.controller.FlowController;
import com.agree.framework.base.ServiceReturn;
import com.agree.framework.constant.ApplicationKeyConst;
import com.agree.framework.constant.ResultCodeEnum;
import com.agree.system.service.IExecuteCaseService;
import com.agree.system.service.ISystemUserService;
import com.agree.util.FileUtil;

import net.sf.json.JSONObject;

/**
 * 测试服务相关类
 * @author xp
 * @date 2019年8月7日
 * 
 */
@Controller
@RequestMapping("/testService")
public class TestService {
	
	private static final Logger log=LogManager.getLogger(TestService.class);
	
	@Autowired
	IExecuteCaseService executeCaseService;
	@Autowired
	ISystemUserService systemUserService;
	
	@RequestMapping(value = "/record", method = RequestMethod.GET)
    public String getRecord(HttpServletRequest request) {
		return "/aat/testService/record/record";
    }
	
	@RequestMapping("/scriptCase")
	public String scriptCase(){
		return "/aat/testService/scriptCase/scriptCase";
	}
	@RequestMapping("/testPlan")
	public String testPlan(){
		return "/aat/testService/testPlan/testPlan";
	}
	@RequestMapping("/executeTerminal")
	public String executeTerminal() {
		return "/aat/testService/executeTerminal/executeTerminal";
	}
	
	@RequestMapping("/caseItem")
	public String caseItem() {
		return "/aat/testService/record/caseItems";
	}

	@RequestMapping("/upload/RP")
	public String upload(HttpServletRequest request) {
		request.setAttribute("casetype", "RP");
		return "/aat/testService/record/upload";
	}

	@RequestMapping("/upload/ST")
	public String uploadzs(HttpServletRequest request) {
		request.setAttribute("casetype", "ST");
		return "/aat/testService/scriptCase/uploadst";
	}

	@RequestMapping(value="/supplyInfos/{caseId}/{rporst}",method=RequestMethod.GET)
	public String supplyInfos( @PathVariable("caseId")String caseId,@PathVariable("rporst")String rporst,Model model) {
		model.addAttribute("caseId", caseId);
		model.addAttribute("rporst", rporst);
		return "/aat/testService/record/scriptInfos";
	}
	
	@RequestMapping(value="/scriptcaseSupplyInfos/{caseId}/{rporst}",method=RequestMethod.GET)
	public String scriptcaseSupplyInfos( @PathVariable("caseId")String caseId,@PathVariable("rporst")String rporst,Model model) {
		model.addAttribute("caseId", caseId);
		model.addAttribute("rporst", rporst);
		return "/aat/testService/scriptCase/scriptInfos";
	}
	
	@RequestMapping("/replay")
	public String replay() {
		return "/aat/testService/record/replay";
	}
	
	@RequestMapping("/dataPlugin")
	public String dataPlugin() {
		return "/aat/testService/record/dataPlugin";
	}
	
	@RequestMapping("/casesExecute")
	public String submitCasesExecute() {
		return "/aat/testService/testPlan/casesExecute";
	}
	
	@RequestMapping("/expectresult")
	public String expectResult() {
		return "/aat/testService/expectResult/expectResult";
	}
	
	@RequestMapping("/addPlanTask")
	public String addPlanTask() {
		return "/aat/testService/testPlan/addPlanTask";
	}
	
	@RequestMapping("/statementDetails")
	public String statementDetails() {
		return "/aat/statisticalForm/statementDetails/statementDetails";
	}

	
	 
    /**
     * <B>方法名称：上传数据外挂</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @return
     */
    @RequestMapping("/uploadDataFile")
    @ResponseBody
    public boolean uploadDataFile(@RequestParam("sealPfxFile") CommonsMultipartFile upscript, HttpServletRequest request) {
    	String caseId = request.getParameter("dataPlugin-caseid");
        //原文件名
        String orgName = upscript.getOriginalFilename();
        //原文件扩展名
        String suffix = orgName.substring(orgName.lastIndexOf(".") + 1);
        //八位时间
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(c.getTime());
        today = today.replaceAll("-", "");
        //uuid+时间戳
        String rootPath = this.getClass().getResource("/").getPath().replaceAll("^\\/", ""); 
        rootPath= rootPath.replace("WEB-INF/classes/", "");
        String rootPathParent = new File(rootPath).getParentFile().getAbsolutePath(); //webapps
        String pPath = request.getSession().getServletContext().getRealPath(File.separator + "temp" + File.separator + today + File.separator + caseId);
//        String realPath = rootPath+ApplicationKeyConst.TEMPPATH+ caseId;
        String realPath = rootPathParent+ApplicationKeyConst.AT_ASSET_PATH + caseId + "/dataPlugin";
        
        if ("xlsx".equals(suffix)) {
            //上传前先清空dataPlugin目录下所有文件
            deleteFile(new File(realPath));
            
        	new java.io.File(realPath).mkdir();
            String path = realPath + File.separator + orgName;
            File newFile = new File(path);
            if (!newFile.exists()) {
                newFile.mkdirs();
            }
            try {
                upscript.transferTo(newFile);
                
            	//判断上传的文件是否存在
                if (FileUtil.isFileExist(orgName, realPath+"/")) {
                	//如果存在,则更新BATCHCASE为T,否则为F
                	executeCaseService.updateBatchcaseByCaseid(caseId,"T");
        		}else {
        			executeCaseService.updateBatchcaseByCaseid(caseId,"F");
        			return false;
        		}
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
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
            return true;
		}else {
			return false;
		}
    }
    
    /**
 	  *  删除路径下所有文件
     * @param file
     */
    public static void deleteFile(File file){
        //判断文件不为null或文件目录存在
        if (file == null || !file.exists()){
            log.error("文件删除失败, 请检查文件路径是否正确");
            return;
        }
        //取得这个目录下的所有子文件对象
        File[] files = file.listFiles();
        //遍历该目录下的文件对象
        for (File f: files){
            //判断子目录是否存在子目录,如果是文件则删除
            if (f.isDirectory()){
                deleteFile(f);
            }else {
                f.delete();
            }
        }
    }
}
