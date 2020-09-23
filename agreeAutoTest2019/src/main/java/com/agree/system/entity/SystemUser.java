package com.agree.system.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SystemUser implements Serializable {
	
    private String userid;

    private String username;

    private String belongto;

    private Long sex;

    private String email;

    private String phonenumber;

    private String permission;

    private String account;

    private String password;

    private Long state;

    private String createuser;

    private String createname;

    private Date createdate;

    private String modifieduser;

    private String modifiedname;

    private Date modifieddate;

    private Long userpower;
    
    private List<SystemRole> rolist;
    
    public List<SystemRole> getRolist() {
		return rolist;
	}

	public void setRolist(List<SystemRole> rolist) {
		this.rolist = rolist;
	}

	private static final long serialVersionUID = 1L;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getBelongto() {
        return belongto;
    }

    public void setBelongto(String belongto) {
        this.belongto = belongto == null ? null : belongto.trim();
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
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

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
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

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
    }

    public Long getUserpower() {
        return userpower;
    }

    public void setUserpower(Long userpower) {
        this.userpower = userpower;
    }
}