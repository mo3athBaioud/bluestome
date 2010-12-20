package com.sky.commons.dao;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;
import com.sky.commons.channel.dao.IChannelDAO;
import com.sky.commons.channel.domain.ChannelFileInfo;
import com.sky.commons.spring.factory.SkyBeanFactory;

public class IChannelDAOTest {
	
	IChannelDAO dao = null;
	ChannelFileInfo channelFileInfo = null;
	
	@Before
	public void init(){
		dao =(IChannelDAO)
		 SkyBeanFactory.getBean("context/commons-application-context.xml","com.sky.commons.channel.dao.ibatis.ChannelDaoImpl");
		channelFileInfo = new 	ChannelFileInfo();
	}
	
	@After
	public void destory(){
		if(null != dao)
			dao = null;
		if(null != channelFileInfo)
			channelFileInfo = null;
	}
	@Test
	public void save(){
		 channelFileInfo.setProvinceCode("110");
		 channelFileInfo.setFileBytes(new byte[600]);
		 channelFileInfo.setFileName("123456");
		 channelFileInfo.setStartPrice(0);
		 channelFileInfo.setEndPrice(100);
		 int result = dao.saveChannelFileInfo(channelFileInfo);
		 Assert.assertNotNull(result);
		 System.out.println("result:"+result);
	}
}
