package com.sky.commons.test;

import org.apache.commons.lang.StringEscapeUtils;

import com.sky.commons.channel.dao.IChannelDAO;
import com.sky.commons.channel.domain.ChannelFileInfo;
import com.sky.commons.channel.service.IChannelService;
import com.sky.commons.ip.dao.IipDao;
import com.sky.commons.spring.factory.SkyBeanFactory;

public class DaoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(StringEscapeUtils.escapeXml("<+"));
		 IChannelDAO dao = (IChannelDAO)
		 SkyBeanFactory.getBean("context/commons-application-context.xml","channelDao");
		 IipDao ipdao = (IipDao)
		 SkyBeanFactory.getBean("context/commons-application-context.xml","ipDao");
		 System.out.println(dao.getChannelFileInfo("000000"));
		 ChannelFileInfo channelFileInfo = new ChannelFileInfo();
		 channelFileInfo.setProvinceCode("000000");
		 channelFileInfo.setFileBytes(new byte[600]);
		 channelFileInfo.setFileName("123456");
		 channelFileInfo.setStartPrice(0);
		 channelFileInfo.setEndPrice(10000);
		 System.out.println(dao.saveChannelFileInfo(channelFileInfo));
    	 System.out.println(ipdao.getIpProvinceRelations());
//		IChannelService service = (IChannelService) SkyBeanFactory.getBean(
//				"context/commons-application-context.xml", "channelService");
//		System.out
//				.println(service.getChannelFileByProvinceCode("000000").length);
//		System.out.println(service.getChannelFileByIp("123.123.123.1").length);
		try {
			SkyBeanFactory.getBean(
					"support/cache/ehcache.xml", "commons-ehCache");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
