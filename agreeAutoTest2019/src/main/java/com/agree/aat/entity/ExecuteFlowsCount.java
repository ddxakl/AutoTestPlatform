package com.agree.aat.entity;

public class ExecuteFlowsCount {
	
    private String exeflowscountid;

    private String planid;

    private String caseid;

    private Long total;

    private Long success;

    private Long fail;

    private String passrate;

    private String remark;

    private String casename;
   private String planflowid;
    

    public String getPlanflowid() {
		return planflowid;
	}

	public void setPlanflowid(String planflowid) {
		this.planflowid = planflowid;
	}

    public String getExeflowscountid() {
        return exeflowscountid;
    }

    public void setExeflowscountid(String exeflowscountid) {
        this.exeflowscountid = exeflowscountid == null ? null : exeflowscountid.trim();
    }

    public String getPlanid() {
        return planid;
    }

    public void setPlanid(String planid) {
        this.planid = planid == null ? null : planid.trim();
    }

    public String getCaseid() {
        return caseid;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid == null ? null : caseid.trim();
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getSuccess() {
        return success;
    }

    public void setSuccess(Long success) {
        this.success = success;
    }

    public Long getFail() {
        return fail;
    }

    public void setFail(Long fail) {
        this.fail = fail;
    }

    public String getPassrate() {
        return passrate;
    }

    public void setPassrate(String passrate) {
        this.passrate = passrate == null ? null : passrate.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCasename() {
        return casename;
    }

    public void setCasename(String casename) {
        this.casename = casename == null ? null : casename.trim();
    }
}