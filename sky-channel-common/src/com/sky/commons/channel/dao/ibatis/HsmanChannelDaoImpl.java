package com.sky.commons.channel.dao.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sky.commons.channel.dao.IHsmanChannelDAO;
import com.sky.commons.channel.domain.HsmanChannelFileInfo;

public class HsmanChannelDaoImpl extends SqlMapClientDaoSupport implements
		IHsmanChannelDAO {

	public HsmanChannelFileInfo getHsmanChannelFileInfo(String mcc,String hsman) {
		HashMap<String,String> param = new HashMap<String,String>();
		param.put("mcc", mcc);
		param.put("hsman", hsman);
		return (HsmanChannelFileInfo) getSqlMapClientTemplate().queryForObject("getHsmanChannelFileInfo",param);
	}

	public int saveHsmanChannelFileInfo(HsmanChannelFileInfo channelFileInfo) throws Exception{
		HashMap<String,Object> param = new HashMap<String, Object>();
		param.put("mcc", channelFileInfo.getMcc());
		param.put("hsman", channelFileInfo.getHsman());
		param.put("fileName", channelFileInfo.getFileName());
		param.put("fileBytes", channelFileInfo.getFileBytes());
		param.put("startPrice", channelFileInfo.getStartPrice());
		param.put("endPrice", channelFileInfo.getEndPrice());
		param.put("starttime", channelFileInfo.getStartTime());
		param.put("endtime", channelFileInfo.getEndTime());
		getSqlMapClientTemplate().queryForObject("saveHsmanChannelFileInfoProcedure",param);
		Object obj = param.get("returnCode");
		Integer code = (Integer)obj;
		return code;
	}

}
