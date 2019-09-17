package com.cf.dao;

import java.util.List;

import com.cf.entity.Product;
import com.cf.entity.PurchaseOrder;

public interface PurchaseDao {

	public abstract List<PurchaseOrder> getViewPurchaseOrderDetails(String userId);
	public abstract List<PurchaseOrder> getAllViewPurchaseOrderDetails();
	public abstract List<PurchaseOrder> getFullfillmentDetails(String userId);
	public abstract List<PurchaseOrder> getAllFullfillmentDetails();
	public abstract PurchaseOrder getPurchaseOrder(long PurchaseOrderId);
	public abstract void updatePurchaseOrder(PurchaseOrder purchaseOrder);
	public abstract void addPurchaseOrder(PurchaseOrder purchaseOrder);
	public abstract List<Product> getAllproducts();
	public abstract Product getProduct(String productName, float unitPrice);
	public abstract void saveProduct(Product product);
	public abstract List<PurchaseOrder> getInvoiceDetails(int invoiceId);
	public abstract long getLastSyncedRecord();
}
