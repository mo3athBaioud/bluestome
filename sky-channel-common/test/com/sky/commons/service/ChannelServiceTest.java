package com.sky.commons.service;

import org.junit.*;

import com.sky.commons.channel.domain.ChannelFileInfo;
import com.sky.commons.channel.service.IChannelService;
import com.sky.commons.spring.factory.SkyBeanFactory;

public class ChannelServiceTest {

	IChannelService channelService = null;
	ChannelFileInfo channelFileInfo = null;
	
	@Before
	public void init(){
		channelService =(IChannelService)
		 SkyBeanFactory.getBean("context/commons-application-context.xml","com.sky.commons.channel.service.impl.ChannelServiceImpl");
		channelFileInfo = new 	ChannelFileInfo();
	}
	
	@After
	public void destory(){
		if(null != channelService)
			channelService = null;
		if(null != channelFileInfo)
			channelFileInfo = null;
	}
	@Test
	public void save(){
		 channelFileInfo  = channelService.getChannelFileByImsi("404021192212443");
		 Assert.assertNotNull(channelFileInfo);
		 System.out.println("result:"+channelFileInfo.toString());
	}
	
}
