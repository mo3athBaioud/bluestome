package com.xcms.web.service;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.mss.dal.dao.CallingLogDao;
import com.mss.dal.dao.CustomerTacDao;
import com.mss.dal.dao.TacDao;
import com.mss.dal.view.ViewTerminal;

/**
 * 用户终端查询业务类
 * @author bluestome
 *
 */
@IocBean
public class UtpService {
	
	@Inject
	private Dao dao;

	@Inject
	private TacDao tacDao;
	
	@Inject
	private CallingLogDao callingLogDao;
	
	@Inject
	private CustomerTacDao customerTacDao;
	
	/**
	 * 根据手机号码查找数据
	 * @param phoneNum
	 * @return
	 */
	public ViewTerminal search(String phoneNum){
		Cnd condition = Cnd.where("d_phone_num","=",phoneNum);
		if(null != tacDao){
			ViewTerminal v = tacDao.findByCondition(ViewTerminal.class, condition);
			return v;
		}else{
			return null;
		}
	}
	
	/**
	 * 分页查询
	 * @param phonenum
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<ViewTerminal> search(String phonenum,int start,int limit){
		Cnd condition = Cnd.where("d_phone_num","=",phonenum);
		List<ViewTerminal> list = dao.query(ViewTerminal.class, condition, dao.createPager(start, limit));
		return list;
		
	}

	
	public CustomerTacDao getCustomerTacDao() {
		return customerTacDao;
	}

	public void setCustomerTacDao(CustomerTacDao customerTacDao) {
		this.customerTacDao = customerTacDao;
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public CallingLogDao getCallingLogDao() {
		return callingLogDao;
	}

	public void setCallingLogDao(CallingLogDao callingLogDao) {
		this.callingLogDao = callingLogDao;
	}

	public TacDao getTacDao() {
		return tacDao;
	}

	public void setTacDao(TacDao tacDao) {
		this.tacDao = tacDao;
	}
	

	
}
