package com.skymobi.android.bean.esb.core;

public interface EsbMutableHeaderable extends EsbHeaderable {

	public void setSrcESBAddr(int srcESBAddr);

	public void	setDstESBAddr(int dstESBAddr);
	
	public void setLength(short length);
	
	public void	setFlags(short flags);
	
	public void	setSeqNum(short seqNum);
	
	public void	setResult(short result);
	
}
