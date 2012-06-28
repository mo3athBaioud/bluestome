/**
 * 
 */
package com.skymobi.android.util;

import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Marvin.Ma
 *
 */
public class PojoAsMapAdapter implements Map<String, Object> {

    private static final Logger logger = 
    	LoggerFactory.getLogger(PojoAsMapAdapter.class);
	
	private	Object pojo;
	private Map<String, Field> fields = new HashMap<String, Field>();
	private	boolean readonly;
	
	public PojoAsMapAdapter(Object pojo, boolean readonly) {
		if ( null == pojo ) {
			throw new RuntimeException("pojo can't be null.");
		}
		
		this.pojo = pojo;
		this.readonly = readonly;
		
		Field[] fieldList = pojo.getClass().getDeclaredFields();
		for ( Field field : fieldList ) {
			field.setAccessible(true);
			fields.put(field.getName(), field);
		}
	}
	
	@Override
	public void clear() {
		if ( readonly ) {
			throw new RuntimeException("can't invoke clear method when readonly mode");
		}
		
		for ( Field field : fields.values() ) {
			try {
				field.set(pojo, null);
			} catch (Exception e) {
				logger.error("failed to invoke set null for field ["+field+"] bcs of ",e);
			} 
		}
	}

	@Override
	public boolean containsKey(Object key) {
		return fields.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		for ( Field field : fields.values() ) {
			try {
				Object obj = field.get(pojo);
				if ( value == null ) {
					if ( obj == null ) {
						return	true;
					}
				}
				else {
					if ( value.equals(obj)) {
						return	true;
					}
				}
			} catch (Exception e) {
				logger.error("failed to invoke get for field ["+field+"] bcs of:",e);
			}
		}
		
		return	false;
	}

	@Override
	public Set<Map.Entry<String, Object>> entrySet() {
		Set<Map.Entry<String, Object>> ret = new HashSet<Map.Entry<String, Object>>();
		
		for ( String key : fields.keySet() ) {
			final String entryKey = key;
			ret.add(new Map.Entry<String, Object>() {

				@Override
				public String getKey() {
					return entryKey;
				}

				@Override
				public Object getValue() {
					return get(entryKey);
				}

				@Override
				public Object setValue(Object value) {
					return put(entryKey, value);
				}} );
		}

		return	ret;
	}

	@Override
	public Object get(Object key) {
		Field field = fields.get(key);
		if ( null != field ) {
			try {
				return	field.get(pojo);
			}
			catch (Exception e) {
				logger.error("failed to invoke get for field ["+field+"] bcs of:",e);
			}
		}
		else {
			logger.warn("can't found field named ["+key+"]");
		}
		
		return null;
	}

	@Override
	public boolean isEmpty() {
		return fields.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		return fields.keySet();
	}

	@Override
	public Object put(String key, Object value) {
		if ( readonly ) {
			throw new RuntimeException("can't invoke put method when readonly mode");
		}
		
		Field field = fields.get(key);
		if ( null != field ) {
			try {
				Object oldv = field.get(pojo);
				field.set(pojo, value);
				return	oldv;
			}
			catch (Exception e) {
				logger.error("failed to invoke get/set for field ["+field+"] bcs of:",e);
			}
		}
		else {
			logger.warn("can't found field named ["+key+"]");
		}
		
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object remove(Object key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return fields.size();
	}

	@Override
	public Collection<Object> values() {
		List<Object> ret = new ArrayList<Object>();
		Collection<Field> fieldList = fields.values();
		for ( Field field : fieldList ) {
			try {
				ret.add(field.get(pojo));
			} catch (Exception e) {
				logger.error("failed to invoke get for field ["+field+"] bcs of:",e);
			}
		}
		return	ret;
	}

}
