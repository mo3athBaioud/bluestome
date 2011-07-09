package com.xcms.web.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.ExpGroup;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.mss.dal.dao.TerminalCoreDao;
import com.mss.dal.domain.Bussiness;
import com.mss.dal.domain.TerminalCore;

@IocBean
@InjectName
public class TerminalCoreService {

	private static Log log = LogFactory.getLog(TerminalCoreService.class);
	
	@Inject
	private TerminalCoreDao terminalCoreDao;

	@Inject
	private Dao dao;
	
	/**
	 * 添加终端核心数据
	 * @param core
	 * @return
	 */
	public boolean add(TerminalCore core){
		boolean b = false;
		try{
			if(checkUnique(core.getTac()) > 0 && core.getId() > 0){
				//TODO 修改
				log.error(" >> 修改核心数据，暂未实现");
			}else{
				core = terminalCoreDao.save(core);
				if(core.getId() > 0){
					b = true;
				}
			}
		}catch(Exception e){
			log.error("TerminalCoreService.add.Exception:"+e);
		}
		return b;
	}
	
	/**
	 * 修改终端核心数据
	 * @param core
	 * @return
	 */
	public boolean update(TerminalCore core){
		boolean b = false;
		Condition  cnd = null;
		try{
			cnd = Cnd.where("d_tac", "=", core.getTac());
			if(core.getId() > 0){
//				b = terminalCoreDao.update(TerminalCore.class, Chain.from(core), cnd);
				b = terminalCoreDao.update(core);
			}
		}catch(Exception e){
			log.error("TerminalCoreService.update.Exception:"+e);
		}
		return b;
	}
	
	
	/**
	 * 分页查询
	 * @param phonenum
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<TerminalCore> search(String colName,String value,int start,int limit){
		Condition  cnd = null;
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "like", "%"+value+"%");
		}
		start = start/limit+1;
		if(start == 0){
			start = 1;
		}
		Pager pager = dao.createPager(start, limit);
		List<TerminalCore> list = dao.query(TerminalCore.class, cnd, pager);
		return list;
	}
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public int getCount(String colName,String value){
		Condition  cnd = null;
		if(null != colName && !"".equals(colName)){
			cnd = Cnd.where(colName, "=",value);
		}
		return terminalCoreDao.searchCount(TerminalCore.class, cnd);
	}
	
	/**
	 * 检查是否存在相同TAC的数据
	 * @param phonenum
	 * @param btype
	 * @return
	 */
	public int checkUnique(String tac){
		int c = 0;
		try{
			Condition  cnd = null;
			ExpGroup e0 = Cnd.exps("d_tac", "=", tac);
			cnd = Cnd.where(e0);
			c = terminalCoreDao.searchCount(TerminalCore.class, cnd);
		}catch(Exception e){
			c = 1;
		}
		return c;
	}
	
	/**
	 * 适配对象
	 * @param strs
	 * @param btype
	 * @return
	 */
	public TerminalCore adapter(String[] strs){
		TerminalCore tc = null;
		try{
			if(strs.length == 10){
				tc = new TerminalCore();
				tc.setTac(strs[0]);
				tc.setHsman(strs[1]);
				tc.setHsmanch(strs[2]);
				tc.setHstype(strs[3]);
				tc.setHstypech(strs[4]);
				tc.setGprs(Integer.valueOf(strs[5]));
				tc.setOs(Integer.valueOf(strs[6]));
				tc.setSmartphone(Integer.valueOf(strs[7]));
				tc.setWap(Integer.valueOf(strs[8]));
				tc.setWifi(Integer.valueOf(strs[9]));
			}
		}catch(Exception e){
			log.error(" >> BussinessService.adapter.exception:"+e);
		}
		return tc;
	}
	
	public TerminalCoreDao getTerminalCoreDao() {
		return terminalCoreDao;
	}

	public void setTerminalCoreDao(TerminalCoreDao terminalCoreDao) {
		this.terminalCoreDao = terminalCoreDao;
	}


	public Dao getDao() {
		return dao;
	}


	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	
}
