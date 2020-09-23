package com.agree.aat.entity;

public class PlanCase {
    private String id;

    private String caseid;

    private String casename;

    private String casebelong;

    private String abversion;

    private String casetype;

    private String casedesc;

    private String scriptinfos;

    private String userid;

    private String createdate;

    private String quote;

    private String planid;

    private byte[] scriptclass;
    private String tradecodes;

    private String caseidentifier;
    private String batchcase;
    

    public String getTradecodes() {
		return tradecodes;
	}

	public void setTradecodes(String tradecodes) {
		this.tradecodes = tradecodes;
	}

	public String getCaseidentifier() {
		return caseidentifier;
	}

	public void setCaseidentifier(String caseidentifier) {
		this.caseidentifier = caseidentifier;
	}

	public String getBatchcase() {
		return batchcase;
	}

	public void setBatchcase(String batchcase) {
		this.batchcase = batchcase;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCaseid() {
        return caseid;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid == null ? null : caseid.trim();
    }

    public String getCasename() {
        return casename;
    }

    public void setCasename(String casename) {
        this.casename = casename == null ? null : casename.trim();
    }

    public String getCasebelong() {
        return casebelong;
    }

    public void setCasebelong(String casebelong) {
        this.casebelong = casebelong == null ? null : casebelong.trim();
    }

    public String getAbversion() {
        return abversion;
    }

    public void setAbversion(String abversion) {
        this.abversion = abversion == null ? null : abversion.trim();
    }

    public String getCasetype() {
        return casetype;
    }

    public void setCasetype(String casetype) {
        this.casetype = casetype == null ? null : casetype.trim();
    }

    public String getCasedesc() {
        return casedesc;
    }

    public void setCasedesc(String casedesc) {
        this.casedesc = casedesc == null ? null : casedesc.trim();
    }

    public String getScriptinfos() {
        return scriptinfos;
    }

    public void setScriptinfos(String scriptinfos) {
        this.scriptinfos = scriptinfos == null ? null : scriptinfos.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate == null ? null : createdate.trim();
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote == null ? null : quote.trim();
    }

    public String getPlanid() {
        return planid;
    }

    public void setPlanid(String planid) {
        this.planid = planid == null ? null : planid.trim();
    }

    public byte[] getScriptclass() {
        return scriptclass;
    }

    public void setScriptclass(byte[] scriptclass) {
        this.scriptclass = scriptclass;
    }
}