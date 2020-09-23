package com.agree.util;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public abstract class Config {
	private final static Logger logger = LogManager.getLogger(Config.class);
	
	protected static Properties prop ;

	static {
		try {
			prop = PropertiesLoaderUtils.loadAllProperties("communication.properties","catalina.properties","application.properties");
						
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
		
}
