package com.baizhi.util;

import java.util.Random;

/**
 * Salt的工具类
 * @author xmc
 *
 */
public class SaltUtil {

	/**
	 * 生成随机盐的方法
	 */
	public static String getSalt(int n){
		char[] code="1234567890ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz".toCharArray();
		//创建random实例，生成伪随机数流
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		
		//n=random.nextInt(code.length)--随机生成一个int类型的数，不超出code的下标范围。
		//x=code[n] 取出下标为随机产生的的数组中的元素x
		//sb.append(x) 将从code中取出的数拼接起来
		for(int i=0;i<n;i++){
			sb.append(code[random.nextInt(code.length)]);
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
/*	public static void main(String[] args) {
			
			String salt = getSalt(8);
			//System.out.println(salt);
		}
	*/
}
