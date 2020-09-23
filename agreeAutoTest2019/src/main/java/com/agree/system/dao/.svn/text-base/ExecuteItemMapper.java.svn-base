package com.agree.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.agree.system.entity.ExecuteItem;

public interface ExecuteItemMapper {
    int deleteByPrimaryKey(String itemid);

    int insert(ExecuteItem record);

    int insertSelective(ExecuteItem record);

    ExecuteItem selectByPrimaryKey(String itemid);

    int updateByPrimaryKeySelective(ExecuteItem record);

    int updateByPrimaryKey(ExecuteItem record);
    
	List<ExecuteItem> getCaseInfoByCaseid(String caseid);

	void updateByItemid(@Param("itemid")String itemid, @Param("column")String column, @Param("input")String input);
	
	List<ExecuteItem> selectAliasByCaseid(String caseid);

	ExecuteItem getListByItemid(String itemid);

	List<String> getItemidByCaseid(String caseid);

	long getItemindexByItemid(String rtnItemid);

	void updateItemIndex(@Param("itemid")String rtnItemid, @Param("index")long index);

	List<ExecuteItem> getItemListByItemid(String itemid);

	void uptItemByItemid(ExecuteItem executeItem);
}