package com.ferret.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ferret.entity.PurchaseOrder;
import com.ferret.service.PurchaseOrderService;

@RestController
@RequestMapping("/purchaseOrder")
public class PurchaseController {

	@Autowired
	PurchaseOrderService service;
	
	@RequestMapping("/getPurchaeOrders")
	public List<PurchaseOrder> getPurchaeOrders(@RequestParam("referenceNumber") long referenceNumber) {
		System.out.println("getPurchaeOrders method Starts..." + referenceNumber);
		return service.getPurchaeOrders(referenceNumber);
	}
	
}
