/**
 * �ļ�com.sky.spirit.common.util.IOUtils.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * ע��
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����11:20:16
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 *
 */
public class IOUtils extends org.apache.commons.io.IOUtils {
	/** Logger. */
	private static final Log logger = LogFactory.getLog(IOUtils.class);

	/**
	 * Attempts to load a resource from the file system, from a URL, or from the
	 * classpath, in that order.
	 * 
	 * @param resourceName
	 *            The name of the resource to load
	 * @param callingClass
	 *            The Class object of the calling object
	 * @return the requested resource as a string
	 * @throws java.io.IOException
	 *             IO error
	 */
	public static String getResourceAsString(final String resourceName,
			final Class callingClass) throws IOException {
		InputStream is = getResourceAsStream(resourceName, callingClass);
		if (is != null) {
			return toString(is);
		} else {
			throw new IOException("Unable to load resource " + resourceName);
		}
	}

	/**
	 * Attempts to load a resource from the file system, from a URL, or from the
	 * classpath, in that order.
	 * 
	 * @param resourceName
	 *            The name of the resource to load
	 * @param callingClass
	 *            The Class object of the calling object
	 * @return an InputStream to the resource or null if resource not found
	 * @throws java.io.IOException
	 *             IO error
	 */
	public static InputStream getResourceAsStream(final String resourceName,
			final Class callingClass) throws IOException {
		return getResourceAsStream(parseResourceName(resourceName),
				callingClass, true, true);
	}

	/**
	 * Attempts to load a resource from the file system, from a URL, or from the
	 * classpath, in that order.
	 * 
	 * @param resourceName
	 *            The name of the resource to load
	 * @param callingClass
	 *            The Class object of the calling object
	 * @param tryAsFile
	 *            - try to load the resource from the local file system
	 * @param tryAsUrl
	 *            - try to load the resource as a URL
	 * @return an InputStream to the resource or null if resource not found
	 * @throws java.io.IOException
	 *             IO error
	 */
	public static InputStream getResourceAsStream(final String resourceName,
			final Class callingClass, boolean tryAsFile, boolean tryAsUrl)
			throws IOException {

		URL url = getResourceAsUrl(resourceName, callingClass, tryAsFile,
				tryAsUrl);

		if (url == null) {
			return null;
		} else {
			return url.openStream();
		}
	}

	/**
	 * Attempts to load a resource from the file system or from the classpath,
	 * in that order.
	 * 
	 * @param resourceName
	 *            The name of the resource to load
	 * @param callingClass
	 *            The Class object of the calling object
	 * @return an URL to the resource or null if resource not found
	 */
	public static URL getResourceAsUrl(final String resourceName,
			final Class callingClass) {
		return getResourceAsUrl(resourceName, callingClass, true, true);
	}

	/**
	 * Attempts to load a resource from the file system or from the classpath,
	 * in that order.
	 * 
	 * @param resourceName
	 *            The name of the resource to load
	 * @param callingClass
	 *            The Class object of the calling object
	 * @param tryAsFile
	 *            - try to load the resource from the local file system
	 * @param tryAsUrl
	 *            - try to load the resource as a Url string
	 * @return an URL to the resource or null if resource not found
	 */
	public static URL getResourceAsUrl(final String resourceName,
			final Class callingClass, boolean tryAsFile, boolean tryAsUrl) {
		if (resourceName == null) {
			throw new IllegalArgumentException("无效资源名称 ");
		}
		URL url = null;
		boolean fileSystemRead = false;
		boolean classPathRead = false;
		// Try to load the resource from the file system.
		if (tryAsFile) {
			try {
				File file = FileUtils.newFile(resourceName);
				if (file.exists()) {
					url = file.getAbsoluteFile().toURL();
					fileSystemRead = true;
				} else {
					// logger
					// .debug("Unable to load resource from the file system: "
					// + file.getAbsolutePath());
				}
			} catch (Exception e) {
				logger.debug("Unable to load resource from the file system: "
						+ e.getMessage());
			}
		}

		// Try to load the resource from the classpath.
		if (url == null) {
			try {
				url = (URL) AccessController
						.doPrivileged(new PrivilegedAction() {
							public Object run() {
								return ClassUtils.getResource(resourceName,
										callingClass);
							}
						});
				if (url == null) {
					// logger.debug("Unable to load resource " + resourceName
					// + " from the classpath");
				} else {
					classPathRead = true;
				}
			} catch (Exception e) {
				logger.debug("Unable to load resource " + resourceName
						+ " from the classpath: " + e.getMessage());
			}
		}

		if (url == null) {
			try {
				url = new URL(resourceName);
			} catch (MalformedURLException e) {
				// ignore
			}
		}
		if (classPathRead == false && fileSystemRead == false) {
			logger.debug("Unable to load resource " + resourceName
					+ " from the classpath or ");
			File file = FileUtils.newFile(resourceName);
			logger.debug("Unable to load resource from the file system: "
					+ file.getAbsolutePath());

		}
		return url;
	}

	/**
	 * This method checks whether the name of the resource needs to be parsed.
	 * If it is, it parses the name and tries to get the variable from the
	 * Environmental Variables configured on the system.
	 * 
	 * @param src
	 * @return
	 */
	private static String parseResourceName(String src) {
		return src;
	}

	/**
	 * This method wraps {@link org.apache.commons.io.IOUtils}'
	 * <code>toString(InputStream)</code> method but catches any
	 * {@link IOException} and wraps it into a {@link RuntimeException}.
	 */
	public static String toString(InputStream input) {
		try {
			return org.apache.commons.io.IOUtils.toString(input);
		} catch (IOException iox) {
			throw new RuntimeException(iox);
		}
	}

	/**
	 * This method wraps {@link org.apache.commons.io.IOUtils}'
	 * <code>toByteArray(InputStream)</code> method but catches any
	 * {@link IOException} and wraps it into a {@link RuntimeException}.
	 */
	public static byte[] toByteArray(InputStream input) {
		try {
			return org.apache.commons.io.IOUtils.toByteArray(input);
		} catch (IOException iox) {
			throw new RuntimeException(iox);
		}
	}
}
