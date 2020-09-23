package com.agree.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agree.system.dao.DictTypeMapper;
import com.agree.system.dao.DictValueMapper;
import com.agree.system.entity.DictType;
import com.agree.system.entity.DictValue;
import com.agree.system.service.IDictManagmentService;

@Service
public class DictManagementService implements IDictManagmentService{

	@Autowired
	DictTypeMapper dictTypeDao;
	
	@Autowired
	DictValueMapper dictValueDao;
	
	@Override
	public List<DictType> getAllDict(Map map) {

		return dictTypeDao.selectAllByPage(map);
	}

	@Override
	public boolean addDict(DictType dicttype) {
		//字典中文名和英文名重复考虑
			if(dictTypeDao.selectByDict(dicttype)!=null){
				//logger.info("该字典已存在，添加失败");
				return false;
			}		
		return dictTypeDao.insert(dicttype)==1;
	}

	@Override
	public boolean uptDict(DictType dicttype) {
		
		if(dictTypeDao.selectNoSelfByDict(dicttype)!=null) {
			return false;
		}
		return dictTypeDao.updateByPrimaryKey(dicttype)==1;
	}

	@Override
	public List<DictValue> getDiVByTid(String typeId) {
		
		List<DictValue> selectByType = dictValueDao.selectByType(typeId);
		return selectByType;
	}

	@Override
	public List<DictType> selectAllType() {
		
		return dictTypeDao.listAll();
	}

	@Override
	public List<DictValue> selectAllValue() {
		
		return dictValueDao.listAll();
	}

	@Override
	public boolean delDis(String[] dicttypeids) {
		int ids=dicttypeids.length;		
		int delType = dictTypeDao.deleteMany(dicttypeids);
		if(delType==ids) {
			List<DictValue> selectByTypes = dictValueDao.selectByTypes(dicttypeids);
			if(selectByTypes==null||selectByTypes.size()==0) {
				return true;
			}else {
				int size = selectByTypes.size();
				int delVal = dictValueDao.deleteMany(dicttypeids);
				if(size==delVal) {
					return true;
				}							
			}			
		}
		return false;
	}

	@Override
	public boolean uptDictval(List<DictValue> list, String typeId) {
		boolean boo=false;
		//1.查询该type下的字典值数量
		List<DictValue> oldDict = dictValueDao.selectByType(typeId);
		int all = oldDict.size();
		//2.删除该type下的所有value
		int delByTypeId = dictValueDao.deleteManyByTypeId(typeId);
		if(delByTypeId==all) {
			//3.批量新增数据
			int insDiv = dictValueDao.insertBatch(list);
			System.out.println("批量新增数量："+insDiv);
			if(insDiv==list.size()) {
				boo = true;
			}
		}			
		return boo;
	}

	@Override
	public boolean delDiv(String[] dictvalueids) {
		int ids=dictvalueids.length;
		return dictValueDao.deleteManyByValueId(dictvalueids)==ids;
	}

	@Override
	public List<DictType> getListById(String dicttypeid) {
		return dictTypeDao.selectByDicttypeid(dicttypeid);
	}

	@Override
	public boolean dltDict(String dicttypeid) {
		return dictTypeDao.deleteByPrimaryKey(dicttypeid)>0;
	}
	
	

}
