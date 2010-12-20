/**
 * 
 */
package com.sky.channel.sync;

import java.util.List;

import javax.jws.WebService;

import com.sky.channel.sync.bean.SyncFileBean;

/**
 * 无厂商属性的通道同步接口 [老接口]
 * @description:
 * @author:<E-mail="zjf@email.sky-mobi.com"/>
 * @create Date:Dec 8, 2009
 * @modify:
 */
@WebService
public interface ISyncChannelFile {
	
	int PARAM_INVALID = 1;
	int IO_FAILURE = 2;
	int DATA_ACCESS_FAIL = 3;
	int DEPLOY_IN_BUSINESS = 4;
	int SUCCESS = 200;
	
	/**
	 * sync channel file
	 * @param list channel file info list
	 * @return int [返回同步状态]
	 */
	public int synFile(List<SyncFileBean> list);

}
