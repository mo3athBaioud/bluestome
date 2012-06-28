/**
 * 
 */
package com.skymobi.android.bean.esb.core;

/**
 * @author Marvin.Ma
 *
 */
public interface EsbHeaderable {
	
	public 	int getSrcESBAddr();
	
	public	int	getDstESBAddr();
	
	public  short getLength();
	
	public 	short  getFlags();
	
	public	short	getSeqNum();
	
	public 	short getResult();

	public	boolean checkIntegrity();
}
