/**
 * 文件com.sky.spirit.basic.message.i18n.LocaleMessageHandler.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.basic.message.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午02:25:14
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
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
