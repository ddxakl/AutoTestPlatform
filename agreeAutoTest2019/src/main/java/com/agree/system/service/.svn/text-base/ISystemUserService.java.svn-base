/**
 * 
 */
package com.agree.system.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.agree.system.dto.SystemUserDTO;
import com.agree.system.entity.SystemUser;
import com.agree.system.entity.SystemUserRole;

/**
 * 代码不规范，维护两行泪！
 * @author xp
 * @date 2019年10月23日
 * 
 */
public interface ISystemUserService {

	SystemUser selectByPrimaryKey(String userId);

	List<SystemUser> getUserList(Map userMap);

	List<SystemUser> getList(String userid);

	boolean addUser(SystemUser sysUser);

	boolean uptUser(SystemUser sysUser);

	boolean dltUser(String userid);

	List<SystemUser> sltUserByName(String account);

	/**
	 * <B>方法名称：根据用户ID查询角色</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */
	SystemUserRole selectUrByUid(String userid);

	/**
	 * <B>方法名称：分配角色</B><BR>
	 * <B>概要说明：</B><BR>
	 * @param userRole
	 * @return
	 */
	boolean addUserRole(SystemUserRole userRole);

	boolean uptUserRole(SystemUserRole userRole);

	SystemUserDTO getByIdWithRoleAction(String userId);

	boolean assignRole(SystemUserDTO userDto);

	/**
	 * 
	 * <B>方法名称：用户校验</B><BR>
	 * <B>概要说明：校验用户名/密码是否正确</B><BR>
	 * @param userDto
	 * @return
	 */
	boolean validate(SystemUserDTO userDto);

	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：相同用户只能登陆一次</B><BR>
	 * @param session
	 * @param userDto
	 */
	void singleLogin(HttpSession session, SystemUserDTO userDto);

	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：判断当前用户是否是admin</B><BR>
	 * @param userDto
	 * @return
	 */
	boolean isAdmin(SystemUserDTO userDto);

	List<String> getRoleIdByUserId(String userid);

}
