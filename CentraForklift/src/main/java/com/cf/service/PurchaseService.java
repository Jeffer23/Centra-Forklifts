package com.cf.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.cf.dao.PurchaseDao;
import com.cf.dao.UserDao;
import com.cf.dao.impl.PurchaseDaoImpl;
import com.cf.dao.impl.UserDaoImpl;
import com.cf.dto.UserDTO;
import com.cf.entity.Invoice;
import com.cf.entity.Product;
import com.cf.entity.PurchaseOrder;
import com.cf.entity.User;
import com.cf.ferret.FerretApp;

@Service
public class PurchaseService {


	public List<PurchaseOrder> getAllUnFullfilmentPurchases(String userId) {
		System.out.println("User ID : " + userId);
		UserDao userDao = new UserDaoImpl();
		PurchaseDao purchaseDao = new PurchaseDaoImpl();
		List<PurchaseOrder> purchaseOrderEntities= null;
		User user = userDao.getUser(userId);
		if(user.getUserRole().equalsIgnoreCase("admin")) {
			purchaseOrderEntities = purchaseDao.getAllViewPurchaseOrderDetails();
		} else if(user.getUserRole().equalsIgnoreCase("dealer")) {
			purchaseOrderEntities = purchaseDao.getViewPurchaseOrderDetails(userId);
		}
		

		System.out.println(purchaseOrderEntities);
		return purchaseOrderEntities;
	}

	public List<PurchaseOrder> getFullfilledTabDetails(@RequestParam("userId") String userId) {
		System.out.println("User ID : " + userId);
		UserDao userDao = new UserDaoImpl();
		PurchaseDao purchaseDao = new PurchaseDaoImpl();
		List<PurchaseOrder> purchaseOrderEntities= null;
		User user = userDao.getUser(userId);
		if(user.getUserRole().equalsIgnoreCase("admin")) {
			purchaseOrderEntities = purchaseDao.getAllFullfillmentDetails();
		} else if(user.getUserRole().equalsIgnoreCase("dealer")) {
			purchaseOrderEntities = purchaseDao.getFullfillmentDetails(userId);
		}
		

		System.out.println(purchaseOrderEntities);
		return purchaseOrderEntities;
	}

	public boolean updatePurchaseOrderStatus(List<Long> purchaseOrderIds) {
		System.out.println("purchase Order Id's : " + purchaseOrderIds);
		PurchaseDao purchaseDao = new PurchaseDaoImpl();
		purchaseOrderIds.parallelStream().forEach(purchaseOrderId->{
			PurchaseOrder purchaseOrder = purchaseDao.getPurchaseOrder(purchaseOrderId);
			purchaseOrder.setOrderStatus("Approved");
			purchaseOrder.setApprovedDate(Calendar.getInstance());
			
			purchaseDao.updatePurchaseOrder(purchaseOrder);
		});
		
		return true;
	}

	public boolean fullfillPurchaseOrder(List<PurchaseOrder> purchaseOrders) {
		System.out.println("purchase Order Id's : " + purchaseOrders);
		PurchaseDao purchaseDao = new PurchaseDaoImpl();
		purchaseOrders.parallelStream().forEach(order->{
			Invoice invoice = order.getInvoice();
			invoice.setInvoiceDate(Calendar.getInstance());
			invoice.setDueDate(Calendar.getInstance());
			invoice.getDueDate().add(Calendar.DAY_OF_MONTH, 14);
			invoice.setBalanceDue(invoice.getSubTotalLessDiscount() + invoice.getTotalTax() + invoice.getShippingFee());
			
			order.setOrderStatus("Fullfilled");
			purchaseDao.updatePurchaseOrder(order);
		});

		return true;
	}

	public boolean addPurchaseOrder(PurchaseOrder purchaseOrder) {
		System.out.println(purchaseOrder);
		UserDao userDao = new UserDaoImpl();
		PurchaseDao purchaseDao = new PurchaseDaoImpl();
		
		User user = userDao.getUser(purchaseOrder.getUserID().getEmailAddress());
		purchaseOrder.setUserID(user);
		Calendar orderDate = Calendar.getInstance();
		orderDate.add(Calendar.DAY_OF_MONTH, 1);
		purchaseOrder.setOrderDate(orderDate);
		purchaseOrder.setPurchaseTotalAmount((float) purchaseOrder.getPurchaseProducts().stream()
				.mapToDouble(product -> product.getTotalAmount()).reduce(0, Double::sum));
		
		
		purchaseDao.addPurchaseOrder(purchaseOrder);
		return true;
	}

	public List<Product> getAllProducts() {
		PurchaseDao purchaseDao = new PurchaseDaoImpl();
		return purchaseDao.getAllproducts();
	}

	public List<PurchaseOrder> getInvoiceDetails(int invoiceId) {
		System.out.println("Invoice Id : " + invoiceId);
		PurchaseDao purchaseDao = new PurchaseDaoImpl();
		return purchaseDao.getInvoiceDetails(invoiceId);
		
	}
	
	public void syncWithFerretDB() {
		FerretApp ferretApp = new FerretApp();
		ferretApp.sync();
	}
	
}
