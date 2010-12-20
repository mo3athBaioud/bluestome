package com.sky.commons.channel.service;

import com.sky.commons.channel.domain.HsmanChannelFileInfo;

/**
 * @description:厂商通道业务类
 * @author:<E-mail="bluestome.zhang@email.sky-mobi.com"/>
 * @create Date:Sept 9, 2010
 * @modify:
 */
public interface IHsmanChannelService {

	/**
	 * 根据国家码查找通道
	 * @param provinceCode
	 * @return
	 */
	public HsmanChannelFileInfo getHsmanChannelFileByHsman(String mcc,String hsman);
	
}
