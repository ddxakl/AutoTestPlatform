/**
 * 
 */
package com.agree.system.service;

import java.util.List;

import com.agree.aat.entity.TransItemExpectResult;
import com.agree.system.entity.ExecuteItem;

/**
 * 代码不规范，维护两行泪！
 * @author xp
 * @date 2019年10月18日
 * 
 */

public interface IExecuteItemService {
	
	void insertSelective(ExecuteItem executeItem);
	
	List<ExecuteItem> getCaseInfo(String caseid);

	ExecuteItem getItemInfo(String itemid);

	List<ExecuteItem> getItemAliasByCaseid(String caseid);

	List<ExecuteItem> getItemIndexByCaseid(String caseid);

	List<ExecuteItem> getItemCodeByCaseid(String caseid);

	void uptItemByItemid(ExecuteItem executeItem);

	List<ExecuteItem> getItemListByItemid(String itemid);

//	List<TransItemExpectResult> getExpectResult(String itemid);

}
