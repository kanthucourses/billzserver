package com.billing.model;

public class TaxDetails {
	private String _id;
	private String taxName;
	private Double taxPercentage;
	private String taxNamePercentage;
	private Double taxAmount;
	
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
	public Double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public String getTaxNamePercentage() {
		return taxNamePercentage;
	}
	public void setTaxNamePercentage(String taxNamePercentage) {
		this.taxNamePercentage = taxNamePercentage;
	}
	
	
}
