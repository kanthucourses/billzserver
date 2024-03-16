package com.billing.model;

public class CustomerDetails {

	private String customerName;
	private String phoneNumber;
	private String alternativePhoneNumber;
	private String emailID;
	private Integer age;
	private String gender;
	
	private String address;
	private String city;
	private String state;
	private String country;
	private String pincode;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAlternativePhoneNumber() {
		return alternativePhoneNumber;
	}
	public void setAlternativePhoneNumber(String alternativePhoneNumber) {
		this.alternativePhoneNumber = alternativePhoneNumber;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "CustomerDetails [customerName=" + customerName + ", phoneNumber=" + phoneNumber
				+ ", alternativePhoneNumber=" + alternativePhoneNumber + ", emailID=" + emailID + ", age=" + age
				+ ", gender=" + gender + ", address=" + address + ", city=" + city + ", state=" + state + ", country="
				+ country + ", pincode=" + pincode + "]";
	}

	
	
}
