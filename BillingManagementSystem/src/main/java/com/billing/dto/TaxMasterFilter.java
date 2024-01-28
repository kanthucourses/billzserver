package com.billing.dto;

public class TaxMasterFilter {
	private String taxName;
	private String organizationID;
	private String organizationName;
	private String organizationIDName;
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName;
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
