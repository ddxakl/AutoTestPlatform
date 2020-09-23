package com.agree.system.dao;

import java.util.List;

import com.agree.system.entity.SystemModule;

public interface SystemModuleMapper {
    
    int deleteByPrimaryKey(Long moduleId);

    int insert(SystemModule record);

    int insertSelective(SystemModule record);

    SystemModule selectByPrimaryKey(Long moduleId);
    
    SystemModule selectSystemModuleByPrimaryKey(Long moduleId);
    
    List<SystemModule> selectUserMenu();
    
    int updateByPrimaryKeySelective(SystemModule record);

    int updateByPrimaryKey(SystemModule record);
    
    List<SystemModule> selectByBelong(Long belong);
    
    List<SystemModule> selectBySubBelong(Long belong);

	SystemModule getUserModule(Long moduleId);

	Long getUserParentModId(Long moduleId);

	List<SystemModule> selectAdminMenu();

	List<SystemModule> getBelongMenuList();

	List<Long> getModIdByBelongIsZero();
}