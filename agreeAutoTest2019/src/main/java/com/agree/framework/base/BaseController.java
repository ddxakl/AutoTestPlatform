package com.agree.framework.base;

import java.util.List;

import com.agree.framework.constant.ResultCodeEnum;

public class BaseController {
	/**
	 * 
	 * <B>方法名称：构建返回值,不同参数自行添加</B><BR>
	 * <B>概要说明：封装返回参数</B><BR>
	 * @return
	 */
	public ServiceReturn buildReturn(List<Object> data,ResultCodeEnum reCode){		
		ServiceReturn reStr = new ServiceReturn();
		reStr.setRetCode(reCode.getCode());
		reStr.setMsg(reCode.getMsg());
		reStr.setData(data);
		return reStr;
	}
}
