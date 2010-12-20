/**
 * 文件com.sky.spirit.common.util.StringUtils.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.common.util;

import org.apache.commons.lang.CharUtils;
import org.safehaus.uuid.UUIDGenerator;

/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午11:25:05
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
 *
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

	

	/**
	 * Convert a hexadecimal string into its byte representation.
	 * 
	 * @param hex
	 *            The hexadecimal string.
	 * @return The converted bytes or <code>null</code> if the hex String is
	 *         null.
	 */
	public static byte[] hexStringToByteArray(String hex) {
		if (hex == null) {
			return null;
		}

		int stringLength = hex.length();
		if (stringLength % 2 != 0) {
			throw new IllegalArgumentException(
					"Hex String must have even number of characters!");
		}

		byte[] result = new byte[stringLength / 2];

		int j = 0;
		for (int i = 0; i < result.length; i++) {
			char hi = Character.toLowerCase(hex.charAt(j++));
			char lo = Character.toLowerCase(hex.charAt(j++));
			result[i] = (byte) ((Character.digit(hi, 16) << 4) | Character
					.digit(lo, 16));
		}

		return result;
	}

	/**
	 * Like {@link #repeat(String, int)} but with a single character as
	 * argument.
	 */
	public static String repeat(char c, int len) {
		return repeat(CharUtils.toString(c), len);
	}

	/**
	 * @see #toHexString(byte[])
	 */
	public static String toHexString(byte[] bytes) {
		return StringUtils.toHexString(bytes, false);
	}

	/**
	 * Convert a byte array to a hexadecimal string.
	 * 
	 * @param bytes
	 *            The bytes to format.
	 * @param uppercase
	 *            When <code>true</code> creates uppercase hex characters
	 *            instead of lowercase (the default).
	 * @return A hexadecimal representation of the specified bytes.
	 */
	public static String toHexString(byte[] bytes, boolean uppercase) {
		if (bytes == null) {
			return null;
		}

		int numBytes = bytes.length;
		StringBuffer str = new StringBuffer(numBytes * 2);

		String table = (uppercase ? HEX_CHARACTERS_UC : HEX_CHARACTERS);

		for (int i = 0; i < numBytes; i++) {
			str.append(table.charAt(bytes[i] >>> 4 & 0x0f));
			str.append(table.charAt(bytes[i] & 0x0f));
		}

		return str.toString();
	}
	
	public static String getUUID() {
		return generator.generateTimeBasedUUID().toString();
	}
	
	// lookup tables needed for toHexString(byte[], boolean)
	private static final String HEX_CHARACTERS = "0123456789abcdef";
	private static final String HEX_CHARACTERS_UC = HEX_CHARACTERS
			.toUpperCase();
	private static final UUIDGenerator generator = UUIDGenerator.getInstance();
	


}
