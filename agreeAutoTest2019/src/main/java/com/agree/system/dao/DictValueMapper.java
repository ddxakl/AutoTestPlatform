package com.agree.system.dao;

import java.util.List;

import com.agree.system.entity.DictValue;

public interface DictValueMapper {
	
    int deleteByPrimaryKey(String dictvalueid);
    
    int deleteMany(String[] dicttypeid);
    
    int deleteManyByTypeId(String dicttypeid);
    
    int deleteManyByValueId(String[] dictvalueid);

    int insert(DictValue record);

    int insertSelective(DictValue record);
    
    int insertBatch(List<DictValue> records);

    DictValue selectByPrimaryKey(String dictvalueid);

    int updateByPrimaryKeySelective(DictValue record);

    int updateByPrimaryKey(DictValue record);
    
    List<DictValue> listAll();
    
    List<DictValue> selectByType(String dicttypeid);
    
    List<DictValue> selectByTypes(String[] dicttypeid);
}