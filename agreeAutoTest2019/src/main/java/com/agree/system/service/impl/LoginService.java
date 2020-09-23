package com.agree.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agree.framework.constant.ApplicationKeyConst;
import com.agree.system.dao.SystemUserMapper;
import com.agree.system.entity.SystemUser;
import com.agree.system.service.ILoginService;


@Service
public class LoginService implements ILoginService{

	@Autowired
	SystemUserMapper systemUserDao;

	@Override
	public void singleLogin(HttpSession session, SystemUser systemUser) {
		//同一用户去掉同时登陆
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
				if(us.getUserid().equals(systemUser.getUserid()) && !(session.getId().equals(sess.getId()))){
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

}
