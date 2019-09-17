package com.cf.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "INVOICE")
public class Invoice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5507760182738354203L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INVOICE_ID")
	private int invoiceId;
	
	@Column(name = "INVOICE_DATE")
	@Temporal(TemporalType.DATE)
	private Calendar invoiceDate;
	
	@Column(name = "DUE_DATE")
	@Temporal(TemporalType.DATE)
	private Calendar dueDate;
	
	@Column(name = "SUB_TOTAL")
	private float subTotal;
	
	@Column(name = "DISCOUNT")
	private float discount;
	
	@Column(name = "SUB_TOTAL_LESS_DISCOUNT")
	private float subTotalLessDiscount;
	
	@Column(name = "TAX_RATE")
	private float taxRate;
	
	@Column(name = "TOTAL_TAX")
	private float totalTax;
	
	@Column(name = "SHIPPING_FEE")
	private float shippingFee;
	
	@Column(name = "BALANCE_DUE")
	private float balanceDue;
	
	/*
	 * @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	 * 
	 * @JoinColumn(name="INVOICE_ID") private Set<PurchaseOrder> purchaseOrders =
	 * new HashSet<PurchaseOrder>();
	 */
	
	public int getInvoiceId() {
		return invoiceId;
	}


	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}


	public Calendar getInvoiceDate() {
		return invoiceDate;
	}


	public void setInvoiceDate(Calendar invoiceDate) {
		this.invoiceDate = invoiceDate;
	}


	public Calendar getDueDate() {
		return dueDate;
	}


	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

	public float getSubTotal() {
		return subTotal;
	}


	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}


	public float getDiscount() {
		return discount;
	}


	public void setDiscount(float discount) {
		this.discount = discount;
	}


	public float getSubTotalLessDiscount() {
		return subTotalLessDiscount;
	}


	public void setSubTotalLessDiscount(float subTotalLessDiscount) {
		this.subTotalLessDiscount = subTotalLessDiscount;
	}


	
	public float getTotalTax() {
		return totalTax;
	}


	public void setTotalTax(float totalTax) {
		this.totalTax = totalTax;
	}


	public float getShippingFee() {
		return shippingFee;
	}


	public void setShippingFee(float shippingFee) {
		this.shippingFee = shippingFee;
	}


	public float getBalanceDue() {
		return balanceDue;
	}


	public void setBalanceDue(float balanceDue) {
		this.balanceDue = balanceDue;
	}


	public float getTaxRate() {
		return taxRate;
	}


	public void setTaxRate(float taxRate) {
		this.taxRate = taxRate;
	}

	


	/*
	 * @Override public String toString() { return "Invoice [invoiceId=" + invoiceId
	 * + ", invoiceDate=" + invoiceDate + ", dueDate=" + dueDate + ", subTotal=" +
	 * subTotal + ", discount=" + discount + ", subTotalLessDiscount=" +
	 * subTotalLessDiscount + ", taxRate=" + taxRate + ", totalTax=" + totalTax +
	 * ", shippingFee=" + shippingFee + ", balanceDue=" + balanceDue + "]"; }
	 */


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + invoiceId;
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
		Invoice other = (Invoice) obj;
		if (invoiceId != other.invoiceId)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", invoiceDate=" + invoiceDate + ", dueDate=" + dueDate
				+ ", subTotal=" + subTotal + ", discount=" + discount + ", subTotalLessDiscount=" + subTotalLessDiscount
				+ ", taxRate=" + taxRate + ", totalTax=" + totalTax + ", shippingFee=" + shippingFee + ", balanceDue="
				+ balanceDue + "]";
	}
	
	
	
}
