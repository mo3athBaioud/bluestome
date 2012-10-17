/**
 * 
 */
package com.skymobi.android.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Marvin.Ma
 *
 */
public class ReadonlyCompositeMap<K, V> implements Map<K, V> {

	private	Map<K,V> first;
	private Map<K,V> second;
	
	public ReadonlyCompositeMap(Map<K,V> first, Map<K,V> second) {
		if ( null == first || null == second) {
			throw new NullPointerException();
		}
		
		this.first = first;
		this.second = second;
	}
	
	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsKey(Object key) {
		if ( first.containsKey(key) ) {
			return	true;
		}
		else {
			return	second.containsKey(key);
		}
	}

	@Override
	public boolean containsValue(Object value) {
		if ( first.containsValue(value) ) {
			return	true;
		}
		else {
			return	second.containsValue(value);
		}
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> ret = new HashSet<Map.Entry<K, V>>();
		
		ret.addAll(first.entrySet());
		ret.addAll(second.entrySet());
		return ret;
	}

	@Override
	public V get(Object key) {
		if ( first.containsKey(key)) {
			return	first.get(key);
		}
		else {
			return	second.get(key);
		}
	}

	@Override
	public boolean isEmpty() {
		return first.isEmpty() && second.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		Set<K> ret = new HashSet<K>();
		
		ret.addAll(first.keySet());
		ret.addAll(second.keySet());
		return ret;
	}

	@Override
	public V put(K key, V value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public V remove(Object key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return first.size() + second.size();
	}

	@Override
	public Collection<V> values() {
		List<V> ret = new ArrayList<V>();
		
		ret.addAll(first.values());
		ret.addAll(second.values());
		return ret;
	}

}
