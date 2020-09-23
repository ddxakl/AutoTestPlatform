package com.agree.aat.entity;

public class TransItemExpectResult {
    private String expectid;

    private String transitemid;

    private String expectresultid;

    private String expression;

    private String remark;

    private String index1;

    private String expectresultname;

    private String expectresultcode;

    private String expectresultdesc;

    public String getExpectid() {
        return expectid;
    }

    public void setExpectid(String expectid) {
        this.expectid = expectid == null ? null : expectid.trim();
    }

    public String getTransitemid() {
        return transitemid;
    }

    public void setTransitemid(String transitemid) {
        this.transitemid = transitemid == null ? null : transitemid.trim();
    }

    public String getExpectresultid() {
        return expectresultid;
    }

    public void setExpectresultid(String expectresultid) {
        this.expectresultid = expectresultid == null ? null : expectresultid.trim();
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression == null ? null : expression.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIndex1() {
        return index1;
    }

    public void setIndex1(String index1) {
        this.index1 = index1 == null ? null : index1.trim();
    }

    public String getExpectresultname() {
        return expectresultname;
    }

    public void setExpectresultname(String expectresultname) {
        this.expectresultname = expectresultname == null ? null : expectresultname.trim();
    }

    public String getExpectresultcode() {
        return expectresultcode;
    }

    public void setExpectresultcode(String expectresultcode) {
        this.expectresultcode = expectresultcode == null ? null : expectresultcode.trim();
    }

    public String getExpectresultdesc() {
        return expectresultdesc;
    }

    public void setExpectresultdesc(String expectresultdesc) {
        this.expectresultdesc = expectresultdesc == null ? null : expectresultdesc.trim();
    }
}