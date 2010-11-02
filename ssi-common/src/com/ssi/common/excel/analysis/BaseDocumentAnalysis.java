package com.ssi.common.excel.analysis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ssi.common.ResultBean;
import com.ssi.common.UploadPathConfig;
import com.ssi.common.excel.ExcelSheetConfig;
import com.ssi.common.exception.ExcelException;
import com.ssi.common.exception.UnzipException;
import com.ssi.common.utils.ArrayUtils;
import com.ssi.common.utils.FileUtils;


public abstract class  BaseDocumentAnalysis implements IDocumentAnalysis {

	protected final Log logger = LogFactory.getLog(getClass());
	
	//文件扩展名 子类可以根据需要修改该配置信息
	protected String[] fileExt = {".jpg",".png",".gif",".3gp",".mp4",".avi",".mp3",".acc",".wav",".mrp"}; //
	
	//图标文件扩展名
	protected String[] iconFileExt = {".jpg",".png",".gif"};
	
	/**
	 * 资源类型， 可配置,子类根据实际类型在配置文件中配置该属性
	 * 如果未配置，则该类型值由当前的父类设置决定
	 */
	protected String resType = "common";

	//消息属性
	protected String message = "";

	
	/**
	 * 解析Excel 由子类实现
	 */
	public ResultBean parserExcel(String filePath) throws IOException{
		return null;
	}
	
	/**
	 * 创建EXCEL的解析配置
	 * @return
	 */
	protected abstract ExcelSheetConfig createSheetConfig();
	
	/**
	 * 验证excel文件
	 * @return
	 */
	protected abstract boolean validationExcel(String filePath);
	
	/**
	 * 根据指定的格式读取excel文件
	 * 
	 * @param excelFileName
	 * @return
	 * @throws BizException
	 *             读取excel文件时出错
	 */
	protected abstract List<Map<String, Object>> readExcelInfo(String excelFileName) throws ExcelException;
	
	/**
	 * 入口函数
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public abstract ResultBean process(Object bean) throws IOException;
	
	/**
	 * 解压ZIP文件
	 * @param filePath 需要解压的ZIP文件地址
	 * @return ResultBean 结果对象
	 */
	public ResultBean parserZIP(String filePath){
		ResultBean result = new ResultBean();
		File[] files = null;
		try{
			files = unzip(filePath);
		}catch(UnzipException be){ // 解压异常
			result.setStatusCode(5001);
			result.setDesc(be.getMessage());
			return result;
		}catch(Exception e){
			result.setStatusCode(5010);
			result.setDesc("解压ZIP["+filePath+"]失败:"+e.getMessage());
			return result;
		}
		
		//TODO 文件验证由子类实现
		
		//设置返回成功的数据
		result.setObj(files);
		result.setSuccess(true);
		result.setStatusCode(1000);
		result.setDesc("解压ZIP["+filePath+"]成功");
		return result;
	}
	
	/**
	 * 从文件夹中获取文件名
	 * @param root
	 * @return String[]  str[0] 文件长度 str[1] 文件名
	 */
	protected List<String[]> getFileName(File root){
		List<String[]> fileList = new ArrayList<String[]>();
		if(root.isDirectory()){
			logger.debug(">> 文件地址:"+root.getAbsolutePath());
			try{
				File[] files = root.listFiles();
				if(null != files && files.length > 0){
					for(File file:files){
						if(file.isFile()){
							if(filter(file.getName())){
								String[] arrayFile = new String[2];
								arrayFile[0] = String.valueOf(file.length());
								arrayFile[1] = file.getName();
								fileList.add(arrayFile);
							}
						}
					}
				}
			}catch(Exception e){
				logger.error(">> 获取ZIP解压后的文件夹中的内容出错:"+e);
			}
		}
		return fileList;
	}
	
