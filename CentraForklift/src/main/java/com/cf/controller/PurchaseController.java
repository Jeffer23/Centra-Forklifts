package com.cf.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cf.entity.Product;
import com.cf.entity.PurchaseOrder;
import com.cf.service.PurchaseService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService service;
	
	@RequestMapping(path="/getInvoiceTabDetails", produces = "application/json")
	public List<PurchaseOrder> getAllUnFullfilmentPurchases(@RequestParam("userId") String userId) {
		return service.getAllUnFullfilmentPurchases(userId);
	}
	
	@RequestMapping("/getFullfilledTabDetails")
	public List<PurchaseOrder> getFullfilledTabDetails(@RequestParam("userId") String userId) {
		return service.getFullfilledTabDetails(userId);
	}
	
	@PostMapping("/approvePurchaseOrder")
	public boolean updatePurchaseOrderStatus(@RequestBody ArrayList<Long> purchaseOrderIds) {
		return service.updatePurchaseOrderStatus(purchaseOrderIds);
	}
	
	@PostMapping("/fullfillPurchaseOrder")
	public boolean fullfillPurchaseOrder(@RequestBody List<PurchaseOrder> purchaseOrders) {
		return service.fullfillPurchaseOrder(purchaseOrders);
	}
	
	@PostMapping("/addPurchaseOrder")
	public boolean addPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
		return service.addPurchaseOrder(purchaseOrder);
	}
	
	@RequestMapping("/getAllProducts")
	public List<Product> getAllProducts(){
		return service.getAllProducts();
	}
	
	@RequestMapping("/getInvoiceDetails")
	public List<PurchaseOrder> getInvoiceDetails(@RequestParam("invoiceId") int invoiceId){
		return service.getInvoiceDetails(invoiceId);
	}
	
	@RequestMapping("/syncWithFerretApp")
	public void syncWithFerretDB() {
		service.syncWithFerretDB();
	}
	public static void main(String[] args) {
		PurchaseController controller = new PurchaseController();
		controller.getAllUnFullfilmentPurchases("pemelajefferson@gmail.com");
	}
}
