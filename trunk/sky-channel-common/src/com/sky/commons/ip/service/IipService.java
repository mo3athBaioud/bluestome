package com.sky.commons.ip.service;

import com.sky.commons.ip.bean.IpProvinceRelation;

public interface IipService {

	/**
	 * ����ip��ȡip��Ӧ��ʡ����Ϣ
	 * @param ip
	 * @return
	 */
	public IpProvinceRelation getProvinceRelationByIp(String ip);
}
