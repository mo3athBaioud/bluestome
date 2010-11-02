package com.ssi.common;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.util.Assert;

/**
 * 分页器
 * 
 * @author wangqi
 * 
 */
public class Paginator implements Serializable, Cloneable {
	private static final long serialVersionUID = 3688506614705500726L;

	/**
	 * 默认每页20条
	 */
	public static final int DEFAULT_ITEMS_PER_PAGE = 5;

	public static final int UNKNOWN_ITEMS = Integer.MAX_VALUE;

	/**
	 * 当前页码，从1开始
	 */
	private int page;// 1-based

	/**
	 * 总记录数
	 */
	private int items;

	/**
	 * 总页数
	 */
	private int pageCount;

	/**
	 * 每页记录条数
	 */
	private int itemsPerPage;

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * 当前页码从1开始，总条数为无限大，每页50条
	 */
	public Paginator() {
		this(0);
	}

	/**
	 * 当前页码从1开始，总条数为无限大，每页<code>itemsPerPage</code>条
	 * 
	 * @param itemsPerPage
	 *            每页记录条数
	 */
	public Paginator(int itemsPerPage) {
		this(itemsPerPage, UNKNOWN_ITEMS);
	}

	/**
	 * 当前页码从1开始，总条数为<code>items</code>，每页<code>itemsPerPage</code>条
	 * 
	 * @param itemsPerPage
	 *            每页记录条数
	 * @param items
	 *            总记录数
	 */
	public Paginator(int itemsPerPage, int items) {
		this.items = (items >= 0) ? items : 0;
		this.itemsPerPage = (itemsPerPage > 0) ? itemsPerPage
				: DEFAULT_ITEMS_PER_PAGE;
		this.page = calcPage(0);
	}

	/**
	 * 获取总页数
	 * 
	 * @return
	 */
	public int getPages() {
		return (int) Math.ceil((double) items / itemsPerPage);
	}

	/**
	 * 获取当前页
	 * 
	 * @return
	 */
	public int getPage() {
		if (page < 1)
			page = 1;
		return page;
	}

	/**
	 * 设置当前页
	 * 
	 * @param page
	 *            当前页码
	 * @return
	 */
	public int setPage(int page) {
		return (this.page = calcPage(page));
	}

	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public int getItems() {
		return items;
	}

	/**
	 * 设置总记录数，并重新计算当前页码以确保其不超过总页数
	 * 
	 * @param items
	 *            总记录数
	 * @return
	 */
	public int setItems(int items) {
		this.items = (items >= 0) ? items : 0;
		setPage(page);
		setPageCount(getPages());
		return this.items;
	}

	/**
	 * 获取每页记录数
	 * 
	 * @return
	 */
	public int getItemsPerPage() {
		return itemsPerPage;
	}

	/**
	 * 设置每页记录数，并重新计算当前页码
	 * 
	 * @param itemsPerPage
	 *            每页记录数
	 * @return
	 */
	public int setItemsPerPage(int itemsPerPage) {
		int tmp = this.itemsPerPage;

		this.itemsPerPage = (itemsPerPage > 0) ? itemsPerPage
				: DEFAULT_ITEMS_PER_PAGE;

		if (page > 0) {
			setPage((int) (((double) (page - 1) * tmp) / this.itemsPerPage) + 1);
		}

		return this.itemsPerPage;
	}

	/**
	 * 获取开始索引，从1开始，应用于数据库层分页
	 * 
	 * @return
	 */
	public int getBeginIndex() {
		if (page > 0) {
			return (itemsPerPage * (page - 1)) + 1;
		} else {
			return 0;
		}
	}

	/**
	 * 获取结束索引，应用于数据库层分页
	 * 
	 * @return
	 */
	public int getEndIndex() {
		if (page > 0) {
			return Math.min(itemsPerPage * page, items);
		} else {
			return 0;
		}
	}

	/**
	 * 获取第一页
	 * 
	 * @return
	 */
	public int getFirstPage() {
		return calcPage(1);
	}

	/**
	 * 获取最后一页
	 * 
	 * @return
	 */
	public int getLastPage() {
		return calcPage(getPages());
	}

