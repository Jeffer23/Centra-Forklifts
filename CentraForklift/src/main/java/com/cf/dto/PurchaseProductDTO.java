package com.cf.dto;

public class PurchaseProductDTO {

	private int purchaseProductId;
	private ProductDTO product;
	private int quantity;
	private float totalAmount;
	public int getPurchaseProductId() {
		return purchaseProductId;
	}
	public void setPurchaseProductId(int purchaseProductId) {
		this.purchaseProductId = purchaseProductId;
	}
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
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
	@Override
	public String toString() {
		return "PurchaseProductDTO [purchaseProductId=" + purchaseProductId + ", product=" + product + ", quantity="
				+ quantity + ", totalAmount=" + totalAmount + "]";
	}
	
	
	
}
