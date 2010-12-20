package com.sky.commons.channel.service;

import com.sky.commons.channel.domain.ChannelFileInfo;

/**
 * @description:通道相关业务接口
 * @author:<E-mail="zjf@email.sky-mobi.com"/>
 * @create Date:May 4, 2009
 * @modify:
 */
public interface IChannelService {

	public ChannelFileInfo getChannelFileByIp(String ip);
	
	public ChannelFileInfo getChannelFileByProvinceCode(String provinceCode);
	
	/**
	 * 根据mcc获取通道
	 * @param provinceCode
	 * @return
	 */
	public ChannelFileInfo getChannelFileByMcc(String mcc);
	/**
	 * 根据imsi获取通道
	 * @param provinceCode
	 * @return
	 */
	public ChannelFileInfo getChannelFileByImsi(String imsi);
	
}
