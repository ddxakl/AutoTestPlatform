package com.agree.aat.dao;

import java.util.List;
import java.util.Map;

import com.agree.aat.entity.ExecutePlanFlows;

public interface ExecutePlanFlowsMapper {
	
    int deleteByPrimaryKey(String planflowid);

    int insert(ExecutePlanFlows record);

    int insertSelective(ExecutePlanFlows record);

    ExecutePlanFlows selectByPrimaryKey(String planflowid);

    int updateByPrimaryKeySelective(ExecutePlanFlows record);

    int updateByPrimaryKey(ExecutePlanFlows record);

	List<ExecutePlanFlows> selectAllByPage(Map reportparam);

	List<ExecutePlanFlows> getReportDetailsListByPlanidNoPage(Map flowparam);

}