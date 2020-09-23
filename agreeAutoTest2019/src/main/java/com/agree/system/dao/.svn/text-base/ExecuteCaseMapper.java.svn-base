package com.agree.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.agree.system.entity.ExecuteCase;

public interface ExecuteCaseMapper {
    int deleteByPrimaryKey(String caseid);

    int insert(ExecuteCase record);

    int insertSelective(ExecuteCase record);

    ExecuteCase selectByPrimaryKey(String caseid);

    int updateByPrimaryKeySelective(ExecuteCase record);

    int updateByPrimaryKeyWithBLOBs(ExecuteCase record);

    int updateByPrimaryKey(ExecuteCase record);
    
    List<ExecuteCase> getAllExecuteCase(Map map);

	List<ExecuteCase> getPlanCaseList(Map param);

	void updatePlanCase(String caseid);

	List<ExecuteCase> getListByCaseid(String caseid);
	
	ExecuteCase existByCaseidentifier(String caseidentifier);

	void updateBatchcaseByCaseid(@Param("caseid")String caseId, @Param("batchcase")String batchcase);

	String selectBatchcaseByCaseid(String caseid);

	List<String> selectCaseidsByBatchcase(String batchcase);
	
}