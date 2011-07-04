package com.xcms.web.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.ExpGroup;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.mss.dal.dao.NOplogDao;
import com.mss.dal.domain.Noplog;

@IocBean
@InjectName
public class NOplogService {

	private static Log log = LogFactory.getLog(NOplogService.class);
	
	@Inject
	private NOplogDao nOplogDao;

	@Inject
	private Dao dao;
	
	/**
	 * 添加日志
	 * @param noplog
	 * @return
	 */
	public boolean save(Noplog noplog){
		boolean b = false;
		try{
			noplog = nOplogDao.save(noplog);
			if(noplog.getId() > 0){
				b = true;
			}
		}catch(Exception e){
			log.error(e);
		}
		return b;
	}
	
	/**
	 * 分页查询
	 * @param phonenum
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Noplog> search(String colName,String value,int start,int limit){
		Condition  cnd = null;
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "like", "%"+value+"%");
		}
		start = start/limit+1;
		if(start == 0){
			start = 1;
		}
		Pager pager = dao.createPager(start, limit);
		List<Noplog> list = dao.query(Noplog.class, cnd, pager);
		return list;
	}
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public int getCount(String colName,String value){
		Condition  cnd = null;
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "=",value);
		}
		return nOplogDao.searchCount(Noplog.class, cnd);
	}
	
	/**
	 * 业务数据查询
	 * @param btype 业务类型
	 * @param colName 字段名称
	 * @param value 字段值
	 * @param start 起始位置
	 * @param limit 分页数量
	 * @return
	 */
	public List<Noplog> search(String loginanem,String colName,String value,int start,int limit){
		Condition  cnd = null;
		ExpGroup e1 = Cnd.exps("d_loginname", "=", loginanem);
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "=",value).and(e1);
		}
		start = start/limit+1;
		if(start == 0){
			start = 1;
		}
		Pager pager = dao.createPager(start, limit);
		List<Noplog> list = dao.query(Noplog.class, cnd, pager);
		return list;
	}
	
	/**
	 * 业务数据统计
	 * @param btype 业务类型
	 * @param colName 字段名
	 * @param value 字段值
	 * @return
	 */
	public int getCount(String loginanem,String colName,String value){
		Condition  cnd = null;
		ExpGroup e1 = Cnd.exps("d_loginname", "=", loginanem);
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "=", value).and(e1);
		}
		return nOplogDao.searchCount(Noplog.class, cnd);
	}

	
	public NOplogDao getNOplogDao() {
		return nOplogDao;
	}

	public void setNOplogDao(NOplogDao oplogDao) {
		nOplogDao = oplogDao;
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	
}
