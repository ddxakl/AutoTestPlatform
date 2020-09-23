package com.agree.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HttpTest {
	
	
	private static final Logger log=LogManager.getLogger(HttpTest.class);

	
	static String url = " http://192.168.187.90:8080/AT_Platform_2019A/flows/addResultFlow";
	
	static String encoding = "utf-8";
	
	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：post请求 没有参数,或者参数凭借在path中</B><BR>
	 * @param path
	 * @return
	 */
	public static String postString(String path, JSONObject obj) {
		
		if(obj==null) {
			return post(path,obj).toString();
		}
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
 
		// 创建Post请求
		HttpPost req = new HttpPost(url);
		StringEntity entity = new StringEntity(obj.toString(), encoding);
		req.setEntity(entity);
		req.setHeader("Content-Type", "application/json;charset="+encoding);
		
		// 响应模型
		CloseableHttpResponse response = null;
		try {
			
			response = httpClient.execute(req);
			HttpEntity respEntity = response.getEntity();
			log.info(url+"响应状态为: [ " + response.getStatusLine()+"]");
			
			if(response.getStatusLine().getStatusCode()==200) {
				if(respEntity !=null) {
					try {
						String content = EntityUtils.toString(respEntity);
						return content;
					}catch(Exception e) {
						log.error("格式化json出错...");
						e.printStackTrace();
					}
				}else {
					log.info("响应体中没有包含任何数据...");
				}				
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	
	}
	
	
	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：post对象参数</B><BR>
	 *  <B>参数说明：参数通过jsonObj形式传入</B><BR>
	 * @param path
	 * @param obj
	 * @return
	 */
	public static JSONArray post(String path,JSONObject obj) {		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
 
		// 创建Post请求
		HttpPost req = new HttpPost(url);
		StringEntity entity = new StringEntity(obj.toString(), encoding);
		req.setEntity(entity);
		req.setHeader("Content-Type", "application/json;charset="+encoding);
		
		// 响应模型
		CloseableHttpResponse response = null;
		try {
			
			response = httpClient.execute(req);
			HttpEntity respEntity = response.getEntity();
			log.info(url+"响应状态为: [ " + response.getStatusLine()+"]");
			
			if(response.getStatusLine().getStatusCode()==200) {
				if(respEntity !=null) {
					try {
						String content = EntityUtils.toString(respEntity);
						return JSONArray.fromObject(content);
					}catch(Exception e) {
						log.error("格式化json出错...");
						e.printStackTrace();
					}
				}else {
					log.info("响应体中没有包含任何数据...");
				}				
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
