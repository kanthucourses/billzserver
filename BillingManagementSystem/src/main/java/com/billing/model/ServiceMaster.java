package com.billing.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

public class ServiceMaster {
	private String _id;
	private String serviceID;
	private String serviceName;
	private String serviceIDName;
	private Double serviceCost;
	private String serviceCategory;
	private String serviceDescription;
	private OrganizationInfo organizationInfo;
	private List<TaxMasterInfo> taxMasterInfos;
	
	private BigDecimal height;
	private BigDecimal length;
	@Field(targetType = FieldType.DECIMAL128)
	private BigDecimal checkNum;
	private BigDecimal weight;
	private BigDecimal quantity;
	@Field(targetType = FieldType.DECIMAL128)
	private BigDecimal dweight;
	@Field(targetType = FieldType.DECIMAL128)
	private BigDecimal dquantity;
	private BigDecimal weightofOneProduct;
	@Field(targetType = FieldType.DECIMAL128)
	private BigDecimal dweightofOneProduct;

	
  //  private String discountType; //percentage,amount

	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
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
	public Double getServiceCost() {
		return serviceCost;
	}
	public void setServiceCost(Double serviceCost) {
		this.serviceCost = serviceCost;
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
	public OrganizationInfo getOrganizationInfo() {
		return organizationInfo;
	}
	public void setOrganizationInfo(OrganizationInfo organizationInfo) {
		this.organizationInfo = organizationInfo;
	}
	
	public List<TaxMasterInfo> getTaxMasterInfos() {
		return taxMasterInfos;
	}
	public void setTaxMasterInfos(List<TaxMasterInfo> taxMasterInfos) {
		this.taxMasterInfos = taxMasterInfos;
	}
	public BigDecimal getHeight() {
		return height;
	}
	public void setHeight(BigDecimal height) {
		this.height = height;
	}
	public BigDecimal getLength() {
		return length;
	}
	public void setLength(BigDecimal length) {
		this.length = length;
	}
	public BigDecimal getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(BigDecimal checkNum) {
		this.checkNum = checkNum;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getDweight() {
		return dweight;
	}
	public void setDweight(BigDecimal dweight) {
		this.dweight = dweight;
	}
	public BigDecimal getDquantity() {
		return dquantity;
	}
	public void setDquantity(BigDecimal dquantity) {
		this.dquantity = dquantity;
	}
	public BigDecimal getWeightofOneProduct() {
		return weightofOneProduct;
	}
	public void setWeightofOneProduct(BigDecimal weightofOneProduct) {
		this.weightofOneProduct = weightofOneProduct;
	}
	public BigDecimal getDweightofOneProduct() {
		return dweightofOneProduct;
	}
	public void setDweightofOneProduct(BigDecimal dweightofOneProduct) {
		this.dweightofOneProduct = dweightofOneProduct;
	}

}
