package com.agree.system.service;

import java.util.List;

import com.agree.system.entity.SystemModule;

/**
 * 
 * <B>系统名称：</B><BR>
 * <B>模块名称：菜单模块操作接口</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * @author（admin）
 */

public interface ISystemModuleService {

	
	List<SystemModule> getMenuList();
	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：返回admin菜单模块</B><BR>
	 * @return
	 */
	List<SystemModule> getAdminMod();
	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：返回admin二级菜单</B><BR>
	 * @return
	 */
	List<SystemModule> getsubAdminMod();	
	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：返回用户菜单模块</B><BR>
	 * @return
	 */
	List<SystemModule> getUserMod();
	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：返回用户二级菜单</B><BR>
	 * @return
	 */
	List<SystemModule> getsubUserMod();
	
	SystemModule getUserModule(Long moduleId);
	
	Long getUserParentModId(Long moduleId);
	
	List<SystemModule> getAdminMenuList();
	
	List<SystemModule> getBelongMenuList();
	
}
