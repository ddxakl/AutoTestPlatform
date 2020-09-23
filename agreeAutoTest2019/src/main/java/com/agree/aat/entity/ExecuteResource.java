package com.agree.aat.entity;

public class ExecuteResource {
    private String resourceid;

    private String executeid;

    private String resname;

    private String restype;

    private String resfilename;

    private String resmark;

    public String getResourceid() {
        return resourceid;
    }

    public void setResourceid(String resourceid) {
        this.resourceid = resourceid == null ? null : resourceid.trim();
    }

    public String getExecuteid() {
        return executeid;
    }

    public void setExecuteid(String executeid) {
        this.executeid = executeid == null ? null : executeid.trim();
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname == null ? null : resname.trim();
    }

    public String getRestype() {
        return restype;
    }

    public void setRestype(String restype) {
        this.restype = restype == null ? null : restype.trim();
    }

    public String getResfilename() {
        return resfilename;
    }

    public void setResfilename(String resfilename) {
        this.resfilename = resfilename == null ? null : resfilename.trim();
    }

    public String getResmark() {
        return resmark;
    }

    public void setResmark(String resmark) {
        this.resmark = resmark == null ? null : resmark.trim();
    }
}