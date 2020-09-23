package com.agree.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.agree.system.entity.SystemUserRole;

public interface SystemUserRoleMapper {
    int deleteByPrimaryKey(String userroleid);

    int insert(SystemUserRole record);

    int insertSelective(SystemUserRole record);

    SystemUserRole selectByPrimaryKey(String userroleid);

    int updateByPrimaryKeySelective(SystemUserRole record);

    int updateByPrimaryKey(SystemUserRole record);

	SystemUserRole selectByUserId(String userid);

	int deleteByUserId(@Param("userid")String userid);

	int insertBatch(List<SystemUserRole> list);

	List<String> getRoleIdByUserId(String userid);
}