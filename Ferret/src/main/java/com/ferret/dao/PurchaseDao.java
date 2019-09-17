package com.ferret.dao;

import java.util.List;

import com.ferret.entity.PurchaseOrder;

public interface PurchaseDao {

	public List<PurchaseOrder> getPurchaseProductsAfter(long referenceNumber);
}
