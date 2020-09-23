package com.agree.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

/**
 * 
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：加载配置文件</B><BR>
 * @author（admin）
 * @since 2019年5月29日
 */
public class PropertiesLoaderUtils {
	private static final String PROPERTIES_FILE_EXTENSION = ".properties";
	
	public static Properties loadProperties(String resourceName) throws IOException {
		
		ClassLoader classLoaderToUse = ClassUtils.getDefaultClassLoader();		
		URL url = classLoaderToUse.getResource(resourceName);
		Properties props = new Properties();
		
		URLConnection con = url.openConnection();
		InputStream in = con.getInputStream();
		try {
			props.load(in);
		}finally {
			in.close();
		}
		return props;		
	} 
	
	
	//读取并且合并配置文件
	public static Properties loadAllProperties(String... resourceName)throws IOException{
		
		Assert.noNullElements(resourceName);
		
		ClassLoader classLoaderToUse = ClassUtils.getDefaultClassLoader();		
		Properties props = new Properties();
		for (int i=0;i<resourceName.length;i++) {
			String resource = resourceName[i];			
			if(!PROPERTIES_FILE_EXTENSION.equals(resource.substring(resource.lastIndexOf('.')))){
				continue;
			}
			URL url = classLoaderToUse.getResource(resourceName[i]);			
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			try {
				props.load(in);
			}finally {
				in.close();
			}
		}		
		return props;
	}
	
	
	
}
