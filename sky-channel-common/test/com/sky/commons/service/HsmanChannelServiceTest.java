package com.sky.commons.service;

import org.junit.*;

import com.sky.commons.channel.domain.HsmanChannelFileInfo ;
import com.sky.commons.channel.service.IHsmanChannelService;
import com.sky.commons.spring.factory.SkyBeanFactory;

public class HsmanChannelServiceTest {

	IHsmanChannelService channelService = null;
	HsmanChannelFileInfo  channelFileInfo = null;
	
	@Before
	public void init(){
		channelService =(IHsmanChannelService)
		 SkyBeanFactory.getBean("context/commons-application-context.xml","com.sky.commons.channel.service.impl.HsmanChannelServiceImpl");
	}
	
	@After
	public void destory(){
		if(null != channelService)
			channelService = null;
		if(null != channelFileInfo)
			channelFileInfo = null;
	}
	
	@Test
	public void find(){
		 channelFileInfo  = channelService.getHsmanChannelFileByHsman("460","skytest");
		 if(null != channelFileInfo){
			 Assert.assertNotNull(channelFileInfo);
			 System.out.println("result:"+channelFileInfo.toString());
		 }else{
			 System.out.println("数据为空");
		 }
	}
	
}
