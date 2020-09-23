package com.agree.framework.helper;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.agree.util.ScriptClassLoader;


@Component
public class ApplicationContentHelper {
	
	/**
	 * 类加载器
	 * key : caseid
	 * value : classloader
	 */
	private volatile ConcurrentHashMap<String,ScriptClassLoader> classLoads = new ConcurrentHashMap<String,ScriptClassLoader>();
	
	
	
	
	
	
	
	

}
