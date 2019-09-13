package com.cf.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InvoiceDTO {

	private int invoiceId;
	private Calendar invoiceDate;
	private Calendar dueDate;
	private float subTotal;
	private float discount;
	private float subTotalLessDiscount;
	private float taxRate;
	private float totalTax;
	private float shippingFee;
	private float balanceDue;
	
	
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



	@Override
	public String toString() {
		return "InvoiceDTO [invoiceId=" + invoiceId + ", invoiceDate=" + invoiceDate + ", dueDate=" + dueDate
				+ ", subTotal=" + subTotal + ", discount=" + discount + ", subTotalLessDiscount=" + subTotalLessDiscount
				+ ", taxRate=" + taxRate + ", totalTax=" + totalTax + ", shippingFee=" + shippingFee + ", balanceDue="
				+ balanceDue + "]";
	}
	
	
	
}
