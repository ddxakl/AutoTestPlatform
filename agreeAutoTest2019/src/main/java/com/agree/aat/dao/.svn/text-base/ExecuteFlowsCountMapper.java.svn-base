package com.agree.aat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.agree.aat.entity.ExecuteFlowsCount;

public interface ExecuteFlowsCountMapper {
	
    int deleteByPrimaryKey(String exeflowscountid);

    int insert(ExecuteFlowsCount record);

    int insertSelective(ExecuteFlowsCount record);

    ExecuteFlowsCount selectByPrimaryKey(String exeflowscountid);

    int updateByPrimaryKeySelective(ExecuteFlowsCount record);

    int updateByPrimaryKey(ExecuteFlowsCount record);

	List<ExecuteFlowsCount> getExecuteFlowsCountListNoPage(Map flowparam);

	ExecuteFlowsCount getEFCount(@Param("planid")String planid,@Param("caseid")String caseid,@Param("planflowid") String planflowid);
}