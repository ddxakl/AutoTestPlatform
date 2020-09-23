package com.agree.aat.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agree.aat.entity.ExecuteFlows;
import com.agree.aat.entity.ExecuteFlowsCount;
import com.agree.aat.entity.ExecutePlanFlows;
import com.agree.aat.entity.ExecuteResource;
import com.agree.aat.service.IFlowService;
import com.agree.framework.base.Config;
import com.agree.framework.base.ServiceReturn;
import com.agree.framework.constant.ResultCodeEnum;
import com.agree.util.FileUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONObject;
import net.sf.jsqlparser.statement.select.FromItem;



@RestController
@RequestMapping("/flows")
public class FlowController {
	
	private static final Logger log=LogManager.getLogger(FlowController.class);
	
	
	@Autowired
	IFlowService flowService;
	
	@Autowired
	private Config config;
	
	// http://192.168.187.90:8089/AT_Platform_2019A/flows/addResultFlow
	@RequestMapping(value = "/addResultFlow", method = RequestMethod.POST)
    public void addResultFlow(@RequestBody String resultmsg) {
		log.info("收到流水数据：" + resultmsg);
		ServiceReturn re = new ServiceReturn();
		try {
			flowService.addFlowResult(JSONObject.fromObject(resultmsg));
			re.setRetCode(ResultCodeEnum.RESULT_TASK_SUCCESS.getCode());
		} catch (Exception e) {
			re.setRetCode(ResultCodeEnum.RESULT_TASK_FAIL.getCode());
			e.printStackTrace();
		}
//		return re;
	}
	
	@RequestMapping(value = "/getResultFlowList", method = RequestMethod.GET)
    public Map<String, Object> getResultFlowList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize"));// 每页显示的size
        Integer draw = Integer.valueOf(request.getParameter("draw") == null ? "0" : request.getParameter("draw"));// 记录操作的次数 每次加1
        Integer page = Integer.valueOf(request.getParameter("page") == null ? "0" : request.getParameter("page"));//
        String casename = request.getParameter("casename");
        String startdate = request.getParameter("startdate")+" 00:00:00";
        String enddate = request.getParameter("enddate")+" 23:59:59";
        Map flowparam = new HashMap<>();
        flowparam.put("casename", casename);
        flowparam.put("startdate", startdate);
        flowparam.put("enddate", enddate);
        PageHelper.startPage(page, pageSize);
		
		ServiceReturn re = new ServiceReturn();
		try {
			List<ExecuteFlows> resultFlowList = flowService.getResultFlowList(flowparam);
			final PageInfo<ExecuteFlows> pageInfo = new PageInfo<>(resultFlowList);
		    map.put("draw", draw);
		    map.put("total", pageInfo.getTotal());//数据总条数
		    map.put("pageData", resultFlowList);//数据集合
			re.setRetCode(ResultCodeEnum.RESULT_TASK_SUCCESS.getCode());
		} catch (Exception e) {
			re.setRetCode(ResultCodeEnum.RESULT_TASK_FAIL.getCode());
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value = "/getComAndPerByExecuteflowid/{executeflowid}", method = RequestMethod.POST)
    public Map<String, Object> getComAndPerByExecuteflowid(@PathVariable("executeflowid") String executeflowid, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> comList = new ArrayList<Object>();
		List<Object> perList = new ArrayList<Object>();
		List<String> resList = new ArrayList<String>();
		List<Long> timeList = new ArrayList<Long>();
		ServiceReturn re = new ServiceReturn();
		try {
			List<ExecuteFlows> resultFlowList = flowService.getComAndPerByExecuteflowid(executeflowid);
			for (ExecuteFlows executeFlows : resultFlowList) {
				String componentindex = executeFlows.getComponentindex();
				if (!"".equals(componentindex)) {
					JSONObject comObject = JSONObject.fromObject(componentindex);
					comList.add(comObject);
				}else {
					comList.add("");
				}
				
				String performance = executeFlows.getPerformance();
				if (!"".equals(performance)) {
					JSONObject perObject = JSONObject.fromObject(performance);
					perList.add(perObject);
				}else {
					perList.add("");
				}
				
				String executeresult = executeFlows.getExecuteresult();
				resList.add(executeresult);
				
				Date endDate = executeFlows.getEnddate();
				Date startDate = executeFlows.getStartdate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String endDateString = sdf.format(endDate);
				String startDateString = sdf.format(startDate);
				long t = sdf.parse(endDateString).getTime() - sdf.parse(startDateString).getTime();
				long timeConsume = t/1000;
				timeList.add(timeConsume);
				
			}
			re.setRetCode(ResultCodeEnum.RESULT_TASK_SUCCESS.getCode());
		} catch (Exception e) {
			re.setRetCode(ResultCodeEnum.RESULT_TASK_FAIL.getCode());
			e.printStackTrace();
		}
		map.put("timeList", timeList);
		map.put("resList", resList);
		map.put("perList", perList);
		map.put("comList", comList);
		return map;
	}
	
