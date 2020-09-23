package com.agree.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agree.system.dao.SystemModuleMapper;
import com.agree.system.entity.SystemModule;
import com.agree.system.service.ISystemModuleService;

@Service
public class SystemModuleService implements ISystemModuleService {

	@Autowired
	private SystemModuleMapper moduleDao;

	@Override
	public List<SystemModule> getAdminMod() {

		// 参数1 表示获取admin菜单
		List<SystemModule> modle = moduleDao.selectByBelong((long) 0);
		if (modle.size() > 0) {
			return modle;
		} else {
			return null;
		}

	}

	public List<SystemModule> getsubAdminMod() {
		List<SystemModule> modle = (List<SystemModule>) moduleDao.selectBySubBelong((long) 0);
		if (modle.size() > 0) {
			return modle;
		} else {
			return null;
		}
	}

	public List<SystemModule> getUserMod() {
		// 参数1 表示获取普通用户菜单
		List<SystemModule> modle = (List<SystemModule>) moduleDao.selectByBelong((long) 1);
		if (modle.size() > 0) {
			return modle;
		} else {
			return null;
		}
	}
	
	public List<SystemModule> getsubUserMod() {
		List<SystemModule> modle = (List<SystemModule>) moduleDao.selectBySubBelong((long) 1);
		if (modle.size() > 0) {
			return modle;
		} else {
			return null;
		}
	}

	@Override
	public List<SystemModule> getMenuList() {
		return moduleDao.selectUserMenu();
	}

	@Override
	public SystemModule getUserModule(Long moduleId) {
		return moduleDao.getUserModule(moduleId);
	}

	@Override
	public Long getUserParentModId(Long moduleId) {
		return moduleDao.getUserParentModId(moduleId);
	}

	@Override
	public List<SystemModule> getAdminMenuList() {
		return moduleDao.selectAdminMenu();
	}

	@Override
	public List<SystemModule> getBelongMenuList() {
		return moduleDao.getBelongMenuList();
	}
}
