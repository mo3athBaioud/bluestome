package com.sky.commons.channel.dao;

import com.sky.commons.channel.domain.HsmanChannelFileInfo;

/**
 * @description:channel dao
 * @author:<E-mail="zjf@email.sky-mobi.com"/>
 * @create Date:May 4, 2009
 * @modify:
 */
public interface IHsmanChannelDAO {

	public HsmanChannelFileInfo getHsmanChannelFileInfo(String mcc,String hsman);
	public int saveHsmanChannelFileInfo(HsmanChannelFileInfo channelFileInfo) throws Exception;
	
}
