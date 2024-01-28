package com.billing.dto;

import java.util.List;

public class InvoiceFilter {
	private Long invoiceID;
	private String serviceID;
	private String organizationID;
	private String organizationName;
	private String organizationIDName;
	private Integer page;
	private Integer pageSize;
	private String sortBy;
	private String sortDirection;
	private List<String> sortFields;
	private List<String> searchOnKeys;
	private String searchKeyword;
	
	public Long getInvoiceID() {
		return invoiceID;
	}
	public void setInvoiceID(Long invoiceID) {
		this.invoiceID = invoiceID;
	}
	
	public String getServiceID() {
		return serviceID;
	}
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getSortDirection() {
		return sortDirection;
	}
	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}
	public List<String> getSortFields() {
		return sortFields;
	}
	public void setSortFields(List<String> sortFields) {
		this.sortFields = sortFields;
	}
	public List<String> getSearchOnKeys() {
		return searchOnKeys;
	}
	public void setSearchOnKeys(List<String> searchOnKeys) {
		this.searchOnKeys = searchOnKeys;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public String getOrganizationID() {
		return organizationID;
	}
	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getOrganizationIDName() {
		return organizationIDName;
	}
	public void setOrganizationIDName(String organizationIDName) {
		this.organizationIDName = organizationIDName;
	}
	
	
}
