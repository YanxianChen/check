package com.check.util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//将字符串转换为指定格式的sql.Time类型的时间
public class StringToTime {

	public static Time Convert(String time) throws ParseException {
		
		 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	        Time ConvertedTime=new Time(sdf.parse(time).getTime());
	    
		return ConvertedTime;
	}
}
