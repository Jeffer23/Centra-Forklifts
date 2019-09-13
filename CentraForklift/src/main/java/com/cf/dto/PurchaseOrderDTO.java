package com.cf.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PurchaseOrderDTO {

	private long purchaseOrderID;
	private UserDTO userID;
	private List<PurchaseProductDTO> products = new ArrayList<PurchaseProductDTO>();
	private InvoiceDTO invoice;
	private Calendar orderDate;
	private String orderStatus = "Unapproved";
	private Calendar approvedDate;
	private String billingAddress;
	private String shippingAddress;
	private float purchaseTotalAmount;
	
	
	public long getPurchaseOrderID() {
		return purchaseOrderID;
	}


	public void setPurchaseOrderID(long purchaseOrderID) {
		this.purchaseOrderID = purchaseOrderID;
	}


	public UserDTO getUserID() {
		return userID;
	}


	public void setUserID(UserDTO userID) {
		this.userID = userID;
	}

	public List<PurchaseProductDTO> getProducts() {
		return products;
	}


	public void setProducts(List<PurchaseProductDTO> products) {
		this.products = products;
	}



	public InvoiceDTO getInvoice() {
		return invoice;
	}


	public void setInvoice(InvoiceDTO invoice) {
		this.invoice = invoice;
	}


	public Calendar getOrderDate() {
		return this.orderDate;
	}


	public void setOrderDate(Calendar orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public Calendar getApprovedDate() {
		return approvedDate;
	}


	public void setApprovedDate(Calendar approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getBillingAddress() {
		return billingAddress;
	}


	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}


	public String getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	public float getPurchaseTotalAmount() {
		return purchaseTotalAmount;
	}


	public void setPurchaseTotalAmount(float purchaseTotalAmount) {
		this.purchaseTotalAmount = purchaseTotalAmount;
	}


	@Override
	public String toString() {
		return "PurchaseOrderDTO [purchaseOrderID=" + purchaseOrderID + ", userID=" + userID + ", products=" + products
				+ ", invoice=" + invoice + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus
				+ ", approvedDate=" + approvedDate + ", billingAddress=" + billingAddress + ", ShippingAddress="
				+ shippingAddress + ", purchaseTotalAmount=" + purchaseTotalAmount + "]";
	}
	
	
}
