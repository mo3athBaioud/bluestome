/**
 * 
 */
package com.skymobi.android.util;

import java.util.Iterator;

/**
 * @author Marvin.Ma
 * @param <E>
 *
 */
public class IteratorImplOfArray<E> implements Iterator<E> {

	private	E[] data;
	private	int	offset;
	
	public IteratorImplOfArray(E[] array) {
		this.data = array;
		this.offset = 0;
	}
	
	@Override
	public boolean hasNext() {
		return this.offset < data.length;
	}

	@Override
	public E next() {
		if ( hasNext() ) {
			return this.data[this.offset++];
		}
		else {
			return	null;
		}
	}

	@Override
	public void remove() {
		throw new RuntimeException("Not implemented");
	}

}
