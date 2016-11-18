package com.duan.sysmodule.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

/**
 * MD5加密
 * @author yhm
 */
public class MD5 {
	
	/**
	 * MD5加密
	 * @param plainText
	 * @return
	 */
	public static String md5(String plainText) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			//result = buf.toString(); //md5 32bit
			// result = buf.toString().substring(8, 24))); //md5 16bit
			result = buf.toString().substring(8, 24).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	} 
	
	/**
	 * 标准32位Md5加密
	 * @param plainText
	 * @return
	 */
	public static String encryption(String plainText) {
		if(null == plainText || "".equals(plainText)){
			return "d41d8cd98f00b204e9800998ecf8427e";
		}
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes("utf-8"));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            re_md5 = buf.toString();
        } catch (NoSuchAlgorithmException e) {
           return null;
        } catch (UnsupportedEncodingException e) {
        	return null;
		}
        return re_md5;
    }
	
	/**
	 * OA加密
	 * @param originalPwd
	 * @return
	 */
	public static String getEncryptionString(String originalPwd){
    	if(null==originalPwd || originalPwd.trim().isEmpty()){
    		return "";
    	}
    	StringBuffer returnString=new StringBuffer();
    	//最长拼接字符
    	int fullLength=30;
    	//默认数值，如果不忙30，用该数字填补
    	int tempIntValue=21;
    	//临时单个字符变量
    	char tempChar;
    	
        Double finalValue = 1d;
    	for(int i=0;i<fullLength;i++){
    		if(i>=originalPwd.length()){
    			tempIntValue=21;
        	}else{
        		tempChar=originalPwd.charAt(i);
        		tempIntValue=(int)tempChar;
        	}
    		//System.out.println("I="+i+";tempIntValue="+tempIntValue);
        	finalValue=finalValue*(30+tempIntValue*(i+1));
        }
    	//.NET统一格式
    	DecimalFormat netFormat = new DecimalFormat("#.##############E0");
    	netFormat.setRoundingMode(RoundingMode.HALF_UP);
    	String netValue=netFormat.format(finalValue);
    	//最终算术值ToChar
    	char [] finalString=new BigDecimal(netValue).toString().toCharArray();
    	//最后生成的字符串
    	StringBuffer totalString=new StringBuffer();
    	StringBuffer intString = new StringBuffer();
    	for(int j=0;j<finalString.length;j++){
    		intString=new StringBuffer();
    		for(int k=0;k<3;k++){
    			if(j+k>=finalString.length){
    				break;
    			}
    			tempChar=finalString[j+k];
	    		intString.append((int)tempChar);
    		}
    		if(!"".equals(intString.toString())){
    			totalString.append(Integer.toHexString(Integer.parseInt(intString.toString())));
    		}
    	}
    	for(int i=20;i<=totalString.length()-18;i=i+2){
    		returnString.append(totalString.substring(i-1, i));
    	}
    	return returnString.toString().toUpperCase();
    }
    
	/**
	 * 用户中心加密方式
	 * @param text
	 * @return
	 */
	public static String encode(String text) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(text.getBytes());
	        final byte[] digest = messageDigest.digest();
	        return getFormattedText(digest);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
    }
	
	private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static String getFormattedText(final byte[] bytes) {
        final StringBuilder buf = new StringBuilder(bytes.length * 2);
        for (int j = 0; j < bytes.length; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
}