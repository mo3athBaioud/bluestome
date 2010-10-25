package com.ssi.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class UploadPathConfig {
	private UploadPathConfig() {

	}

	/*
	 * 约定的子目录名
	 */
	private static final String TXT_DIR_NAME = "txt/";
	private static final String ZIP_DIR_NAME = "zip/";
	private static final String TEMP_DIR_NAME = "temp/";
	private static final String IMAGE_DIR_NAME = "image/";
	private static final String EXCEL_DIR_NAME = "excel/";

	/*
	 * 本地目录
	 */
	public static String ROOT;
	public static String UPLOAD_TXT_DIR;
	public static String UPLOAD_ZIP_DIR;
	public static String UPLOAD_IMAGE_DIR;
	public static String UPLOAD_TEMP_DIR = "F:\\tmp\\zip\\";//System.getProperty("java.io.tmpdir");
	public static String UPLOAD_EXCEL_DIR;

	public void setPath(String path) {

		ROOT = checkPath(path);
		UPLOAD_TXT_DIR = ROOT + File.separator + TXT_DIR_NAME;
		UPLOAD_ZIP_DIR = ROOT + File.separator + ZIP_DIR_NAME;
		UPLOAD_IMAGE_DIR = ROOT + File.separator + IMAGE_DIR_NAME;
		UPLOAD_TEMP_DIR = ROOT + File.separator + TEMP_DIR_NAME;
		UPLOAD_EXCEL_DIR = ROOT + File.separator + EXCEL_DIR_NAME;

		init();
	}

	private void init() {
		List<String> subDirPathList = new ArrayList<String>();
		subDirPathList.add(UPLOAD_TXT_DIR);
		subDirPathList.add(UPLOAD_IMAGE_DIR);
		subDirPathList.add(UPLOAD_ZIP_DIR);
		subDirPathList.add(UPLOAD_TEMP_DIR);
		subDirPathList.add(UPLOAD_EXCEL_DIR);

		for (String dirPath : subDirPathList) {
			File subDir = new File(dirPath);
			if (!subDir.exists()) {
				try {
					subDir.mkdirs();
				} catch (Exception ex) {
					throw new RuntimeException("Create SubDir Exception: ", ex);
				}
			}
		}
	}

	/*
	 * 检测注入的路径是否合法
	 */
	private static String checkPath(String path) {
		if ("Windows XP".equals(System.getProperty("os.name"))) {
			if (path.indexOf("/") != -1) {
				path = path.replace("/", "\\");
			}
			if (!path.endsWith("\\")) {
				return path + "\\";
			} else {
				return path;
			}
		} else {
			if (path.indexOf("\\") != -1) {
				path = path.replace("\\", "/");
			}
			if (!path.endsWith("/")) {
				return path + "/";
			} else {
				return path;
			}
		}
	}

}
