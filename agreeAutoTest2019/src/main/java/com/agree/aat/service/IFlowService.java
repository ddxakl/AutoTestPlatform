package com.agree.aat.service;

import java.util.List;
import java.util.Map;

import com.agree.aat.entity.ExecuteFlows;
import com.agree.aat.entity.ExecuteFlowsCount;
import com.agree.aat.entity.ExecutePlanFlows;
import com.agree.aat.entity.ExecuteResource;

import net.sf.json.JSONObject;

public interface IFlowService {
	
	/**
	 * 返回流水
	 * @param json
	 * @throws Exception 
	 */
	void addFlowResult(Map map) throws Exception;

	List<ExecuteFlows> getResultFlowList(Map param);

	ExecuteFlows getResultFlowById(String exeid);

	void delFlowById(String executeflowid);
	
	List<ExecuteResource> getExecutResByExeId(String exeId);

	List<ExecuteFlows> getResultFlowListNoPage(Map flowparam);

	List<ExecutePlanFlows> getReportDetailsListByPlanidNoPage(Map flowparam);

	List<ExecuteFlowsCount> getExecuteFlowsCountListNoPage(Map flowparam);

	List<ExecuteFlows> getComponentindexByExecuteflowid(String executeflowid);
	
	List<ExecuteFlows> getPerformanceByExecuteflowid(String executeflowid);

	List<ExecuteFlows> getComAndPerByExecuteflowid(String executeflowid);
	

}
