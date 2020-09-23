package com.agree.system.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agree.framework.constant.ApplicationKeyConst;
import com.agree.framework.constant.ResultCodeEnum;
import com.agree.system.dto.SystemUserDTO;
import com.agree.system.entity.SystemModule;
import com.agree.system.service.ILoginService;
import com.agree.system.service.ISystemModuleService;
import com.agree.system.service.ISystemRoleService;
import com.agree.system.service.ISystemUserService;

/**
 * @author xp
 * @date 2019年7月31日
 * 用户登陆的类
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	private static final Logger log=LogManager.getLogger(LoginController.class);

	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private ISystemUserService userService;
	
	@Autowired
	private ISystemRoleService roleService;
	
	@Autowired
	private ISystemModuleService moduleService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping
	public String index() {
		return "/login/login";
	}
	
	/**
	 * session超时
	 */
	@RequestMapping("/sessionTimeout")
	public String sessionTimeout(Model model) {
		model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);
		return "/common/error";
	}
	
	/**
	 * 点击登陆按钮
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/validate")
	public String validate(SystemUserDTO userDto, RedirectAttributes attr) {
		if (userService.validate(userDto)) {
			session.setAttribute(ApplicationKeyConst.USER_INFO, userDto);
			userService.singleLogin(session, userDto);
			
			Set<String> adminset = new HashSet<String>();
			Set<String> otherset = new HashSet<String>();
			
			if(userService.isAdmin(userDto)){
//				List<SystemModule> list = moduleService.getAdminMod();
//				List<SystemModule> slist = moduleService.getsubAdminMod();
//				if(list==null){
//					attr.addFlashAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);
//					return "redirect:/login";
//				}else{
//					session.setAttribute(ApplicationKeyConst.MENU_INFO, list);
//					session.setAttribute(ApplicationKeyConst.SUB_MENU_INFO, slist);
//				}				
//				return "redirect:/admin";
				List<SystemModule> list = new ArrayList<SystemModule>();
				List<SystemModule> slist = new ArrayList<SystemModule>();
				List<String> roleIds = userService.getRoleIdByUserId(userDto.getUserid());
				if (roleIds.size() == 0) {
					return "redirect:/login";
				}
				for (String roleId : roleIds) {
					List<String> moduleIds =  roleService.getModuleIdByRoleId(roleId);
					for (String moduleId : moduleIds) {
						if (adminset.add(moduleId)) {
							if (moduleService.getUserParentModId(Long.parseLong(moduleId))==-1) {
								list.add(moduleService.getUserModule(Long.parseLong(moduleId)));
							}else {
								slist.add(moduleService.getUserModule(Long.parseLong(moduleId)));
							}
						}
					}
				}
				if(list==null){
					attr.addFlashAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);
					return "redirect:/login";
				}else{
					session.setAttribute(ApplicationKeyConst.MENU_INFO, list);
					session.setAttribute(ApplicationKeyConst.SUB_MENU_INFO, slist);
				}	
				return "redirect:/admin";
			}else {
				List<SystemModule> list = new ArrayList<SystemModule>();
				List<SystemModule> slist = new ArrayList<SystemModule>();
				List<String> roleIds = userService.getRoleIdByUserId(userDto.getUserid());
				if (roleIds.size() == 0) {
					return "redirect:/login";
				}
				for (String roleId : roleIds) {
					List<String> moduleIds =  roleService.getModuleIdByRoleId(roleId);
					for (String moduleId : moduleIds) {
						if (otherset.add(moduleId)) {
							if (moduleService.getUserParentModId(Long.parseLong(moduleId))==-1) {
								list.add(moduleService.getUserModule(Long.parseLong(moduleId)));
							}else {
								slist.add(moduleService.getUserModule(Long.parseLong(moduleId)));
							}
						}
					}
				}
				if(list==null){
					attr.addFlashAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);
					return "redirect:/login";
				}else{
					session.setAttribute(ApplicationKeyConst.MENU_INFO, list);
					session.setAttribute(ApplicationKeyConst.SUB_MENU_INFO, slist);
//					if("".equals(projectService.getProjectId(userDto.getUserid()))) {
//						return "redirect:/login/uerror";
//					}
//					session.setAttribute(ApplicationKeyConst.USER_PROJECT, projectService.getProjectId(userDto.getUserid()));
				}	
				return "redirect:/admin";
			}

		}
		attr.addFlashAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);
		return "redirect:/login";
	}
	
	
}
