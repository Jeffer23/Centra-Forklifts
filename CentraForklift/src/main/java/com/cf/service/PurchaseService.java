package com.cf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cf.dto.PurchaseOrder;
@Service
public class PurchaseService {

	
	public List<PurchaseOrder> getAllUnfillmentPurchases(int userId){
		System.out.println("User ID : " + userId);
		List<PurchaseOrder> purchaseOrders = new ArrayList<PurchaseOrder>();
		
		PurchaseOrder po = new PurchaseOrder();
		po.setDateOfFullfillment(new Date());
		po.setOrderDate(new Date());
		po.setOrderStatus("Pending");
		po.setProductName("Mobile");
		po.setPurchaseOrderID(12345);
		po.setQuantity(1);
		po.setTotalPrice(15000f);
		po.setUnitPrice(15000f);
		po.setUserID(1);
		
		purchaseOrders.add(po);
		
		return purchaseOrders;
	}
	
	
	public boolean updatePurchaseOrderStatus(List<Integer> purchaseOrderIds) {
		System.out.println("purchase Order Id's : " + purchaseOrderIds);
		
		return true;
	}
	
	public boolean addPurchaseOrder(PurchaseOrder purchaseOrder) {

		return true;
	}
}
