package com.sky.commons.dao;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;
import com.sky.commons.channel.dao.IHsmanChannelDAO;
import com.sky.commons.channel.domain.HsmanChannelFileInfo;
import com.sky.commons.spring.factory.SkyBeanFactory;

public class IHsmanChannelDAOTest {

	IHsmanChannelDAO channelDAO = null;

	HsmanChannelFileInfo channelFileInfo = null;

	@Before
	public void init() {
		channelDAO = (IHsmanChannelDAO) SkyBeanFactory.getBean(
				"context/commons-application-context.xml", "com.sky.commons.channel.dao.ibatis.HsmanChannelDaoImpl");
		 channelFileInfo = new HsmanChannelFileInfo();
	}

	@After
	public void destory() {
		if(null != channelDAO)
			channelDAO = null;
		if(null != channelFileInfo)
			channelFileInfo = null;
	}
	
	@Test
	public void save() throws Exception{
	 channelFileInfo.setMcc("460");
	 channelFileInfo.setHsman("skytest");
	 channelFileInfo.setFileBytes(new byte[1024]);
	 channelFileInfo.setFileName("123456789123");
	 channelFileInfo.setStartPrice(0);
	 channelFileInfo.setEndPrice(100);
//	 channelFileInfo.setStartTime(new Date());
//	 channelFileInfo.setEndTime(new Date());
	 System.out.println(channelFileInfo.toString());
	 int result = channelDAO.saveHsmanChannelFileInfo(channelFileInfo);
	 Assert.assertNotNull(result);
	 System.out.print("result:"+result);
	}

}
