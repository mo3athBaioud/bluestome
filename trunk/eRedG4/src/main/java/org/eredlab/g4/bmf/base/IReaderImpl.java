package org.eredlab.g4.bmf.base;

import java.util.List;

import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * 数据读取器<br>
 * 基于iBatis实现,只有query权限,提供在Action中使用
 * 
 * @author XiongChun
 * @since 2009-07-23
 * @see org.springframework.orm.ibatis.support.SqlMapClientDaoSupport
 */
public class IReaderImpl extends SqlMapClientDaoSupport implements IReader {

	/**
	 * 查询一条记录
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            查询条件对象(map javaBean)
	 */
	public Object queryForObject(String statementName, Object parameterObject) {
		return super.getSqlMapClientTemplate().queryForObject(statementName, parameterObject);
	}

	/**
	 * 查询一条记录
	 * 
	 * @param SQL语句ID号
	 */
	public Object queryForObject(String statementName) {
		return super.getSqlMapClientTemplate().queryForObject(statementName, new BaseDto());
	}

	/**
	 * 查询记录集合
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            查询条件对象(map javaBean)
	 */
	public List queryForList(String statementName, Object parameterObject) {
		return super.getSqlMapClientTemplate().queryForList(statementName, parameterObject);
	}

	/**
	 * 按分页查询
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            查询条件对象(map javaBean)
	 */
	public List queryForPage(String statementName, Dto qDto) {
		return super.getSqlMapClientTemplate().queryForList(statementName, qDto, qDto.getAsInteger("start").intValue(),
				qDto.getAsInteger("end").intValue());
	}

	/**
	 * 查询记录集合
	 * 
	 * @param SQL语句ID号
	 */
	public List queryForList(String statementName) {
		return super.getSqlMapClientTemplate().queryForList(statementName, new BaseDto());
	}
}
