package com.athudong.video.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

	/**
	 * 格式化long型的日期，将其转换成字符串，（例：今天11：23， 2013年1月3日 22：30等， 今天，昨天，前天，今年，去年）
	 */
	public static String getDateStr(long targetTime) {
		String dateStr = "";

		SimpleDateFormat timeDf = new SimpleDateFormat("HH:mm");
		SimpleDateFormat dayDf = new SimpleDateFormat("yyyy年MM月dd日");

		Date targetDate = new Date(targetTime);

		String targetDayStr = dayDf.format(targetDate);

		// 今天
		Date today = new Date(System.currentTimeMillis());
		// 判断目标日期是否是今天
		if (dayDf.format(today).equals(targetDayStr)) {
			dateStr = "今天" + timeDf.format(targetDate);
		} else {
			// 昨天
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.DATE, -1);
			Date yesterday = ca.getTime();

			// 判断目标日期是否是昨天
			if (dayDf.format(yesterday).equals(targetDayStr)) {
				dateStr = "昨天" + timeDf.format(targetDate);
			} else {
				// 前天
				ca.add(Calendar.DATE, -1);
				Date beforeYesDay = ca.getTime();
				// 判断目标日期是否是前天
				if (dayDf.format(beforeYesDay).equals(targetDayStr)) {
					dateStr = "前天" + timeDf.format(targetDate);
				} else {

					SimpleDateFormat yearDf = new SimpleDateFormat("yyyy年");

					String currentYearStr = yearDf.format(today);
					String targetYearStr = yearDf.format(targetDate);

					// 判断目标日期是否是今年
					if (currentYearStr.equals(targetYearStr)) {
						dateStr = targetDayStr.replace(currentYearStr, "") + " " + timeDf.format(targetDate);
					} else {
						dateStr = targetDayStr + " " + timeDf.format(targetDate);
					}

				}
			}
		}

		return dateStr;
	}

	/**
	 * 从日期中获取星座
	 */
	public static String getStarSignFromDate(int month, int day){
		if(month<0|| month>12){
			month=1;
		}
		if(day<0 || day>31){
			day = 1;
		}
		String sign = "";
		
		if(month==1){
			if(day<=19){
				sign = "水瓶";
			}else{
				sign = "双鱼";
			}
		}else if(month==2){
			if(day<=18){
				sign = "双鱼";
			}else{
				sign = "白羊";
			}
		}else if(month==3){
			if(day<=20){
				sign = "白羊";
			}else{
				sign = "金牛";
			}
		}else if(month==4){
			if(day<=19){
				sign = "金牛";
			}else{
				sign = "双子";
			}
		}else if(month==5){
			if(day<=20){
				sign = "双子";
			}else{
				sign = "巨蟹";
			}
		}else if(month==6){
			if(day<=21){
				sign = "巨蟹";
			}else{
				sign = "狮子";
			}
		}else if(month==7){
			if(day<=22){
				sign = "狮子";
			}else{
				sign = "处女";
			}
		}else if(month==8){
			if(day<=22){
				sign = "处女";
			}else{
				sign = "天枰";
			}
		}else if(month==9){
			if(day<=22){
				sign = "天枰";
			}else{
				sign = "天蝎";
			}
		}else if(month==10){
			if(day<=23){
				sign = "天蝎";
			}else{
				sign = "射手";
			}
		}else if(month==11){
			if(day<=22){
				sign = "射手";
			}else{
				sign = "摩羯";
			}
		}else if(month==12){
			if(day<=21){
				sign = "摩羯";
			}else{
				sign = "水瓶";
			}
		}
		return sign;

	}
	
}
