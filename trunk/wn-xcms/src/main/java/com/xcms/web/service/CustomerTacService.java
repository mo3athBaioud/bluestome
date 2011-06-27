package com.xcms.web.service;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdNameEntityService;

import com.mss.dal.dao.CustomerTacDao;
import com.mss.dal.domain.CustomerTac;

@IocBean
public class CustomerTacService extends IdNameEntityService {

	@Inject
	private CustomerTacDao customerTacDao;

	/**
	 * 添加客户数据
	 * @param t
	 * @return
	 */
	public CustomerTac save(CustomerTac t){
		Cnd condition = Cnd.where("d_phone_num", "=", t.getPhoneNum()).and("d_imei", "=", t.getImei());
		CustomerTac tmp = customerTacDao.findByCondition(CustomerTac.class, condition);
		if(null == tmp){
			t.setTac(t.getImei().substring(0,8));
			t = customerTacDao.save(t);
		}
		return t;
	}
	
	public CustomerTacDao getCustomerTacDao() {
		return customerTacDao;
	}

	public void setCustomerTacDao(CustomerTacDao customerTacDao) {
		this.customerTacDao = customerTacDao;
	}
	
	
}
