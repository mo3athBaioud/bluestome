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

import com.mss.dal.dao.ChannelDao;
import com.mss.dal.domain.Channel;
import com.mss.dal.domain.Channel;
import com.mss.dal.domain.Staff;

@IocBean
@InjectName
public class ChannelService {

	private static Log log = LogFactory.getLog(ChannelService.class);

	@Inject
	private Dao dao;
	
	@Inject
	private ChannelDao channelDao;

	public ChannelDao getChannelDao() {
		return channelDao;
	}

	public void setChannelDao(ChannelDao channelDao) {
		this.channelDao = channelDao;
	}
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public int getCount(String colName,String value){
		Condition  cnd = null;
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "like", "%"+value+"%");
		}
		return channelDao.searchCount(Channel.class, cnd);
	}
	
	/**
	 * 分页查询
	 * @param phonenum
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Channel> search(String colName,String value,int start,int limit){
		Condition  cnd = null;
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "like", "%"+value+"%");
		}
		start = start/limit+1;
		if(start == 0){
			start = 1;
		}
		Pager pager = dao.createPager(start, limit);
		List<Channel> list =  dao.query(Channel.class, cnd, pager);
		return list;
	}

	/**
	 * 获取总记录数
	 * @return
	 */
	public int getchListCount(String colName,String value){
		Condition  cnd = null;
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "=", value).and("status","=","1");
		}
		return channelDao.searchCount(Channel.class, cnd);
	}
	
	/**
	 * 可用的渠道列表
	 * @param phonenum
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Channel> chlist(String colName,String value,int start,int limit){
		Condition  cnd = null;
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "=", value).and("status", "=", "1");
		}
		start = start/limit+1;
		if(start == 0){
			start = 1;
		}
		Pager pager = dao.createPager(start, limit);
		List<Channel> list =  dao.query(Channel.class, cnd, pager);
		return list;
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
			//.and("d_status", "=", "1")
			condition = Cnd.where("d_channel_code", "=", code);
			channel = channelDao.findByCondition(Channel.class, condition);
		}catch(Exception e){
			log.error(e);
		}
		return channel;
	}
	/**
	 * 添加员工
	 * @param channel
	 * @return
	 */
	public boolean add(Channel channel){
		try{
			channel = channelDao.save(channel);
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
	 * @param channel
	 * @return boolean true:成功 false:失败
	 */
	public boolean update(Channel channel){
		try{
			return channelDao.update(channel);
		}catch(Exception e){
			log.error(e);
			return false;
		}
	}

	/**
	 * 启用渠道
	 * @param id
	 * @return
	 */
	public boolean enable(String code){
		boolean b = false;
		try{
			Channel channel = findByChannelCode(code);
			if(null != channel){
				if(channel.getStatus() != 1){
					channel.setStatus(1);
					b = update(channel);
				}
			}
		}catch(Exception e){
			log.error(e);
		}
		return b;
	}
	
	/**
	 * 禁用渠道
	 * @param id
	 * @return
	 */
	public boolean disable(String code){
		boolean b = false;
		try{
			Channel channel = findByChannelCode(code);
			if(null != channel){
				if(channel.getStatus() != 0){
					channel.setStatus(0);
					b = update(channel);
				}
			}
		}catch(Exception e){
			log.error(e);
		}
		return b;
	}
	
}
