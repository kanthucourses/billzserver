package com.billing.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class InvoiceLine {
	private Long invoiceLineID;
	private String serviceID;
	private String serviceName;
	private String serviceIDName;
	private String serviceCategory;
	private String serviceDescription;
	private Double quantity;
	private Double serviceCost;
	private Double amount;
	private String currency;
	private List<TaxMasterInfo> taxMasterInfos;
	private List<TaxDetails> taxDetailsList;
    private String discountType;
    private Double discountPercentage;
    private Double discountAmount;
    private Double taxAmount;
    private Double grossAmount;
    private Double netAmount;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime createdDateTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime lastUpdatedDateTime;
	
	
	public InvoiceLine() {
	
	}
	public Long getInvoiceLineID() {
		return invoiceLineID;
	}
	public void setInvoiceLineID(Long invoiceLineID) {
		this.invoiceLineID = invoiceLineID;
	}
	public String getServiceID() {
		return serviceID;
	}
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceIDName() {
		return serviceIDName;
	}
	public void setServiceIDName(String serviceIDName) {
		this.serviceIDName = serviceIDName;
	}
	public String getServiceCategory() {
		return serviceCategory;
	}
	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}
	public String getServiceDescription() {
		return serviceDescription;
	}
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getServiceCost() {
		return serviceCost;
	}
	public void setServiceCost(Double serviceCost) {
		this.serviceCost = serviceCost;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public List<TaxDetails> getTaxDetailsList() {
		return taxDetailsList;
	}
	public void setTaxDetailsList(List<TaxDetails> taxDetailsList) {
		this.taxDetailsList = taxDetailsList;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public Double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(Double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public Double getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(Double grossAmount) {
		this.grossAmount = grossAmount;
	}
	public Double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}
	public List<TaxMasterInfo> getTaxMasterInfos() {
		return taxMasterInfos;
	}
	public void setTaxMasterInfos(List<TaxMasterInfo> taxMasterInfos) {
		this.taxMasterInfos = taxMasterInfos;
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
	@Override
	public String toString() {
		return "InvoiceLine [invoiceLineID=" + invoiceLineID + ", serviceID=" + serviceID + ", serviceName="
				+ serviceName + ", serviceIDName=" + serviceIDName + ", serviceCategory=" + serviceCategory
				+ ", serviceDescription=" + serviceDescription + ", quantity=" + quantity + ", serviceCost="
				+ serviceCost + ", amount=" + amount + ", currency=" + currency + ", taxMasterInfos=" + taxMasterInfos
				+ ", taxDetailsList=" + taxDetailsList + ", discountType=" + discountType + ", discountPercentage="
				+ discountPercentage + ", discountAmount=" + discountAmount + ", taxAmount=" + taxAmount
				+ ", grossAmount=" + grossAmount + ", netAmount=" + netAmount + "]";
	}
	

}
