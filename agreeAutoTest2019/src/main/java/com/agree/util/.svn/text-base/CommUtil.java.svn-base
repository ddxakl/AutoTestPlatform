package com.agree.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CommUtil {
	
	static String format="yyyy-MM-dd hh:mm:ss";
	
	/**
	 * 根据日期生成有序ID  YYYYMMDDHHMISS
	 * @return String
	 * */
	public synchronized static String getUniqueID() {
//		return DateUtil.formatDate2Str(new Date(), "yyyyMMddHHmmssSSS");
		return UniqueUtil.getRcid(null);
	}
	public static void main(String[] args) {
		getUniqueID();
		
	}
	
	/**
	 * 返回既定格式date
	 * @param dateStr
	 * @return
	 */
	public static Date getDateByStr(String dateStr) {
		Date parse=null;
		SimpleDateFormat dateFormat= new SimpleDateFormat(format);
		try {
			parse = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return parse;
		
	}
	
	public static ArrayList<String> testecpectResult(String str) {
		ArrayList<String>  word=new ArrayList<String>();
		int m=0,n=0;
		int count=0;
		for(int i=0;i<str.length();i++){
			if(str.charAt(i)=='{'){
				if(count==0){
					m=i;
				}
				count++;
			}
			if(str.charAt(i)=='}'){
				count--;
				if(count==0){
					n=i;
					word.add(str.substring(m, n+1));
				}
			}
			
		}
		return word;
	}
	
	

}
