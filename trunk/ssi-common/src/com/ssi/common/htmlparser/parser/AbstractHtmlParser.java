package com.ssi.common.htmlparser.parser;

import com.ssi.common.htmlparser.bean.AbsResource;
import com.ssi.common.htmlparser.bean.LinkPage;

/**
 * 解析抽象类
 * @author bluestome
 *
 */
public interface AbstractHtmlParser {
	
	/**
	 * 获取分类
	 * @param url
	 * @return
	 * @throws Exception
	 */
	LinkPage catalog(String url) throws Exception;

	/**
	 * 获取分页
	 * @param url
	 * @return
	 * @throws Exception
	 */
	LinkPage page(String url) throws Exception;
	
	
	/**
	 * 获取详情数据
	 * @param url
	 * @return
	 * @throws Exception
	 */
	AbsResource detail(String url) throws Exception;
}
