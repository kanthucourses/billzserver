package com.billing.dto;

public class QuantityByServiceIDName {

	private String serviceIDName;
	private Double quantity;
	private Double netAmount;

	
	public String getServiceIDName() {
		return serviceIDName;
	}
	public void setServiceIDName(String serviceIDName) {
		this.serviceIDName = serviceIDName;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	
}
