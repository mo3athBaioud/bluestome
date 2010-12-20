package com.sky.commons.ip.bean;

import java.io.Serializable;

import com.sky.commons.config.SystemConfig;

//@table(name="bs_ip_province")
/**
 * 
create table BS_IP_PROVINCE
(
  ID            NUMBER not null,
  IP_START_NUM  NUMBER(12) not null,
  IP_END_NUM    NUMBER(12) not null,
  IP_START      VARCHAR2(20 CHAR),
  IP_END        VARCHAR2(20 CHAR),
  PROVINCE_NAME VARCHAR2(40),
  PROVINCE_ID   NUMBER(10),
  PROVIDER_ID   NUMBER(10)
)
 */
public class IpProvinceRelation implements Serializable,Comparable<IpProvinceRelation>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6950911591723639777L;
	/**
	 * 
	 */
	private Long ipStartNum;

    private Long ipEndNum;

    private String ipStart;

    private String ipEnd;

    private String provinceName;

    private String province;

    private Short provider;

    public static IpProvinceRelation getDefaultInstance(){
    	IpProvinceRelation instance = new IpProvinceRelation();
    	instance.setIpStart("0.0.0.0");
    	instance.setIpEnd("999.999.999.999");
    	instance.setIpStartNum(0L);
    	instance.setIpEndNum(99999999999999L);
    	instance.setProvinceName("China");
    	instance.setProvince(SystemConfig.default_province);
    	instance.setProvider((short)0);
    	return instance;
    }
    
	public Long getIpStartNum() {
		return ipStartNum;
	}

	public void setIpStartNum(Long ipStartNum) {
		if(ipStartNum==null){
			ipStartNum = 0L;
		}
		this.ipStartNum = ipStartNum;
	}

	public Long getIpEndNum() {
		return ipEndNum;
	}

	public void setIpEndNum(Long ipEndNum) {
		if(ipEndNum==null){
			ipEndNum = 0L;
		}
		this.ipEndNum = ipEndNum;
	}

	public String getIpStart() {
		return ipStart;
	}

	public void setIpStart(String ipStart) {
		this.ipStart = ipStart;
	}

	public String getIpEnd() {
		return ipEnd;
	}

	public void setIpEnd(String ipEnd) {
		this.ipEnd = ipEnd;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Short getProvider() {
		return provider;
	}

	public void setProvider(Short provider) {
		this.provider = provider;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[ipStartNum = ").append(ipStartNum).append("] ");
		buffer.append("[ipEndNum = ").append(ipEndNum).append("] ");
		buffer.append("[ipStart = ").append(ipStart).append("] ");
		buffer.append("[ipEnd = ").append(ipEnd).append("] ");
		buffer.append("[provinceName = ").append(provinceName).append("] ");
		buffer.append("[province = ").append(province).append("] ");
		buffer.append("[provider = ").append(provider).append("] ");
		return buffer.toString();
	}

	public int compareTo(IpProvinceRelation o) {
		int result = 0;
		if(this.ipStartNum>o.getIpStartNum()){
			result=1;
		}else if(this.ipStartNum==o.getIpStartNum()){
			result=0;
		}else{
			result = -1;
		}
		return result;
	}

	

}
