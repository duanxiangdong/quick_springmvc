package com.duan.sysmodule.util;

import java.math.BigDecimal;

/**
 * 字符串处理工具类
 * @author long
 */
public final class StringUtils {
	
	private StringUtils(){}
	
	/**
	 * 判断字符串是否为空
	 * <p>如果字符串为null或'',则返回true</p>
	 * @param str 待判断的字符串
	 * @return
	 */
	public final static boolean isNull(String str){
		return (null == str || "".equals(str.trim()))? true : false;
	}
	
	/**
	 * 判断是否为Long类型
	 * @param str  待验证的字符串
	 * @return
	 */
	public final static boolean isLong(String str){
		if(isNull(str)){
			return false;
		}else{
			try{
				Long.parseLong(str);
				return true;
			}catch(NumberFormatException e){
				return false;
			}
		}
	}

	/**
	 * 判断是否为数字
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isNumber(String str){
		try{
			new Double(str);
			return true;
		}catch (Exception e){
			return false;
		}
	}
	
	/**
	 * 判断string是否null
	 * <p>如果string为null时，则返回''</p>
	 * @param str
	 * @return
	 */
	@Deprecated
	public final static String stringVal(String str){
		if(null == str || "".equals(str.trim())){
			return "";
		}else{
			return str;
		}
	}
	
	/**
	 * 当字符串str为null或''时返回''
	 * @param str
	 * @return
	 */
	public final static String strVal(String str){
		return isNull(str) ? "" : str;
	}
	
	/**
	 * 替换指定的最后一个字符串
	 * @param str    原始字符串
	 * @param regex  被替换字符串
	 * @param newStr 新的字符串
	 * @return
	 */
	public final static String replaceLast(String str,String regex,String newStr){
		if(isNull(str)){
			return null;
		}else{
			int index = str.lastIndexOf(regex);
			if(index == -1){//不存在regex
				return str;
			}else if(str.startsWith(regex)){//以regex开头
				return str.replaceFirst(regex, newStr);
			}else if(str.endsWith(regex)){//以regex结尾
				return str.substring(0,index) + newStr;
			}else{
				return str.substring(0,index) + newStr + str.substring(index +1);
			}
		}
	}
	
	/**
	 * 去掉所有空白字符
	 * <p>可以匹配空格、制表符(\t)、换页符(\f)、回车符(\r)和换行符(\n)其中任意一个</p>
	 * @param str
	 * @return
	 */
	public static final String trim(String str){
		if(isNull(str)){
			return null;
		}else{
			return str.replaceAll("\\s*", "");
		}
	}
	
	/**
	 * 屏蔽手机号码
	 * @param mobile
	 * @return
	 */
	public static String hideMobile(String mobile){
		if(null == mobile || "".equals(mobile.trim())){
			return "";
		}else{
			return mobile.replaceAll("(?<=\\G.{3})(\\d{1})(\\d{3})", "****");
		}
	}
	
	/**
	 * 屏蔽身份证号码
	 * @param idCard
	 * @return
	 */
	public static String hideIdCard(String idCard){
		if(null == idCard || "".equals(idCard.trim())){
			return "";
		}else{
			return idCard.replaceAll("(?<=\\G.{4})(\\d{1})(\\d{11})", "************");
		}
	}
	
	/**
	 * 屏蔽银行卡号
	 * @param bankCard
	 * @return
	 */
	public static String hideBankCard(String bankCard){
		if(null == bankCard || "".equals(bankCard.trim())){
			return "";
		}else{
			int len = bankCard.length();
			int index = len - 10;//开头6位，结尾4位
			if(index <= 0){
				return bankCard;
			}else{
				final String code = "***************";
				return bankCard.substring(0,6) + code.substring(0,index) + bankCard.substring(len - 4);
			}
		}
	}
	
	/**
	 * 获取银行卡后四位
	 * @param bankCard  银行卡号
	 * @return
	 */
	public static String simpleBankCard(String bankCard){
		if(isNull(bankCard)){
			return "";
		}else{
			return bankCard.substring(bankCard.length() - 4);
		}
	}
	
	/**
	 * 大于(BigDecimal类型)
	 * <p>参数必须为数字类型</p>
	 * @param numo
	 * @param numt
	 * @return
	 */
	public static final boolean gt(String numo,String numt){
		return equals(numo, numt) == 1 ? true : false;
	}
	
	/**
	 * 大于等于(BigDecimal类型)
	 * <p>参数必须为数字类型</p>
	 * @param numom
	 * @param numt
	 * @return
	 */
	public static final boolean gte(String numo,String numt){
		return equals(numo, numt) != -1 ? true : false;
	}
	
	/**
	 * 小于(BigDecimal类型)
	 * <p>参数必须为数字类型</p>
	 * @param numo
	 * @param numt
	 * @return
	 */
	public static final boolean lt(String numo,String numt){
		return equals(numo, numt) == -1 ? true : false;
	}
	
	/**
	 * 小于等于(BigDecimal类型)
	 * <p>参数必须为数字类型</p>
	 * @param numo
	 * @param numt
	 * @return
	 */
	public static final boolean lte(String numo,String numt){
		return equals(numo, numt) != 1 ? true : false;
	}
	
	/**
	 * 等于(BigDecimal类型)
	 * <p>参数必须为数字类型</p>
	 * @param numo
	 * @param numt
	 * @return
	 */
	public static final boolean eq(String numo,String numt){
		return equals(numo, numt) == 0 ? true : false;
	}
	
	/**
	 * 比较二个数字大小(BigDecimal)
	 * <p>参数必须为数字类型</p>
	 * @param numo
	 * @param numt
	 * @return
	 */
	private static final int equals(String numo,String numt){
		BigDecimal num = new BigDecimal(numo);
		BigDecimal item = new BigDecimal(numt);
		return num.compareTo(item);
	}
}