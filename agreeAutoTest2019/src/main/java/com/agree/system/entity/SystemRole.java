package com.agree.system.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SystemRole implements Serializable {
	
    private String roleid;

    private String rolename;

    private String roledesc;

    private String isenable;

    private String createuser;

    private String createname;

    private Date createtime;

    private String modifieduser;

    private String modifiedname;

    private Date modifiedtime;
    
    private List<SystemModule> mdlist;
    
    public List<SystemModule> getMdlist() {
		return mdlist;
	}

	public void setMdlist(List<SystemModule> mdlist) {
		this.mdlist = mdlist;
	}

	private static final long serialVersionUID = 1L;

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc == null ? null : roledesc.trim();
    }

    public String getIsenable() {
        return isenable;
    }

    public void setIsenable(String isenable) {
        this.isenable = isenable == null ? null : isenable.trim();
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname == null ? null : createname.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getModifieduser() {
        return modifieduser;
    }

    public void setModifieduser(String modifieduser) {
        this.modifieduser = modifieduser == null ? null : modifieduser.trim();
    }

    public String getModifiedname() {
        return modifiedname;
    }

    public void setModifiedname(String modifiedname) {
        this.modifiedname = modifiedname == null ? null : modifiedname.trim();
    }

    public Date getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(Date modifiedtime) {
        this.modifiedtime = modifiedtime;
    }
}