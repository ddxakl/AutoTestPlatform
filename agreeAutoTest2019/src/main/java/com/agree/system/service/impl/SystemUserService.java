/**
 * 
 */
package com.agree.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agree.framework.constant.ApplicationKeyConst;
import com.agree.system.dao.SystemUserMapper;
import com.agree.system.dao.SystemUserRoleMapper;
import com.agree.system.dto.SystemUserDTO;
import com.agree.system.entity.SystemRole;
import com.agree.system.entity.SystemUser;
import com.agree.system.entity.SystemUserRole;
import com.agree.system.service.ISystemUserService;
import com.agree.util.CommonUtil;

@Service
public class SystemUserService implements ISystemUserService{

	@Autowired
	private SystemUserMapper systemUserDao;
	
	@Autowired
	private SystemUserRoleMapper userRoleDao;
	
	@Override
	public SystemUser selectByPrimaryKey(String userId) {
		return systemUserDao.selectByPrimaryKey(userId);
	}

	@Override
	public List<SystemUser> getUserList(Map userMap) {
		return systemUserDao.getUserList(userMap);
	}

	@Override
	public List<SystemUser> getList(String userid) {
		return systemUserDao.selectByTerminalid(userid);
	}

	@Override
	public boolean addUser(SystemUser sysUser) {
		return systemUserDao.insert(sysUser)>0;
	}

	@Override
	public boolean uptUser(SystemUser sysUser) {
		return systemUserDao.updateByUserid(sysUser)>0;
	}

	@Override
	public boolean dltUser(String userid) {
		return systemUserDao.deleteByPrimaryKey(userid)>0;
	}

	@Override
	public List<SystemUser> sltUserByName(String account) {
		List<SystemUser> list = new ArrayList<SystemUser>();
		SystemUser selectUserByName = systemUserDao.selectUserByName(account);
		if(selectUserByName!=null) {
			list.add(selectUserByName);
		}
		return list;
	}

	@Override
	public SystemUserRole selectUrByUid(String userid) {
		return userRoleDao.selectByUserId(userid);
	}

	@Override
	public boolean addUserRole(SystemUserRole userRole) {
		return userRoleDao.insert(userRole)>0;
	}

	@Override
	public boolean uptUserRole(SystemUserRole userRole) {
		return userRoleDao.updateByPrimaryKey(userRole)>0;
	}

	@Override
	public SystemUserDTO getByIdWithRoleAction(String userId) {
		SystemUserDTO result = new SystemUserDTO();
		List<SystemRole> roleDtoList = new ArrayList<>();
		result.setRolist(roleDtoList);		
		SystemUser user = systemUserDao.selectRoleListById(userId);
		if(user != null) {
			BeanUtils.copyProperties(user, result);
			for(SystemRole ro : user.getRolist()) {
				SystemRole role = new SystemRole();
				roleDtoList.add(role);
				BeanUtils.copyProperties(ro, role);
			}
		}
		return result;
	}

	@Override
	@Transactional
	public boolean assignRole(SystemUserDTO userDto) {
		userRoleDao.deleteByUserId(userDto.getUserid());
		
		if(userDto.getRoleIdList() != null && userDto.getRoleIdList().size() > 0) {
			List<SystemUserRole> list = new ArrayList<>();
			for(String role : userDto.getRoleIdList()) {
				if(role != null) {
					SystemUserRole usrole = new SystemUserRole();
					usrole.setUserid(userDto.getUserid().toString());
					usrole.setRoleid(role);
					list.add(usrole);
				}
			}
			userRoleDao.insertBatch(list);
		}		
		return true;
	}
	

	@Override
	public boolean validate(SystemUserDTO userDto) {
		if(userDto!=null && !CommonUtil.isEmpty(userDto.getAccount()) && !CommonUtil.isEmpty(userDto.getPassword())){
			SystemUser uIfo = new SystemUser();
			BeanUtils.copyProperties(userDto,uIfo);
			uIfo = systemUserDao.getEntity(uIfo);
			if(uIfo==null){
				return false;
			}
			BeanUtils.copyProperties(uIfo,userDto);	
			return true;
		}
		return false;
	}

	@Override
	public void singleLogin(HttpSession session, SystemUserDTO userDto) {
		//同一用户去掉同时登录
		@SuppressWarnings("unchecked")
		List<Object> slist = (List<Object>)session.getServletContext().getAttribute(ApplicationKeyConst.LOGIN_INFO);
		
		boolean isSame = false;
		if(slist==null){
			slist = new ArrayList<Object>();
		}
		Object[] sessionListCopy= slist.toArray();
		for(int i=0; i< sessionListCopy.length; i++){
			HttpSession sess = (HttpSession)sessionListCopy[i];
			try{
				SystemUser us = (SystemUser)sess.getAttribute(ApplicationKeyConst.USER_INFO);
				if(us.getUserid().equals(userDto.getUserid()) && !(session.getId().equals(sess.getId()))){
					slist.remove(sess);
					sess.invalidate();
				}
				if(session.getId().equals(sess.getId())){
					isSame=true;
				}
			}catch(java.lang.IllegalStateException e){
				slist.remove(sess);
			}
		}
		if(!isSame){
			slist.add(session);
		}
		session.getServletContext().setAttribute(ApplicationKeyConst.LOGIN_INFO,slist);
	}

	@Override
	public boolean isAdmin(SystemUserDTO userDto) {
		return userDto.getUserpower()==0;
	}

	@Override
	public List<String> getRoleIdByUserId(String userid) {
		return userRoleDao.getRoleIdByUserId(userid);
	}

}
