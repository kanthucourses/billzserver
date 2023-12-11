package com.billing.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Invoice {

	private String _id;
	private Long invoiceID;
	private CustomerDetails customerDetails;
	private List<InvoiceLine> invoiceLines;
	private Double totalAmount;
	private LocalDate invoiceDate;
	private LocalDate dueDate;
	private LocalDateTime createdDateTime;
	private LocalDateTime lastUpdatedDateTime;
	private OrganizationInfo organizationInfo;
	private String paymentMethod;
	private Double totalTaxPercentage;
	private Double totalTaxAmount;
	private Double totalNetAmount;
    private Double totalDiscountAmount;


	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	
	public Long getInvoiceID() {
		return invoiceID;
	}
	public void setInvoiceID(Long invoiceID) {
		this.invoiceID = invoiceID;
	}
	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}
	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}
	public List<InvoiceLine> getInvoiceLines() {
		return invoiceLines;
	}
	public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
		this.invoiceLines = invoiceLines;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public LocalDateTime getLastUpdatedDateTime() {
		return lastUpdatedDateTime;
	}
	public void setLastUpdatedDateTime(LocalDateTime lastUpdatedDateTime) {
		this.lastUpdatedDateTime = lastUpdatedDateTime;
	}
	public OrganizationInfo getOrganizationInfo() {
		return organizationInfo;
	}
	public void setOrganizationInfo(OrganizationInfo organizationInfo) {
		this.organizationInfo = organizationInfo;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Double getTotalTaxPercentage() {
		return totalTaxPercentage;
	}
	public void setTotalTaxPercentage(Double totalTaxPercentage) {
		this.totalTaxPercentage = totalTaxPercentage;
	}
	public Double getTotalTaxAmount() {
		return totalTaxAmount;
	}
	public void setTotalTaxAmount(Double totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}
	public Double getTotalNetAmount() {
		return totalNetAmount;
	}
	public void setTotalNetAmount(Double totalNetAmount) {
		this.totalNetAmount = totalNetAmount;
	}
	public Double getTotalDiscountAmount() {
		return totalDiscountAmount;
	}
	public void setTotalDiscountAmount(Double totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}
	
}
