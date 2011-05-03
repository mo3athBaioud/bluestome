package com.mss.dal.dao;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

import com.mss.dal.domain.Tac;

/**
 * TACDAO
 * @author bluestome
 *
 */
@IocBean
public class TacDao extends BasicDao {

	public boolean checkTac(String tac,Tac t){
		boolean b = true;
		Cnd condition = Cnd.where("d_tac", "=", tac);
		try{
			Tac tmp = findByCondition(Tac.class, condition);
			if(null != tmp){
				if(null == tmp.getHsmanNameEn() || "".equals(tmp.getHsmanNameEn())){
					tmp.setHsmanNameEn(t.getHsmanNameEn());
				}
				if(null == tmp.getHstypeNameEn() || "".equals(tmp.getHstypeNameEn())){
					tmp.setHstypeNameEn(t.getHstypeNameEn());
				}
				b = update(tmp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * 根据TAC来查找记录
	 * @param tac
	 * @return
	 */
	public Tac getByTac(String tac){
		Cnd condition = Cnd.where("d_tac", "=", tac);
		Tac tmp = findByCondition(Tac.class, condition);
		return tmp;
	}
}
