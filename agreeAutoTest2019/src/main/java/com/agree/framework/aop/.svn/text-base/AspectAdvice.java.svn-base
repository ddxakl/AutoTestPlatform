package com.agree.framework.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * aop切面处理类
 * 
 * @author xrp09
 *
 */
// @Aspect 注解方式
@Component
public class AspectAdvice {

	private static Logger logger = LoggerFactory.getLogger(AspectAdvice.class);

	/**
	 * JoinPoint 表示织入在增强处理的连接点 Object[] getArgs：返回目标方法的参数 Signature
	 * getSignature：返回目标方法的签名 Object getTarget：返回被织入增强处理的目标对象 Object
	 * getThis：返回AOP框架为目标对象生成的代理对象
	 *
	 * @Title: beforeMethod
	 * @Description: 切面处理-方法前调用,用于保存页面传递参数，来给方法后切面方法记录日志
	 * @throws Exception
	 */
	public void before(JoinPoint joinpoint) throws Exception {
		logger.info(" Controller start ...");
		Object[] args = joinpoint.getArgs();
		String classType = joinpoint.getTarget().getClass().getName();
		Class<?> clazz = Class.forName(classType);
		String clazzName = clazz.getName();
		String methodName = joinpoint.getSignature().getName(); // 获取方法名称
		// 获取参数名称和值
		StringBuffer sb = LogAopUtil.getNameAndArgs(this.getClass(), clazzName, methodName, args);

		logger.info(" 调用方法：" + joinpoint.getSignature().toShortString() + ",方法参数 ：" + sb.toString());
	}

	/**
	 * @Title: after
	 * @Description: 切面处理-方法前调用,用于保存页面传递参数，来给方法后切面方法记录日志
	 * @throws Exception
	 */
	public void after(JoinPoint joinpoint) throws Exception {
		logger.info(" 调用方法：" + joinpoint.getSignature().toShortString() + " ... Controller end ...");
	}

	// 基于注解的方式实现aop
	/*
	 * @Before(value = "execution(* com.gray.user.controller.*.*(..))") public void
	 * before(){ logger.info("login start!"); }
	 * 
	 * @After(value = "execution(* com.gray.user.controller.*.*(..))") public void
	 * after(){ logger.info("login end!"); }
	 */

}