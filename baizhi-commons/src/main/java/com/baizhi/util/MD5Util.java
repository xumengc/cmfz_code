package com.baizhi.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5Util 工具方法的封装
 * @author xmc
 *
 */
public class MD5Util {

	public static String getMd5Code(String password){
		try {
			//java.secutiry
			MessageDigest messageDigest = MessageDigest.getInstance("md5");
			//对password进行加密
			byte[] digest = messageDigest.digest(password.getBytes());
			StringBuilder sb=new StringBuilder();
			//byte转为16进制长度是32位字符串
			for (byte b : digest) { 
				int c = b & 0xff; 
				//System.out.println(c);
				if (c<16) {
					sb.append("0");		
				}
				sb.append(Integer.toHexString(c));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/*public static void main(String[] args) {
		String md5Code = getMd5Code("199566");
		System.out.println(md5Code);
		System.out.println(md5Code.length());
	}*/
}
