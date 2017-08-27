package com.check.util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//将字符串转换为指定格式的sql.Time类型的时间
public class ConvertDateUtil {

	public static String Convert(Date date) throws ParseException {
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 return sdf.format(date);
	}
}
