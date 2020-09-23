package com.agree.system.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SystemRoleModule implements Serializable {
	
    private String rolemoduleid;

    private String roleid;

    private String moduleid;

    public String getRolemoduleid() {
        return rolemoduleid;
    }

    public void setRolemoduleid(String rolemoduleid) {
        this.rolemoduleid = rolemoduleid == null ? null : rolemoduleid.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getModuleid() {
        return moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid == null ? null : moduleid.trim();
    }
}