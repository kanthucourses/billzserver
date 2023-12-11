package com.billing.model;

public class TaxMasterInfo {
	private String _id;
	private String taxName;
	private Double taxPercentage;
	private String taxNamePercentage;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}
	public Double getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(Double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	public String getTaxNamePercentage() {
		return taxNamePercentage;
	}
	public void setTaxNamePercentage(String taxNamePercentage) {
		this.taxNamePercentage = taxNamePercentage;
	}
	
	
}
