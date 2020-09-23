package com.agree.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

public class ClientSocketUtil {
	private static final Logger logger = LoggerFactory.getLogger(ClientSocketUtil.class);
	private static final int MES_HEAD_ONE = 8;
	
	/**
	 * 发送报文
	 * 
	 * */
	public static String sendMsg(String ip,Integer port,JSONObject content){  
        	Socket socket = null;
        	try {
        		//创建一个流套接字并将其连接到指定主机上的指定端口号
	        	socket = new Socket(ip,port);
	        	socket.setSoTimeout(5*1000);
	        	logger.info("连接已经建立");     
	        	//将json转化为String类型  
	        	String conToString = content.toString();
	        	//将String转化为byte[]
	        	byte[] bytes = conToString.getBytes("UTF-8");
	        	//发送报文长度
	        	String messageLen = StringUtil.fillZeroLeft(""+bytes.length,ClientSocketUtil.MES_HEAD_ONE);
	        	OutputStream os = socket.getOutputStream();
	        	//发送报文内容
	        	String messageCon=messageLen + conToString;
	        	logger.info("发送报文内容：["+messageCon+"]");
	        	os.write(messageCon.getBytes("UTF-8"));
	        	os.flush();	
	        	logger.info("传输数据完毕");	        	  		            
        	} catch (Exception e) {
        		logger.debug("客户端异常:" + e.getMessage()); 
        	} finally {
        		if (socket != null) {
        			try {
						socket.close();
					} catch (IOException e) {
						socket = null; 
						logger.debug("客户端 finally 异常:" + e.getMessage()); 
					}
        		}
        	}
		 return "SUCCESS";	
	}

	
}