	/**
	 * 从文件夹中获取文件名
	 * @param root
	 * @return
	 */
	protected List<String[]> getIconFileName(File root){
		List<String[]> fileList = new ArrayList<String[]>();
		if(root.isDirectory()){
			try{
				File[] files = root.listFiles();
				if(null != files && files.length > 0){
					for(File file:files){
						if(file.isFile()){
							if(iconExtFilter(file.getName())){
								String[] arrayFile = new String[2];
								arrayFile[0] = String.valueOf(file.length());
								arrayFile[1] = file.getName();
								fileList.add(arrayFile);
							}
						}
					}
				}
			}catch(Exception e){
				logger.error(">> 获取ZIP解压后的文件夹中的内容出错:"+e);
			}
		}
		return fileList;
	}
	
	/**
	 * 验证zip文件
	 * 目前的对ZIP的验证要求是，zip文件必须包含2个内容，一个为文件夹，一个为xls文件，即资源文件和信息文件必须同时存在，否则验证不通过
	 * 子类根据实际情况对验证的方式进行修改，重载该方法
	 * @return
	 */
	protected boolean validationZip(File[] files){
		//是否包含文件夹目录
		boolean hasDir = false;
		//是否包含Excel文件
		boolean hasXls = false;
		if(files.length != 2){
			logger.debug(">> ZIP包内的文件数量不满足要求["+files.length+"],一个文件夹目录，一个Excel文件");
			return false;
		}
		// TODO 指定验证ZIP的规则
		for(File file:files){
			logger.debug(">> 文件路径:"+file.getAbsolutePath());
			if(file.isDirectory()){
				hasDir = true;
			}
			if(file.getName().toLowerCase().endsWith(".xls")){
				hasXls = true;
			}
		}
		if(!hasDir || !hasXls){
			logger.debug(">> ZIP内容不满足要求 ");
			return false;
		}
		return true;
	}

	/**
	 * 解压缩文件
	 * @param zipFileName
	 * @return
	 * @throws ExcelException
	 */
	protected File[] unzip(String zipFileName) throws ExcelException {
		String zipTmpPath = UploadPathConfig.UPLOAD_TEMP_DIR;
		File root = null;
		File[] files = null;
		try {
			FileUtils.delAllFiles(zipTmpPath);
			FileUtils.uncoilZIP(zipFileName, zipTmpPath);
		} catch (UnzipException e) {
			logger.error("解压缩[" + zipFileName + "]文件出错。", e);
			throw new UnzipException(message);
		}catch(Exception e){
			logger.error("解压缩[" + zipFileName + "]文件出错。", e);
			throw new ExcelException(message);
		}

		root = new File(zipTmpPath);
		files = root.listFiles();
		return files;
	}

	/**
	 * 根据配置的文件扩展名对当前需要匹配的文件名进行过滤
	 * @param fileName
	 * @return
	 */
	public boolean filter(String fileName) {
		boolean b = false;
		// TODO 文件过滤
		for(String ext:fileExt){
			if(fileName.toLowerCase().endsWith(ext)){
				b = true;
				break;
			}
		}
		return b;
	}
	
	/**
	 * 根据配置的图标文件扩展名对当前需要匹配的文件名进行过滤
	 * @param fileName
	 * @return
	 */
	public boolean iconExtFilter(String fileName) {
		boolean b = false;
		logger.debug(">> 图标文件["+ArrayUtils.toString(iconFileExt)+"]");
		// TODO 文件过滤
		for(String ext:iconFileExt){
			logger.debug(">> 当前文件名["+fileName+"]");
			if(fileName.toLowerCase().endsWith(ext)){
				b = true;
				break;
			}
		}
		return b;
	}
	
	/**
	 * 获取文件扩展名
	 * @param fileName
	 * @return
	 */
	protected String getFileExtendName(String fileName){
		if(fileName.indexOf(".") == -1){
			return "";
		}
		return fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
	}
	
	/**
	 * 获取类型编号
	 * @param cata
	 */
	 public String getCatalogNum(String cata){
		int start = cata.indexOf("[");
		if(start == -1)
			return cata;
		int end = cata.indexOf("]");
		if(end == -1)
			return cata;
		return cata.substring(start+1,end);
	 }
	 
