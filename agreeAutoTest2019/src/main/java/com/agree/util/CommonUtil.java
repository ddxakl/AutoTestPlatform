package com.agree.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.UUID;

/**
 * 
 * <B>系统名称：</B><BR>
 * <B>模块名称：共同工具类</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author admin（admin）
 * @since 2018年8月22日
 */
public class CommonUtil {
	/**
	 * 方法描述：判断一个字符串是否为null或空字符串（被trim后为空字符串的也算）。
	 * 
	 * @param str 需要判断的字符串
	 * @return false：不是空字符串，true：是空字符串
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 生成指定位数的随机整数
	 * 
	 * @param number 位数
	 * @return 随机整数
	 */
	public static int random(int number) {
		return (int) ((Math.random() * 9 + 1) * Math.pow(10, number - 1));
	}

	/**
	 * 获取UUID
	 * 
	 * @return UUID
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String getMD5String(String str) {
		String code = "";
		try {
			code = MD5.getInstance().getMD5String(str);
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return code;
	}

	public static String getBase64(byte[] src) {
		String str = Base64.getEncoder().encodeToString(src);
		return str;
	}

	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：通过时间戳以及用户名作为salt生成md5信息</B><BR>
	 * 
	 * @return
	 */
	public static String getmd5salt(String timestam, String salt) {
		try {
			String datastr = timestam + salt;
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(datastr.getBytes("UTF-8"));
			byte s[] = m.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < s.length; i++) {
				sb.append(Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6));
			}
			return sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
}
