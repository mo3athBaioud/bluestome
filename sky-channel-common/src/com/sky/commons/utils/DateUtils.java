package com.sky.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtils {

	/**
	 * ��ȡ����ڶ�����������
	 * @return
	 */
	public static int getNextDayInMillis(){
		Date date = new Date();
		return (int) ((86400000L-(date.getTime()-org.apache.commons.lang.time.DateUtils.truncate(date, Calendar.DAY_OF_MONTH).getTime())));
	}
	
	public static int getNextDayInSeconds(){
		Date date = new Date();
		return (int) ((86400000L-(date.getTime()-org.apache.commons.lang.time.DateUtils.truncate(date, Calendar.DAY_OF_MONTH).getTime()))/1000);
	}
	/**
	 * ��֤�ַ����Ƿ��ǺϷ�������format���͵��ַ���,���򷵻�true,���򷵻�false
	 * @param date
	 * @param format
	 * @return
	 */
 	public static boolean isValidDate(String date,String format) { 
 		boolean isValid = false;
 		if (StringUtils.isEmpty(date) || date.length() != format.length()) {
			isValid = false;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setLenient(false);
			try {
				Date dat = sdf.parse(date);
				String convert = sdf.format(dat);
				if (convert.equals(date)) {
					isValid = true;
				}
			} catch (Exception pe) {
				isValid = false;
			}
		}
		return isValid;   
	 } 
	public static void main(String args[]){
		System.out.println(getNextDayInMillis()/1000/60/60);
		System.out.println(getNextDayInSeconds()/60/60);
	}
}
