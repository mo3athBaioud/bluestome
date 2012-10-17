/**
 * 
 */
package com.skymobi.android.bean.esb.core;

import com.skymobi.android.util.DefaultPropertiesSupport;
import com.skymobi.android.util.IdentifyableOfInt;

/**
 * @author Marvin.Ma
 *
 */
public abstract class AbstractEsbHeaderable extends DefaultPropertiesSupport 
	implements	IdentifyableOfInt, EsbMutableHeaderable {

	private	int	srcESBAddr = 0;
	private	int	dstESBAddr;
	private short    length;
	private	short	flags;
	private	short	seqNum;
	private	short	result;
	
	public int getDstESBAddr() {
		return dstESBAddr;
	}

	public short getSeqNum() {
		return seqNum;
	}

	public void setDstESBAddr(int dstESBAddr) {
		this.dstESBAddr = dstESBAddr;
	}

	public void setSeqNum(short seqNum) {
		this.seqNum = seqNum;
	}

	public short getResult() {
		return result;
	}

	public void setResult(short result) {
		this.result = result;
	}

	public short getFlags() {
		return flags;
	}

	public void setFlags(short flags) {
		this.flags = flags;
	}

	public int getIntIdentification() {
		return getSeqNum();
	}

	public int getSrcESBAddr() {
		return srcESBAddr;
	}

	public void setSrcESBAddr(int srcESBAddr) {
		this.srcESBAddr = srcESBAddr;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final short prime = 31;
		int result = super.hashCode();
		result = prime * result + dstESBAddr;
		result = prime * result + flags;
		result = prime * result + this.result;
		result = prime * result + seqNum;
		result = prime * result + srcESBAddr;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEsbHeaderable other = (AbstractEsbHeaderable) obj;
		if (dstESBAddr != other.dstESBAddr)
			return false;
		if (flags != other.flags)
			return false;
		if (result != other.result)
			return false;
		if (seqNum != other.seqNum)
			return false;
		if (srcESBAddr != other.srcESBAddr)
			return false;
		return true;
	}

    /**
     * @return the length
     */
    public short getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(short length) {
        this.length = length;
    }
	
}
