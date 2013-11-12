package com.athudong.video.util;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SuppressLint("SimpleDateFormat")
public class DateUtil {

	/**
	 * 获取今年
	 */
	public static int getCurrentYear() {
		SimpleDateFormat fm = new SimpleDateFormat("yyyy");
		String year = fm.format(new Date(System.currentTimeMillis()));
		return Integer.parseInt(year);
	}

	public static int getCurrentMonth() {
		SimpleDateFormat fm = new SimpleDateFormat("MM");
		String month = fm.format(new Date(System.currentTimeMillis()));
		return Integer.parseInt(month);
	}

	public static int getCurrentDay() {
		SimpleDateFormat fm = new SimpleDateFormat("dd");
		String day = fm.format(new Date(System.currentTimeMillis()));
		return Integer.parseInt(day);
	}

	public static int getCurrentDayOfWeekInMonth() {
		Calendar cal = Calendar.getInstance();
		int a = cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		return a;
	}

	public static int getDayofWeekInMonthYear(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static String getEnglishMonth(int month) {
		String result = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM");
			Date date = sdf.parse(month + "");
			sdf = new SimpleDateFormat("MMMMM", Locale.US);
			result = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static int getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 年
		cal.set(Calendar.YEAR, year);
		// 月，因为Calendar里的月是从0开始，所以要-1
		cal.set(Calendar.MONTH, month - 1);
		// 日，设为一号
		cal.set(Calendar.DATE, 1);
		// 月份加一，得到下个月的一号
		cal.add(Calendar.MONTH, 1);
		// 下一个月减一为本月最后一天
		cal.add(Calendar.DATE, -1);
		return cal.get(Calendar.DAY_OF_MONTH);// 获得月末是几号
	}
}
