package com.mss.dal.dao;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

import com.mss.dal.domain.Hsman;

/**
 * 厂商DAO
 * @author bluestome
 *
 */
@IocBean
public class HsmanDao extends BasicDao {

	/**
	 * 查找所有激活的厂商
	 * @return
	 */
	public List<Hsman> findAll(Integer status){
		Cnd condition = Cnd.where("d_status", "=", status);
		condition.asc("d_name");
		return search(Hsman.class, condition);
	}
	
}
