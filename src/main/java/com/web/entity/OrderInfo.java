package com.web.entity;

import java.util.List;

public class OrderInfo {


	private Long orderId;

	private String customerName;

	private String phoneNumber;

	private String paymentMethod;

	private String address;

	private Double subtotalAmount;

	private List<Dishes> dishDetails;
	
	private String timestamp;

	public OrderInfo() {

	}

	public OrderInfo(Long orderId, String customerName, String phoneNumber, String paymentMethod, String address,
			Double subtotalAmount, List<Dishes> dishDetails,String timestamp) {
		super();
		this.orderId = orderId;
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
		this.paymentMethod = paymentMethod;
		this.address = address;
		this.subtotalAmount = subtotalAmount;
		this.dishDetails = dishDetails;
		this.timestamp = timestamp;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

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

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getSubtotalAmount() {
		return subtotalAmount;
	}

	public void setSubtotalAmount(Double subtotalAmount) {
		this.subtotalAmount = subtotalAmount;
	}

	public List<Dishes> getDishDetails() {
		return dishDetails;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setDishDetails(List<Dishes> dishDetails) {
		this.dishDetails = dishDetails;
	}

	@Override
	public String toString() {
		return "OrderInfo [orderId=" + orderId + ", customerName=" + customerName + ", phoneNumber=" + phoneNumber
				+ ", paymentMethod=" + paymentMethod + ", address=" + address + ", subtotalAmount=" + subtotalAmount
				+ ", dishDetails=" + dishDetails + ", timestamp=" + timestamp + "]";
	}


	
	

}
