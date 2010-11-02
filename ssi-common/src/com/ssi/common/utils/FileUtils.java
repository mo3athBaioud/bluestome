package com.ssi.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.RuleBasedCollator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.upload.FormFile;

import com.ssi.common.Rules;
import com.ssi.common.zip.ZipEntry;
import com.ssi.common.zip.ZipInputStream;

public class FileUtils extends org.apache.commons.io.FileUtils {
	private static Log logger = LogFactory.getLog(FileUtils.class);

	private static final int BUFFER_SIZE = 10 * 1024;

	public static void copyStream(final InputStream _inputStream,
			final OutputStream _ouputStream) throws IOException {
		final byte[] buffer = new byte[BUFFER_SIZE];
		int c;
		try {
			while ((c = _inputStream.read(buffer)) != -1) {
				_ouputStream.write(buffer, 0, c);
			}
		} finally {
			_ouputStream.flush();
			_ouputStream.close();
			_inputStream.close();
		}
	}

	/**
	 * 将字节数组保存在指定文件中
	 * 
	 * @param pathname
	 *            需要保存的文件路径
	 * @param buffer
	 *            需要被保存的内容
	 * @param append
	 *            是否采用追加的方式
	 * @return
	 */
	public static int saveFile(String pathname, byte[] buffer, boolean append) {
		String path = null;
		pathname = pathname.replaceAll("\\\\", "/");
		path = pathname.substring(0, pathname.lastIndexOf("/"));
		java.io.File dir = new java.io.File(path);
		if (!dir.isDirectory())
			dir.mkdirs();// 创建不存在的目录
		try {
			// writeln the file to the file specified
			OutputStream bos = new FileOutputStream(pathname, append);
			bos.write(buffer);
			bos.close();
		} catch (FileNotFoundException fnfe) {
			logger.error("", fnfe);
			return -1;
		} catch (IOException ioe) {
			logger.error("", ioe);
			return -2;
		}
		return 0;
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static void uploadFile(FormFile formFile, String targetFile)
			throws Exception {
		FileOutputStream fout = null;
		InputStream input = null;

		try {
			File file = new File(targetFile);

			fout = new FileOutputStream(file);

			byte[] bytes = new byte[1024];
			input = formFile.getInputStream();

			int length = input.read(bytes);

			while (length > 0) {
				fout.write(bytes, 0, length);
				length = input.read(bytes);
			}

		} catch (Exception e) {
			logger.error("", e);
		} finally {
			fout.close();
			fout = null;
			input.close();
			input = null;
		}
	}

	public static void encoding(String filename, String sourcEncoding,
			String destEncoding) throws IOException {
		String content = FileUtils.readFileToString(new File(filename),
				sourcEncoding);
		content = new String(content.getBytes(), destEncoding);
		FileUtils.writeStringToFile(new File(filename), content);
	}

	public static File createFile(String filename) throws Exception {
		File file = FileUtils.newFile(filename);
		if (!file.canWrite()) {
			String dirName = file.getPath();
			int i = dirName.lastIndexOf(File.separator);
			if (i > -1) {
				dirName = dirName.substring(0, i);
				File dir = FileUtils.newFile(dirName);
				dir.mkdirs();
			}
			file.createNewFile();
		}
		return file;
	}

	public static File newFile(String pathName) throws Exception {
		try {
			return new File(pathName).getCanonicalFile();
		} catch (IOException e) {
			logger.error("", e);
			throw new Exception("创建文件失败", e);
		}
	}

	/**
	 * 
	 * 解压缩文件
	 * 
	 * @param inFileName
	 *            输入需要解压缩的文件的全路径
	 * @param outFilePath
	 *            输出解压缩完的文件的路径
	 * @return
	 */
	public static void uncoilZIP(String inFileName, String outFilePath) {
		File file = new File(outFilePath);
		if (!file.exists()) {
			file.mkdirs();
		}

		int BUFFER = 2048;
		BufferedOutputStream output = null;
		BufferedInputStream input = null;
		ZipInputStream zip = null;
		ZipEntry entry = null;

		try {
			zip = new ZipInputStream(new FileInputStream(inFileName));
			while ((entry = zip.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					new File(outFilePath + File.separator + entry.getName())
							.mkdirs();
					continue;
				}

				int count;
				byte data[] = new byte[BUFFER];
				FileOutputStream fos = new FileOutputStream(outFilePath
						+ File.separator + entry.getName());
				while ((count = zip.read(data, 0, BUFFER)) != -1) {
					fos.write(data, 0, count);
				}

				fos.flush();
				fos.close();
			}
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			try {
				if (output != null) {
					output.close();
				}
				if (input != null) {
					input.close();
				}
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}

	/**
	 * 列举某个目录下的所有文件列表（含子目录）
	 * 
	 * @param dir
	 * @return
	 */
	public static List<File> listAllSubFiles(File dir) {
		List<File> ret = new ArrayList<File>();
		listAllFiles(dir, ret);
		return ret;
	}

	/**
	 * 列举某个目录下的所有文件列表（含子目录）
	 * 
	 * @param dir
	 * @param List
	 * @return
	 */
	public static void listAllFiles(File dir, List<File> ret) {
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File aFile : files) {
				if (aFile.isDirectory()) {
					listAllFiles(aFile, ret);
				} else {
					ret.add(aFile);
				}
			}
		} else {
			ret.add(dir);
		}
	}

	/**
	 * 删除文件夹里面的所有文件以及子文件夹
	 * 
	 * @param path
	 *            String 文件夹路径 如 c:/fqf
	 */
	public static void delAllFiles(String path) {
		File dir = new File(path);
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		for (File f : dir.listFiles()) {
			delFile(f);
		}
	}

	/**
	 * 删除指定文件，如果是文件则直接删除；如果是目录，递归遍历删除所有文件再删除该目录
	 * 
	 * @param p
	 */
	public static void delFile(File p) {
		if (p.isFile()) {
			p.delete();
		}
		if (p.isDirectory()) {
			File[] files = p.listFiles();
			for (File file : files) {
				delFile(file);
			}
			p.delete();
		}
	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public static void copyFolder(String oldPath, String newPath) {

		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
					// temp.renameTo(arg0)
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			logger.error("复制整个文件夹内容操作出错", e);
		}

	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/old/
	 * @param newPath
	 *            String 如：d:/new/
	 */
	public static void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		delAllFiles(oldPath);
	}

	@SuppressWarnings("unchecked")
	public static void sortFilesByFileName(List<File> files) {

		Collections.sort(files, new Comparator() {
			RuleBasedCollator collator = Rules.getCollator();

			public int compare(Object o1, Object o2) {
				return collator.compare(((File) o1).getName(), ((File) o2)
						.getName());
			}

		});
	}

	public static void sortFilesByFileName(File[] files) {
		List<File> fileAsList = Arrays.asList(files);
		sortFilesByFileName(fileAsList);
		files = fileAsList.toArray(new File[] {});
	}

	/**
	 * 读取文件文本
	 * 
	 * @param fileName
	 *            文件名
	 * @return 文件文本 @
	 */
	public static String getFileTxt(String fileName) {
		String txt = null;
		try {
			FileInputStream fs = new FileInputStream(fileName);

			byte buff[] = new byte[fs.available()];
			fs.read(buff);
			String s = new String(buff, "utf-8");
			txt = s.trim();
			fs.close();
		} catch (Exception e) {
			StringUtils.logger.error("\nError in read file:" + e.getMessage());
		}
		return txt;
	}

	private static AtomicInteger atomic = new AtomicInteger(0);

	private static String getNextInteger() {
		int i = atomic.getAndIncrement();
		if (i > 98)
			atomic.set(0);
		String str = "000" + i;
		return str.substring(str.length() - 3, str.length());
	}

	public static String createFileNameByTime(String ext) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String filename = df.format(new Date());
		String suffix = getNextInteger();

		filename += suffix;
		if (!ext.startsWith(".")) {
			ext = "." + ext;
		}
		filename += ext;
		return filename;
	}

}
