package android.skymobi.messenger.net.beans.commons;

import android.skymobi.messenger.net.utils.SysUtils;

import com.skymobi.android.bean.esb.annotation.EsbField;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class EsbFailed implements EsbResult {

	@EsbField(index = 6, bytes=1)
    private	int	msgLen;

	@EsbField(index = 7, length="msgLen", charset=SysUtils.DEFAULT_CHARSET)
    private	String	failedMsg;

	public int getMsgLen() {
		return msgLen;
	}

	public void setMsgLen(int msgLen) {
		this.msgLen = msgLen;
	}

	public void setFailedMsg(String failedMsg) {
		this.failedMsg = failedMsg;
		this.msgLen=SysUtils.length(failedMsg);
	}

	/**
	 * @return the failedMsg
	 */
	public String getFailedMsg() {
		return failedMsg;
	}

    public String toString() {
        return  ToStringBuilder.reflectionToString(this,
                            ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
