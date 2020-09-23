package com.agree.aat.dao;

import java.util.List;
import java.util.Map;

import com.agree.aat.entity.PlanCase;

public interface PlanCaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(PlanCase record);

    int insertSelective(PlanCase record);

    PlanCase selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PlanCase record);

    int updateByPrimaryKeyWithBLOBs(PlanCase record);

    int updateByPrimaryKey(PlanCase record);

	List<PlanCase> getPlanCaseList(Map param);

	List<String> getPlanCaseListRtnPid();
}