package com.ssi.common.excel.analysis;

import java.io.IOException;

import com.ssi.common.ResultBean;

/**
 * 文件解析接口
 * 目前只定义2个方法
 * @author bluestome
 *
 */
public interface IDocumentAnalysis {

	/**
	 * 解析ZIP文件
	 * @param filePath 文件路径
	 * @return
	 * @throws IOException
	 */
	public ResultBean parserZIP(String filePath) throws IOException;
	
	/**
	 * 解析excel文件
	 * @param filePath 文件路径
	 * @return
	 * @throws IOException
	 */
	public ResultBean parserExcel(String filePath) throws IOException;
	
}
