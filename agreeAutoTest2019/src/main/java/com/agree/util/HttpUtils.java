package com.agree.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：httpclient</B><BR>
 */
public class HttpUtils {

	private final static Logger log = LogManager.getLogger(HttpUtils.class);
	private final static String BASE_URL; 
	
	
	static {		
		BASE_URL = CommunicationConfig.getBaseurl();
	}
	
	
	
	public static JSONObject getJsonObj(String path) {
		String content = getString(path);
		if(content!=null) {
			return JSONObject.fromObject(content);
		}
		return null;
	}
	
	public static String getString(String path) {
		String url = BASE_URL+path;
		
		HttpGet req = new HttpGet(url);
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		CloseableHttpResponse response = null;
		
		try {
			response = httpClient.execute(req);
			HttpEntity respEntity = response.getEntity();
			log.info(url+"响应状态为: [ " + response.getStatusLine()+"]");
			
			if(response.getStatusLine().getStatusCode()==200) {
				if(respEntity !=null) {
					try {
						String content = EntityUtils.toString(respEntity);
						log.info("内容为: "+ content);
						return content;
					}catch(Exception e) {
						log.error("格式化json出错...");
						e.printStackTrace();
					}
				}else {
					log.info("响应体中没有包含任何数据...");
				}				
			}
		} catch (IOException e) {
			log.debug("http请求出错...");
			e.printStackTrace();
		}finally {
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
	 * <B>概要说明：get请求</B><BR>
	 */
	public static JSONArray get(String path){
		String url = BASE_URL+path;
		
		HttpGet req = new HttpGet(url);
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		CloseableHttpResponse response = null;
		
		try {
			response = httpClient.execute(req);
			HttpEntity respEntity = response.getEntity();
			log.info(url+"响应状态为: [ " + response.getStatusLine()+"]");
			
			if(response.getStatusLine().getStatusCode()==200) {
				if(respEntity !=null) {
					try {
						String content = EntityUtils.toString(respEntity);
						log.info("内容为: "+ content);
						return JSONArray.fromObject(content);
					}catch(Exception e) {
						log.error("格式化json出错...");
						e.printStackTrace();
					}
				}else {
					log.info("响应体中没有包含任何数据...");
				}				
			}
		} catch (IOException e) {
			log.debug("http请求出错...");
			e.printStackTrace();
		}finally {
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
	 * <B>概要说明：检测服务端是否可用</B><BR>
	 */
	public static boolean getAlive(String path){
		String url = BASE_URL+path;
		
		HttpGet req = new HttpGet(url);
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		CloseableHttpResponse response = null;
		
		try {
			response = httpClient.execute(req);
			log.info(url+"响应状态为: [ " + response.getStatusLine()+"]");			
			if(response.getStatusLine().getStatusCode()==200) {
				return true;			
			}
		} catch (IOException e) {
			log.debug("http请求出错...");
			log.error(e);
	//		e.printStackTrace();
			return false;
		}finally {
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
		return false;
	}
	
	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：get请求</B><BR>
	 * <B>参数说明：参数通过map存储 key:请求的键 value:上传的值</B><BR>
	 */
	public static JSONArray get(String path,Map<String,String> args){
		
		if(args==null || args.size()==0) {
			return get(path);
		}
		StringBuilder sb = new StringBuilder();
		
		args.forEach((key,value)->{
			try {
				sb.append(key+"="+URLEncoder.encode(value, CommunicationConfig.getEncoding()));
				sb.append("&");
			} catch (UnsupportedEncodingException e) {
				log.error("get请求拼接参数错误...");
				e.printStackTrace();
			}
		});
		path = "?"+StringUtils.subStr(sb.toString(), 0,-1);
		log.debug(path);
		return get(path);
	}
	
	
	
	public static JSONObject postJsonObj(String path, JSONObject obj) {
		String content = postString(path,obj);
		if(content!=null) {
			return JSONObject.fromObject(content);
		}
		return null;
	}
	
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
		String url = BASE_URL+path;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
 
		// 创建Post请求
		HttpPost req = new HttpPost(url);
		StringEntity entity = new StringEntity(obj.toString(), CommunicationConfig.getEncoding());
		req.setEntity(entity);
		req.setHeader("Content-Type", "application/json;charset="+CommunicationConfig.getEncoding());
		
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
	 * <B>概要说明：post请求 没有参数,或者参数凭借在path中</B><BR>
	 * @param path
	 * @return
	 */
	public static JSONArray post(String path) {
		String url = BASE_URL+path;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
 
		// 创建Post请求
		HttpPost req = new HttpPost(url);
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
						log.info("内容为: "+ content);
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
	
	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：post请求 参数拼接在url中</B><BR>
	 * <B>参数说明：参数通过map存储 key:请求的键 value:上传的值</B><BR>
	 * @param path
	 * @param map
	 * @return
	 */
	public static JSONArray post(Map<String,String> args,String path) {
		if(args==null || args.size()==0) {
			return post(path);
		}
		StringBuilder sb = new StringBuilder();
		
		args.forEach((key,value)->{
			try {
				sb.append(key+"="+URLEncoder.encode(value, CommunicationConfig.getEncoding()));
				sb.append("&");
			} catch (UnsupportedEncodingException e) {
				log.error("post请求拼接参数错误...");
				e.printStackTrace();
			}
		});
		path = "?"+StringUtils.subStr(sb.toString(), 0,-1);
		log.debug(path);
		return post(path);
	}
	
	
	
	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：post对象参数</B><BR>
	 *  <B>参数说明：参数通过jsonArray形式传入</B><BR>
	 * @param path
	 * @param obj
	 * @return
	 */
	public static JSONArray post(String path,JSONArray obj) {
		if(obj==null) {
			return post(path);
		}
		String url = BASE_URL+path;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
 
		// 创建Post请求
		HttpPost req = new HttpPost(url);
		StringEntity entity = new StringEntity(obj.toString(), CommunicationConfig.getEncoding());
		req.setEntity(entity);
		req.setHeader("Content-Type", "application/json;charset="+CommunicationConfig.getEncoding());
		
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
		if(obj==null) {
			return post(path);
		}
		String url = BASE_URL+path;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
 
		// 创建Post请求
		HttpPost req = new HttpPost(url);
		StringEntity entity = new StringEntity(obj.toString(), CommunicationConfig.getEncoding());
		req.setEntity(entity);
		req.setHeader("Content-Type", "application/json;charset="+CommunicationConfig.getEncoding());
		
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
	
	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：post对象参数</B><BR>
	 *  <B>参数说明：参数通过url 和 jsonObj 两种形式传入</B><BR>
	 * @param path
	 * @param obj
	 * @return
	 */
	public static JSONArray post(String path,Map<String,String> args,JSONObject obj) {
		if(args==null || args.size()==0) {
			return post(path,obj);
		}
		StringBuilder sb = new StringBuilder();
		
		args.forEach((key,value)->{
			try {
				sb.append(key+"="+URLEncoder.encode(value, CommunicationConfig.getEncoding()));
				sb.append("&");
			} catch (UnsupportedEncodingException e) {
				log.error("post请求拼接参数错误...");
				e.printStackTrace();
			}
		});
		path = "?"+StringUtils.subStr(sb.toString(), 0,-1);
		log.debug(path);
		return post(path,obj);
	}
}
