package com.worthy.ly.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	
	public static final String DEFAULT_DATE_TYPE = "yyyy-MM-dd HH:mm:ss";


	/**
	 * 格式化日期
	 * @param dateValue
	 * @return
     */
	public static String dateToString(Date dateValue) {
		return dateToString(dateValue,DEFAULT_DATE_TYPE);
	}

	/**

	 * @Title: dateToString
	 * @Description: 将时间转换为字符串
	 * @param dateValue 需要转换的日期
	 * @param strFormat 需要转换的日期格式 
	 * 					例如(yyyy/MM/dd HH:mm:ss, yyyy-MM-dd HH:mm:ss, HH:mm)
	 * @return String
	 */
	public static String dateToString(Date dateValue, String strFormat) {
		SimpleDateFormat clsFormat = new SimpleDateFormat(strFormat);
		return clsFormat.format(dateValue);
	}
	/**
	 * @Title: stringToDate
	 * @Description: 将字符串转换为date类型
	 * @param strValue
	 * @param dateformat 需要转换的日期格式   
	 * 					   例如：(yyyy/MM/dd HH:mm:ss, yyyy-MM-dd HH:mm:ss, HH:mm)
	 * @return Date
	 */
	public static Date stringToDate(String strValue, String dateformat) {
		if (StringUtils.isEmpty(strValue)) {
			return null;
		}
		SimpleDateFormat clsFormat = new SimpleDateFormat(dateformat);
		ParsePosition pos = new ParsePosition(0);
		return clsFormat.parse(strValue, pos);
	}
	
	public static Date stringToDate(String strValue) {
		if (StringUtils.isEmpty(strValue)) {
			return null;
		}
		SimpleDateFormat clsFormat = new SimpleDateFormat(DEFAULT_DATE_TYPE);
		ParsePosition pos = new ParsePosition(0);
		return clsFormat.parse(strValue, pos);
	}
	
	
	/**
	 * @Title: getSysOptDate
	 * @Description: 获得系统的当前时间
	 * @param strFormat 例如：需要转换的日期格式  (yyyy/MM/dd HH:mm:ss, yyyy-MM-dd HH:mm:ss, HH:mm)
	 * @return String 根据传入的数据类型返回日期字符串

	 */
	public static String getSysOptDate(String strFormat) {
		Calendar date = Calendar.getInstance();
		Date sysDate = date.getTime();
		String optDate=""; 
		if(StringUtils.isEmpty(strFormat))
			optDate = DateUtils.dateToString(sysDate, "yyyy-MM-dd HH:mm:ss");
		else
			optDate = DateUtils.dateToString(sysDate, strFormat);
		return optDate;
	}


	/**
	 * 
	 * @Title: formatDate
	 * @Description:对日期时间进行格式化去掉后面的时间信息
	 * @param date  要格式化的时间
	 * @throws ParseException 
	 */
	public static Date formatDate(Date date) throws ParseException{
		  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		  date=sdf.parse(sdf.format(date));  
		  return date;
	}
	
	/**
     * 格式化
     *
     * @param date
     * @param format
     * @return
     */
    public static String Format(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
    
    /**
     * 距离0点的秒数
     * @return
     */
    
    public static int getTodayRemainSeconds(){
    	try {
			 Date now = new Date();
			
			 Calendar calendar = new GregorianCalendar();
			 calendar.setTime(now);
			 calendar.add(Calendar.DATE,1);
			 Date tomorrow = calendar.getTime(); 
			 
			 String dateString = new SimpleDateFormat("yyyy-MM-dd").format(tomorrow);
			 Date tbegainDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString + " 00:00:00");
			 
			 int second = (int) ((tbegainDate.getTime() - now.getTime()) / 1000);
			 
			 return second;
			 
		} catch (ParseException e) {
		}
    	
    	return 0;
    }
    
    public static int dayForWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 将long型时间转成指定格式时间
     * @param format
     * @param time
     * @return
     */
	public static String parseLongDate(String format, Long time) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String result = dateFormat.format(time);
        return result;
	}
    
}
