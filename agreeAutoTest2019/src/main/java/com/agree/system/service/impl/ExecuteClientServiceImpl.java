package com.agree.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agree.system.dao.SystemTerminalMapper;
import com.agree.system.entity.SystemTerminal;
import com.agree.system.service.IExecuteClientService;

@Service
public class ExecuteClientServiceImpl implements IExecuteClientService {
	
	@Autowired
	SystemTerminalMapper executecliDO;
	
	@Override
	public List<SystemTerminal> getList(String terminalid) {
		return executecliDO.selectByTerminalid(terminalid);
	}
	
	@Override
	public List<SystemTerminal> getEClientByStatus1() {
		List<SystemTerminal> selectByStatus1 = executecliDO.selectByStatus1();
		return selectByStatus1;
	}

	@Override
	public List<SystemTerminal> getEClientList(Map map) {
		return executecliDO.getEClientList(map);
	}

	@Override
	public boolean addTerminal(SystemTerminal sysTerminal) {
		return executecliDO.insert(sysTerminal)>0;
	}

	@Override
	public boolean uptTerminal(SystemTerminal sysTerminal) {
		return executecliDO.updateByTerminalid(sysTerminal)>0;
	}

	@Override
	public boolean dltTerminal(String terminalid) {
		return executecliDO.deleteByPrimaryKey(terminalid)>0;
	}

	@Override
	public boolean updateStatus(String terminalid, String status) {
		return executecliDO.updateStatus(terminalid, status)>0;
	}

	@Override
	public List<SystemTerminal> sltTerminalByName(String name) {
		return executecliDO.selectTerminalByName(name);
	}

	@Override
	public List<SystemTerminal> sltTerByName(String name) {
		return executecliDO.sltTerByName(name);
	}
	
	
	

}
