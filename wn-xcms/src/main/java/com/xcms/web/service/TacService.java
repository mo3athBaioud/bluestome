package com.xcms.web.service;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.mss.dal.dao.TacDao;
import com.mss.dal.domain.Tac;
import com.mss.dal.view.ViewTerminal;

@IocBean
public class TacService {

	@Inject
	private TacDao tacDao;
	
	@Inject
	private Dao dao;
	
	/**
	 * 添加TAC对象
	 * @param t
	 * @return
	 */
	public boolean add(Tac t){
		boolean result = false;
		Tac tmp = null;
		try{
			tmp = tacDao.getByTac(t.getTac());
			if(null == tmp){
				t = tacDao.save(t);
				if(t.getId() > 0){
					//缓存处理
					result = true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return result;
	}

	/**
	 * 分页查询
	 * @param phonenum
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Tac> search(String colName,String value,int start,int limit){
		Condition  cnd = null;
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "like", "%"+value+"%");
		}
		start = start/limit+1;
		if(start == 0){
			start = 1;
		}
		Pager pager = dao.createPager(start, limit);
		List<Tac> list =  dao.query(Tac.class, cnd, pager);
		return list;
	}
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public int getCount(){
		return tacDao.searchCount(Tac.class);
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
		return tacDao.searchCount(Tac.class, cnd);
	}
	
	/**
	 * 更新记录
	 * @param tac
	 * @return
	 */
	public boolean update(Tac tac){
		return tacDao.update(tac);
	}
	
	public TacDao getTacDao() {
		return tacDao;
	}

	public void setTacDao(TacDao tacDao) {
		this.tacDao = tacDao;
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	
}
