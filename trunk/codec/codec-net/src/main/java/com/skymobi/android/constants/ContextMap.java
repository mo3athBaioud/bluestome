package com.skymobi.android.constants;

import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;
import com.skymobi.android.transport.util.MutableIdentifyable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

public class ContextMap {
	
	private static Logger logger = LoggerFactory.getLogger(ContextMap.class);

	private static Map<UUID,MutableIdentifyable> map = new HashMap<UUID,MutableIdentifyable>();
	
	private static Map<String,Object> TEMP_MAP = new HashMap<String,Object>();
	
	private static Vector ACCESS_TEMP_VECTOR = new Vector();
	
	public synchronized static void put(UUID key,MutableIdentifyable value){
		map.put(key,value);
	}
	
	public synchronized static void put(String key,Object value){
		TEMP_MAP.put(key, value);
	}
	
	public static void put2Vector(Object value){
		ACCESS_TEMP_VECTOR.add(value);
	}
	
	public synchronized static MutableIdentifyable getValue(UUID key){
		MutableIdentifyable value = map.get(key);
		map.remove(key);
		return value;
	}
	
	/**
	 * 获取临时变量
	 * @param key
	 * @return
	 */
	public synchronized static Object getValue(String key){
		Object value = TEMP_MAP.get(key);
		TEMP_MAP.remove(key);
		return value;
	}
	
	/**
	 * 从队列中获取已经请求的内容
	 * @return
	 */
	public static Object getValueFromVector(){
		Object value = ACCESS_TEMP_VECTOR.firstElement();
		if(null != value){
			return value;
		}
		return null;
	}
	
	public synchronized static void removeFromVector(){
		logger.debug(" > ContextMap.removeFromVector,ACCESS_TEMP_VECTOR.size:"+ACCESS_TEMP_VECTOR.size());
		if(ACCESS_TEMP_VECTOR.size() == 1){
			ACCESS_TEMP_VECTOR.remove(0);
		}else{
			ACCESS_TEMP_VECTOR.removeAllElements();
			logger.debug(" > ContextMap.removeFromVector,ACCESS_TEMP_VECTOR.size > 1,is:"+ACCESS_TEMP_VECTOR.size());
		}
	}
	
	public static int getMapSize(){
		return map.size();
	}
	
	//清理内存中的数据
	public static void clear(){
		if(null != map && map.size() > 0){
			map.clear();
		}
		
		if(null != ACCESS_TEMP_VECTOR && ACCESS_TEMP_VECTOR.size() > 0){
			ACCESS_TEMP_VECTOR.clear();
		}
		
		if(null != TEMP_MAP && TEMP_MAP.size() > 0){
			TEMP_MAP.clear();
		}
	}
}
