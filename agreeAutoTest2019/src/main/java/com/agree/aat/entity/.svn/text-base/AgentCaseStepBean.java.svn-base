package com.agree.aat.entity;



import java.util.List;

@SuppressWarnings("serial")
public class AgentCaseStepBean implements java.io.Serializable {

	private String stepId;		// 步骤Id
	private String stepName;	// 步骤名称
	
	/*
	 * 步骤类型：I-输入数据, E-提交事件
	 * 事件信息也保存在ItemBean中,itemValue中保存事件名称
	 */
	private String stepType; 
	private List<AgentCaseItemBean> itemList;// 数据项List
	private AgentCaseBean agentCaseBean;
	

	/**
	 * @return the stepId
	 */
	public String getStepId() {
		return stepId;
	}
	/**
	 * @param stepId the stepId to set
	 */
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	/**
	 * @return the itemList
	 */
	public List<AgentCaseItemBean> getItemList() {
		return itemList;
	}
	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(List<AgentCaseItemBean> itemList) {
		this.itemList = itemList;
	}
	
	/**
	 * @return the stepType
	 */
	public String getStepType() {
		return stepType;
	}
	/**
	 * @param stepType the stepType to set
	 */
	public void setStepType(String stepType) {
		this.stepType = stepType;
	}
	/**
	 * @return the stepName
	 */
	public String getStepName() {
		return stepName;
	}
	/**
	 * @param stepName the stepName to set
	 */
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	
	public AgentCaseBean getAgentCaseBean() {
		return agentCaseBean;
	}
	
	public void setAgentCaseBean(AgentCaseBean agentCaseBean) {
		this.agentCaseBean = agentCaseBean;
	}
	
}
