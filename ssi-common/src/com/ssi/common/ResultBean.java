package com.ssi.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回对象
 * @author bluestome
 *
 */
public class ResultBean {

	private Object obj;
	private List list = new ArrayList();
	private boolean isSuccess;
	private String desc;
	private int statusCode;
	
	public ResultBean(){
		this.isSuccess = false;
	}
	
	public ResultBean(boolean isSuccess,Object obj){
		this.isSuccess = isSuccess;
		this.obj = obj;
	}

	public ResultBean(boolean isSuccess,Object obj,int statusCode){
		this.isSuccess = isSuccess;
		this.obj = obj;
		this.statusCode = statusCode;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
	
	
	
}
