package com.agree.aat.entity;

import java.util.HashMap;
import java.util.Map;



/***
 * 
 * Agent案例数据项Bean
 *
 */
@SuppressWarnings("serial")
public class AgentCaseItemBean  implements java.io.Serializable{
	
	private String objectType;
	private String pageCode;
	private String itemCode;
	private String itemValue;
	private String transCode;
	private String direction;
	
	private String itemName;
	private String pageId;
	private String itemPath;
	private String pageAscriptionType;
	private String pagePath;

	private Map<Object,Object> resultMap  = new HashMap<Object,Object>(); 
	
	public Map<Object, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<Object, Object> resultMap) {
		this.resultMap = resultMap;
	}
	/**
	 * @return the objectType
	 */
	public String getObjectType() {
		return objectType;
	}
	/**
	 * @param objectType the objectType to set
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	/**
	 * @return the itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}
	/**
	 * @param itemCode the itemCode to set
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	/**
	 * @return the itemValue
	 */
	public String getItemValue() {
		return itemValue;
	}
	/**
	 * @param itemValue the itemValue to set
	 */
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
	/**
	 * @return the pageCode
	 */
	public String getPageCode() {
		return pageCode;
	}
	/**
	 * @param pageCode the pageCode to set
	 */
	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}
	
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	
	public String getItemPath() {
		return itemPath;
	}
	public void setItemPath(String itemPath) {
		this.itemPath = itemPath;
	}
	public String getPageAscriptionType() {
		return pageAscriptionType;
	}
	public void setPageAscriptionType(String pageAscriptionType) {
		this.pageAscriptionType = pageAscriptionType;
	}
	public String getPagePath() {
		return pagePath;
	}
	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}
}
