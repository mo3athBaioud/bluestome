package com.xcms.web.service;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.mss.dal.dao.TmpHpgjDao;
import com.mss.dal.domain.TmpHpgj;

/**
 * 号谱管家
 * @author bluestome
 *
 */
@IocBean
@InjectName
public class TmpHpgjService {

	@Inject
	private Dao dao;
	
	@Inject
	private TmpHpgjDao tmpHpgjDao;
	
	/**
	 * 分页查询
	 * @param phonenum
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<TmpHpgj> search(String phonenum,int start,int limit) throws Exception{
		Cnd condition = null;
		if(null != phonenum && !"".equals(phonenum)){
			condition = Cnd.where("d_phone_number","=",phonenum);
		}
		start = start/limit+1;
		if(start == 0){
			start = 1;
		}
		List<TmpHpgj> list = tmpHpgjDao.searchByPage(TmpHpgj.class, condition, start, limit);
		return list;
		
	}
	
	/**
	 * 分页查询
	 * @param phonenum
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<TmpHpgj> search(String phonenum,String loginName,int start,int limit) throws Exception{
		Cnd condition = null;
		if(null != phonenum && !"".equals(phonenum)){
			condition = Cnd.where("d_phone_number","=",phonenum).and("d_uid","=",loginName.replace("weinan2", "weinan1"));
			start = start/limit+1;
			if(start == 0){
				start = 1;
			}
			List<TmpHpgj> list = tmpHpgjDao.searchByPage(TmpHpgj.class, condition, start, limit);
			return list;
		}else{
			return null;
		}
	}
	
	public Dao getDao() {
		return dao;
	}
	
	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public TmpHpgjDao getTmpHpgjDao() {
		return tmpHpgjDao;
	}

	public void setTmpHpgjDao(TmpHpgjDao tmpHpgjDao) {
		this.tmpHpgjDao = tmpHpgjDao;
	}

	
}