	/**
	 *	获取xls中的图标文件名 
	 */
	protected ResultBean checkIconXls(List<Map<String,Object>> xls,String iconFileName){
		ResultBean result = new ResultBean();
		ExcelVO vo = null;
		File file = null;
		List<String> iconNameList = new ArrayList<String>();
		int i=0;
		
		if(null == iconFileName)
			return result;
		try{
			file = new File(iconFileName);
		}catch(Exception e){
			result.setDesc("找不到实体文件，异常信息为"+e.getMessage());
			result.setStatusCode(5010);
			return result;
		}
		if(null == file){
			result.setDesc("找不到实体文件,请检查上传的ZIP文件!");
			result.setStatusCode(5010);
			return result;
		}
		
		file = new File(iconFileName);
		if(file.isDirectory()){
			File[] files = file.listFiles();
			if(files.length != xls.size()){
				message = "图标实体文件数量和信息文件中图标记录数量不匹配，请检查上传文件!";
				result.setStatusCode(4001);
				result.setDesc(message);
				return result;
			}
			for (Map<String,Object> tobj : xls) {
				if (tobj instanceof Map) {
					vo = new ExcelVO();
					Map<String, Object> exl = (Map<String, Object>) tobj;
					vo.setIconName((String) exl.get("iconName"));
					try{
						for(File sub:files){
							//检查图标文件是否符合图标扩展名
							if(iconExtFilter(sub.getName())){
//								logger.debug(">> 图标名称:"+sub.getName());
								if(sub.getName().toLowerCase().equalsIgnoreCase(vo.getIconName().toLowerCase())){
									logger.debug(">> IconName:"+vo.getIconName() + ",IconFileName:"+sub.getName());
									i++;
								}
							}
						}
					}catch(Exception e){
						result.setDesc("检查文件内容出现异常，异常信息为"+e.getMessage());
						result.setStatusCode(5010);
						return result;
					}
				}
			}
		}
		
		logger.debug(">> i:"+i);
		if(i == xls.size()){
			result.setSuccess(true);
		}else{
			result.setStatusCode(4001);
			result.setDesc("信息文件记录与图标文件数量不一致，信息文件中有["+xls.size()+"]条记录,图标文件夹中有["+i+"]个符合要求的图片");
		}
		return result;
	}
	
	/**
	 *	获取xls中的图标文件名 
	 */
	protected ResultBean checkFile(List<Map<String,Object>> xls,String filePath){
		ResultBean result = new ResultBean();
		ExcelVO vo = null;
		File file = null;
		boolean isType = true;
		int i=0;
		
		if(null == filePath)
			return result;
		for (Map<String,Object> tobj : xls) {
			if (tobj instanceof Map) {
				vo = new ExcelVO();
				Map<String, Object> exl = (Map<String, Object>) tobj;
				vo.setIconName((String) exl.get("fileName"));
				try{
					file = new File(filePath);
					if(file.isDirectory()){
						File[] files = file.listFiles();
						for(File sub:files){
							if(filter(sub.getName())){
								message = "实体文件类型不符合上传类型["+ArrayUtils.toString(fileExt)+"]要求，请检查实体文件";
								isType = false;
								result.setDesc(message);
								result.setStatusCode(4001);
								break;
							}
							if(sub.getName().toLowerCase().equalsIgnoreCase(vo.getIconName().toLowerCase())){
								logger.debug(">> xlsFileName:"+vo.getIconName() + ",fileName:"+sub.getName());
								i++;
							}
						}
					}
				}catch(Exception e){
					result.setDesc("检查实体文件出现异常，异常信息为"+e.getMessage());
					result.setStatusCode(5010);
					return result;
				}
			}
		}
		if(!isType){
			return result;
		}
		logger.debug(">> i:"+i);
		if(i == xls.size()){
			result.setSuccess(true);
		}else{
			result.setStatusCode(4001);
			result.setDesc("信息文件中的记录数与实际文件记录数不一致，信息文件中有["+xls.size()+"]条记录,实体文件夹中有["+i+"]个符合要求的文件");
		}
		return result;
	}
	
	public String[] getFileExt() {
		return fileExt;
	}

	public void setFileExt(String[] fileExt) {
		this.fileExt = fileExt;
	}
	
	public String[] getIconFileExt() {
		return iconFileExt;
	}

	public void setIconFileExt(String[] iconFileExt) {
		this.iconFileExt = iconFileExt;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

}