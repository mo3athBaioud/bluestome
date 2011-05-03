package com.xcms.web.service;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.mss.dal.dao.CallingLogDao;
import com.mss.dal.dao.CustomerTacDao;
import com.mss.dal.dao.TacDao;
import com.mss.dal.domain.Waplog;
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
			if(null == v){
				//如果没有从通话记录中找到匹配内容，则从WAP日志数据中匹配，看是否有相同数据
				Waplog w = tacDao.findByCondition(Waplog.class, condition);
				if(null != w){
					System.out.println(" >> find hsman,hstype from wap log");
					v = new ViewTerminal();
					v.setHsmanName(w.getHsmanName());
					v.setHstypeName(w.getHstypeName());
					v.setPhoneNum(w.getPhoneNum());
					return v;
				}
			}
			System.out.println(" >> find hsman,hstype from calling log");
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
