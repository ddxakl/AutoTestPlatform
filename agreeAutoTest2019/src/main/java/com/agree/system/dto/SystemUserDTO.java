package com.agree.system.dto;

import java.util.List;

import com.agree.system.entity.SystemUser;

/**
 * 
 * <B>系统名称：at</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明： 数据传输对象</B><BR>
 * @author（admin）
 * @since 2019年12月17日
 */

@SuppressWarnings("serial")
public class SystemUserDTO extends SystemUser{
	
	private static final long serialVersionUID = 1L;

	private List<String> roleIdList;

	private Integer pId;

	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}
}
