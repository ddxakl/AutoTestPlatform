package com.agree.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xp
 * @date 2019年8月18日
 */
@Controller
@RequestMapping("/statisticalForm")
public class StatisticalForm {
	
	@RequestMapping("/executeFlowInfos")
	public String  executeFlowInfos() {
		return "/aat/statisticalForm/executeFlowInfos/executeFlowInfos";
	}
	
	@RequestMapping("/statementDetails")
	public String statementDetails() {
		return "/aat/statisticalForm/statementDetails/statementDetails";
	}
	
	@RequestMapping("/statementDetailsFlowTable/{planflowid}/{planid}/{planname}")
	public String statementDetailsFlowTable(@PathVariable("planflowid") String planflowid,@PathVariable("planid") String planid, @PathVariable("planname") String planname, Model model) {
		model.addAttribute("planflowid", planflowid);
		model.addAttribute("planid", planid);
		model.addAttribute("planname", planname);
		return "/aat/statisticalForm/statementDetails/executeFlowInfosTable";
	}
	
	@RequestMapping("/testReport")
	public String executePlanDetails() {
		return "/aat/statisticalForm/statementDetails/testReport";
	}

}
