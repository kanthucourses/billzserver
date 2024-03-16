package com.billing.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Document(collection = "invoice")
public class Invoice {

	private String _id;
	private Long invoiceID;
	private CustomerDetails customerDetails;
	@Field("invoiceLines")
	private List<InvoiceLine> invoiceLines;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate invoiceDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dueDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime createdDateTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime lastUpdatedDateTime;
	private OrganizationInfo organizationInfo;
	private String paymentMethod;
	private Double totalGrossAmount;
	private Double totalTaxPercentage;
	private Double totalTaxAmount;
	private Double totalNetAmount;
    private Double totalDiscountAmount;
    private List<TaxDetails> taxDetailsList;
	private String invoiceStatus;
	private String paymentStatus;


	public Invoice() {
		
	}
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
	public Double getTotalGrossAmount() {
		return totalGrossAmount;
	}
	public void setTotalGrossAmount(Double totalGrossAmount) {
		this.totalGrossAmount = totalGrossAmount;
	}
	public List<TaxDetails> getTaxDetailsList() {
		return taxDetailsList;
	}
	public void setTaxDetailsList(List<TaxDetails> taxDetailsList) {
		this.taxDetailsList = taxDetailsList;
	}
	public String getInvoiceStatus() {
		return invoiceStatus;
	}
	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	@Override
	public String toString() {
		return "Invoice [_id=" + _id + ", invoiceID=" + invoiceID + ", customerDetails=" + customerDetails
				+ ", invoiceLines=" + invoiceLines + ", invoiceDate=" + invoiceDate + ", dueDate=" + dueDate
				+ ", createdDateTime=" + createdDateTime + ", lastUpdatedDateTime=" + lastUpdatedDateTime
				+ ", organizationInfo=" + organizationInfo + ", paymentMethod=" + paymentMethod + ", totalGrossAmount="
				+ totalGrossAmount + ", totalTaxPercentage=" + totalTaxPercentage + ", totalTaxAmount=" + totalTaxAmount
				+ ", totalNetAmount=" + totalNetAmount + ", totalDiscountAmount=" + totalDiscountAmount
				+ ", taxDetailsList=" + taxDetailsList + ", invoiceStatus=" + invoiceStatus + ", paymentStatus="
				+ paymentStatus + "]";
	}
	
	
}
