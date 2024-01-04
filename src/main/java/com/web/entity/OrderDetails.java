package com.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Resturant_Order_Details")
public class OrderDetails {

	// private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq_gen")
	@SequenceGenerator(name = "order_id_seq_gen", sequenceName = "ORDER_ID_SEQ", allocationSize = 1, initialValue = 1001)
	@Column(name = "ORDER_ID")
	private Long orderId; // Auto-incremented ID

	@Column(name = "Customer_Name")
	private String customerName;

	@Column(name = "Phone_Number")
	private String phoneNumber;

	@Column(name = "Dishes_Details")
	private String dishes;

	@Column(name = "Payment_Method")
	private String paymentMethod;

	@Column(name = "Address")
	private String address;

	@Column(name = "Total_Amount")
	private Double subtotalAmount;

	// Use the 'TIMESTAMP' type to store LocalDateTime in the database
	@Column(name = "TIMESTAMP")
	private String myDateTime;

	public OrderDetails() {
		super();
	}

	public OrderDetails(Long orderId, String customerName, String phoneNumber, String dishes, String paymentMethod,
			String address, Double subtotalAmount, String myDateTime) {
		super();
		this.orderId = orderId;
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
		this.dishes = dishes;
		this.paymentMethod = paymentMethod;
		this.address = address;
		this.subtotalAmount = subtotalAmount;
		this.myDateTime = myDateTime;
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

	public String getDishes() {
		return dishes;
	}

	public void setDishes(String dishes) {
		this.dishes = dishes;
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

	public String getMyDateTime() {
		return myDateTime;
	}

	public void setMyDateTime(String myDateTime) {
		this.myDateTime = myDateTime;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderId=" + orderId + ", customerName=" + customerName + ", phoneNumber=" + phoneNumber
				+ ", dishes=" + dishes + ", paymentMethod=" + paymentMethod + ", address=" + address
				+ ", subtotalAmount=" + subtotalAmount + ", myDateTime=" + myDateTime + "]";
	}

}
