package com.agree.system.service;

import java.util.List;
import java.util.Map;

import com.agree.system.entity.DictType;
import com.agree.system.entity.DictValue;

/**
 * <B>系统名称：at</B><BR>
 * <B>中文类名：字典服务类</B><BR>
 * <B>概要说明：</B><BR>
 */
public interface IDictManagmentService {
	
	List<DictType> selectAllType();
	
	List<DictValue> selectAllValue();
	
	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：获得所有字典类型</B><BR>
	 * @return
	 */
	List<DictType> getAllDict(Map map);
	/**
	 * 
	 * <B>方法名称：增加字典类型</B><BR>
	 * <B>概要说明：</B><BR>
	 * @param dInfo
	 * @return
	 */
	boolean addDict(DictType dicttype);
	/**
	 * 
	 * <B>方法名称：更新字典类型</B><BR>
	 * <B>概要说明：</B><BR>
	 * @param dInfo
	 * @return
	 */
	boolean uptDict(DictType dicttype);
	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：获得某类型下的字典值</B><BR>
	 * @return
	 */
	List<DictValue> getDiVByTid(String typeId);
	/**
	 * 
	 * <B>方法名称：批量删除字典类型及对应字典值</B><BR>
	 * <B>概要说明：</B><BR>
	 * @param dicttypeid[]
	 * @return
	 */
	boolean delDis(String[] dicttypeids);
	/**
	 * 
	 * <B>方法名称：更新字典类型下对应字典值</B><BR>
	 * <B>概要说明：</B><BR>
	 * @param dicttypeid[]
	 * @return
	 */
	boolean uptDictval(List<DictValue> list, String typeId);
	/**
	 * 
	 * <B>方法名称：批量删除字典值</B><BR>
	 * <B>概要说明：</B><BR>
	 * @param orgId[]
	 * @return
	 */
	boolean delDiv(String[] dictvalueids);

	List<DictType> getListById(String dicttypeid);

	boolean dltDict(String dicttypeid);
}
