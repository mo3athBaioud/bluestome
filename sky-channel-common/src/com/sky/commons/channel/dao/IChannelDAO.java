package com.sky.commons.channel.dao;

import com.sky.commons.channel.domain.ChannelFileInfo;

/**
 * @description:channel dao
 * @author:<E-mail="zjf@email.sky-mobi.com"/>
 * @create Date:May 4, 2009
 * @modify:
 */
public interface IChannelDAO {

	//获取计费通道文件
	public ChannelFileInfo getChannelFileInfo(String provinceCode);
	//保存计费通道文件
	public int saveChannelFileInfo(ChannelFileInfo channelFileInfo);
	
}
