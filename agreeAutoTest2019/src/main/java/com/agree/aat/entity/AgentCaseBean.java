package com.agree.aat.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.agree.system.entity.SystemTerminal;

import net.sf.json.JSONObject;

/***
 * Agent专用交易案例信息Bean
 *
 */
@SuppressWarnings("serial")
public class AgentCaseBean implements java.io.Serializable {
	
	private String caseId;												        //案例ID
	
	private String executeType;													//执行类型

	private String transCode;													//交易代码
	
	private Record transCase;												 //案例信息
	
	private SystemTerminal baseExecuteClient=new SystemTerminal();			//执行机信息
	
	private Map<String,String> userMap = null;			                        //用户信息	

	private List<AgentCaseStepBean> stepList=new ArrayList<AgentCaseStepBean>();//案例步骤List
	
	private Map<String,String> inputMap = new HashMap<String,String>();         // 业务场景调起入参
	
	
	private String userActionLevel;												//用户操作级别
	
	private String filePath;												//公共数据文件路径
	private String treeRt;													//案例名-- 屏幕截图存放
	private String seqNo;													//案例执行序号
	
	private JSONObject casejson;
	
	private String execlientId;
	
	
	public String getExeclientId() {
		return execlientId;
	}
	public void setExeclientId(String execlientId) {
		this.execlientId = execlientId;
	}
	public JSONObject getCasejson() {
		return casejson;
	}
	public void setCasejson(JSONObject casejson) {
		this.casejson = casejson;
	}
	public Map<String, String> getInputMap() {
		return inputMap;
	}
	public void setInputMap(Map<String, String> inputMap) {
		this.inputMap = inputMap;
	}

	/**
	 * @return the stepList
	 */
	public List<AgentCaseStepBean> getStepList() {
		return stepList;
	}
	/**
	 * @param stepList the stepList to set
	 */
	public void setStepList(List<AgentCaseStepBean> stepList) {
		this.stepList = stepList;
	}
	
	public SystemTerminal getBaseExecuteClient() {
		return baseExecuteClient;
	}
	public void setBaseExecuteClient(SystemTerminal baseExecuteClient) {
		this.baseExecuteClient = baseExecuteClient;
	}
	public String getExecuteType() {
		return executeType;
	}
	public void setExecuteType(String executeType) {
		this.executeType = executeType;
	}
	
	/**
	 * @return the transCode
	 */
	public String getTransCode() {
		return transCode;
	}
	/**
	 * @param transCode the transCode to set
	 */
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}


	public Record getTransCase() {
		return transCase;
	}
	public void setTransCase(Record transCase) {
		this.transCase = transCase;
	}

	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
	public Map<String, String> getUserMap() {
		return userMap;
	}
	public void setUserMap(Map<String, String> userMap) {
		this.userMap = userMap;
	}
	
	public String getUserActionLevel() {
		return userActionLevel;
	}
	public void setUserActionLevel(String userActionLevel) {
		this.userActionLevel = userActionLevel;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getTreeRt() {
		return treeRt;
	}
	public void setTreeRt(String treeRt) {
		this.treeRt = treeRt;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	
}

