package com.nutzd;

import java.util.ArrayList;
import java.util.List;

public class JsonObject {

	private int count;
	
	private boolean success = true;
	
	private Object obj;
	
	private String msg = "No Msg";

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	

}
