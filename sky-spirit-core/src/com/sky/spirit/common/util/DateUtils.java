/**
 * 文件com.sky.spirit.common.util.DateUtils.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.common.util;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午11:16:03
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
 *
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

	public static String getTimeStamp(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date currentTime = new Date();
		return formatter.format(currentTime);
	}

	public static String formatTimeStamp(Date dateTime, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(dateTime);
	}

	public static String getStringFromDate(Date date, String format) {

		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	public static Date getDateFromString(String date, String format) {

		SimpleDateFormat formatter = new SimpleDateFormat(format);
		ParsePosition pos = new ParsePosition(0);

		return formatter.parse(date, pos);
	}

}
