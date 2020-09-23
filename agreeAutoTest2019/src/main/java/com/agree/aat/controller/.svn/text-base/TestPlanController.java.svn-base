package com.agree.aat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.agree.aat.entity.ExecutePlan;
import com.agree.aat.entity.PlanCase;
import com.agree.aat.service.ITestPlanService;
import com.agree.framework.base.ServiceReturn;
import com.agree.framework.constant.ResultCodeEnum;
import com.agree.system.entity.ExecuteCase;
import com.agree.system.service.IExecuteCaseService;
import com.agree.system.service.ISystemUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 提测计划controller
 * @author xrp09
 *
 */
@Controller
@RequestMapping("/testplan")
public class TestPlanController {
	
	private static final Logger log=LogManager.getLogger(TestPlanController.class);
	@Autowired
	IExecuteCaseService executeCaseService;
	@Autowired
	ISystemUserService systemUserService;
	@Autowired
	ITestPlanService testPlanService;
	
	
	/**
	 * 删除
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delplanlist", method = RequestMethod.GET)
	@ResponseBody
    public ServiceReturn delplanlist(HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
		String palanId = request.getParameter("planid");
		ServiceReturn result = null;
		try {
			testPlanService.delPlan(palanId);
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error(e.getMessage());
		}
	    return result;
    }
	
	
	
	/**
	 * 录制回放
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/pbexecute", method = RequestMethod.POST)
	@ResponseBody
    public ServiceReturn pbexecute(HttpServletRequest request) {
		ServiceReturn result = null;
		//获取执行数据
		JSONObject json = new JSONObject();
		JSONArray jsonarr = new JSONArray();
		String batchcase = request.getParameter("batchcase");
		String caseId = request.getParameter("caseId");
		if(batchcase.equals("T")) {
			jsonarr=testPlanService.getTransItems(caseId);
		}
		
		if(jsonarr.isEmpty()) {
			jsonarr.add(caseId);
		}
		
		json.put("caseList",jsonarr);
		json.put("type", request.getParameter("type"));
		json.put("executeclientid", request.getParameter("executeclientid"));
		json.put("userId",  request.getParameter("username"));
		
		try {
			testPlanService.findCaseAndExecute(json);
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error(e.getMessage());
		}
	    return result;
    }
	
	
	
	
	
	/**
	 * 提测任务
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/planexecute", method = RequestMethod.POST)
	@ResponseBody
    public ServiceReturn bacthexecute(HttpServletRequest request) {
		ServiceReturn result = null;
		
		String parameter = request.getParameter("");//获取执行数据
		try {
//			List<ExecutePlan> executeCaseList = executeCaseService.getAllExecuteCase();
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
			result.setData(new ArrayList());
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error("查询提测出错："+e.getMessage());
		}
	    return result;
    }
	
	
	@RequestMapping(value = "/getPlanList", method = RequestMethod.GET)
	@ResponseBody
    public Map<String, Object> getRecord(HttpServletRequest request) {
		ServiceReturn result = null;
        Map<String, Object> map = new HashMap<String, Object>();
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize"));// 每页显示的size
        Integer draw = Integer.valueOf(request.getParameter("draw") == null ? "0" : request.getParameter("draw"));// 记录操作的次数 每次加1
        Integer page = Integer.valueOf(request.getParameter("page") == null ? "0" : request.getParameter("page"));//
        String planname = request.getParameter("planname");
        Map param = new HashMap<>();
        param.put("planname", planname);
        PageHelper.startPage(page, pageSize);
		List<ExecutePlan> planList = testPlanService.getPlanList(param);
        final PageInfo<ExecutePlan> pageInfo = new PageInfo<ExecutePlan>(planList);
        map.put("draw", draw);
        map.put("total", pageInfo.getTotal());//数据总条数
        map.put("pageData", planList);//数据集合
		try {
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error("查询录制案例出错："+e.getMessage());
		}
        return map;
    }
	
	
	
	@RequestMapping(value = "/getPlanCaseList/{planid}", method = RequestMethod.GET)
	@ResponseBody
    public Map<String, Object> getPlanCaseList(@PathVariable("planid") String planid,HttpServletRequest request) {
		ServiceReturn result = null;
        Map<String, Object> map = new HashMap<String, Object>();
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize"));// 每页显示的size
        Integer draw = Integer.valueOf(request.getParameter("draw") == null ? "0" : request.getParameter("draw"));// 记录操作的次数 每次加1
        Integer page = Integer.valueOf(request.getParameter("page") == null ? "0" : request.getParameter("page"));//
//        String planid = request.getParameter("planid");
        Map param = new HashMap<>();
        param.put("planid", planid);
        if("0".equals(planid)) {
        	 param.put("planid", "");
        }
        PageHelper.startPage(page, pageSize);
		List<PlanCase> list = testPlanService.getPlanCaseList(param);
        final PageInfo<PlanCase> pageInfo = new PageInfo<PlanCase>(list);
        map.put("draw", draw);
        map.put("total", pageInfo.getTotal());//数据总条数
        map.put("pageData", list);//数据集合
		try {
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error(e.getMessage());
		}
        return map;
    }
	
	@RequestMapping(value = "/getCaseList/{planid}", method = RequestMethod.GET)
	@ResponseBody
    public Map<String, Object> getCaseList(@PathVariable("planid") String planid,HttpServletRequest request) {
		ServiceReturn result = null;
        Map<String, Object> map = new HashMap<String, Object>();
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize"));// 每页显示的size
        Integer draw = Integer.valueOf(request.getParameter("draw") == null ? "0" : request.getParameter("draw"));// 记录操作的次数 每次加1
        Integer page = Integer.valueOf(request.getParameter("page") == null ? "0" : request.getParameter("page"));//
//        String planid = request.getParameter("planid");
        String casetype = request.getParameter("casetype");
        Map param = new HashMap<>();
        param.put("planid", planid);
        if("0".equals(planid)) {
        	 param.put("planid", "");
        	 param.put("casetype", casetype);
        }
        PageHelper.startPage(page, pageSize);
		List<ExecuteCase> list = testPlanService.getExecuteCaseList(param);
        final PageInfo<ExecuteCase> pageInfo = new PageInfo<ExecuteCase>(list);
        map.put("draw", draw);
        map.put("total", pageInfo.getTotal());//数据总条数
        map.put("pageData", list);//数据集合
		try {
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error(e.getMessage());
		}
        return map;
    }
	
	
	
	/**
	 * 添加任务
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addplan", method = RequestMethod.POST)
    public ModelAndView addplan(ExecutePlan testplan,Model model) {
		if (testPlanService.addTPlan(testplan)) {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_SUCCESS);
		} else {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);
		}
		
		ModelAndView mv = new ModelAndView("/aat/testService/testPlan/testPlan");
		return mv;
    }
	
	@RequestMapping(value = "/updateplan", method = RequestMethod.POST)
    public ModelAndView updateplan(ExecutePlan testplan, Model model) {
		
		if (testPlanService.updateplan(testplan)) {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_SUCCESS);
		} else {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);
		}
		
		ModelAndView mv = new ModelAndView("/aat/testService/testPlan/testPlan");
		return mv;
    }
	
	/**
	 * 删除任务
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delplan", method = RequestMethod.POST)
	@ResponseBody
    public ServiceReturn delplan(HttpServletRequest request) {
		ServiceReturn result = null;
		String planid = request.getParameter("planid");//获取执行数据
		try {
			testPlanService.delPlan(planid);
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error(e.getMessage());
		}
	    return result;
    }
	
	
	/**
	 * 删除轮次案例
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delplancase", method = RequestMethod.POST)
	@ResponseBody
    public ServiceReturn delplancase(HttpServletRequest request) {
		ServiceReturn result = null;
		String caseid = request.getParameter("caseid");//获取执行数据
		String planid = request.getParameter("planid");
		String id = request.getParameter("id");
		try {
			Map<String,String> map = new HashMap<String,String>();
			map.put("caseid", caseid);
			map.put("planid", planid);
			map.put("id", id);
			if (testPlanService.delPlanCase(map)) {
				result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
				testPlanService.updateCountMinByPlanid(planid);
			}
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error(e.getMessage());
		}
	    return result;
    }
	
	
	/**
	 * 添加轮次案例
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addplancase", method = RequestMethod.POST)
	@ResponseBody
    public ServiceReturn addplancase(HttpServletRequest request) {
		ServiceReturn result = null;
		String caseid = request.getParameter("caseid");//获取执行数据
		String planid = request.getParameter("planid");
		Map<String,String> map = new HashMap<String,String>();
		map.put("caseid", caseid);
		map.put("planid", planid);
		try {
			if (testPlanService.addplancase(map)) {
				result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
				testPlanService.updateCountAddByPlanid(planid);
			}
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error(e.getMessage());
		}
	    return result;
    }
    
    
	/**
	 * 添加轮次案例
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updatePlanExecuteclient", method = RequestMethod.POST)
	@ResponseBody
    public ServiceReturn updatePlanExecuteclient(HttpServletRequest request) {
		ServiceReturn result = null;
		String executeclientid = request.getParameter("executeclientid");//获取执行数据
		String planid = request.getParameter("planid");
		Map<String,String> map = new HashMap<String,String>();
		map.put("executeclientid", executeclientid);
		map.put("planid", planid);
		try {
			testPlanService.updatePlanExecuteclient(map);
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error(e.getMessage());
		}
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
    
    
    
	/**
	 * 执行批次案例
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/executeplan", method = RequestMethod.POST)
	@ResponseBody
    public ServiceReturn executeplan(HttpServletRequest request) {
		ServiceReturn result = null;
//		String executeclientid = request.getParameter("executeclientid");//获取执行数据
		String planid = request.getParameter("planid");
		Map<String,String> map = new HashMap<String,String>();
//		map.put("executeclientid", executeclientid);
		map.put("planid", planid);
		try {
			testPlanService.executeplan(map);
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error(e.getMessage());
		}
	    return result;
    }
	
	
	/**
	 * 修改回显
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getPlanListById", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPlanListById(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
        String planid = request.getParameter("planid");
		List<ExecutePlan> planList = testPlanService.getPlanListById(planid);
		map.put("dataList", planList);
		return map;
	}
	
	/**
	 * 查询案例列表返回planid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getPlanCaseListRtnPid", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getPlanCaseListRtnPid() {
		List<String> planidList = testPlanService.getPlanCaseListRtnPid();
		return planidList;
	}
	
	/**
	 * 查询执行计划列表返回planid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getLatestPlanid", method = RequestMethod.GET)
	@ResponseBody
	public String getLatestPlanid(HttpServletRequest request) {
		String executer = request.getParameter("curuser");
		List<String> planids = testPlanService.getLatestPlanid(executer);
		return planids.get(0);
	}

}
