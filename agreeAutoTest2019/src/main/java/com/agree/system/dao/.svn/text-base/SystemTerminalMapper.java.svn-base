package com.agree.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.agree.system.entity.SystemTerminal;

public interface SystemTerminalMapper {
    int deleteByPrimaryKey(String terminalid);

    int insert(SystemTerminal record);

    int insertSelective(SystemTerminal record);

    SystemTerminal selectByPrimaryKey(String terminalid);
    
    List<SystemTerminal> selectByTerminalid(String terminalid);

    int updateByPrimaryKeySelective(SystemTerminal record);

    int updateByPrimaryKey(SystemTerminal record);
    
    int updateByTerminalid(SystemTerminal record);

	List<SystemTerminal> selectByStatus1();

	List<SystemTerminal> getEClientList(Map map);
	
	int updateStatus(@Param("terminalid")String terminalid, @Param("status")String status);

	SystemTerminal getEClientByName(String target);
	
    List<SystemTerminal> selectTerminalByName(String name);
    
    List<SystemTerminal> sltTerByName(String name);

}