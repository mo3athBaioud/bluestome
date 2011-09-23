package com.skymobi.qunker.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.commons.fileupload.FileUploadException;

public abstract class UploadAction extends BaseAction {


	//文件缓存大小 
	private static int BUFFER_SIZE = 2048;
	
	// 用File数组来封装多个上传文件域对象
	protected File[] upload = null;
	
	// 用String数组来封装多个上传文件名
	protected String[] uploadFileName = null;
	
	// 用String数组来封装多个上传文件类型
	protected String[] uploadContentType = null;
	
	//单个上传文件
	protected File file = null;
	
	//单个上传文件名
	protected String fileFileName = null;
	
	//单个上传文件类型
	protected String fileContentType = null;
	
	// 保存文件的目录路径(通过依赖注入)
	protected String savePath;
	
	UploadResult copy(File src, File dst) throws IOException{
		UploadResult result = new UploadResult();
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst),
					BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			result.setResult(true);
			if(buffer.length > 0){
				byte[] tmp = buffer;
				//计算文件的MD5值
				/**
				String md5 = MD5.getInstance().getMD5ofStr(new String(tmp));
				logger.info(" >> md5:"+md5);
				result.setMd5(md5);
				**/
			}
		} catch (Exception e) {
			logger.error(" >> UploadAction.copy.exception:"+e);
			result.setResult(false);
			throw new IOException();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					throw new IOException(e);
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					throw new IOException(e);
				}
			}
		}
		return result;
	}
	
	/**
	 * 上传方法
	 * @throws IOException
	 */
	public abstract void upload() throws FileUploadException,IOException;
	
}

class UploadResult{
	private boolean result = false;
	private String md5 = null;
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
}
