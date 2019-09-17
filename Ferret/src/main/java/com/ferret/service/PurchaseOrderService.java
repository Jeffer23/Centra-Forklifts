package com.ferret.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ferret.dao.PurchaseDao;
import com.ferret.dao.impl.PurchaseDaoImpl;
import com.ferret.entity.PurchaseOrder;

@Service
public class PurchaseOrderService {

	public List<PurchaseOrder> getPurchaeOrders(long referenceNumber) {
		PurchaseDao dao = new PurchaseDaoImpl();
		return dao.getPurchaseProductsAfter(referenceNumber);
	}
}
