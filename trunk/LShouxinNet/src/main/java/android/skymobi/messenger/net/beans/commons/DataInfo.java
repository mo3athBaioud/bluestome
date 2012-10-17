package  android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

public class DataInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -634950015273693998L;

	@TLVAttribute(tag = 32010006, description = "数据类型")
	private Integer data_code;
	
	@TLVAttribute(tag = 32010007, description = "时间戳")
	private Integer which_hour;
	
	@TLVAttribute(tag = 32010008, description = "发送对象")
	private Byte msg_dest;
	
	@TLVAttribute(tag = 32010009, description = "消息类型")
	private Byte msg_type;
	
	@TLVAttribute(tag = 32010010, description = "消息数量")
	private Short count;

	public Integer getData_code() {
		return data_code;
	}

	public void setData_code(Integer data_code) {
		this.data_code = data_code;
	}

	public Integer getWhich_hour() {
		return which_hour;
	}

	public void setWhich_hour(Integer which_hour) {
		this.which_hour = which_hour;
	}

	public Byte getMsg_dest() {
		return msg_dest;
	}

	public void setMsg_dest(Byte msg_dest) {
		this.msg_dest = msg_dest;
	}

	public Byte getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(Byte msg_type) {
		this.msg_type = msg_type;
	}

	public Short getCount() {
		return count;
	}

	public void setCount(Short count) {
		this.count = count;
	}

}
