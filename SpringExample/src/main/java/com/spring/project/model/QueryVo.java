package com.spring.project.model;

public class QueryVo {

	private String queryString; // 查询关键字

	private String catalog_name; // 商品类别

	private String price; // 商品价格

	private Integer page; // 当前页

	private String sort; // 价格排序 1:升序 0:降序

	public String getCatalog_name() {
		return catalog_name;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public void setCatalog_name(String catalog_name) {
		this.catalog_name = catalog_name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
