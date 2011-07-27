package com.ssi.common.htmlparser.bean;

import java.io.Serializable;

/**
 * 抽象类资源
 * @author bluestome
 *
 */
public class AbsResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 资源ID
	 */
	protected Integer id;
	
	/**
	 * 资源标题
	 */
	protected String title;
	
	/**
	 * 资源地址
	 */
	protected String url;
	
	/**
	 * 资源发布时间
	 */
	protected String publishTime;
	
	/**
	 * 资源备注
	 */
	protected String remarks;
}
