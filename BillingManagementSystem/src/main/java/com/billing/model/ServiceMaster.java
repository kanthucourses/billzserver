package com.billing.model;

import java.util.List;

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
    private String discountType; //percentage,amount

	
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
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}


}
