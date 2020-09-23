/**
 * 
 */
package com.agree.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.agree.framework.base.ServiceReturn;
import com.agree.framework.constant.ResultCodeEnum;
import com.agree.system.entity.SystemRole;
import com.agree.system.entity.SystemUser;
import com.agree.system.service.ISystemRoleService;
import com.agree.system.service.ISystemUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 代码不规范，维护两行泪！
 * @author xp
 * @date 2019年10月14日
 * 
 */
@Controller
@RequestMapping("/systemManagement")
public class SystemController {
	
	private static final Logger log=LogManager.getLogger(SystemController.class);
	
	@Autowired
	ISystemUserService userService;
	
	@Autowired
	ISystemRoleService roleService;
	
	@RequestMapping("/userManage")
	public String userManage() {
		return "aat/systemSettings/userManage";
	}
	
	@RequestMapping("/roleManage")
	public String roleManage() {
		return "aat/systemSettings/roleManage";
	}
	
	/**
	 * 用户
	 */
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> userList(HttpServletRequest request) {
		ServiceReturn result = null;
        Map<String, Object> map = new HashMap<String, Object>();
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize"));// 每页显示的size
        Integer draw = Integer.valueOf(request.getParameter("draw") == null ? "0" : request.getParameter("draw"));// 记录操作的次数 每次加1
        Integer page = Integer.valueOf(request.getParameter("page") == null ? "0" : request.getParameter("page"));//
        String username = request.getParameter("username");
        Map userMap = new HashMap<>();
        userMap.put("username", username);
        PageHelper.startPage(page, pageSize);
		List<SystemUser> userList = userService.getUserList(userMap);
        final PageInfo<SystemUser> pageInfo = new PageInfo<>(userList);
        map.put("draw", draw);
        map.put("total", pageInfo.getTotal());//数据总条数
        map.put("pageData", userList);//数据集合
		try {
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
			result.setData(userList);
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error("查询用户信息出错："+e.getMessage());
		}
        return map;
	}
	
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	@ResponseBody
    public Map<String, Object> getList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String userid = request.getParameter("userid");
		List<SystemUser> userList = userService.getList(userid);
		map.put("dataList", userList);
        return map;
    }
	
	@RequestMapping(value = "/useradd", method = RequestMethod.POST)
	@ResponseBody
    public ModelAndView useradd(SystemUser sysUser, Model model) {
		sysUser.setUserpower((long) 1);
		if (userService.addUser(sysUser)) {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_SUCCESS);
		} else {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);
		}
		
		ModelAndView mv = new ModelAndView("/aat/systemSettings/userManage");
		return mv;
    }
	
	@RequestMapping(value = "/userupt", method = RequestMethod.POST)
	@ResponseBody
    public ModelAndView userupt(SystemUser sysUser, Model model) {
		if (userService.uptUser(sysUser)) {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_SUCCESS);
		} else {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);
		}
		
		ModelAndView mv = new ModelAndView("/aat/systemSettings/userManage");
		return mv;
    }
	
	@RequestMapping(value = "/userdlt", method = RequestMethod.GET)
	@ResponseBody
    public ModelAndView userdlt(String userid, Model model) {
		if (userService.dltUser(userid)) {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_SUCCESS);
		} else {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);
		}
		ModelAndView mv = new ModelAndView("/aat/systemSettings/userManage");
		return mv;
    }
	
	
	/**
	 * 角色
	 */
	@RequestMapping(value = "/getRoleListById", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getRoleListById(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
        String roleid = request.getParameter("roleid");
		List<SystemRole> roleList = roleService.getRoleListById(roleid);
		map.put("dataList", roleList);
		return map;
	}
	
	@RequestMapping(value="/roleList",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getSystemRoleList(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>(); 
		
		Integer pageSize = Integer.valueOf(request.getParameter("pageSize")==null?"10":request.getParameter("pageSize"));// 每页显示的size
		Integer draw = Integer.valueOf(request.getParameter("draw")==null?"0":request.getParameter("draw"));// 记录操作的次数 每次加1
		Integer page = Integer.valueOf(request.getParameter("page")==null?"0":request.getParameter("page"));// 记录操作的次数 每次加1
		String rolename = request.getParameter("rolename");
		Map roleInfo =new HashMap<>();
		roleInfo.put("rolename", rolename);
		PageHelper.startPage(page,pageSize);
		List<SystemRole> sysRoleList = roleService.getAllRole(roleInfo);
		final PageInfo<SystemRole> pageInfo = new PageInfo<>(sysRoleList);
		
		map.put("draw", draw); 
	    map.put("total",pageInfo.getTotal());//数据总条数 
	    map.put("pageData", sysRoleList);//数据集合 
		return map;
	}
	
	/**
	 * <B>方法名称：添加角色</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */	
	@RequestMapping(value="/roleadd",method = RequestMethod.POST)
	@ResponseBody
	public String roleAdd(SystemRole sysRole, Model model){	
		  if(roleService.addRole(sysRole)){ 
			  model.addAttribute(ResultCodeEnum.KEY,ResultCodeEnum.RESULT_SUCCESS); 
		  }else{
			  model.addAttribute(ResultCodeEnum.KEY,ResultCodeEnum.RESULT_FAIL); 
		  }		 
		  return "/aat/systemSettings/roleManage";
	}
	
	/**
	 * <B>方法名称：修改角色</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */
	@RequestMapping(value="/roleupt",method = RequestMethod.POST)
	@ResponseBody
	public String roleUpt(SystemRole sysRole, Model model){
		 if(roleService.uptRole(sysRole)){ 
			 model.addAttribute(ResultCodeEnum.KEY,ResultCodeEnum.RESULT_SUCCESS); 
		 }else{ 
			 model.addAttribute(ResultCodeEnum.KEY,ResultCodeEnum.RESULT_FAIL); 
		 }		 
		 return "/aat/systemSettings/roleManage";
	}
	
	/**
	 * <B>方法名称：删除角色</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */
	@RequestMapping(value = "/roledlt", method = RequestMethod.GET)
	@ResponseBody
    public String roledlt(String roleid, Model model) {
		if (roleService.dltRole(roleid)) {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_SUCCESS);
		} else {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);
		}
		return "/aat/systemSettings/roleManage";
    }
	
	/**
	 * <B>方法名称：新增_判断角色是否存在</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */
	@RequestMapping(value = "/existAdd", method = RequestMethod.GET)
	@ResponseBody
    public boolean existAdd(HttpServletRequest request) {
		String rolename = request.getParameter("rolename");
		Map roleInfo =new HashMap<>();
		roleInfo.put("rolename", rolename);
		if (roleService.existAdd(roleInfo)) {
			return true;
		}
		return false;
    }
	
	/**
	 * <B>方法名称：修改_判断角色除了自身是否存在</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */
	@RequestMapping(value = "/existUpt", method = RequestMethod.GET)
	@ResponseBody
    public boolean existUpt(HttpServletRequest request) {
		String roleid = request.getParameter("roleid");
		String rolename = request.getParameter("rolename");
		Map roleInfo =new HashMap<>();
		roleInfo.put("roleid", roleid);
		roleInfo.put("rolename", rolename);
		if (roleService.existUpt(roleInfo)) {
			return true;
		}
		return false;
    }
	
	@RequestMapping(value = "/sltUserByName", method = RequestMethod.GET)
	@ResponseBody
    public boolean sltUserByName(HttpServletRequest request) {
		String account = request.getParameter("account");
		List<SystemUser> list = userService.sltUserByName(account);
		if (list.size()>0) {
			return true;
		}
		return false;
    }
	
	@RequestMapping(value = "/sltUserByName2", method = RequestMethod.GET)
	@ResponseBody
    public boolean sltUserByName2(HttpServletRequest request) {
		String account = request.getParameter("account");
		List<SystemUser> list = userService.sltUserByName(account);
		if (list.size()>1) {
			return true;
		}
		return false;
    }

}
