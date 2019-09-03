package com.cf.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cf.dto.PurchaseOrder;
import com.cf.service.PurchaseService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService service;
	
	@RequestMapping("/getInvoiceTabDetails")
	public List<PurchaseOrder> getAllUnfillmentPurchases(@RequestParam("userId") int userId) {
		return service.getAllUnfillmentPurchases(userId);
	}
	
	@PostMapping("/approvePurchaseOrder")
	public boolean updatePurchaseOrderStatus(@RequestBody ArrayList<Integer> purchaseOrderIds) {
		return service.updatePurchaseOrderStatus(purchaseOrderIds);
	}
	
	@PostMapping("/addPurchaseOrder")
	public boolean addPurchaseOrder(PurchaseOrder purchaseOrder) {
		return service.addPurchaseOrder(purchaseOrder);
	}
}
