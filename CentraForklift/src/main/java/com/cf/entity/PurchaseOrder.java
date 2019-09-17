package com.cf.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "PURCHASE_ORDER")
public class PurchaseOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1237091662730089339L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PURCHASE_ORDER_ID")
	private long purchaseOrderID;
	
	@Column(name = "referenceNumber")
	private long referenceNumber;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private User userID;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="PURCHASE_ORDER_ID") 
	private List<PurchaseProduct> purchaseProducts = new ArrayList<PurchaseProduct>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="INVOICE_ID")
	private Invoice invoice;
	
	@Column(name = "ORDER_DATE")
	@Temporal(TemporalType.DATE)
	private Calendar orderDate;
	
	@Column(name = "ORDER_STATUS")
	private String orderStatus = "Unapproved";
	
	@Column(name = "APPROVED_DATE")
	@Temporal(TemporalType.DATE)
	private Calendar approvedDate;
	
	@Column(name = "BILLING_ADDRESS")
	private String billingAddress;
	
	@Column(name = "SHIPPING_ADDRESS")
	private String shippingAddress;
	
	@Column(name = "PURCHASE_TOTAL_AMOUNT")
	private float purchaseTotalAmount;

	public long getPurchaseOrderID() {
		return purchaseOrderID;
	}

	public void setPurchaseOrderID(long purchaseOrderID) {
		this.purchaseOrderID = purchaseOrderID;
	}

	public User getUserID() {
		return userID;
	}

	public void setUserID(User userID) {
		this.userID = userID;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Calendar getOrderDate() {
		return orderDate;
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

	
	public List<PurchaseProduct> getPurchaseProducts() {
		return purchaseProducts;
	}

	public void setPurchaseProducts(List<PurchaseProduct> purchaseProducts) {
		this.purchaseProducts = purchaseProducts;
	}
	
	

	public long getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(long referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	/*
	 * @Override public String toString() { return "PurchaseOrder [purchaseOrderID="
	 * + purchaseOrderID + ", userID=" + userID + ", invoice=" + invoice +
	 * ", orderDate=" + orderDate + ", orderStatus=" + orderStatus +
	 * ", approvedDate=" + approvedDate + ", billingAddress=" + billingAddress +
	 * ", shippingAddress=" + shippingAddress + ", purchaseTotalAmount=" +
	 * purchaseTotalAmount + "]"; }
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (purchaseOrderID ^ (purchaseOrderID >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PurchaseOrder other = (PurchaseOrder) obj;
		if (purchaseOrderID != other.purchaseOrderID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [purchaseOrderID=" + purchaseOrderID + ", userID=" + userID + ", purchaseProducts="
				+ purchaseProducts + ", invoice=" + invoice + ", orderDate=" + orderDate + ", orderStatus="
				+ orderStatus + ", approvedDate=" + approvedDate + ", billingAddress=" + billingAddress
				+ ", shippingAddress=" + shippingAddress + ", purchaseTotalAmount=" + purchaseTotalAmount + "]";
	}
	
	
	
	
}
