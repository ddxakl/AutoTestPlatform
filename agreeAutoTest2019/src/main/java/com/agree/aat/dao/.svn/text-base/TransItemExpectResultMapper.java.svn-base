package com.agree.aat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.agree.aat.entity.TransItemExpectResult;

public interface TransItemExpectResultMapper {
    int deleteByPrimaryKey(String expectid);

    int insert(TransItemExpectResult record);

    int insertSelective(TransItemExpectResult record);

    TransItemExpectResult selectByPrimaryKey(String expectid);

    int updateByPrimaryKeySelective(TransItemExpectResult record);

    int updateByPrimaryKey(TransItemExpectResult record);

	List<TransItemExpectResult> getItemExpectResults(Map param);

	List<TransItemExpectResult> getItemExpectResultByItemId(String itemid);

	int updateItemExpect(@Param("expectid")String expectid,@Param("expression") String expression);

	List<String> getExpResListRtnEid();
}