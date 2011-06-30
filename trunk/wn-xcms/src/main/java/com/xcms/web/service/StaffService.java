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
import com.mss.dal.dao.ChannelDao;
import com.mss.dal.dao.StaffDao;
import com.mss.dal.domain.BDistrict;
import com.mss.dal.domain.Channel;
import com.mss.dal.domain.Staff;
import com.mss.dal.domain.Tac;

@IocBean
@InjectName
public class StaffService {
	
	private static Log log = LogFactory.getLog(StaffService.class);
	
	@Inject
	private StaffDao staffDao;
	
	@Inject
	private ChannelDao channelDao;
	
	@Inject
	private BDistrictDao bDistrictDao;
	
	@Inject
	private Dao dao;
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public int getCount(String colName,String value){
		Condition  cnd = null;
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "like", "%"+value+"%");
		}
		return staffDao.searchCount(Staff.class, cnd);
	}
	
	/**
	 * 分页查询
	 * @param phonenum
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Staff> search(String colName,String value,int start,int limit){
		Condition  cnd = null;
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "like", "%"+value+"%");
		}
		start = start/limit+1;
		if(start == 0){
			start = 1;
		}
		Pager pager = dao.createPager(start, limit);
		List<Staff> list =  dao.query(Staff.class, cnd, pager);
		return list;
	}
	
	/**
	 * 根据用户名查找用户对象
	 * @param username
	 * @return
	 */
	public Staff findByName(String username){
		Staff sta = null;
		Cnd condition = null;
		try{
			condition = Cnd.where("d_username", "=", username); //.and("d_status", "=", "1")
			sta = staffDao.findByCondition(Staff.class, condition);
		}catch(Exception e){
			log.error(e);
		}
		return sta;
	}

	/**
	 * 根据渠道编码获取渠道对象
	 * @param code
	 * @return
	 */
	public Channel findByChannelCode(String code){
		Channel channel = null;
		Cnd condition = null;
		try{
			condition = Cnd.where("d_channel_code", "=", code).and("d_status", "=", "1");
			channel = channelDao.findByCondition(Channel.class, condition);
		}catch(Exception e){
			log.error(e);
		}
		return channel;
	}
	
	/**
	 * 根据代码获取业务区局对象
	 * @param code
	 * @return
	 */
	public BDistrict findByCode(String code){
		BDistrict bdistrict = null;
		Cnd condition = null;
		try{
			condition = Cnd.where("d_code", "=", code).and("d_status", "=", "1");
			bdistrict = bDistrictDao.findByCondition(BDistrict.class,condition);
		}catch(Exception e){
			log.error(e);
		}
		return bdistrict;
	}
	
	/**
	 * 添加员工
	 * @param staff
	 * @return
	 */
	public boolean add(Staff staff){
		try{
			if(null != staff.getId() && staff.getId() > 0){
				log.debug("update staff");
				return update(staff);
			}else{
				staff = staffDao.save(staff);
				if(staff.getId() > 0){
					return true;
				}else{
					return false;
				}
			}
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
		try{
			if(staffDao.delById(id, Staff.class)){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			log.error(e);
			return false;
		}
	}
	
	/**
	 * 启用员工
	 * @param id
	 * @return
	 */
	public boolean enable(Integer id){
		boolean b = false;
		try{
			Staff staff = findById(id);
			if(null != staff){
				if(staff.getStatus() != 1){
					staff.setStatus(1);
					b = update(staff);
				}
			}
		}catch(Exception e){
			log.error(e);
		}
		return b;
	}
	
	/**
	 * 禁用员工
	 * @param id
	 * @return
	 */
	public boolean disable(Integer id){
		boolean b = false;
		try{
			Staff staff = findById(id);
			if(null != staff){
				if(staff.getStatus() != 0){
					staff.setStatus(0);
					b = update(staff);
				}
			}
		}catch(Exception e){
			log.error(e);
		}
		return b;
	}
	
	/**
	 * 根据员工ID查找记录
	 * @param id
	 * @return
	 */
	public Staff findById(Integer id){
		return staffDao.find(id, Staff.class);
	}
	
	/**
	 * 修改员工信息
	 * @param staff
	 * @return boolean true:成功 false:失败
	 */
	public boolean update(Staff staff){
		try{
			return staffDao.update(staff);
		}catch(Exception e){
			log.error(e);
			return false;
		}
	}
	
	public StaffDao getStaffDao() {
		return staffDao;
	}

	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}

	public ChannelDao getChannelDao() {
		return channelDao;
	}

	public void setChannelDao(ChannelDao channelDao) {
		this.channelDao = channelDao;
	}

	public BDistrictDao getBDistrictDao() {
		return bDistrictDao;
	}

	public void setBDistrictDao(BDistrictDao districtDao) {
		bDistrictDao = districtDao;
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	
}
