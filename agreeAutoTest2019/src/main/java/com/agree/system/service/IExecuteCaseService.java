/**
 * 
 */
package com.agree.system.service;

import java.util.List;
import java.util.Map;

import com.agree.system.entity.ExecuteCase;
import com.agree.system.entity.ExecuteItem;

/**
 * 代码不规范，维护两行泪！
 * @author xp
 * @date 2019年10月22日
 * 
 */
public interface IExecuteCaseService {
	
	void insertSelective(ExecuteCase executeCase);
	
	void updateByPrimaryKeySelective(ExecuteCase executeCase);
	
	List<ExecuteCase> getAllExecuteCase(Map map);

	void removeByCaseid(String caseid);

	void removeByItemid(String itemid);

	void updateByItemid(String itemid, String column, String input);

	List<ExecuteCase> getListByCaseid(String caseid);

	List<ExecuteCase> existByCaseidentifier(String caseidentifier);

	void updateBatchcaseByCaseid(String caseId, String batchcase);

	String selectBatchcaseByCaseid(String caseid);

	List<String> selectCaseidsByBatchcase(String batchcase);

	ExecuteItem getListByItemid(String itemid);

	List<String> getItemidByCaseid(String caseid);

	long getItemindexByItemid(String rtnItemid);

	void updateItemIndex(String rtnItemid, long index);


}
