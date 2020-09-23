/**
 * 
 */
package com.agree.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.agree.framework.base.ServiceReturn;
import com.agree.framework.constant.ResultCodeEnum;
import com.agree.system.entity.SystemUserRole;
import com.agree.system.service.ISystemUserService;

/**
 * @author xp
 * @date 2019年8月5日
 * 
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ISystemUserService userService;

	@RequestMapping
	public String admin(){
		return "/system/admin";
	}
	
	@RequestMapping("/index")
	public String index(){
		return "/system/adminIndex";
	}
	
	/**
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：用户修改角色</B><BR>
	 * @param groupDto
	 * @return
	 */
	@RequestMapping(value="/userroleupt",method = RequestMethod.POST)
	public String assignMenu(SystemUserRole userr) {
		ServiceReturn result;
		SystemUserRole userRole = userService.selectUrByUid(userr.getUserid());
		if(userRole==null) {
			userRole = new SystemUserRole();
			userRole.setUserid(userr.getUserid());
			userRole.setRoleid(userr.getRoleid());
			userService.addUserRole(userRole);
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
		}else {
			userRole.setRoleid(userr.getRoleid());			
			if(userService.uptUserRole(userRole)) {
				result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
			}else {
				result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			}
		}

		return "/aat/systemSettings/userManage";
	}
	
}