	@RequestMapping(value = "/getComponentindexByExecuteflowid/{executeflowid}", method = RequestMethod.POST)
    public List<Object> getComponentindexByExecuteflowid(@PathVariable("executeflowid") String executeflowid, HttpServletRequest request) {
		List<Object> list = new ArrayList<Object>();
		ServiceReturn re = new ServiceReturn();
		try {
			List<ExecuteFlows> resultFlowList = flowService.getComponentindexByExecuteflowid(executeflowid);
			for (ExecuteFlows executeFlows : resultFlowList) {
				String componentindex = executeFlows.getComponentindex();
				if (!"".equals(componentindex)) {
					JSONObject fromObject = JSONObject.fromObject(componentindex);
					list.add(fromObject);
				}else {
					list.add("");
				}
				
			}
			re.setRetCode(ResultCodeEnum.RESULT_TASK_SUCCESS.getCode());
		} catch (Exception e) {
			re.setRetCode(ResultCodeEnum.RESULT_TASK_FAIL.getCode());
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = "/getPerformanceByExecuteflowid/{executeflowid}", method = RequestMethod.POST)
    public List<Object> getPerformanceByExecuteflowid(@PathVariable("executeflowid") String executeflowid, HttpServletRequest request) {
		List<Object> list = new ArrayList<Object>();
		ServiceReturn re = new ServiceReturn();
		try {
			List<ExecuteFlows> resultFlowList = flowService.getPerformanceByExecuteflowid(executeflowid);
			for (ExecuteFlows executeFlows : resultFlowList) {
				String performance = executeFlows.getPerformance();
				if (!"".equals(performance)) {
					JSONObject fromObject = JSONObject.fromObject(performance);
					list.add(fromObject);
				}else {
					list.add("");
				}
			}
			re.setRetCode(ResultCodeEnum.RESULT_TASK_SUCCESS.getCode());
		} catch (Exception e) {
			re.setRetCode(ResultCodeEnum.RESULT_TASK_FAIL.getCode());
			e.printStackTrace();
		}
		return list;
	}
	
	
	@RequestMapping(value = "/getflowLogs/{executeflowid}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> depthParticularLogs(@PathVariable("executeflowid") String exeId, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		ExecuteFlows resultFlow= flowService.getResultFlowById(exeId);
		String exeLogcatLog = resultFlow.getLogfile();
		String prefix = config.getFtPath();
		String filename = prefix + exeLogcatLog;
		String readFile=null;
		try {
			readFile = FileUtil.readFile(new File(filename),"GBK");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		if(readFile==null||"".equals(readFile)){
			readFile="无内容";
		}
		map.put("total", "");
		map.put("log", readFile);
		return map;
	}
	
	
	@RequestMapping(value = "/delflows", method = RequestMethod.GET)
	@ResponseBody
	public ServiceReturn delflows(HttpServletRequest request){
		String executeflowid = request.getParameter("executeflowid");
		ServiceReturn re = new ServiceReturn();
		try {
			flowService.delFlowById(executeflowid);
			re.setRetCode(ResultCodeEnum.RESULT_TASK_SUCCESS.getCode());
		} catch (Exception e) {
			re.setRetCode(ResultCodeEnum.RESULT_TASK_FAIL.getCode());
			e.printStackTrace();
		}
		return re;
	}
	
	
	/**
	 * 传入日志文件
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getflowslog", method = RequestMethod.GET)
	@ResponseBody
	public String uploadFile(@PathVariable("addiFilename") String addiFilename,HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.reset();
		response.setCharacterEncoding("UTF-8");
		byte[] buffer;
		try {
//			String addiFilename = request.getParameter("addiFilename");
			System.out.println("获取到的日志路径》》》》"+addiFilename);
			File file = new File(addiFilename);
			if(file.isFile() && file.exists()){
				String context = FileUtil.readFile(file, "UTF-8");
				buffer = context.getBytes("UTF-8");
			}else{
				buffer = "文件不存在".getBytes("UTF-8");
			}
		} catch(Exception e) {
			buffer = e.getMessage().getBytes();
		}
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			os.write(buffer,0, buffer.length);
			os.flush();
		} catch(Exception e) {
			log.error(e.getMessage());
		} finally {
			try {
				os.close();
			} catch(IOException e) {
				log.error(e.getMessage());
			}
		}
		return null;
	}
	
	
	@RequestMapping(value = "/getflowPics/{executeflowid}", method = RequestMethod.GET)
	@ResponseBody
	public ServiceReturn getPiccuterLogs(@PathVariable("executeflowid") String exeId, HttpServletRequest request){
		ServiceReturn re = new ServiceReturn();
		try {
			List<ExecuteResource> reslist= flowService.getExecutResByExeId(exeId);
			re.setRetCode(ResultCodeEnum.RESULT_TASK_SUCCESS.getCode());
			re.setData(reslist);
		} catch (Exception e) {
			re.setRetCode(ResultCodeEnum.RESULT_TASK_FAIL.getCode());
			e.printStackTrace();
		}
		
		return re;
	}
	
	@RequestMapping(value = "/getResultFlowListNoPage/{planflowid}", method = RequestMethod.POST)
    public Map<String, Object> getResultFlowListNoPage(@PathVariable("planflowid") String planflowid, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer pageSize = Integer.valueOf(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize"));// 每页显示的size
        Integer draw = Integer.valueOf(request.getParameter("draw") == null ? "0" : request.getParameter("draw"));// 记录操作的次数 每次加1
        Integer page = Integer.valueOf(request.getParameter("page") == null ? "0" : request.getParameter("page"));//
        String casename = request.getParameter("casename");
        String startdate = request.getParameter("startdate")+" 00:00:00";
        String enddate = request.getParameter("enddate")+" 23:59:59";
		Map flowparam = new HashMap<>();
        flowparam.put("planflowid", planflowid);
        flowparam.put("casename", casename);
        flowparam.put("startdate", startdate);
        flowparam.put("enddate", enddate);
        PageHelper.startPage(page, pageSize);
		ServiceReturn re = new ServiceReturn();
		try {
			List<ExecuteFlows> resultFlowList = flowService.getResultFlowListNoPage(flowparam);
			final PageInfo<ExecuteFlows> pageInfo = new PageInfo<>(resultFlowList);
		    map.put("draw", draw);
		    map.put("total", pageInfo.getTotal());//数据总条数
		    map.put("pageData", resultFlowList);//数据集合
//		    map.put("dataList", resultFlowList);//数据集合
			re.setRetCode(ResultCodeEnum.RESULT_TASK_SUCCESS.getCode());
		} catch (Exception e) {
			re.setRetCode(ResultCodeEnum.RESULT_TASK_FAIL.getCode());
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value = "/getReportDetailsListByPlanidNoPage", method = RequestMethod.POST)
    public Map<String, Object> getReportDetailsListByPlanidNoPage(HttpServletRequest request) {
		String planflowid = request.getParameter("planflowid");
		Map<String, Object> map = new HashMap<String, Object>();
		Map flowparam = new HashMap<>();
        flowparam.put("planflowid", planflowid);
		ServiceReturn re = new ServiceReturn();
		try {
			List<ExecutePlanFlows> resultFlowList = flowService.getReportDetailsListByPlanidNoPage(flowparam);
		    map.put("dataList", resultFlowList);//数据集合
			re.setRetCode(ResultCodeEnum.RESULT_TASK_SUCCESS.getCode());
		} catch (Exception e) {
			re.setRetCode(ResultCodeEnum.RESULT_TASK_FAIL.getCode());
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value = "/getExecuteFlowsCountListNoPage", method = RequestMethod.POST)
    public Map<String, Object> getExecuteFlowsCountListNoPage(HttpServletRequest request) {
		String planflowid = request.getParameter("planflowid");
		Map<String, Object> map = new HashMap<String, Object>();
		Map flowparam = new HashMap<>();
        flowparam.put("planflowid", planflowid);
		ServiceReturn re = new ServiceReturn();
		try {
			List<ExecuteFlowsCount> resultFlowList = flowService.getExecuteFlowsCountListNoPage(flowparam);
		    map.put("dataList", resultFlowList);//数据集合
			re.setRetCode(ResultCodeEnum.RESULT_TASK_SUCCESS.getCode());
		} catch (Exception e) {
			re.setRetCode(ResultCodeEnum.RESULT_TASK_FAIL.getCode());
			e.printStackTrace();
		}
		return map;
	}
	
	
	
}
