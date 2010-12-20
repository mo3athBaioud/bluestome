package com.sky.commons.channel.dao.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sky.commons.channel.dao.IChannelDAO;
import com.sky.commons.channel.domain.ChannelFileInfo;

public class ChannelDaoImpl extends SqlMapClientDaoSupport implements
		IChannelDAO {

	public ChannelFileInfo getChannelFileInfo(String provinceCode) {
		return (ChannelFileInfo) getSqlMapClientTemplate().queryForObject("getChannelFileInfo",provinceCode);
	}

	public int saveChannelFileInfo(ChannelFileInfo channelFileInfo) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("returnCode",150);
		param.put("provinceCode", channelFileInfo.getProvinceCode());
		param.put("fileName", channelFileInfo.getFileName());
		param.put("fileBytes", channelFileInfo.getFileBytes());
		param.put("startPrice", channelFileInfo.getStartPrice());
		param.put("endPrice", channelFileInfo.getEndPrice());
		param.put("starttime", channelFileInfo.getStartTime());
		param.put("endtime", channelFileInfo.getEndTime());
		Object obj = getSqlMapClientTemplate().queryForObject("saveChannelFileInfoProcedure",param);
		System.out.println(">>"+param.get("returnCode"));
		return 200;
	}


}
