package com.agree.framework.base;

import java.util.List;

import com.agree.framework.constant.ResultCodeEnum;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 * <B>系统名称：amt</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：返回结果</B><BR>
 * <B>概要说明：</B><BR>
 * @author admin（admin）
 * @since 2018年9月11日
 */
public class ServiceReturn {
	
	private String RetCode;
	private String msg;
	@SuppressWarnings("rawtypes")
	private List data;
	private JSONObject json;
	private JSONObject json2;
	private JSONArray jsonArray;
	public ServiceReturn() {
		
	}

	public ServiceReturn(ResultCodeEnum re) {		
		RetCode = re.getCode();
		this.msg = re.getMsg();
	}
	
	
	public ServiceReturn(String retCode, String msg, List<Object> data) {		
		RetCode = retCode;
		this.msg = msg;
		this.data = data;
	}

	
	public String getRetCode() {
		return RetCode;
	}

	public void setRetCode(String retCode) {
		RetCode = retCode;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	public JSONObject getJson2() {
		return json2;
	}

	public void setJson2(JSONObject json2) {
		this.json2 = json2;
	}
	
	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}
	
	
}
