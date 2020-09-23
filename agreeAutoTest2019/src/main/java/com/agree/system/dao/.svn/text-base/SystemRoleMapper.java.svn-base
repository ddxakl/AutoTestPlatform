package com.agree.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.agree.system.entity.SystemRole;

public interface SystemRoleMapper {
	
    int deleteByPrimaryKey(String roleid);

    int insert(SystemRole record);

    int insertSelective(SystemRole record);

    List<SystemRole> selectByPrimaryKey(String roleid);

    List<SystemRole> selectAllByPage(Map roleInfo);
    
    int updateByPrimaryKeySelective(SystemRole record);

    int updateByPrimaryKey(SystemRole record);

	int selectByRolename(Map roleInfo);

	int selectNoSelfRolename(Map roleInfo);
	
	SystemRole selectMenuListById (@Param("roleid")String roleid);

	List<SystemRole> noAdminList();

	int deleteMany(String[] roleIds);

}