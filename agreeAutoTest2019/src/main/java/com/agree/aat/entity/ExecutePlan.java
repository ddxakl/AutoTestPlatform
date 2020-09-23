package com.agree.aat.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExecutePlan {
    private String planid;

    private String planname;

    private String executer;

    private String abversion;

    private String descinfos;

    private String executeresult;

    private String targetterminals;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdate;

    private Long count;

    private String plandesc;

    public String getPlanid() {
        return planid;
    }

    public void setPlanid(String planid) {
        this.planid = planid == null ? null : planid.trim();
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

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getPlandesc() {
        return plandesc;
    }

    public void setPlandesc(String plandesc) {
        this.plandesc = plandesc == null ? null : plandesc.trim();
    }
}