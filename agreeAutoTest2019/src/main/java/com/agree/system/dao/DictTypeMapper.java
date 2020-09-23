package com.agree.system.dao;

import java.util.List;
import java.util.Map;

import com.agree.system.entity.DictType;

public interface DictTypeMapper {
	
    int deleteByPrimaryKey(String dicttypeid);
    
    int deleteMany(String[] dicttypeid);

    int insert(DictType record);

    int insertSelective(DictType record);

    DictType selectByPrimaryKey(String dicttypeid);
    
    DictType selectByDict(DictType record);
    
    DictType selectNoSelfByDict(DictType record);

    int updateByPrimaryKeySelective(DictType record);

    int updateByPrimaryKey(DictType record);
    
    List<DictType> listAll();
    
    List<DictType> selectAllByPage(Map map);

	List<DictType> selectByDicttypeid(String dicttypeid);
}