package com.sky.commons.ip.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sky.commons.ip.bean.IpProvinceRelation;
import com.sky.commons.ip.dao.IipDao;

public class IpDaoImpl extends SqlMapClientDaoSupport implements IipDao {

	@SuppressWarnings("unchecked")
	public List<IpProvinceRelation> getIpProvinceRelations() {
		return getSqlMapClientTemplate().queryForList("selectIpProvinces");
	}

}
