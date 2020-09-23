package com.agree.aat.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agree.aat.dao.ExpectResultMapper;
import com.agree.aat.dao.TransItemExpectResultMapper;
import com.agree.aat.entity.ExpectResult;
import com.agree.aat.entity.TransItemExpectResult;
import com.agree.aat.service.IExpectResultService;
import com.agree.framework.constant.ApplicationKeyConst;
import com.agree.system.entity.SystemTerminal;
import com.agree.system.entity.SystemUser;

@Service
public class ExpectResultServiceImpl implements IExpectResultService {
	
	private static final Logger log = LogManager.getLogger(ExpectResultServiceImpl.class);

	@Autowired
	ExpectResultMapper expectresultDO;
	@Autowired
	private HttpSession session;
	@Autowired
	TransItemExpectResultMapper itemExpectResultDO;

	@Override
	public List<ExpectResult> getExpectList(Map param) {
		List<ExpectResult> selectExpectResult = expectresultDO.selectExpectResult(param);
		return selectExpectResult;
	}

	@Override
	public boolean addExpect(ExpectResult expect) {
		SystemUser user = (SystemUser)session.getAttribute(ApplicationKeyConst.USER_INFO);
		expect.setExpectresultid("I"+System.currentTimeMillis());
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
		expect.setCreatedate(dataFormat.format(new Date()));
		expect.setCreater(user.getUsername());
		return expectresultDO.insert(expect)>0;
	}

	@Override
	public boolean delExpect(String id) {
		return expectresultDO.deleteByPrimaryKey(id)>0;
	}

	@Override
	public boolean uptExpect(ExpectResult expect) {
		return expectresultDO.updateByPrimaryKey(expect)>0;
	}

	@Override
	public ExpectResult getExpectResult(String expectresultid) {
		return expectresultDO.selectByPrimaryKey(expectresultid);
	}

	@Override
	public List<TransItemExpectResult> getitemExpectList(Map param) {
		return itemExpectResultDO.getItemExpectResults(param);
	}

	@Override
	public boolean removesItemExpect(String expectid) {
		return itemExpectResultDO.deleteByPrimaryKey(expectid)>0;
	}

	@Override
	public boolean addItemExpect(Map param) {
		String itemid  = (String)param.get("itemid");
		String expectresultid  = (String)param.get("expectresultid");
		ExpectResult erdata = expectresultDO.selectByPrimaryKey(expectresultid);
		TransItemExpectResult ter = new TransItemExpectResult();
		ter.setExpectid("T"+System.currentTimeMillis());
		ter.setExpectresultid(expectresultid);
		ter.setTransitemid(itemid);
		ter.setExpectresultname(erdata.getExpectresultname());
		ter.setExpectresultcode(erdata.getExpectresultcode());
		ter.setExpectresultdesc(erdata.getExpectresultdesc());
		ter.setExpression(erdata.getExpression());
		return itemExpectResultDO.insert(ter)>0;
	}

	@Override
	public boolean updateItemExpect(String expectid, String expression) {
		return itemExpectResultDO.updateItemExpect(expectid,expression)>0;
	}

	@Override
	public List<String> getExpResListRtnEid() {
		return itemExpectResultDO.getExpResListRtnEid();
	}

	@Override
	public List<String> getAllExpectResName() {
		return expectresultDO.getAllExpectResName();
	}

	@Override
	public List<ExpectResult> getExpectRes() {
		return expectresultDO.getExpectRes();
	}
	
	

}
