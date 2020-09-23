/**
 * 
 */
package com.agree.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agree.framework.base.Config;
import com.agree.system.dao.ExecuteCaseMapper;
import com.agree.system.dao.ExecuteItemMapper;
import com.agree.system.entity.ExecuteCase;
import com.agree.system.entity.ExecuteItem;
import com.agree.system.service.IExecuteCaseService;

/**
 * 代码不规范，维护两行泪！
 * @author xp
 * @date 2019年10月22日
 * 
 */
@Service
public class ExecuteCaseService implements IExecuteCaseService{

	@Autowired
	ExecuteCaseMapper executeCaseDao;
	@Autowired
	ExecuteItemMapper executeItemDao;
	@Autowired
	private Config config;
	
	@Override
	public void insertSelective(ExecuteCase executeCase) {
		executeCaseDao.insertSelective(executeCase);
	}
	
	@Override
	public void updateByPrimaryKeySelective(ExecuteCase executeCase) {
		executeCaseDao.updateByPrimaryKeySelective(executeCase);
	}

	@Override
	public List<ExecuteCase> getAllExecuteCase(Map map) {
		return executeCaseDao.getAllExecuteCase(map);
	}

	@Override
	public void removeByCaseid(String caseid) {
		executeCaseDao.deleteByPrimaryKey(caseid);
	}

	@Override
	public void removeByItemid(String itemid) {
		executeItemDao.deleteByPrimaryKey(itemid);
	}

	@Override
	public void updateByItemid(String itemid, String column, String input) {
		executeItemDao.updateByItemid(itemid, column, input);
	}

	@Override
	public List<ExecuteCase> getListByCaseid(String caseid) {
		return executeCaseDao.getListByCaseid(caseid);
	}

	@Override
	public List<ExecuteCase> existByCaseidentifier(String caseidentifier) {
		List<ExecuteCase> list = new ArrayList<ExecuteCase>();
		ExecuteCase existByCaseidentifier = executeCaseDao.existByCaseidentifier(caseidentifier);
		if(existByCaseidentifier!=null) {
			list.add(existByCaseidentifier);
		}
		return list;
	}

	@Override
	public void updateBatchcaseByCaseid(String caseId, String batchcase) {
		executeCaseDao.updateBatchcaseByCaseid(caseId,batchcase);
	}

	@Override
	public String selectBatchcaseByCaseid(String caseid) {
		return executeCaseDao.selectBatchcaseByCaseid(caseid);
	}

	@Override
	public List<String> selectCaseidsByBatchcase(String batchcase) {
		return executeCaseDao.selectCaseidsByBatchcase(batchcase);
	}

	@Override
	public ExecuteItem getListByItemid(String itemid) {
		return executeItemDao.getListByItemid(itemid);
	}

	@Override
	public List<String> getItemidByCaseid(String caseid) {
		return executeItemDao.getItemidByCaseid(caseid);
	}

	@Override
	public long getItemindexByItemid(String rtnItemid) {
		return executeItemDao.getItemindexByItemid(rtnItemid);
	}

	@Override
	public void updateItemIndex(String rtnItemid, long index) {
		executeItemDao.updateItemIndex(rtnItemid, index);
	}


}
