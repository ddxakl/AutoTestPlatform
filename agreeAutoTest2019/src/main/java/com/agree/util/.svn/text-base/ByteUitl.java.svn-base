package com.agree.util;

/**
 * 
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：byte数据处理类</B><BR>
 * @author（admin）
 * @since 2019年6月28日
 */
public class ByteUitl {

	public static int byte2int(byte[] b,int start,int len) {
		int num = 0;
		int end = start+len;
		for (int i=start;i<end;i++) {
			int n = ((int)b[i]) & 0xff;
			n <<= (--len) * 8;
			num =n+num;
		}
		return num;
	}
	
	public static byte[] int2byte(int value,int len) {
		
		byte[] bt = new byte[len];
		for(int i=0;i<len;i++) {
			bt[len-i-1] = (byte)((value >> 8*i) & 0xff);
		}
		return bt;
	}
	
	
	public static String byte2String(byte[] b,int start,int len) {
		
		return new String(b,start,len);
	}
}
