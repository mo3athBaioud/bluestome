/**
 * �ļ�com.sky.spirit.basic.message.i18n.LocaleMessageHandler.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.basic.message.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * ע��
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����02:25:14
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 *
 */
public class LocaleMessageHandler {
	/**
	 * logger used by this class
	 */
	protected static final Log logger = LogFactory
			.getLog(LocaleMessageHandler.class);

	/**
	 * Get the resource string for the given bundle name and resource code
	 */
	public static String getString(String bundleName, String code) {
		return getString(bundleName, code, new Object[] {});
	}

	/**
	 * Get the resource string for the given bundle name, resource code and one
	 * argument
	 */
	public static String getString(String bundleName, String code, Object arg1) {
		if (arg1 == null) {
			arg1 = "null";
		}

		return getString(bundleName, code, new Object[] { arg1 });
	}

	/**
	 * Get the resource string for the given bundle name, resource code and two
	 * arguments
	 */
	public static String getString(String bundleName, String code, Object arg1,
			Object arg2) {
		if (arg1 == null) {
			arg1 = "null";
		}

		if (arg2 == null) {
			arg2 = "null";
		}

		return getString(bundleName, code, new Object[] { arg1, arg2 });
	}

	/**
	 * Get the resource string for the given bundle name, resource code and
	 * array of arguments. All above methods invoke this one.
	 */
	public static String getString(String bundleName, String code, Object[] args) {
		String path = bundleName + "-messages";
		Locale locale = Locale.getDefault();
		ResourceBundle bundle = ResourceBundle.getBundle(path, locale);
		String m = bundle.getString(code);

		if (m == null) {
			logger.error("Failed to find message for id " + code
					+ " in resource bundle " + path);
			return "";
		}

		return MessageFormat.format(m, args);
	}

}
