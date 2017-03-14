package com.spring.project.model;

import java.util.List;

public class ResultModel<T> {
	// 商品列表
	private List<T> list;
	// 商品总数
	private Long recordCount;
	// 总页数
	private Long pageCount;
	// 当前页
	private Long curPage;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}

	public Long getPageCount() {
		return pageCount;
	}

	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}

	public Long getCurPage() {
		return curPage;
	}

	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}

}
