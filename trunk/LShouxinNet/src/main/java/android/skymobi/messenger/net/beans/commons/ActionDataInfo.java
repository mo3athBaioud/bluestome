package  android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;



public class ActionDataInfo implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -520629798699961453L;
	
	@TLVAttribute(tag = 32010011, description = "动作发生时间")
	private Integer time;
	
	private String timeStr;
	@TLVAttribute(tag = 32010012, description = "具体动作")
	private Integer action;
	@TLVAttribute(tag = 32010013, description = "动作操作结果")
	private Integer result;
	@TLVAttribute(tag = 32010014, description = "0 （注册页面），1（登录页面，此次不用），2（主页面，此次为登录成功后绑定)")
	private Integer where;
	@TLVAttribute(tag = 32010015, description = "事件附带信息1")
	private String reserve1;
	@TLVAttribute(tag = 32010016, description = "事件附带信息2")
	private String reserve2;
	@TLVAttribute(tag = 32010017, description = "事件附带信息3")
	private String reserve3;

	

	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Integer getWhere() {
		return where;
	}

	public void setWhere(Integer where) {
		this.where = where;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public String getReserve3() {
		return reserve3;
	}

	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}
   

}
