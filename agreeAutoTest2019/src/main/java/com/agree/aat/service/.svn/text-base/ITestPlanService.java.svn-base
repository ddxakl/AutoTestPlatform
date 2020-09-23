package com.agree.aat.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.agree.aat.entity.ExecutePlan;
import com.agree.aat.entity.PlanCase;
import com.agree.system.entity.ExecuteCase;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ITestPlanService {
	
	
	List<ExecutePlan> getPlanList(Map map);

	boolean addTPlan(ExecutePlan tplan);
	
	void uptTPlan(String planId);

	void delPlan(String planId);

	void findCaseAndExecute(JSONObject json)throws Exception;

	List<PlanCase> getPlanCaseList(Map param);

	boolean delPlanCase(Map<String, String> map);

	boolean addplancase(Map<String, String> map);

	boolean updateplan(ExecutePlan testplan);

	void updatePlanExecuteclient(Map<String, String> map);

	List<ExecuteCase> getExecuteCaseList(Map param);

	void executeplan(Map<String, String> map);

	void updateCountAddByPlanid(String planid);

	void updateCountMinByPlanid(String planid);

	List<ExecutePlan> getPlanListById(String planid);
	public Set<String> getTransItem(String caseId);
	public boolean getExeFlag();
	public void resetExecFlag();
	JSONArray getTransItems(String caseId);

	List<String> getPlanCaseListRtnPid();

	List<String> getLatestPlanid(String executer);

}
