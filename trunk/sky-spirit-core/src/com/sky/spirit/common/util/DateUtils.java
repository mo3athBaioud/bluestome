/**
 * �ļ�com.sky.spirit.common.util.DateUtils.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.common.util;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * ע��
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����11:16:03
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
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
