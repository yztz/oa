package top.yztz.oa.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {
	
	public static String getFormatDateTime(Date date){//将日期转成字符串格式时间
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
		
	}
	
	public static String getFormatDateTime1(Date date){//将日期转成字符串格式时间
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return dateFormat.format(date);
		
	}

	public static Date getFormateDate(String time){//时间转成日期
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return dateFormat.parse(time);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	public static Date getPreviousDay(int previous){//获取前n天的日期
		
		Calendar ca = Calendar.getInstance();//
		ca.setTime(new Date()); //
		ca.add(Calendar.DATE, -previous);
		Date previousDay = ca.getTime(); //
		
		
		return previousDay;
		
	}
	
	public static Date getNextDay(int next){//获取后n天的日期
		
		Calendar ca = Calendar.getInstance(); 
		ca.setTime(new Date()); 
		ca.add(Calendar.DATE, + next);
		Date previousDay = ca.getTime(); 
		
		
		return previousDay;
		
	}
	
}
