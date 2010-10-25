package com.ssi.common.excel;

/**
 * 数据类型定义
 */
public enum Type {

	DATE("java.util.Date"), DOUBLE("java.lang.Double"), LONG("java.lang.Long"), BYTE(
			"java.lang.Byte"), BOOLEAN("java.lang.Boolean"), STRING(
			"java.lang.String");

	private Type(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
