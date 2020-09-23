package com.agree.aat.entity;

import java.util.Date;

public class ExecutePlanFlows {
    private String planflowid;

    private String planname;

    private String executer;

    private String abversion;

    private String descinfos;

    private String executeresult;

    private String targetterminals;

    private Date startdate;

    private Date enddate;

    private Long succss;

    private Long fail;

    private String plandesc;

    private String executedesc;

    private String status;

    private String remark;
    
    
    private Long count;
    private String planid;

    
    
    public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getPlanflowid() {
        return planflowid;
    }

    public void setPlanflowid(String planflowid) {
        this.planflowid = planflowid == null ? null : planflowid.trim();
    }

    public String getPlanname() {
        return planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname == null ? null : planname.trim();
    }

    public String getExecuter() {
        return executer;
    }

    public void setExecuter(String executer) {
        this.executer = executer == null ? null : executer.trim();
    }

    public String getAbversion() {
        return abversion;
    }

    public void setAbversion(String abversion) {
        this.abversion = abversion == null ? null : abversion.trim();
    }

    public String getDescinfos() {
        return descinfos;
    }

    public void setDescinfos(String descinfos) {
        this.descinfos = descinfos == null ? null : descinfos.trim();
    }

    public String getExecuteresult() {
        return executeresult;
    }

    public void setExecuteresult(String executeresult) {
        this.executeresult = executeresult == null ? null : executeresult.trim();
    }

    public String getTargetterminals() {
        return targetterminals;
    }

    public void setTargetterminals(String targetterminals) {
        this.targetterminals = targetterminals == null ? null : targetterminals.trim();
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Long getSuccss() {
        return succss;
    }

    public void setSuccss(Long succss) {
        this.succss = succss;
    }

    public Long getFail() {
        return fail;
    }

    public void setFail(Long fail) {
        this.fail = fail;
    }

    public String getPlandesc() {
        return plandesc;
    }

    public void setPlandesc(String plandesc) {
        this.plandesc = plandesc == null ? null : plandesc.trim();
    }

    public String getExecutedesc() {
        return executedesc;
    }

    public void setExecutedesc(String executedesc) {
        this.executedesc = executedesc == null ? null : executedesc.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    
    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}