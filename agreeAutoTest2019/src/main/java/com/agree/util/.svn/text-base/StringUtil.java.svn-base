package com.agree.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class StringUtil extends StringUtils {

	public static final String COMMA = ",";
	public static final String DIV = "、";
	public static final String BLANK = " ";

	private StringUtil() {
	}


	/**
	 * 判断传入字符是否包含中文字符
	 * 
	 * @param str
	 *            待判断的字符
	 */
	public static boolean isChiness(String str) {
		String pattern = "[\u4e00-\u9fa5]+";
		Pattern p = Pattern.compile(pattern);
		Matcher result = p.matcher(str);
		return result.find();
	}

	/**
	 * 判断给定字符是否为数字
	 * 
	 * @param str
	 *            待判断的字符
	 * @return boolean 是数字返回true,否则返回false
	 */
	public static boolean isNumLegal(String str) throws UnsupportedEncodingException {
		if (str == null) {
			return false;
		}

		for (int i = 0; i < str.getBytes("GB2312").length; i++) {
			char ch = str.charAt(i);
			if (ch < '0' || ch > '9')
				return false;
		}
		return true;
	}

	/**
	 * 判断给定字符是否为日期格式（必须符合yyyy-MM-dd格式)
	 * 
	 * @param str
	 *            待判断的字符
	 * @return boolean 是日期格式返回true,否则返回false
	 */
	public static boolean isDateLegal(String dateStr) throws UnsupportedEncodingException {
		if (dateStr == null) {
			return false;
		}
		if (dateStr.length() != 10) {
			return false;
		}

		String strArr[] = dateStr.split("-");
		if (strArr.length != 3) {
			return false;
		}
		for (int i = 0; i < strArr.length; i++) {
			if (!isNumLegal(strArr[i])) {
				return false;
			}
		}
		return true;
	}



	/**
	 * 将一个字符串数组拼成一个逗号间隔的字符串，例如：
	 * <p>
	 * <code>
	 * String [] arg = {"1", "2", "3"};
	 * <p>
	 * String test = StringUtilTools.getStringFromArray(arg);
	 * <p>
	 * test = "1,2,3";
	 * </code>
	 * 
	 * @param str
	 * @return
	 */
	public static String getStringFromArray(String[] str) {
		StringBuffer temp = new StringBuffer();
		if (str != null) {
			for (int i = 0; i < str.length; i++) {
				if (i == 0) {
					temp.append(str[i]);
				} else {
					temp.append(COMMA + str[i]);
				}
			}
		}
		return temp.toString();
	}

	/**
	 * 将一个字符串数据拼成一个逗号间隔的字符串数组，例如：
	 * <p>
	 * <code>
	 * String　test = "1,2,3";
	 * <p>
	 * String []arg = StringUtilTools.getArrayFromString(test, ",");
	 * <p>
	 * 
	 * arg = {"1", "2", "3"};
	 * </code>
	 * 
	 * @param str
	 *            要分割的字符串
	 * @param order
	 *            分割规则采用正则表达式
	 * 
	 * @return
	 */
	public static String[] getArrayFromString(String str, String order) {
		String[] temp = null;
		if (str != null) {
			temp = str.split(order);
		}
		return temp;
	}

	/**
	 * 将一个字符串数据拼成一个中文顿号间隔的字符串数组，例如：
	 * 
	 * @param str
	 *            要分割的字符串
	 * @return
	 */
	public static String[] getArrayFromString(String str) {
		return getArrayFromString(str, DIV);
	}


	/**
	 * 将整数转换为中文小写字符串，各个数字依次转换， 比如整数102将被转换为"一○二"
	 * 
	 * @param number
	 *            整数
	 * @return 转换后的汉字小写字符串
	 */
	public static String getStringNumber(int number) {
		// 中文数字字符数组
		String[] chineseNumber = new String[] { "○", "一", "二", "三", "四", "五",
				"六", "七", "八", "九" };
		if (number < 0) {
			return "负" + getStringNumber(-number);
		} else if (number < 10) {
			return chineseNumber[number];
		} else {
			return getStringNumber(number / 10) + getStringNumber(number % 10);
		}
	}

	/**
	 * 将整数转换为中文的整数字符串，按汉语习惯的称呼各个数字依次转换， 比如整数20将被转换为"二十"
	 * 
	 * @param number
	 *            整数(暂不支持绝对值大于99的转换)
	 * @return 转换后的中文的整数字符串
	 */
	public static String getChineseNumber(int number) {
		// 中文数字字符数组
		String[] chineseNumber = new String[] { "零", "一", "二", "三", "四", "五",
				"六", "七", "八", "九" };
		// 中文单位数组
		String[] chineseUnit = new String[] { "", "十", "百", "千", "万", "十", "百",
				"千", "亿", "十", "百", "千" };

		// String sNumber = "";

		if (number < 0) {
			// 负几
			return "负" + getChineseNumber(-number);
		} else if (number < 10) {
			// 几
			return chineseNumber[number];
		} else if (number < 20) {
			if (number % 10 == 0) {
				// "十"
				return chineseUnit[1];
			} else {
				// 十几
				return chineseUnit[1] + chineseNumber[number % 10];
			}
		} else if (number < 100) {
			if (number % 10 == 0) {
				// 几十
				return chineseNumber[number / 10] + chineseUnit[1];
			} else {
				// 几十几
				return chineseNumber[number / 10] + chineseUnit[1]
						+ chineseNumber[number % 10];
			}
		} else {
			throw new java.lang.IllegalArgumentException("暂不支持绝对值大于99的转换");
		}
	}

	/**
	 * 随机生成指定位数且不重复的字符串.去除了部分容易混淆的字符，如1和l，o和0等，
	 * 
	 * 随机范围1-9 a-z A-Z
	 * 
	 * @param length
	 *            指定字符串长度
	 * @return 返回指定位数且不重复的字符串
	 */
	public static String getRandomString(int length) {
		StringBuffer bu = new StringBuffer();
		String[] arr = { "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c",
				"d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "p", "q",
				"r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
				"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		Random random = new Random();
		while (bu.length() < length) {
			String temp = arr[random.nextInt(57)];
			if (bu.indexOf(temp) == -1)
				bu.append(temp);
		}
		return bu.toString();
	}

	/**
	 * 获取某个范围内的随机整数
	 * 
	 * @param sek
	 *            随机种子
	 * @param start
	 *            最小范围
	 * @param max
	 *            最大范围
	 * @return 整数
	 */
	public static int getRandomInt(int sek, int min, int max) {

		Random random = new Random();

		int temp = 0;

		do {
			temp = random.nextInt(sek);
		} while (temp < min || temp > max);

		return temp;
	}
	
	/**
	 * 判断字符串是否为空（NULL 或者 空字符串s）
	 * 
	 * @param obj
	 * @return
	 * @comment
	 */
	public static boolean isStringEmpty(String obj) {
		if (obj == null) {
			return true;
		} else {
			if (obj.trim().length() == 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	* 方法说明: 截取报文标识号中的报文类型
	* @param msgId 报文标识号
	* @author  
	* @return
	* @date 2009-5-15 上午11:38:57
	*/
	public static String subMsgType(String msgId){
		String msgType = "";
		if(!isStringEmpty(msgId.trim())){
			msgType = msgId.substring(20, 23);
		}
		return msgType;
	}
	
	/**
	 * 将字符串 按照 给定的分割符 分隔成 String 类型的数组返回
	 * @param sourceStr		需要分割的字符串
	 * @param spliterStr	分隔符
	 * @return
	 */
	public static String[] splitArray(String sourceStr,String spliterStr){
		String[] tmp=null;
		if(sourceStr!=null && !sourceStr.trim().equalsIgnoreCase("")){
			 tmp= sourceStr.split(spliterStr); 
		}
		return tmp;
	}
	
	/**
	 * 将字符串数组按照给定的分割符组成字符串返回
	 * @param array
	 * @param spliter
	 * @return
	 */
	public static String arrayToString(String[] array,String spliter){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			if(array[i].trim().length()>0){
				sb.append(array[i]);
				if(i<array.length-1)
					sb.append(spliter);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 将字符串数组按照给定的分割符组成字符串返回
	 * @param array
	 * @param spliter
	 * @return
	 */
	public static String arrayObjectToString(Object[] array,String spliter){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			if(((String)array[i]).trim().length()>0){
				sb.append(array[i]);
				if(i<array.length-1)
					sb.append(spliter);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 不支持中文字符串
	 * 将字符串oldStr扩充到长度toLength,如果超过长度则去除左边超过的部分,否则则在左边补上fillChar字符
	 * @param oldStr 	需要补充的原字符串
	 * @param toLength 	指定扩充到的长度
	 * @param fillChar	需要补上的字符
	 * @return  返回的处理后字串
	 * 
	 */
	public static String fillCharLeft(String oldStr,int toLength, char fillChar) {
		int length=0;
		try {
			length = oldStr.getBytes("GB2312").length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (length == toLength) {
			return oldStr;
		} else if (length < toLength) {
			for (int i = length; i < toLength; i++) {
				oldStr = fillChar + oldStr;
			}
			return oldStr;
		} else {
			return oldStr.substring(length - toLength);
		}
	}
	
	/**
	 * 不支持中文字符串
	 * 将字符串oldStr扩充到长度toLength，如果超过长度则去除左边超过的部分，否则右补空格
	 * @param oldStr 	需要补充的原字符串
	 * @param toLength  指定扩充到的长度
	 * @return 返回的处理后字串
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	public static String fillBlankRight(String oldStr,int toLength) throws UnsupportedEncodingException {
		return fillCharRight(oldStr,toLength, ' ');
	}
	
	/**
	 * 不支持中文字符串
	 * 将字符串oldStr扩充到长度toLength,如果超过长度则去除左边超过的部分,否则则在后面补上fillChar字符
	 * @param oldStr 	需要补充的原字符串
	 * @param toLength  指定扩充到的长度
	 * @param fillChar  补上的字符
	 * @return 返回的处理后字串
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	public static String fillCharRight(String oldStr,int toLength, char fillChar) throws UnsupportedEncodingException {
		int length = oldStr.getBytes("GB2312").length;
		if (length == toLength) {
			return oldStr;
		} else if (length < toLength) {
			for (int i = length; i < toLength; i++) {
				oldStr = oldStr + fillChar;
			}
			return oldStr;
		} else {
			return oldStr.substring(length - toLength);
		}
	}
	
	/**
	 * 不支持中文字符串
	 * 将字符串oldStr扩充到长度toLength，如果超过长度则去除左边超过的部分，否则左补0
	 * @param oldStr 	需要补充的原字符串
	 * @param toLength  指定扩充到的长度
	 * @return 返回的处理后字串
	 * 
	 */
	public static String fillZeroLeft(String oldStr,int toLength) {
		return fillCharLeft(oldStr,toLength, '0');
	}
	
	/**
	 * 如果字符串不为null，那么就在两面加上''， 如果字符串为""，那么就把它设为null，该方法一般用于书写Sql语句
	 * 
	 * @param originalStr  传入的字串
	 *            
	 * @return 返回的字串
	 */
	public static String settingSingleQuotationMark(String originalStr) {
		if (originalStr != null) {
			if (!originalStr.equals("")) {
				originalStr = "'" + originalStr + "'";
			} else {
				originalStr = "NULL";
			}
		}
		return originalStr;
	}
	
	 /**
	 * 按照字节数截取字符串
	 */
   public static String getStringByBytesRange(String str, int start, int end) {
    	
        byte[] buffer = new byte[end - start];
        try {
            System.arraycopy(str.getBytes("GB2312"), start, buffer, 0, end - start);
            return new String(buffer, "GB2312");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    } 
   /**
    * 去掉数字前面的0
    */
   public  static String getStrPassPriZone(String str){
    	String nStr="";
    	if(str==null)
    		nStr="";
    	else{
           double dd=new Double(str).doubleValue();
           nStr=String.valueOf(dd);
    	}
    	return nStr;
    }
 
  public static String trim(String str){
	  str=str.trim();
	  return str.replaceAll("\r", "").replaceAll("\n", "");
	  
  }

  /**
	 * 将一个字符串数组拼成一个逗号间隔的字符串，例如：
	 * <p>
	 * <code>
	 * String [] arg = {"1", "2", "3"};
	 * <p>
	 * String test = StringUtilTools.getStringFromArray(arg);
	 * <p>
	 * test = "1,2,3";
	 * </code>
	 * 
	 * @param str
	 * @return
	 */
	public static String getStringFromArray(Object[] obj) {
		StringBuffer temp = new StringBuffer();
		if (obj != null) {
			for (int i = 0; i < obj.length; i++) {
				Object object = obj[i];
				if (i == 0) {
					if(object instanceof String){
						temp.append("'"+object+"'");
					}else{
						temp.append(object);
					}
					
				} else {
					if(object instanceof String){
						temp.append(COMMA +"'"+object+"'");
					}else{
						temp.append(COMMA + object);
					}
				}
			}
		}
		return temp.toString();
	}
	public static String cutMultibyte(String splitStr, int extract) throws UnsupportedEncodingException {
		   byte[] bytes = splitStr.getBytes("GB2312");
		   //截取字节数不小于原始字节数时直接返回原字符串。注意字节数一定要用 bytes.length，用字符串的 length() 方法返回的是字符数！
		   if (extract >= bytes.length) {
		    return splitStr;
		   }
		   else {
		    int result = 0;
		    int cutLength = 0;
		    //统计出在要截取的字节中有几个字节是负数
		    for (int i = 0; i < extract; i++) {
		     if (bytes[i] < 0) {
		      cutLength++;
		     }
		    }
		    //若负数字节数是偶数，即要截取的字节数中遇到刚好是若干完整的汉字，则截取字节数不变；否则负数字节数是奇数，表示遇到的不是完整汉字，截取字节数减 1。   
		    if (cutLength % 2 == 0) {
		     result = extract;   
		    }    
		    else {
		     result = extract - 1;
		    }
		    String substrx = new String(bytes, 0, result);
		    return substrx;
		   }  
		}

	public static String trimZero(String res) {
		boolean flag = true;
		while(flag) {
			if (res.endsWith("0")) {
				res = res.substring(0, res.length() - 1);
			} else {
				flag = false;
			}
		}
		
		return res;
	}
	
	/**
	 * 返回最大值
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */
	public static double  getMaxVlaue(double[] arr) {
		double max;
		max=arr[0];
		for(int i=0;i<arr.length;i++)
		{

			if(arr[i] > max)   // 判断最大值
				max=arr[i];
			
		}
		
		return max;
	}
	
	/**
	 * 返回平均值
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */
	public static double  getAvgVlaue(double[] arr) {
		    double sum = 0;
		    for (double value : arr) {
		        sum += value;
		    }
		    return sum/arr.length;
	}
}
