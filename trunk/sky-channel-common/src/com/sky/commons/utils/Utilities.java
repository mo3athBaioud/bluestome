package com.sky.commons.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class Utilities {

	private static AtomicInteger uuid = new AtomicInteger(0);
	
	/**
	 * min value=1
	 * max value=2100000000
	 * @return
	 */
	public static int getAtomicInteger(){
		int no = uuid.incrementAndGet();
		while ( no > 2100000001 ||no<1) {
			uuid.compareAndSet(no, 1);
			no = uuid.incrementAndGet();
		}
		return no;
	}
	
	public static void main(String args[]){
//		while(true)
		System.out.println(Utilities.getAtomicInteger());
	}
}
