package com.skymobi.qunker.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * 增删改查方法抽象类集合
 * @author Bluestome.Zhang
 *
 * @param <T>
 */
public abstract class CRUDActionSupport<T> extends BaseAction {

	/**
	 * 添加数据方法
	 * 
	 * @throws IOException
	 */
	public abstract void save() throws IOException;

	/**
	 * 删除数据方法
	 * 
	 * @throws IOException
	 */
	public abstract void delete() throws IOException;

	/**
	 * 更新数据方法
	 * 
	 * @throws IOException
	 */
	public abstract void update() throws IOException;

	/**
	 * 查询数据列表方法
	 * 
	 * @throws IOException
	 */
	public abstract void list() throws IOException;

	/**
	 * 数据列表转换为JSON对象
	 * 
	 * @param response
	 * @param sysConfigList2
	 * @param count
	 * @throws IOException
	 */
	protected abstract void toJson(HttpServletResponse response, List<T> list,
			int count) throws IOException;

}
