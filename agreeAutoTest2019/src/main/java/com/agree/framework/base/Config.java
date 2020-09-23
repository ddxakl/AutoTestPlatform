package com.agree.framework.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {
	
	@Value("${ftp.path}")
	private String ftPath;
	
	public String getFtPath() {
		return ftPath;
	}	
	
	@Value("${encoding}")
	private String encoding;
	
	public String getEncoding() {
		return encoding;
	}	
	
	@Value("${test}")
	public String test;
	
	
}
