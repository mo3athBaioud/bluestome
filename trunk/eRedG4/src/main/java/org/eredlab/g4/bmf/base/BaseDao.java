package org.eredlab.g4.bmf.base;

import java.util.List;

import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.Key;
import org.eredlab.g4.ccl.datastructure.impl.BaseDomain;

/**
 * 数据访问接口(封装后)<br>
 * 基于iBatis实现,支持实体对象的数据操作
 * <b>为了简化Dao层面的开发,已经放弃这种封装方式！不推荐使用，仅仅早作者早期进行系统管理开发时使用了这种方式。<br>
 * 以后不推荐使用
 * 
 * @author XiongChun
 * @since 2009-07-18
 * @see com.ibatis.dao.client.Dao
 */
public interface BaseDao {
	/**
	 * 插入一条记录
	 * 
	 * @param domain
	 *            要插入的实体对象
	 */
	public void insertDomain(BaseDomain domain);

	/**
	 * 根据主键删除一条记录
	 * 
	 * @param Key
	 *            主键
	 */
	public void deleteDomainByKey(Key key);

	/**
	 * 根据主键查询一条数据
	 * 
	 * @param Key
	 *            主键
	 * @return object 返回的实体对象
	 */
	public Object queryDomainByKey(Key key);

	/**
	 * 根据Dto查询数据
	 * 
	 * @param dto
	 *            传入的Dto查询参数Dto对象
	 * @return List 返回的List记录集
	 */
	public List queryDomainsByDto(Dto dto);

	/**
	 * 根据Key更新一条记录
	 * 
	 * @param domain
	 *            实体领域对象
	 */
	public void updateDomainByKey(BaseDomain domain);
	
	/**
	 * 按分页查询
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            查询条件对象(map javaBean)
	 */
	public List queryForPage(String statementName, Dto qDto);

}
