package com.ssi.common.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultHolder implements Holder {

	private Map<Object, Object> map = new ConcurrentHashMap<Object, Object>();

	public void put(Object key, Object value) {
		map.put(key, value);
	}

	public Object getAndRemove(Object key) {
		Object ret = map.get(key);
		map.remove(key);
		return ret;
	}

	public Object get(Object key) {
		return map.get(key);
	}

	public void remove(Object key) {
		map.remove(key);
	}
}
