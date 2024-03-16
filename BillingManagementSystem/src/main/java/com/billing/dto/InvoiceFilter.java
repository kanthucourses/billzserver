package com.billing.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate invoiceDateFrom;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate invoiceDateTo;
	private Integer ageFrom;
	private Integer ageTo;
	private Integer year;

	
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
	public LocalDate getInvoiceDateFrom() {
		return invoiceDateFrom;
	}
	public void setInvoiceDateFrom(LocalDate invoiceDateFrom) {
		this.invoiceDateFrom = invoiceDateFrom;
	}
	public LocalDate getInvoiceDateTo() {
		return invoiceDateTo;
	}
	public void setInvoiceDateTo(LocalDate invoiceDateTo) {
		this.invoiceDateTo = invoiceDateTo;
	}
	public Integer getAgeFrom() {
		return ageFrom;
	}
	public void setAgeFrom(Integer ageFrom) {
		this.ageFrom = ageFrom;
	}
	public Integer getAgeTo() {
		return ageTo;
	}
	public void setAgeTo(Integer ageTo) {
		this.ageTo = ageTo;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
	
}
