package com.skymobi.android.skyaaa.bean.common;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

@TLVAttribute(tag = 11020001)
public class Result {
	@TLVAttribute(tag=11010063,description="错误码")
	private int errorCode;
	@TLVAttribute(tag=11010064,description="错误信息")
	private String errorMessage;
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
		this.errorMessage = ErrorCodeReader.getValue(String.valueOf(this.errorCode));
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
 
	public String toString() {
		return  ToStringBuilder.reflectionToString(this, 
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
