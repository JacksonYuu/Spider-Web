package com.xiandian.cloud.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String longToString(String dateFormat,Long millSec) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	    Date date= new Date(millSec);
	    return sdf.format(date);
	}
	public static String DateToString(String dateFormat,Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}
	
	public static Date stringToDate(String dateStr) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		return formatter.parse(dateStr);
	}
	
	public static String timeTostrHMS(Date date) {
		String strDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			strDate = format.format(date);
		}
		return strDate;
	}
	
	public static String timeTostr(Date date) {
		String strDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			strDate = format.format(date);
		}
		return strDate;
	}
	
	public static int beforeday(String myString) {
		String nowdate = timeTostr(new Date());
		// String myString = "2015-01-16";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		Date now = null;
		try {
			d = sdf.parse(myString);
			now = sdf.parse(nowdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d.compareTo(now);
	}
	
	public static String otherday(Date date,int other,String formatto) {
		
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(date);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, other);  //设置为前后几天
		date = calendar.getTime();   //得到前一天的时间
		
		String strDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat(formatto);
			strDate = format.format(date);
		}
		return strDate;
	}
    public static String differentDaysByMillisecond(Date date1)
    {	
    	Date date3=new Date();
    	int hour=(int) ((date3.getTime() - date1.getTime()) / (1000*3600));//小时
    	if(hour<24){
    		return hour+"小时前";
    	}else if((hour/24)<7){
    		return (hour/24)+"天前";
    	}else if((hour/24)>7&&(hour/24)<30){
    		return (hour/24/7)+"周前";
    	}else if((hour/24)>30&&(hour/24)<365){
    		return (hour/24/30)+"月前";
    	}else{
    		return (hour/24/365)+"年前";
    	}
    }
}
