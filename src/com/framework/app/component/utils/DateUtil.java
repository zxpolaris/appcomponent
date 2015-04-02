package com.framework.app.component.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期时间格式化工具
 * 
 * @ClassName: DateUtil.java
 * 
 * @author Xun.Zhang
 * 
 * @date 2015-1-14 上午9:26:20
 */
public class DateUtil {

	/**
	 * 默认日期格式
	 */
	public static final String DEFAULT_FORMAT = "yyyy.MM.dd HH:mm";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	public static String getDefaultFormatDate(long unixTimestamp) {
		Date date = new Date(unixTimestamp * 1000);
		SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_FORMAT, Locale.getDefault());
		return formatter.format(date);
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回<br>
	 *         字符串格式</br> yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDateLong() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS, Locale.getDefault());
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 以友好的方式显示时间
	 * 
	 * @param sdate
	 *            yyyy-MM-dd HH：mm:ss
	 * @return
	 */
	public static String friendlyTime(String sdate) {
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD, Locale.US);
		Date time = strToDateLong(sdate);
		if (time == null) {
			return "Unknown";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		// 判断是否是同一天
		String curDate = sdf.format(cal.getTime());
		String paramDate = sdf.format(time);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0) {
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
			} else {
				ftime = hour + "小时前";
			}
			return ftime;
		}

		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0) {
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
			} else {
				ftime = hour + "小时前";
			}
		} else if (days == 1) {
			ftime = "昨天";
		} else if (days == 2) {
			ftime = "前天";
		} else if (days > 2 && days <= 10) {
			ftime = days + "天前";
		} else if (days > 10) {
			ftime = sdf.format(time);
		}
		return ftime;
	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 *            时间字符串 yyyyMMddHHmmss
	 * @return Date
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS, Locale.getDefault());
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

}