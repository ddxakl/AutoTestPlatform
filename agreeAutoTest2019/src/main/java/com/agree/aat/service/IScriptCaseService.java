package com.agree.aat.service;

import java.util.List;
import java.util.Map;

import com.agree.system.entity.ExecuteCase;

public interface IScriptCaseService {
	void insertSelective(ExecuteCase executeCase);
	
	List<ExecuteCase> getAllExecuteCase(Map map);

	void removeByCaseid(String caseid);

	void removeByItemid(String itemid);

	void updateByItemid(String itemid, String column, String input);

	void executeStCase(Map<String, String> map) throws Exception;
}
