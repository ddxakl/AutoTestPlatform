package com.agree.system.service;

import java.util.List;
import java.util.Map;

import com.agree.system.dto.SystemRoleDTO;
import com.agree.system.entity.SystemRole;
import com.agree.system.entity.SystemRoleModule;

public interface ISystemRoleService {

	List<SystemRole> getRoleListById(String roleid);

	List<SystemRole> getAllRole(Map roleInfo);

	boolean addRole(SystemRole sysRole);

	boolean uptRole(SystemRole sysRole);

	boolean dltRole(String roleid);

	boolean existAdd(Map roleInfo);
	
	boolean existUpt(Map roleInfo);

	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：查询对应权限</B><BR>
	 * @param id
	 * @return
	 */
	SystemRoleDTO getByIdWithMenuAction(String roleId);

	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：为角色分配权限</B><BR>
	 * @param groupDto
	 * @return
	 */
	boolean assignMenu(SystemRoleDTO roleDto);

	List<SystemRole> getList();

	/**
	 * 
	 * <B>方法名称：批量删除机构</B><BR>
	 * <B>概要说明：</B><BR>
	 * @param orgId[]
	 * @return
	 */
	boolean delRoles(String[] roleIds);
	
	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：获取角色对应菜单</B><BR>
	 * @return
	 */
	List<SystemRoleModule> getAllRoMo(String roleId);

	List<String> getModuleIdByRoleId(String roleId);


}
