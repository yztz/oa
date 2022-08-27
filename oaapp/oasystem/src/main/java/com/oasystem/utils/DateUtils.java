package com.oasystem.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {
	
	public static String getFormatDateTime(Date date){//
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
		
	}
	
	public static String getFormatDate(Date date){//
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
		
	}

	public static Date getFormateDate(String time){//
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return dateFormat.parse(time);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date getPreviousDay(int previous){//获取提前n天
		
		Calendar ca = Calendar.getInstance();//
		ca.setTime(new Date()); //
		ca.add(Calendar.DATE, -previous);
		Date previousDay = ca.getTime(); //
		
		
		return previousDay;
		
	}
}
