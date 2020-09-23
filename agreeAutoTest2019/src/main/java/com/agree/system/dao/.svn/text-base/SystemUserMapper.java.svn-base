package com.agree.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.agree.system.entity.SystemUser;

public interface SystemUserMapper {
    int deleteByPrimaryKey(String userid);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    SystemUser selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);

	List<SystemUser> getUserList(Map userMap);

	List<SystemUser> selectByTerminalid(String userid);

	int updateByUserid(SystemUser sysUser);

	SystemUser selectUserByName(String account);

	SystemUser selectRoleListById(@Param("userid")String userId);

	SystemUser getEntity(SystemUser uIfo);
}