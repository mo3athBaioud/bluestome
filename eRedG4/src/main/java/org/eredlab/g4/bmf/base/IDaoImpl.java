package org.eredlab.g4.bmf.base;

import java.util.List;

import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * 数据访问实现类(原生)<br>
 * 基于Spring对iBatis的支持机制实现,支持自定义的数据操作
 * @author XiongChun
 * @since 2009-07-23
 * @see org.springframework.orm.ibatis.support.SqlMapClientDaoSupport
 */
public class IDaoImpl extends SqlMapClientDaoSupport implements IDao{
	
	/**
	 * 插入一条记录
	 * @param SQL语句ID号
	 * @param parameterObject 要插入的对象(map javaBean)
	 */
	public void insert(String statementName, Object parameterObject){
		super.getSqlMapClientTemplate().insert(statementName, parameterObject);
	}
	
	/**
	 * 插入一条记录
	 * @param SQL语句ID号
	 */
	public void insert(String statementName){
		super.getSqlMapClientTemplate().insert(statementName, new BaseDto());
	}
	
	/**
	 * 查询一条记录
	 * @param SQL语句ID号
	 * @param parameterObject 查询条件对象(map javaBean)
	 */
	public Object queryForObject(String statementName, Object parameterObject){
		return super.getSqlMapClientTemplate().queryForObject(statementName, parameterObject);
	}
	
	/**
	 * 查询一条记录
	 * @param SQL语句ID号
	 */
	public Object queryForObject(String statementName){
		return super.getSqlMapClientTemplate().queryForObject(statementName, new BaseDto());
	}
	
	/**
	 * 查询记录集合
	 * @param SQL语句ID号
	 * @param parameterObject 查询条件对象(map javaBean)
	 */
	public List queryForList(String statementName, Object parameterObject){
		return super.getSqlMapClientTemplate().queryForList(statementName, parameterObject);
	}
	
	/**
	 * 查询记录集合
	 * @param SQL语句ID号
	 */
	public List queryForList(String statementName){
		return super.getSqlMapClientTemplate().queryForList(statementName, new BaseDto());
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
	 * 更新记录
	 * @param SQL语句ID号
	 * @param parameterObject 更新对象(map javaBean)
	 */
	public void update(String statementName, Object parameterObject){
		super.getSqlMapClientTemplate().update(statementName, parameterObject);
	}
	
	/**
	 * 更新记录
	 * @param SQL语句ID号
	 */
	public void update(String statementName){
		super.getSqlMapClientTemplate().update(statementName, new BaseDto());
	}
	
	/**
	 * 删除记录
	 * @param SQL语句ID号
	 * @param parameterObject 更新对象(map javaBean)
	 */
	public void delete(String statementName, Object parameterObject){
		super.getSqlMapClientTemplate().delete(statementName, parameterObject);
	}
	
	/**
	 * 删除记录
	 * @param SQL语句ID号
	 */
	public void delete(String statementName){
		super.getSqlMapClientTemplate().delete(statementName, new BaseDto());
	}
}
