package com.billing.model;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

public class ProductMaster {

	private String _id;
	private BigDecimal height;
	private BigDecimal length;
	private BigDecimal weight;
	private BigDecimal volume;
	private BigDecimal quantity;
	private BigDecimal locationVolume;
	private BigDecimal noOfProducts;
	@Field(targetType = FieldType.DECIMAL128)
	private BigDecimal dheight;
	@Field(targetType = FieldType.DECIMAL128)
	private BigDecimal dlength;
	@Field(targetType = FieldType.DECIMAL128)
	private BigDecimal dweight;
	@Field(targetType = FieldType.DECIMAL128)
	private BigDecimal dvolume;
	@Field(targetType = FieldType.DECIMAL128)
	private BigDecimal dquantity;
	@Field(targetType = FieldType.DECIMAL128)
	private BigDecimal dlocationVolume;
	@Field(targetType = FieldType.DECIMAL128)
	private BigDecimal dnoOfProducts;
	
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
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
	public BigDecimal getLocationVolume() {
		return locationVolume;
	}
	public void setLocationVolume(BigDecimal locationVolume) {
		this.locationVolume = locationVolume;
	}
	public BigDecimal getNoOfProducts() {
		return noOfProducts;
	}
	public void setNoOfProducts(BigDecimal noOfProducts) {
		this.noOfProducts = noOfProducts;
	}
	public BigDecimal getDheight() {
		return dheight;
	}
	public void setDheight(BigDecimal dheight) {
		this.dheight = dheight;
	}
	public BigDecimal getDlength() {
		return dlength;
	}
	public void setDlength(BigDecimal dlength) {
		this.dlength = dlength;
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
	public BigDecimal getDlocationVolume() {
		return dlocationVolume;
	}
	public void setDlocationVolume(BigDecimal dlocationVolume) {
		this.dlocationVolume = dlocationVolume;
	}
	public BigDecimal getDnoOfProducts() {
		return dnoOfProducts;
	}
	public void setDnoOfProducts(BigDecimal dnoOfProducts) {
		this.dnoOfProducts = dnoOfProducts;
	}
	public BigDecimal getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	public BigDecimal getDvolume() {
		return dvolume;
	}
	public void setDvolume(BigDecimal dvolume) {
		this.dvolume = dvolume;
	}
	
	
	
}
