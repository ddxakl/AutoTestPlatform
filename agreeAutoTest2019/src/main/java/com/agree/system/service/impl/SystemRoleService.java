package com.agree.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agree.system.dao.SystemModuleMapper;
import com.agree.system.dao.SystemRoleMapper;
import com.agree.system.dao.SystemRoleModuleMapper;
import com.agree.system.dto.SystemRoleDTO;
import com.agree.system.entity.SystemModule;
import com.agree.system.entity.SystemRole;
import com.agree.system.entity.SystemRoleModule;
import com.agree.system.service.ISystemRoleService;

@Service
public class SystemRoleService implements ISystemRoleService {

	@Autowired
	private SystemRoleMapper sysRoleDao;
	
	@Autowired
	private SystemModuleMapper sysModDao;
	
	@Autowired
	private SystemRoleModuleMapper roleModDao;


	@Override
	public List<SystemRole> getRoleListById(String roleid) {
		return sysRoleDao.selectByPrimaryKey(roleid);
	}

	@Override
	public List<SystemRole> getAllRole(Map roleInfo) {
		return sysRoleDao.selectAllByPage(roleInfo);
	}

	@Override
	public boolean addRole(SystemRole sysRole) {
		return sysRoleDao.insert(sysRole)>0;
	}

	@Override
	public boolean uptRole(SystemRole sysRole) {
		return sysRoleDao.updateByPrimaryKey(sysRole)>0;
	}

	@Override
	public boolean dltRole(String roleid) {
		return sysRoleDao.deleteByPrimaryKey(roleid)>0;
	}

	@Override
	public boolean existAdd(Map roleInfo) {
		return sysRoleDao.selectByRolename(roleInfo)>0;
	}

	@Override
	public boolean existUpt(Map roleInfo) {
		return sysRoleDao.selectNoSelfRolename(roleInfo)>0;
	}

	@Override
	public SystemRoleDTO getByIdWithMenuAction(String roleId) {
		SystemRoleDTO result = new SystemRoleDTO();
		List<SystemModule> menuDtoList = new ArrayList<>();
		result.setMdlist(menuDtoList);		
		SystemRole role = sysRoleDao.selectMenuListById(roleId);
		if(role != null) {
			BeanUtils.copyProperties(role, result);
			for(SystemModule menu : role.getMdlist()) {
				SystemModule module = new SystemModule();
				menuDtoList.add(module);
				BeanUtils.copyProperties(menu, module);
			}
		}
		return result;
	}

	@Override
	@Transactional
	public boolean assignMenu(SystemRoleDTO groupDto) {
		roleModDao.deleteByRoleId(groupDto.getRoleid());
		
		if ("1".equals(groupDto.getRoleid())) {
			List<Long> modIdList= sysModDao.getModIdByBelongIsZero();
			for (Long modId : modIdList) {
				roleModDao.insertSysManage(groupDto.getRoleid(), modId.toString());
			}
		}
		if(groupDto.getModuleIdList() != null && groupDto.getModuleIdList().size() > 0) {
			List<SystemRoleModule> list = new ArrayList<>();
			for(String module : groupDto.getModuleIdList()) {
				if(module != null) {
					SystemRoleModule rodule = new SystemRoleModule();
					rodule.setRoleid(groupDto.getRoleid().toString());
					rodule.setModuleid(module);
					list.add(rodule);
				}
			}
			roleModDao.insertBatch(list);
		}		
		return true;
	}

	@Override
	public List<SystemRole> getList() {
		return sysRoleDao.noAdminList();
	}

	@Override
	public boolean delRoles(String[] roleIds) {
		int ids=roleIds.length;		
		return sysRoleDao.deleteMany(roleIds)==ids;		
	}

	@Override
	public List<SystemRoleModule> getAllRoMo(String roleId) {
		return roleModDao.selectAllByRole(roleId);
	}

	@Override
	public List<String> getModuleIdByRoleId(String roleId) {
		return roleModDao.getModuleIdByRoleId(roleId);
	}

	

}
