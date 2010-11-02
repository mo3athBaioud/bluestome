package com.ssi.common.cache;

public interface Holder {
	public void put(Object key, Object value);

	public Object get(Object key);

	public Object getAndRemove(Object key);

	public void remove(Object key);
}
