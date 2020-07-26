package com.datanew.dto;

import java.util.List;

/**
 * 分页对象
 * @param <T>
 */
public class Pages<T> {
	private Integer limit;

	private Integer offset;

	// 页码
	private Integer pageSize;

	// 每页大小
	private Integer pageNumber;

	private Integer total;

	private Integer currentPage;

	private String searchText;

	private String sortName;

	private String sortOrder;

	// 记录
	private List<T> rows;
	
	
	public Pages(){
		
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = pageNumber > 1 ? pageNumber : 1;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Pages(Integer limit, Integer offset, Integer pageSize, Integer pageNumber, Integer total, Integer currentPage, String searchText, String sortName, String sortOrder, List<T> rows) {
		this.limit = limit;
		this.offset = offset;
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		this.total = total;
		this.currentPage = currentPage;
		this.searchText = searchText;
		this.sortName = sortName;
		this.sortOrder = sortOrder;
		this.rows = rows;
	}
}
