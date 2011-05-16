package com.xcms.web.service;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.mss.dal.dao.TmpGprsDao;
import com.mss.dal.domain.TmpGprs;
import com.mss.dal.domain.Waplog;
import com.mss.dal.view.ViewTerminal;

/**
 * GPRS查询
 * @author bluestome
 *
 */
@IocBean
@InjectName
public class TmpGprsService {

	@Inject
	private Dao dao;
	
	@Inject
	private TmpGprsDao tmpGprsDao;
	
	/**
	 * 分页查询
	 * @param phonenum
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<TmpGprs> search(String phonenum,int start,int limit) throws Exception{
		Cnd condition = null;
		if(null != phonenum && !"".equals(phonenum)){
			condition = Cnd.where("d_phone_number","=",phonenum);
		}
		start = start/limit+1;
		if(start == 0){
			start = 1;
		}
		List<TmpGprs> list = tmpGprsDao.searchByPage(TmpGprs.class, condition, start, limit);
		return list;
		
	}
	
	/**
	 * 分页查询
	 * @param phonenum
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<TmpGprs> search(String phonenum,String loginName,int start,int limit) throws Exception{
		Cnd condition = null;
		if(null != phonenum && !"".equals(phonenum)){
			condition = Cnd.where("d_phone_number","=",phonenum);
			if(null != loginName && !"".equals(loginName)){
				if(loginName.equals("weinan1")){
					condition.and("d_id","<","2367");
				}else if(loginName.equals("weinan2")){
					condition.and("d_id",">","2367").and("d_id","<","4735");
				}else if(loginName.equals("weinan3")){
					condition.and("d_id",">","4735").and("d_id","<","7102");
				}else if(loginName.equals("weinan5")){
					condition.and("d_id",">","7102");
				}
			}
			
			start = start/limit+1;
			if(start == 0){
				start = 1;
			}
			List<TmpGprs> list = tmpGprsDao.searchByPage(TmpGprs.class, condition, start, limit);
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

	public TmpGprsDao getTmpGprsDao() {
		return tmpGprsDao;
	}

	public void setTmpGprsDao(TmpGprsDao tmpGprsDao) {
		this.tmpGprsDao = tmpGprsDao;
	}
	
	
	
}
