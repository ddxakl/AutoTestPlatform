package com.agree.util;

/**
 * 截取字符串工具
 * @author xrp09
 *
 */
public class StringUtils {
    /**
     * 从头开始截取
     * 
     * @param str 字符串
     * @param end 结束位置
     * @return
     */
    public static String subStrStart(String str, int end){
        return subStr(str, 0, end);
    }
 
    /**
     * 从尾开始截取
     * 
     * @param str 字符串
     * @param start 开始位置
     * @return
     */
    public static String subStrEnd(String str, int start){
        return subStr(str, str.length()-start, str.length());
    }
 
    /**
     * 截取字符串 （支持正向、反向截取）<br/>
     * 
     * @param str 待截取的字符串
     * @param length 长度 ，>=0时，从头开始向后截取length长度的字符串；<0时，从尾开始向前截取length长度的字符串
     * @return 返回截取的字符串
     * @throws RuntimeException
     */
    public static String subStr(String str, int length) throws RuntimeException{
        if(str==null){
            throw new NullPointerException("字符串为null");
        }
        int len = str.length();
        if(len<Math.abs(length)){
            throw new StringIndexOutOfBoundsException("最大长度为"+len+"，索引超出范围为:"+(len-Math.abs(length)));
        }
        if(length>=0){
            return  subStr(str, 0,length);
        }else{
            return subStr(str, len-Math.abs(length), len);
        }
    }
 
 
    /**
     * 截取字符串 （支持正向、反向选择）<br/>
     * 
     * @param str  待截取的字符串
     * @param start 起始索引 ，>=0时，从start开始截取；<0时，从length-|start|开始截取
     * @param end 结束索引 ，>=0时，从end结束截取；<0时，从length-|end|结束截取
     * @return 返回截取的字符串
     * @throws RuntimeException
     */
    public static String subStr(String str, int start, int end) throws RuntimeException{
        if(str==null){
            throw new NullPointerException("");
        }
        int len = str.length();
        int s = 0;//记录起始索引
        int e = 0;//记录结尾索引
        if(len<Math.abs(start)){
            throw new StringIndexOutOfBoundsException("最大长度为"+len+"，索引超出范围为:"+(len-Math.abs(start)));
        }else if(start<0){
            s = len - Math.abs(start);
        }else if(start<0){
            s=0;
        }else{//>=0
            s = start;
        }
        if(len<Math.abs(end)){
            throw new StringIndexOutOfBoundsException("最大长度为"+len+"，索引超出范围为:"+(len-Math.abs(end)));
        }else if (end <0){
            e = len - Math.abs(end);
        }else if (end==0){
            e = len;
        }else{//>=0
            e = end;
        }
        if(e<s){
            throw new StringIndexOutOfBoundsException("截至索引小于起始索引:"+(e-s));
        }
 
        return str.substring(s, e);
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
	 * 不支持中文字符串
	 * 将字符串oldStr扩充到长度toLength,如果超过长度则去除左边超过的部分,否则则在左边补上fillChar字符
	 * @param oldStr 	需要补充的原字符串
	 * @param toLength 	指定扩充到的长度
	 * @param fillChar	需要补上的字符
	 * @return  返回的处理后字串
	 * 
	 */
	public static String fillCharLeft(String oldStr,int toLength, char fillChar) {
		int length = oldStr.getBytes().length;
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
    
}
