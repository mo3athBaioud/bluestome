package com.sky.commons.ip.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.sky.commons.cache.ICache;
import com.sky.spirit.basic.cache.Cache;
import com.sky.spirit.basic.exception.CacheException;
import com.sky.commons.config.SystemConfig;
import com.sky.commons.ip.bean.IpProvinceRelation;
import com.sky.commons.ip.dao.IipDao;
import com.sky.commons.ip.service.IipService;

public class IpServiceImpl implements IipService {

	private IipDao ipDao;
	private Cache cache;
	private static String IP_INTERZONE = SystemConfig.cache_key_ip_province;
	private static final Logger logger = LoggerFactory
			.getLogger(IpServiceImpl.class);

	@SuppressWarnings("unchecked")
	public List<IpProvinceRelation> getIpProvinceRelationsWithCache() throws CacheException{
		List<IpProvinceRelation> ippRelations = (List<IpProvinceRelation>) cache
				.get(IP_INTERZONE);
		if (ippRelations == null) {
			ippRelations = this.getIpProvinceRelations();
			if (ippRelations != null){
				try{
//					cache.set(IP_INTERZONE, ippRelations, SystemConfig.cache_object_live_time_ip_province);
					cache.put(IP_INTERZONE, ippRelations);
					logger.debug("ipProvinceRelation list is set to cache success!");
				}catch(CacheException e){
					logger.error("ipProvinceRelation list is set to cache failure!");
				}
			}
		} else {
			logger.debug("get ip province relations from cache!");
		}
		return ippRelations;
	}

	public List<IpProvinceRelation> getIpProvinceRelations() {
		List<IpProvinceRelation> ippRelations = null;
		try {
			// sql��ѯʱ��������
			ippRelations = ipDao.getIpProvinceRelations();
		} catch (Exception e) {
			ippRelations = null;
			logger.error("getIpProvinceRelations error={}", e);
		}
		return ippRelations;
	}

	private IpProvinceRelation getProvinceByIp(long ipValue,
			List<IpProvinceRelation> ippList) {
		if (ippList == null) {
			return IpProvinceRelation.getDefaultInstance();
		}
		IpProvinceRelation ipp = null;
		int low = 0;
		int high = ippList.size() - 1;
		int mid = 0;
		while (low <= high) {
			mid = (high + low) / 2;
			ipp = ippList.get(mid);
			if (ipp.getIpStartNum() <= ipValue && ipp.getIpEndNum() >= ipValue) {
				return ipp;
			}
			if (ipp.getIpEndNum() < ipValue) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return IpProvinceRelation.getDefaultInstance();
	}

	/**
	 * ipת������ֵ
	 * 
	 * @param ip
	 * @return
	 */
	private static Long convertIP2Num(String ip) {
		long ipl = 0;
		String s1[] = ip.split("\\.");
		ipl += ((new Long(s1[0])) * 256 * 256 * 256);
		ipl += ((new Long(s1[1])) * 256 * 256);
		ipl += ((new Long(s1[2])) * 256);
		ipl += ((new Long(s1[3])));
		return (ipl);
	}

	/**
	 * ���ip��ȡ��Ӧ��ʡ����Ӫ����Ϣ,ƥ�䲻������null step1:convert ip to number step2:get all the
	 * relations between ip and province
	 * 
	 */
	public IpProvinceRelation getProvinceRelationByIp(String ip) {
		// ip ת����num
		long ipValue = convertIP2Num(ip);
		List<IpProvinceRelation> iprs = null;
		if (SystemConfig.cache_valid) {
			try{
				iprs = getIpProvinceRelationsWithCache();
			}catch(CacheException e){
				logger.error(">> getIpProvinceRelations from cache failure!");
			}
		}else{
			iprs = getIpProvinceRelations();
		}
		// get ipProvinceRelation
		return this.getProvinceByIp(ipValue, iprs);
	}

	public void setIpDao(IipDao ipDao) {
		this.ipDao = ipDao;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

}
