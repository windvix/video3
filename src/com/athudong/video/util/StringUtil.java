package com.athudong.video.util;

import java.util.Random;

/**
 * 字符串处理
 */
public class StringUtil {

	
	/**
	 * 生成指定长度的随机字符串
	 */
	public static String getRandomStr(int length) {
		/**
		 * 产生随机字符串
		 * */
		Random randGen = null;
		char[] numbersAndLetters = null;
		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			randGen = new Random();
			numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}	

	/**
	 * 判断字符串是否为空
	 */
	public static boolean isEmpty(String str) {
		boolean result = false;
		if (str == null) {
			result = true;
		} else if (str.equals("")) {
			result = true;
		}
		return result;
	}
	
}
