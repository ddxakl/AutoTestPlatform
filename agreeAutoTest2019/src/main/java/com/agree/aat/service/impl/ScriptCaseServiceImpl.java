package com.agree.aat.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agree.aat.controller.recordController;
import com.agree.aat.service.IScriptCaseService;
import com.agree.framework.base.ApplicationContextUtil;
import com.agree.system.dao.ExecuteCaseMapper;
import com.agree.system.dao.ExecuteItemMapper;
import com.agree.system.entity.ExecuteCase;
import com.agree.util.ClassUtils;
import com.agree.util.ClientSocketUtil;


@Service
public class ScriptCaseServiceImpl implements IScriptCaseService {

	private static final Logger log=LogManager.getLogger(ScriptCaseServiceImpl.class);

	@Autowired
	ExecuteCaseMapper executeCaseDao;
	@Autowired
	ExecuteItemMapper executeItemDao;
	@Override
	public void insertSelective(ExecuteCase executeCase) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ExecuteCase> getAllExecuteCase(Map map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeByCaseid(String caseid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeByItemid(String itemid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateByItemid(String itemid, String column, String input) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeStCase(Map<String, String> map) throws Exception {
		String caseid =(String) map.get("caseid");
		ExecuteCase executeCase = executeCaseDao.selectByPrimaryKey(caseid);
		if(executeCase==null) {
			throw new Exception("脚本案例信息缺失！");
		}
		//执行脚本
		log.info("执行脚本案例："+executeCase.getCasename());
//		ClientSocketUtil.sendMsg(ip, port, content)
		ClassUtils.loadScriptCase(executeCase);
	}

}
