package com.agree.framework.constant;

/**
 * 
 * <B>系统名称：adp</B><BR>
 * <B>模块名称：adp-show</B><BR>
 * <B>中文类名：请求返回码</B><BR>
 * <B>概要说明：返回请求状态  两位表示是否成功 00-表示成功 01-表示失败</B><BR>
 * @author admin（admin）
 * @since 2018年9月4日
 */
public enum ResultCodeEnum {
	RESULT_SUCCESS("0","成功"),
	RESULT_FAIL("1","失败"),
	RESULT_TASK_SUCCESS("00","服务调用成功"),
	RESULT_TASK_FAIL("01","服务调用成功");
	
	private String code;
	private String msg;
	
	public static final String KEY="return_code";
	
	private ResultCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	
	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
