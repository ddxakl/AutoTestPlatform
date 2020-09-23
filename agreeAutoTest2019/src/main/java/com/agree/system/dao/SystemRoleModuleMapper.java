package com.agree.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.agree.system.entity.SystemRoleModule;

public interface SystemRoleModuleMapper {
    int deleteByPrimaryKey(String rolemoduleid);

    int insert(SystemRoleModule record);

    int insertSelective(SystemRoleModule record);

    SystemRoleModule selectByPrimaryKey(String rolemoduleid);

    int updateByPrimaryKeySelective(SystemRoleModule record);

    int updateByPrimaryKey(SystemRoleModule record);
    
    int deleteByRoleId(@Param("roleid")String roleid);
    
    int insertBatch(List<SystemRoleModule> list);

	List<SystemRoleModule> selectAllByRole(String roleId);

	List<String> getModuleIdByRoleId(String roleId);

	void insertSysManage(@Param("roleid")String roleid, @Param("moduleid")String moduleid);
}