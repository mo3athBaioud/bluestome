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

import com.mss.dal.dao.BussinessSimplifyDao;
import com.mss.dal.domain.BussinessSimplify;

@IocBean
@InjectName
public class BussinessSimplifyService {

	private static Log log = LogFactory.getLog(BussinessSimplifyService.class);
	@Inject
	private BussinessSimplifyDao bussinessSimplifyDao;
	
	@Inject
	private Dao dao;

	/**
	 * 添加记录业务记录
	 * @param bus
	 * @return
	 */
	public boolean save(BussinessSimplify bus){
		boolean b = false;
		try{
			
		}catch(Exception e){
			log.error("BussinessService.save.Exception:"+ e);
			b = false;
		}
		return b;
	}
	
	/**
	 * 适配对象
	 * @param strs
	 * @param btype
	 * @return
	 */
	public BussinessSimplify adapter(String[] strs,int btype){
		BussinessSimplify bus = null;
		try{
			if(strs.length == 7){
				bus = new BussinessSimplify();
				bus.setBtype(btype);
				bus.setBdistrict(strs[0]);
				bus.setPhonenum(strs[1]);
				bus.setSupport(Integer.valueOf(strs[5]));
				bus.setSuuporttype(Integer.valueOf(strs[6]));
			}
		}catch(Exception e){
			log.error(" >> BussinessService.adapter.exception:"+e);
		}
		return bus;
	}

	/**
	 * 分页查询
	 * @param phonenum
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<BussinessSimplify> search(String colName,String value,int start,int limit){
		Condition  cnd = null;
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "like", "%"+value+"%");
		}
		start = start/limit+1;
		if(start == 0){
			start = 1;
		}
		Pager pager = dao.createPager(start, limit);
		List<BussinessSimplify> list = dao.query(BussinessSimplify.class, cnd, pager);
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
		return bussinessSimplifyDao.searchCount(BussinessSimplify.class, cnd);
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
	public List<BussinessSimplify> search(int btype,String colName,String value,int start,int limit){
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
		List<BussinessSimplify> list = dao.query(BussinessSimplify.class, cnd, pager);
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
		return bussinessSimplifyDao.searchCount(BussinessSimplify.class, cnd);
	}
	
	/**
	 * 检查是否存在相同的业务类型下的数据
	 * @param phonenum
	 * @param btype
	 * @return
	 */
	public int checkUnique(String imei,String phonenum,int btype){
		int c = 0;
		try{
			Condition  cnd = null;
			ExpGroup e0 = Cnd.exps("d_imei", "=", imei);
			ExpGroup e1 = Cnd.exps("d_btype", "=", btype);
			ExpGroup e2 = Cnd.exps("d_phonenum", "=", phonenum);
			cnd = Cnd.where(e1).and(e2).and(e0);
			c = bussinessSimplifyDao.searchCount(BussinessSimplify.class, cnd);
		}catch(Exception e){
			c = 1;
		}
		return c;
	}
	
	public BussinessSimplifyDao getBussinessSimplifyDao() {
		return bussinessSimplifyDao;
	}

	public void setBussinessSimplifyDao(BussinessSimplifyDao bussinessSimplifyDao) {
		this.bussinessSimplifyDao = bussinessSimplifyDao;
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	
}
