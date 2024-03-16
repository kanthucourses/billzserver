package com.billing.dto;

public class InvoiceRevenueResponse {
	private String monthYear;
	private Double totalNetAmount;
	
	public String getMonthYear() {
		return monthYear;
	}
	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}
	public Double getTotalNetAmount() {
		return totalNetAmount;
	}
	public void setTotalNetAmount(Double totalNetAmount) {
		this.totalNetAmount = totalNetAmount;
	}
	
}
