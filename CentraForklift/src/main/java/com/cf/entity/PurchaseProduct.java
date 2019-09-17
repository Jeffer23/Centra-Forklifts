package com.cf.entity;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "PURCHASE_PRODUCT")
public class PurchaseProduct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 579661513731132896L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PURCHASE_PRODUCT_ID")
	private int purchaseProductId;
		
	@Column(name = "QUANTITY")
	private int quantity;
	
	@Column(name = "TOTAL_AMOUNT")
	private float totalAmount;
	
	/*
	 * @Column(name = "PURCHASE_ORDER_ID") private long purchaseOrderID;
	 */
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PRODUCT_ID")
	private Product product;
	
	@Override
	public String toString() {
		return "PurchaseProduct [purchaseProductId=" + purchaseProductId + ", quantity=" + quantity + ", totalAmount="
				+ totalAmount + ", product=" + product + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + purchaseProductId;
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
		PurchaseProduct other = (PurchaseProduct) obj;
		if (purchaseProductId != other.purchaseProductId)
			return false;
		return true;
	}
	public int getPurchaseProductId() {
		return purchaseProductId;
	}
	public void setPurchaseProductId(int purchaseProductId) {
		this.purchaseProductId = purchaseProductId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
}
