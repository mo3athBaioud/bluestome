package  android.skymobi.messenger.net.beans.lcs;

import android.skymobi.messenger.net.beans.commons.ActionDataInfo;
import android.skymobi.messenger.net.beans.commons.DataInfo;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;
import com.skymobi.android.transport.protocol.esb.signal.AbstractEsbT2ASignal;

import java.util.ArrayList;

/**
 * LCS 日志收集
 * 
 * */
@EsbSignal(messageCode = 0xA110)
public class LcsLogStatisticsRequest extends AbstractEsbT2ASignal {
	
	public LcsLogStatisticsRequest(){
		this.setEncryptKey(0);
	}
	
	@TLVAttribute(tag = 32010001,description="斯凯ID")
	private Integer skyid;
	
	@TLVAttribute(tag = 32010002,description="imsi")
	private String imsi;
	
	@TLVAttribute(tag = 32010003,description="应用ID")
	private Integer appid;
	
	@TLVAttribute(tag = 32010004,description="应用版本")
	private Integer app_ver;
	
	@TLVAttribute(tag = 32020005,description="统计数据信息")
    private ArrayList<DataInfo> dataInfoList;
	
	@TLVAttribute(tag = 32020006,description="数据信息")
    private ArrayList<ActionDataInfo> actionDataInfoList;
	
	

	public Integer getSkyid() {
		return skyid;
	}

	public void setSkyid(Integer skyid) {
		this.skyid = skyid;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public Integer getAppid() {
		return appid;
	}

	public void setAppid(Integer appid) {
		this.appid = appid;
	}

	public Integer getApp_ver() {
		return app_ver;
	}

	public void setApp_ver(Integer app_ver) {
		this.app_ver = app_ver;
	}

	public ArrayList<DataInfo> getDataInfoList() {
		return dataInfoList;
	}

	public void setDataInfoList(ArrayList<DataInfo> dataInfoList) {
		this.dataInfoList = dataInfoList;
	}

	public ArrayList<ActionDataInfo> getActionDataInfoList() {
		return actionDataInfoList;
	}

	public void setActionDataInfoList(ArrayList<ActionDataInfo> actionDataInfoList) {
		this.actionDataInfoList = actionDataInfoList;
	}

}
