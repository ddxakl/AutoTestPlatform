package com.agree.aat.dao;

import java.util.List;
import java.util.Map;

import com.agree.aat.entity.ExecutePlan;

public interface ExecutePlanMapper {
    int deleteByPrimaryKey(String planid);

    int insert(ExecutePlan record);

    int insertSelective(ExecutePlan record);

    ExecutePlan selectByPrimaryKey(String planid);

    int updateByPrimaryKeySelective(ExecutePlan record);

    int updateByPrimaryKey(ExecutePlan record);
    
    List<ExecutePlan> getPlanList(Map map);

	int updateByRecord(ExecutePlan testplan);

	void updateExecuteClient(Map<String, String> map);
	
	void updateCountAddByPlanid(String planid);

	void updateCountMinByPlanid(String planid);

	List<ExecutePlan> getPlanListById(String planid);

	List<String> getLatestPlanid(String executer);
	
}