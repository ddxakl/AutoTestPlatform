package com.agree.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.agree.framework.base.ServiceReturn;
import com.agree.framework.constant.ResultCodeEnum;
import com.agree.system.dto.SystemUserDTO;
import com.agree.system.entity.SystemRole;
import com.agree.system.service.ISystemRoleService;
import com.agree.system.service.ISystemUserService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * <B>系统名称：amt</B><BR>
 * <B>模块名称：amt</B><BR>
 * <B>中文类名：后台页面controller</B><BR>
 * <B>概要说明：处理后台请求相关</B><BR>
 * @author admin（admin）
 * @since 2019年5月13日
 */

@RestController
@RequestMapping("/roles")
public class RoleController{

	@Autowired
	private ISystemRoleService roleService;
	
	@Autowired
	private ISystemUserService userService;
	
	/**
	 * <B>方法名称：分配角色获取全部</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<SystemRole> getList() {
		return roleService.getList();
	}
		
	/**
	 * <B>方法名称：获取全部角色</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */	
	@RequestMapping(value="/roleList",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getRoleList(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>(); 
		Integer pageSize = Integer.valueOf(request.getParameter("pageSize")==null?"10":request.getParameter("pageSize"));// 每页显示的size
		Integer draw = Integer.valueOf(request.getParameter("draw")==null?"0":request.getParameter("draw"));// 记录操作的次数 每次加1
		Integer page = Integer.valueOf(request.getParameter("page")==null?"0":request.getParameter("page"));// 记录操作的次数 每次加1
		String roleName = request.getParameter("rolename");
		Map role =new HashMap<>();
		role.put("roleName", roleName);
		PageMethod.startPage(page,pageSize);		
		List<SystemRole> roleList = roleService.getAllRole(role);
		final PageInfo<SystemRole> pageInfo = new PageInfo<>(roleList);
		
		map.put("draw", draw); 
	    map.put("total",pageInfo.getTotal());//数据总条数 
	    map.put("pageData", roleList);//数据集合 
		return map;
	}
	
	
	/**
	 * 
	 * <B>方法名称：新增角色</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */
	@RequestMapping(value="/roleadd",method = RequestMethod.POST)
	public ModelAndView roleAdd(SystemRole rInfo, Model model){
		if(roleService.addRole(rInfo)){
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_SUCCESS);		
		}else{
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);	
		}
		 ModelAndView mv = new ModelAndView("/aat/systemSettings/roleManage");
		return mv;
	}

	/**
	 * 
	 * <B>方法名称：修改角色</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */
	@RequestMapping(value="/roleupd",method = RequestMethod.POST)
	public ModelAndView roleUpd(SystemRole rInfo, Model model){
		if(roleService.uptRole(rInfo)){
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_SUCCESS);		
		}else{
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);	
		}
		 ModelAndView mv = new ModelAndView("/aat/systemSettings/roleManage");
		return mv;
	}
	
	/**
	 * <B>方法名称：删除角色</B><BR>
	 * <B>概要说明：</B><BR>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/removes", method = RequestMethod.POST)
	public String rolesDel(HttpServletRequest request, Model model) {
		String parameter = request.getParameter("result");
		JSONArray json = JSONArray.fromObject(parameter);
		JSONObject[] jsb = new JSONObject[json.size()];
		String[] ids = new String[json.size()];
		for (int i = 0; i < json.size(); i++) {
			jsb[i] = json.getJSONObject(i);
			if (jsb[i].containsKey("roleId")) {
				ids[i] = jsb[i].get("roleId").toString();
			} else {

			}
		}
		if (roleService.delRoles(ids)) {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_SUCCESS);
		}
		return "";
	}
	
	/**
	 * <B>方法名称：某个用户下的角色</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */			
	@RequestMapping(value="/{userId}/roles",method = RequestMethod.GET)
	public SystemUserDTO getRoleList(@PathVariable("userId")String userId) {
		return userService.getByIdWithRoleAction(userId);
	}
	
	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：为角色分配可以访问的菜单</B><BR>
	 * @param groupDto
	 * @return
	 */
	@RequestMapping(value="/{userId}/allot",method = RequestMethod.POST)
	public ServiceReturn assignRole(SystemUserDTO userDto) {
		ServiceReturn result;
		if(userService.assignRole(userDto)) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
		} else {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
		}
		return result;
	} 
}
