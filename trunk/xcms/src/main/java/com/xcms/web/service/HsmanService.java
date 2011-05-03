package com.xcms.web.service;

import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.mss.dal.dao.HsmanDao;
import com.mss.dal.domain.Hsman;
import com.mss.dal.domain.HsmanTree;

/**
 * 厂商管理类
 * @author bluestome
 *
 */
@IocBean
@InjectName
public class HsmanService {

	@Inject
	private HsmanDao hsmanDao;
	
	/**
	 * 查找可用的厂商列表
	 * @param status
	 * @return
	 */
	public List<Hsman> findAll(int status){
		if(false){
			//从缓存中查找厂商列表
		}
		return hsmanDao.findAll(status);
	}

	/**
	 * 查找可用的厂商列表
	 * @param status
	 * @return
	 */
	public List<HsmanTree> tree(int status){
		List<HsmanTree> list = new ArrayList<HsmanTree>();
		HsmanTree ht = null;
		for(Hsman hs:findAll(status)){
			ht = new HsmanTree();
			ht.setId(hs.getId());
			ht.setText(hs.getName());
			ht.setIcon(hs.getIcon());
			list.add(ht);
		}
		return list;
	}
	
	
	public HsmanDao getHsmanDao() {
		return hsmanDao;
	}

	public void setHsmanDao(HsmanDao hsmanDao) {
		this.hsmanDao = hsmanDao;
	}
}