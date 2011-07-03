package com.xcms.web.service;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.ExpGroup;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.mss.dal.dao.BussinessDao;
import com.mss.dal.domain.Bussiness;
import com.mss.dal.domain.TerminalProperty;

@IocBean
@InjectName
public class BussinessService {

	@Inject
	private BussinessDao bussinessDao;
	
	@Inject
	private Dao dao;


	/**
	 * 分页查询
	 * @param phonenum
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Bussiness> search(String colName,String value,int start,int limit){
		Condition  cnd = null;
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "like", "%"+value+"%");
		}
		start = start/limit+1;
		if(start == 0){
			start = 1;
		}
		Pager pager = dao.createPager(start, limit);
		List<Bussiness> list = dao.query(Bussiness.class, cnd, pager);
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
		return bussinessDao.searchCount(TerminalProperty.class, cnd);
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
	public List<Bussiness> search(int btype,String colName,String value,int start,int limit){
		Condition  cnd = null;
		ExpGroup e1 = Cnd.exps("d_btype", "=", btype);
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "=",value).and(e1);
		}
		start = start/limit+1;
		if(start == 0){
			start = 1;
		}
		Pager pager = dao.createPager(start, limit);
		List<Bussiness> list = dao.query(Bussiness.class, cnd, pager);
		return list;
	}
	
	/**
	 * 业务数据统计
	 * @param btype 业务类型
	 * @param colName 字段名
	 * @param value 字段值
	 * @return
	 */
	public int getCount(int btype,String colName,String value){
		Condition  cnd = null;
		ExpGroup e1 = Cnd.exps("d_btype", "=", btype);
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "=", value).and(e1);
		}
		return bussinessDao.searchCount(Bussiness.class, cnd);
	}
	
	public BussinessDao getBussinessDao() {
		return bussinessDao;
	}

	public void setBussinessDao(BussinessDao bussinessDao) {
		this.bussinessDao = bussinessDao;
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	
}
