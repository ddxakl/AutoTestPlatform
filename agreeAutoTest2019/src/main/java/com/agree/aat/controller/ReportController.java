package com.agree.aat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agree.aat.entity.ExecutePlanFlows;
import com.agree.aat.service.IReportService;
import com.agree.framework.base.ServiceReturn;
import com.agree.framework.constant.ResultCodeEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/report")
public class ReportController {
	
	private static final Logger log=LogManager.getLogger(ReportController.class);
	
	@Autowired
	IReportService reportService;
	
	@RequestMapping(value = "/getReportDetailsList", method = RequestMethod.GET)
    public Map<String, Object> getResultFlowList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize"));// 每页显示的size
        Integer draw = Integer.valueOf(request.getParameter("draw") == null ? "0" : request.getParameter("draw"));// 记录操作的次数 每次加1
        Integer page = Integer.valueOf(request.getParameter("page") == null ? "0" : request.getParameter("page"));//
        String planname = request.getParameter("planname");
        String startdate = request.getParameter("startdate")+" 00:00:00";
        String enddate = request.getParameter("enddate")+" 23:59:59";
        Map reportparam = new HashMap<>();
        reportparam.put("planname", planname);
        reportparam.put("startdate", startdate);
        reportparam.put("enddate", enddate);
        PageHelper.startPage(page, pageSize);
		
		ServiceReturn re = new ServiceReturn();
		try {
			List<ExecutePlanFlows> resultFlowList = reportService.getReportDetailsList(reportparam);
			final PageInfo<ExecutePlanFlows> pageInfo = new PageInfo<>(resultFlowList);
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
    
    /**
	 * <B>方法名称：删除字典类型</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */
	@RequestMapping(value = "/delreport", method = RequestMethod.POST)
    public String delReportByPlanflowid(String planflowid, Model model) {
		if (reportService.delReportByPlanflowid(planflowid)) {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_SUCCESS);
		} else {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);
		}
		return "/aat/statisticalForm/statementDetails/statementDetails";
    }
	
}
