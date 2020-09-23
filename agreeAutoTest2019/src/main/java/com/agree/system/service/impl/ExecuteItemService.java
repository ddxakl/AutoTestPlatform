/**
 * 
 */
package com.agree.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agree.aat.dao.TransItemExpectResultMapper;
import com.agree.aat.entity.TransItemExpectResult;
import com.agree.system.dao.ExecuteItemMapper;
import com.agree.system.entity.ExecuteItem;
import com.agree.system.service.IExecuteItemService;

/**
 * 代码不规范，维护两行泪！
 * @author xp
 * @date 2019年10月18日
 * 
 */
@Service
public class ExecuteItemService implements IExecuteItemService {

	@Autowired
	private ExecuteItemMapper executeItemDao;
	@Autowired
	private TransItemExpectResultMapper itemExpectResultDO;
	
	@Override
	public void insertSelective(ExecuteItem executeItem) {
		executeItemDao.insertSelective(executeItem);
	}

	@Override
	public List<ExecuteItem> getCaseInfo(String caseid) {
		return executeItemDao.getCaseInfoByCaseid(caseid);
	}

	@Override
	public ExecuteItem getItemInfo(String itemid) {
		return executeItemDao.selectByPrimaryKey(itemid);
	}

	@Override
	public List<ExecuteItem> getItemAliasByCaseid(String caseid) {
		return executeItemDao.selectAliasByCaseid(caseid);
	}
//
//	@Override
//	public List<TransItemExpectResult> getExpectResult(String itemid) {
//		List<TransItemExpectResult> list=itemExpectResultDO.getItemExpectResults(itemid);
//		return list;
//	}

	@Override
	public List<ExecuteItem> getItemIndexByCaseid(String caseid) {
		return executeItemDao.selectAliasByCaseid(caseid);
	}

	@Override
	public List<ExecuteItem> getItemCodeByCaseid(String caseid) {
		return executeItemDao.selectAliasByCaseid(caseid);
	}

	@Override
	public void uptItemByItemid(ExecuteItem executeItem) {
		executeItemDao.uptItemByItemid(executeItem);
	}

	@Override
	public List<ExecuteItem> getItemListByItemid(String itemid) {
		return executeItemDao.getItemListByItemid(itemid);
	}

}
