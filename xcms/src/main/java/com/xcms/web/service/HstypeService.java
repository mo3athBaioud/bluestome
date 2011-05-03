package com.xcms.web.service;

import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.mss.dal.dao.HstypeDao;
import com.mss.dal.domain.Hstype;

@IocBean
@InjectName
public class HstypeService {
	
	@Inject
	private HstypeDao hstypeDao;
	
	/**
	 * 查找厂商下的机型列表
	 * @param hsmanId
	 * @return
	 */
	public List<Hstype> find(Integer hsmanId){
		Cnd condition = null;
		List<Hstype> list = new ArrayList<Hstype>();
		if( hsmanId > 0 ){
			condition = Cnd.where("d_hsman_id", "=", hsmanId);
		}else{
			condition = Cnd.where("d_hsman_id", "<>", hsmanId);
		}
		if(null != condition){
			condition.asc("d_name");
		}
		list = hstypeDao.search(Hstype.class, condition);
		return list;
	}

	public HstypeDao getHstypeDao() {
		return hstypeDao;
	}

	public void setHstypeDao(HstypeDao hstypeDao) {
		this.hstypeDao = hstypeDao;
	}
	
	
}
