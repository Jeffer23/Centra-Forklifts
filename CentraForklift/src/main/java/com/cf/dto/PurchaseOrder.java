package com.cf.dto;

import java.util.Date;

public class PurchaseOrder {

	private long purchaseOrderID;
	private int userID;
	private Date orderDate;
	private String productName;
	private int quantity;
	private float unitPrice;
	private float totalPrice;
	private String orderStatus;
	private Date dateOfFullfillment;
	public long getPurchaseOrderID() {
		return purchaseOrderID;
	}
	public void setPurchaseOrderID(long purchaseOrderID) {
		this.purchaseOrderID = purchaseOrderID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getDateOfFullfillment() {
		return dateOfFullfillment;
	}
	public void setDateOfFullfillment(Date dateOfFullfillment) {
		this.dateOfFullfillment = dateOfFullfillment;
	}
	
	
	
	
}
