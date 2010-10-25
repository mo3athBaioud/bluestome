package com.ssi.common.excel;

import java.util.regex.Pattern;

/**
 * 
 * 定义从excel中解析的数据属性
 * 
 */
public class Property implements Comparable<Property> {
	/** 在一行单元格中的顺序 */
	private int index;
	/** 属性名 */
	private String name;
	/** 要生成的属性数据类型 */
	private Type type;
	/** 属性标题 */
	private String title;
	/** 属性是否允许为空 */
	private boolean nullable;
	/** 属性值必须匹配的格式 */
	private Pattern regex;

	public Property(int index, String name, String title, Type type) {
		this(index, name, title, type, true);
	}

	public Property(int index, String name, Type type) {
		this(index, name, type, true);
	}

	public Property(int index, String name, Type type, boolean nullable) {
		this(index, name, "", type, nullable, null);
	}

	public Property(int index, String name, Type type, boolean nullable,
			Pattern regex) {
		this(index, name, "", type, true, regex);
	}

	public Property(int index, String name, String title, Type type,
			boolean nullable) {
		this(index, name, title, type, nullable, null);
	}

	public Property(int index, String name, String title, Type type,
			boolean nullable, Pattern regex) {
		this.regex = regex;
		this.title = title;
		this.index = index;
		this.name = name;
		this.type = type;
		this.nullable = nullable;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int compareTo(Property o) {
		return this.index - o.index;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public Pattern getRegex() {
		return regex;
	}

	public void setRegex(Pattern regex) {
		this.regex = regex;
	}

}
