package com.sky.commons.ip.service;

import com.sky.commons.ip.bean.IpProvinceRelation;

public interface IipService {

	/**
	 * 根据ip获取ip对应的省份信息
	 * @param ip
	 * @return
	 */
	public IpProvinceRelation getProvinceRelationByIp(String ip);
}
