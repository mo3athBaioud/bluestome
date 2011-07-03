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

import com.mss.dal.dao.TerminalPropertyDao;
import com.mss.dal.domain.Staff;
import com.mss.dal.domain.TerminalProperty;

@IocBean
@InjectName
public class TerminalPropertyService {

	private static Log log = LogFactory.getLog(TerminalPropertyService.class);
	
	@Inject
	private TerminalPropertyDao terminalPropertyDao;
	
	@Inject
	private Dao dao;
	
	/**
	 * 添加终端属性
	 * @param staff
	 * @return
	 */
	public boolean add(TerminalProperty obj){
		try{
			if(null != obj.getId() && obj.getId() > 0){
				log.debug("update TerminalProperty");
				return update(obj);
			}else{
				obj = terminalPropertyDao.save(obj);
				if(obj.getId() > 0){
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
	 * 修改终端属性信息
	 * @param staff
	 * @return boolean true:成功 false:失败
	 */
	public boolean update(TerminalProperty obj){
		try{
			return terminalPropertyDao.update(obj);
		}catch(Exception e){
			log.error(e);
			return false;
		}
	}
	
	/**
	 * 分页查询
	 * @param phonenum
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<TerminalProperty> search(String colName,String value,int start,int limit){
		Condition  cnd = null;
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "like", "%"+value+"%");
		}
		start = start/limit+1;
		if(start == 0){
			start = 1;
		}
		Pager pager = dao.createPager(start, limit);
		List<TerminalProperty> list =  dao.query(TerminalProperty.class, cnd, pager);
		return list;
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
		return terminalPropertyDao.searchCount(TerminalProperty.class, cnd);
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public TerminalPropertyDao getTerminalPropertyDao() {
		return terminalPropertyDao;
	}

	public void setTerminalPropertyDao(TerminalPropertyDao terminalPropertyDao) {
		this.terminalPropertyDao = terminalPropertyDao;
	}
	
	
}
