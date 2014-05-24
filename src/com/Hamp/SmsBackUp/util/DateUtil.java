package com.Hamp.SmsBackUp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String dateUtil(long date) {
		Date d = new Date();
		d.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault());
		String str = sdf.format(d);
		return str;
	}
}