	/**
	 * 获取前一页
	 * 
	 * @return
	 */
	public int getPreviousPage() {
		return calcPage(page - 1);
	}

	/**
	 * 获取前<code>n</code>页
	 * 
	 * @param n
	 * @return
	 */
	public int getPreviousPage(int n) {
		return calcPage(page - n);
	}

	/**
	 * 获取后一页
	 * 
	 * @return
	 */
	public int getNextPage() {
		return calcPage(page + 1);
	}

	/**
	 * 获取后<code>n</code>页
	 * 
	 * @param n
	 * @return
	 */
	public int getNextPage(int n) {
		return calcPage(page + n);
	}

	/**
	 * 确保给定的页码不小于1，不超过总页码
	 * 
	 * @param page
	 * @return
	 */
	protected int calcPage(int page) {
		int pages = getPages();

		if (pages > 0) {
			return (page < 1) ? 1 : ((page > pages) ? pages : page);
		}

		return 0;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	public static void main(String args[]) {
		testConstructor();
		testConstructor2();
		testConstructor3();

		testSetItems();
		testSetPage();
		testSetItemsPerPage();

		System.out.println("All asserts are true!");
	}

	private static void testConstructor() {
		Paginator pg = new Paginator();

		Assert.isTrue(pg.getItems() == Integer.MAX_VALUE);
		Assert.isTrue(pg.getPage() == 1);
		Assert.isTrue(pg.getItemsPerPage() == DEFAULT_ITEMS_PER_PAGE);

		Assert.isTrue(pg.getPages() == (int) Math
				.ceil((double) Integer.MAX_VALUE / DEFAULT_ITEMS_PER_PAGE));
		Assert.isTrue(pg.getFirstPage() == 1);
		Assert.isTrue(pg.getLastPage() == (int) Math
				.ceil((double) Integer.MAX_VALUE / DEFAULT_ITEMS_PER_PAGE));

		Assert.isTrue(pg.getNextPage() == 2);
		Assert.isTrue(pg.getNextPage(1) == 2);
		Assert.isTrue(pg.getNextPage(2) == 3);

		Assert.isTrue(pg.getPreviousPage() == 1);
		Assert.isTrue(pg.getPreviousPage(1) == 1);
		Assert.isTrue(pg.getPreviousPage(2) == 1);

		Assert.isTrue(pg.getBeginIndex() == 1);
		Assert.isTrue(pg.getEndIndex() == DEFAULT_ITEMS_PER_PAGE);
	}

	private static void testConstructor2() {
		Paginator pg = new Paginator(20);

		Assert.isTrue(pg.getItems() == Integer.MAX_VALUE);
		Assert.isTrue(pg.getPage() == 1);
		Assert.isTrue(pg.getItemsPerPage() == 20);

		Assert.isTrue(pg.getPages() == 107374183);
		Assert.isTrue(pg.getFirstPage() == 1);
		Assert.isTrue(pg.getLastPage() == 107374183);

		Assert.isTrue(pg.getNextPage() == 2);
		Assert.isTrue(pg.getNextPage(1) == 2);
		Assert.isTrue(pg.getNextPage(2) == 3);

		Assert.isTrue(pg.getPreviousPage() == 1);
		Assert.isTrue(pg.getPreviousPage(1) == 1);
		Assert.isTrue(pg.getPreviousPage(2) == 1);

		Assert.isTrue(pg.getBeginIndex() == 1);
		Assert.isTrue(pg.getEndIndex() == 20);
	}

	private static void testConstructor3() {
		Paginator pg = new Paginator(30, 901);

		Assert.isTrue(pg.getItems() == 901);
		Assert.isTrue(pg.getPage() == 1);
		Assert.isTrue(pg.getItemsPerPage() == 30);

		Assert.isTrue(pg.getPages() == 31);
		Assert.isTrue(pg.getFirstPage() == 1);
		Assert.isTrue(pg.getLastPage() == 31);

		Assert.isTrue(pg.getNextPage() == 2);
		Assert.isTrue(pg.getNextPage(1) == 2);
		Assert.isTrue(pg.getNextPage(2) == 3);

		Assert.isTrue(pg.getPreviousPage() == 1);
		Assert.isTrue(pg.getPreviousPage(1) == 1);
		Assert.isTrue(pg.getPreviousPage(2) == 1);

		Assert.isTrue(pg.getBeginIndex() == 1);
		Assert.isTrue(pg.getEndIndex() == 30);
	}

	private static void testSetItems() {
		Paginator pg = new Paginator(20);
		pg.setItems(51);

		Assert.isTrue(pg.getItems() == 51);
		Assert.isTrue(pg.getPage() == 1);
		Assert.isTrue(pg.getItemsPerPage() == 20);

		Assert.isTrue(pg.getPages() == 3);
		Assert.isTrue(pg.getFirstPage() == 1);
		Assert.isTrue(pg.getLastPage() == 3);

		Assert.isTrue(pg.getNextPage() == 2);
		Assert.isTrue(pg.getNextPage(1) == 2);
		Assert.isTrue(pg.getNextPage(2) == 3);

		Assert.isTrue(pg.getPreviousPage() == 1);
		Assert.isTrue(pg.getPreviousPage(1) == 1);
		Assert.isTrue(pg.getPreviousPage(2) == 1);

		Assert.isTrue(pg.getBeginIndex() == 1);
		Assert.isTrue(pg.getEndIndex() == 20);
	}

	private static void testSetPage() {
		Paginator pg = new Paginator(50);
		pg.setItems(51);
		pg.setPage(2);

		Assert.isTrue(pg.getItems() == 51);
		Assert.isTrue(pg.getPage() == 2);
		Assert.isTrue(pg.getItemsPerPage() == 50);

		Assert.isTrue(pg.getPages() == 2);
		Assert.isTrue(pg.getFirstPage() == 1);
		Assert.isTrue(pg.getLastPage() == 2);

		Assert.isTrue(pg.getNextPage() == 2);
		Assert.isTrue(pg.getNextPage(1) == 2);
		Assert.isTrue(pg.getNextPage(2) == 2);

		Assert.isTrue(pg.getPreviousPage() == 1);
		Assert.isTrue(pg.getPreviousPage(1) == 1);
		Assert.isTrue(pg.getPreviousPage(2) == 1);

		Assert.isTrue(pg.getBeginIndex() == 51);
		Assert.isTrue(pg.getEndIndex() == 51);
	}

	private static void testSetItemsPerPage() {
		Paginator pg = new Paginator(50);
		pg.setItems(201);
		pg.setPage(2);

		Assert.isTrue(pg.getItems() == 201);
		Assert.isTrue(pg.getPage() == 2);
		Assert.isTrue(pg.getItemsPerPage() == 50);

		Assert.isTrue(pg.getPages() == 5);
		Assert.isTrue(pg.getFirstPage() == 1);
		Assert.isTrue(pg.getLastPage() == 5);

		Assert.isTrue(pg.getNextPage() == 3);
		Assert.isTrue(pg.getNextPage(1) == 3);
		Assert.isTrue(pg.getNextPage(2) == 4);

		Assert.isTrue(pg.getPreviousPage() == 1);
		Assert.isTrue(pg.getPreviousPage(1) == 1);
		Assert.isTrue(pg.getPreviousPage(2) == 1);

		Assert.isTrue(pg.getBeginIndex() == 51);
		Assert.isTrue(pg.getEndIndex() == 100);

		pg.setItemsPerPage(20);

		Assert.isTrue(pg.getItems() == 201);
		Assert.isTrue(pg.getPage() == 3);
		Assert.isTrue(pg.getItemsPerPage() == 20);

		Assert.isTrue(pg.getPages() == 11);
		Assert.isTrue(pg.getFirstPage() == 1);
		Assert.isTrue(pg.getLastPage() == 11);

		Assert.isTrue(pg.getNextPage() == 4);
		Assert.isTrue(pg.getNextPage(1) == 4);
		Assert.isTrue(pg.getNextPage(2) == 5);

		Assert.isTrue(pg.getPreviousPage() == 2);
		Assert.isTrue(pg.getPreviousPage(1) == 2);
		Assert.isTrue(pg.getPreviousPage(2) == 1);

		Assert.isTrue(pg.getBeginIndex() == 41);
		Assert.isTrue(pg.getEndIndex() == 60);
	}
}
