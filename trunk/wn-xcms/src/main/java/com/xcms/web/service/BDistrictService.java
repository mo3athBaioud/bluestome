package com.xcms.web.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.mss.dal.dao.BDistrictDao;
import com.mss.dal.domain.BDistrict;

@IocBean
@InjectName
public class BDistrictService {

	private static Log log = LogFactory.getLog(BDistrictService.class);

	@Inject
	private Dao dao;
	
	@Inject
	private BDistrictDao bDistrictDao;

	/**
	 * 获取总记录数
	 * @return
	 */
	public int getCount(String colName,String value){
		Condition  cnd = null;
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "like", "%"+value+"%");
		}
		return bDistrictDao.searchCount(BDistrict.class, cnd);
	}
	
	/**
	 * 分页查询
	 * @param phonenum
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<BDistrict> search(String colName,String value,int start,int limit){
		Condition  cnd = null;
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "like", "%"+value+"%");
		}
		start = start/limit+1;
		if(start == 0){
			start = 1;
		}
		Pager pager = dao.createPager(start, limit);
		List<BDistrict> list =  dao.query(BDistrict.class, cnd, pager);
		return list;
	}

	/**
	 * 根据渠道编码获取渠道对象
	 * @param code
	 * @return
	 */
	public BDistrict findByChannelCode(String code){
		BDistrict BDistrict = null;
		Cnd condition = null;
		try{
			condition = Cnd.where("d_channel_code", "=", code).and("d_status", "=", "1");
			BDistrict = bDistrictDao.findByCondition(BDistrict.class, condition);
		}catch(Exception e){
			log.error(e);
		}
		return BDistrict;
	}
	/**
	 * 添加员工
	 * @param BDistrict
	 * @return
	 */
	public boolean add(BDistrict BDistrict){
		try{
			BDistrict = bDistrictDao.save(BDistrict);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * 根据员工ID删除员工
	 * @param id
	 * @return
	 */
	public boolean deleteStaff(Integer id){
		Condition  cnd = null;
		try{
			return false;
		}catch(Exception e){
			log.error(e);
			return false;
		}
	}
	
	/**
	 * 修改员工信息
	 * @param BDistrict
	 * @return boolean true:成功 false:失败
	 */
	public boolean update(BDistrict BDistrict){
		try{
			return bDistrictDao.update(BDistrict);
		}catch(Exception e){
			log.error(e);
			return false;
		}
	}
	
}
