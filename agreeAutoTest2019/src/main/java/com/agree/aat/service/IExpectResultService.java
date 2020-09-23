package com.agree.aat.service;

import java.util.List;
import java.util.Map;

import com.agree.aat.entity.ExpectResult;
import com.agree.aat.entity.TransItemExpectResult;
import com.agree.system.entity.SystemTerminal;

public interface IExpectResultService {

	List<ExpectResult> getExpectList(Map param);

	boolean addExpect(ExpectResult expect);

	boolean delExpect(String id);

	boolean uptExpect(ExpectResult expect);

	ExpectResult getExpectResult(String expectresultid);

	List<TransItemExpectResult> getitemExpectList(Map param);

	boolean removesItemExpect(String expectid);

	boolean addItemExpect(Map param);

	boolean updateItemExpect(String expectid, String exepctname);

	List<String> getExpResListRtnEid();

	List<String> getAllExpectResName();

	List<ExpectResult> getExpectRes();


}
