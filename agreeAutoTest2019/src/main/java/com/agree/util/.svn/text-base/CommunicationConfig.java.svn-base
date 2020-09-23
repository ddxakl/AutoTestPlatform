package com.agree.util;

public class CommunicationConfig extends Config{

	private CommunicationConfig() {}
	
	/** 获取socket服务端ip，默认 0.0.0.0 */
	public static String getIp() {
		return prop.getProperty("amtc.socket.ip", "0.0.0.0");
	}

	
	/** 获取socket服务端端口 ，默认 19991 */
	public static int getPort() {
		String ip = prop.getProperty("amtc.socket.port", "19991");
		return Integer.parseInt(ip.trim());
	}
	


	/** 获取socket服务端超时时间，单位毫秒，默认 10000 */
	public static int getTimeout() {
		return Integer.parseInt(prop.getProperty("amtc.socket.timeout", "10000").trim());
	}
	
	/** 获取socket服务端 报文头长度**/
	public static int getmsgLen() {
		return Integer.parseInt(prop.getProperty("amtc.socket.msglen", "8").trim());
	}
	

	/** 获取socket接收数据编码，默认 UTF-8 */
	public static String getEncoding() {
		return prop.getProperty("amtc.socket.encoding", "UTF-8");
	}
	
	/** 获取amt地址 */
	public static String getBaseurl() {
		return prop.getProperty("amtc.http.baseurl", "http://127.0.0.1:8080/amtc");
	}
	
	/** 线程池,核心线程  */
	public static int getcoreThread() {
		return Integer.parseInt(prop.getProperty("amtc.pool.coreThread", "2").trim());
	}

	
	/** 线程池,最大线程  */
	public static int getmaxThread() {
		return Integer.parseInt(prop.getProperty("amtc.pool.maxThread", "5").trim());
	}
	
	
	/** 线程池,队列大小  */
	public static int getqueuesize() {
		return Integer.parseInt(prop.getProperty("amtc.pool.queuesize", "1000").trim());
	}
	
	/** 线程池,线程空闲时间  */
	public static int getkeepAlive() {
		return Integer.parseInt(prop.getProperty("amtc.pool.keepalive", "20").trim());
	}
	
	/** 获取ip地址 */
	public static String getFtpIp() {
		return prop.getProperty("amtc.ftp.ip", "192.9.200.13");
	}
	
	/** port端口 */
	public static int getFtpPort() {
		return Integer.parseInt(prop.getProperty("amtc.ftp.port", "21").trim());
	}

	public static String getFtpUser() {
		return prop.getProperty("amtc.ftp.user", "agree");
	}
	
	public static String getFtpPasswd() {
		return prop.getProperty("amtc.ftp.password", "agree");
	}
	
	public static String getFtpEncode() {
		return prop.getProperty("amtc.ftp.encoding", "UTF-8");
	}
	
	public static String getFtpBasePath() {
		return prop.getProperty("amtc.ftp.basePath", "/home/base/ftp");
	}
}
