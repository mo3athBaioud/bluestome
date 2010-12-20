package com.sky.commons.channel.service;

import com.sky.commons.channel.domain.ChannelFileInfo;

/**
 * @description:ͨ�����ҵ��ӿ�
 * @author:<E-mail="zjf@email.sky-mobi.com"/>
 * @create Date:May 4, 2009
 * @modify:
 */
public interface IChannelService {

	public ChannelFileInfo getChannelFileByIp(String ip);
	
	public ChannelFileInfo getChannelFileByProvinceCode(String provinceCode);
	
	/**
	 * ����mcc��ȡͨ��
	 * @param provinceCode
	 * @return
	 */
	public ChannelFileInfo getChannelFileByMcc(String mcc);
	/**
	 * ����imsi��ȡͨ��
	 * @param provinceCode
	 * @return
	 */
	public ChannelFileInfo getChannelFileByImsi(String imsi);
	
}
