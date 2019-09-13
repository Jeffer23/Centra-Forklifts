package com.cf.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cf.dto.InvoiceDTO;
import com.cf.dto.ProductDTO;
import com.cf.dto.PurchaseOrderDTO;
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
	public List<PurchaseOrderDTO> getAllUnFullfilmentPurchases(@RequestParam("userId") String userId) {
		return service.getAllUnFullfilmentPurchases(userId);
	}
	
	@RequestMapping("/getFullfilledTabDetails")
	public List<PurchaseOrderDTO> getFullfilledTabDetails(@RequestParam("userId") String userId) {
		return service.getFullfilledTabDetails(userId);
	}
	
	@PostMapping("/approvePurchaseOrder")
	public boolean updatePurchaseOrderStatus(@RequestBody ArrayList<Long> purchaseOrderIds) {
		return service.updatePurchaseOrderStatus(purchaseOrderIds);
	}
	
	@PostMapping("/fullfillPurchaseOrder")
	public boolean fullfillPurchaseOrder(@RequestBody List<PurchaseOrderDTO> purchaseOrders) {
		return service.fullfillPurchaseOrder(purchaseOrders);
	}
	
	@PostMapping("/addPurchaseOrder")
	public boolean addPurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrder) {
		return service.addPurchaseOrder(purchaseOrder);
	}
	
	@RequestMapping("/getAllProducts")
	public List<ProductDTO> getAllProducts(){
		return service.getAllProducts();
	}
	
	@RequestMapping("/getInvoiceDetails")
	public List<PurchaseOrderDTO> getInvoiceDetails(@RequestParam("invoiceId") int invoiceId){
		return service.getInvoiceDetails(invoiceId);
	}
}
