package com.agree.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agree.framework.base.ServiceReturn;
import com.agree.framework.constant.ResultCodeEnum;
import com.agree.system.dto.SystemRoleDTO;
import com.agree.system.entity.SystemModule;
import com.agree.system.service.ISystemModuleService;
import com.agree.system.service.ISystemRoleService;

/**
 * 
 * <B>系统名称：AT</B><BR>
 * <B>模块名称：AT</B><BR>
 * <B>中文类名：后台页面controller</B><BR>
 * <B>概要说明：处理后台请求相关</B><BR>
 * @author admin（admin）
 * @since 2019年12月20日
 */

@RestController
@RequestMapping("/menus")
public class MenuController{

	@Autowired
	private ISystemModuleService menuService;
	@Autowired
	private ISystemRoleService roleService;
	
	/**
	 * <B>方法名称：获取全部权限</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */	
	@RequestMapping(method = RequestMethod.GET)
	public List<SystemModule> getMenuList() {	
		return menuService.getMenuList();
	}
	/**
	 * <B>方法名称：获取全部权限-admin</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */	
	@RequestMapping(value="/admin", method = RequestMethod.GET)
	public List<SystemModule> getAdminMenuList() {	
		return menuService.getAdminMenuList();
	}
	/**
	 * <B>方法名称：获取belong为0的module</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */	
	@RequestMapping(value="/belong", method = RequestMethod.GET)
	public List<SystemModule> getBelongMenuList() {	
		return menuService.getBelongMenuList();
	}
	/**
	 * <B>方法名称：某个角色下的菜单</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */			
	@RequestMapping(value="/{roleId}/menus",method = RequestMethod.GET)
	public SystemRoleDTO getMenuList(@PathVariable("roleId")String roleId) {
		return roleService.getByIdWithMenuAction(roleId);
	}
	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：为角色分配可以访问的菜单</B><BR>
	 * @param groupDto
	 * @return
	 */
	@RequestMapping(value="/{roleId}/allot",method = RequestMethod.POST)
	public ServiceReturn assignMenu(SystemRoleDTO roleDto) {
		ServiceReturn result;

		if(roleService.assignMenu(roleDto)) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
		} else {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
		}
		return result;
	} 
}
