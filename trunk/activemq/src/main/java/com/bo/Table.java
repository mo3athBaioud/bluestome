package com.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Table implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private List datas = new ArrayList();
	
	public Table(String name) {
		this.name = name;
	}
	
	public Table(String name, List datas) {
		this.name = name;
		this.datas = datas;
	}
	
	public List getDatas() {
		return datas;
	}
	public void setDatas(List datas) {
		this.datas = datas;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name:").append(name).append(",").append("data.size:").append(datas.size());
		return sb.toString();
	}
	
	
}
