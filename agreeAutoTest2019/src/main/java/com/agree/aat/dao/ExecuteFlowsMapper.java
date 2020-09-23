package com.agree.aat.dao;

import java.util.List;
import java.util.Map;

import com.agree.aat.entity.ExecuteFlows;

public interface ExecuteFlowsMapper {
    int deleteByPrimaryKey(String executeflowid);

    int insert(ExecuteFlows record);

    int insertSelective(ExecuteFlows record);

    ExecuteFlows selectByPrimaryKey(String executeflowid);

    int updateByPrimaryKeySelective(ExecuteFlows record);

    int updateByPrimaryKey(ExecuteFlows record);
    
    List<ExecuteFlows> getExecuteFlowsByPage(Map map);

	List<ExecuteFlows> getExecuteFlowNoPage(Map flowparam);

	List<ExecuteFlows> getListByExecuteflowid(String executeflowid);

}