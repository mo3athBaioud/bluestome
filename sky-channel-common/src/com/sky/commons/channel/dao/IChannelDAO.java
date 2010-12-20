package com.sky.commons.channel.dao;

import com.sky.commons.channel.domain.ChannelFileInfo;

/**
 * @description:channel dao
 * @author:<E-mail="zjf@email.sky-mobi.com"/>
 * @create Date:May 4, 2009
 * @modify:
 */
public interface IChannelDAO {

	//��ȡ�Ʒ�ͨ���ļ�
	public ChannelFileInfo getChannelFileInfo(String provinceCode);
	//����Ʒ�ͨ���ļ�
	public int saveChannelFileInfo(ChannelFileInfo channelFileInfo);
	
}
