package com.nineoldandroids.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringUtil {

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

}
