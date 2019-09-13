package com.cf.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.cf.dto.InvoiceDTO;
import com.cf.dto.ProductDTO;
import com.cf.dto.PurchaseOrderDTO;
import com.cf.dto.PurchaseProductDTO;
import com.cf.dto.UserDTO;

@Service
public class PurchaseService {

	public static List<ProductDTO> products = new ArrayList<ProductDTO>();
	public static Map<String, List<PurchaseOrderDTO>> purchaseOrders = new HashMap<String, List<PurchaseOrderDTO>>();
	public static Map<Integer, InvoiceDTO> invoices = new HashMap<Integer, InvoiceDTO>();
	public static int purchaseOrderId = 10000;
	public static int invoiceId = 30000;

	static {
		ProductDTO product = new ProductDTO();
		product.setProductId(1);
		product.setProductName("Pen");
		product.setUnitPrice(10);
		products.add(product);

		product = new ProductDTO();
		product.setProductId(2);
		product.setProductName("Phone");
		product.setUnitPrice(15000);
		products.add(product);

		product = new ProductDTO();
		product.setProductId(3);
		product.setProductName("Laptop");
		product.setUnitPrice(50000);
		products.add(product);
	}
	static {
		List<PurchaseOrderDTO> orders = new ArrayList<PurchaseOrderDTO>();
		PurchaseOrderDTO order = new PurchaseOrderDTO();
		order.setApprovedDate(Calendar.getInstance());
		order.setBillingAddress("No:16, 5th cross, 1st street, Ammayappa Nagar, Vayalur Rode, Trichy-620017.");
		order.setInvoice(null);
		order.setOrderDate(Calendar.getInstance());
		order.setOrderStatus("Approved");
		order.setPurchaseOrderID(++purchaseOrderId);
		order.setShippingAddress("No:16, 5th cross, 1st street, Ammayappa Nagar, Vayalur Rode, Trichy-620017.");
		order.setPurchaseTotalAmount(15100);
		order.setUserID(UserService.users.get("mani143@gmail.com"));

		PurchaseProductDTO purchaseProduct = new PurchaseProductDTO();
		purchaseProduct.setProduct(products.get(0));
		purchaseProduct.setPurchaseProductId(10001);
		purchaseProduct.setQuantity(10);
		purchaseProduct.setTotalAmount(100);
		order.getProducts().add(purchaseProduct);

		purchaseProduct = new PurchaseProductDTO();
		purchaseProduct.setProduct(products.get(1));
		purchaseProduct.setPurchaseProductId(10002);
		purchaseProduct.setQuantity(1);
		purchaseProduct.setTotalAmount(15000);
		order.getProducts().add(purchaseProduct);

		orders.add(order);

		order = new PurchaseOrderDTO();
		order.setApprovedDate(null);
		order.setBillingAddress("S2, Wilson's Anjali Apartment, Madha Nagar 3rd main road, Madhananthapuram, Chennai-600089.");
		order.setInvoice(null);
		order.setOrderDate(Calendar.getInstance());
		order.setOrderStatus("Unapproved");
		order.setPurchaseOrderID(++purchaseOrderId);
		order.setShippingAddress("S2, Wilson's Anjali Apartment, Madha Nagar 3rd main road, Madhananthapuram, Chennai-600089.");
		order.setPurchaseTotalAmount(65250);
		order.setUserID(UserService.users.get("mani143@gmail.com"));

		purchaseProduct = new PurchaseProductDTO();
		purchaseProduct.setProduct(products.get(2));
		purchaseProduct.setPurchaseProductId(10003);
		purchaseProduct.setQuantity(1);
		purchaseProduct.setTotalAmount(50000);
		order.getProducts().add(purchaseProduct);

		purchaseProduct = new PurchaseProductDTO();
		purchaseProduct.setProduct(products.get(0));
		purchaseProduct.setPurchaseProductId(10004);
		purchaseProduct.setQuantity(25);
		purchaseProduct.setTotalAmount(250);
		order.getProducts().add(purchaseProduct);

		purchaseProduct = new PurchaseProductDTO();
		purchaseProduct.setProduct(products.get(1));
		purchaseProduct.setPurchaseProductId(10005);
		purchaseProduct.setQuantity(1);
		purchaseProduct.setTotalAmount(15000);
		order.getProducts().add(purchaseProduct);

		orders.add(order);

		purchaseOrders.put("mani143@gmail.com", orders);
	}

	public List<PurchaseOrderDTO> getAllUnFullfilmentPurchases(String userId) {
		System.out.println("User ID : " + userId);
		List<PurchaseOrderDTO> purchaseOrderList = new ArrayList<PurchaseOrderDTO>();
		if (UserService.users.get(userId).getUserRole().equals("Admin")) {
			purchaseOrders.values().stream().forEach(orders -> {
				purchaseOrderList.addAll(orders.stream().filter(order-> order.getOrderStatus().equals("Approved") || order.getOrderStatus().equals("Unapproved")).collect(Collectors.toList()));
			});
			
			
		}else if(UserService.users.get(userId).getUserRole().equals("Dealer")){
			List<PurchaseOrderDTO> orders = purchaseOrders.get(userId);
			purchaseOrderList.addAll(orders.stream().filter(order-> order.getOrderStatus().equals("Approved") || order.getOrderStatus().equals("Unapproved")).collect(Collectors.toList()));
		}
		
		System.out.println(purchaseOrderList);
		return purchaseOrderList;
	}

	public List<PurchaseOrderDTO> getFullfilledTabDetails(@RequestParam("userId") String userId) {
		System.out.println("User ID : " + userId);
		List<PurchaseOrderDTO> filteredOrders = null;
		if (UserService.users.get(userId).getUserRole().equals("Admin")) {
			List<PurchaseOrderDTO> allOrders = new ArrayList<PurchaseOrderDTO>();
			purchaseOrders.values().stream().forEach(orders -> allOrders.addAll(orders));
			filteredOrders = allOrders.stream().filter(order -> (order.getOrderStatus().equalsIgnoreCase("Approved")
					|| order.getOrderStatus().equalsIgnoreCase("Fullfilled"))).collect(Collectors.toList());
			System.out.println(filteredOrders);
			return filteredOrders;
		}
		
		List<PurchaseOrderDTO> purchaseOrderList = purchaseOrders.get(userId);
		filteredOrders = purchaseOrderList.stream().filter(order -> (order.getOrderStatus().equalsIgnoreCase("Approved")
				|| order.getOrderStatus().equalsIgnoreCase("Fullfilled"))).collect(Collectors.toList());

		return filteredOrders;
	}

	public boolean updatePurchaseOrderStatus(List<Long> purchaseOrderIds) {
		System.out.println("purchase Order Id's : " + purchaseOrderIds);

		purchaseOrders.values().parallelStream().forEach(orders -> orders.stream().forEach(order -> {
			if (purchaseOrderIds.contains(order.getPurchaseOrderID())) {
				order.setOrderStatus("Approved");
				order.setApprovedDate(Calendar.getInstance());
			}
		}));

		return true;
	}

	public boolean fullfillPurchaseOrder(List<PurchaseOrderDTO> purchaseOrders) {
		System.out.println("purchase Order Id's : " + purchaseOrders);
		if(purchaseOrders.size() > 0) {
			InvoiceDTO invoice = purchaseOrders.get(0).getInvoice();
			invoice.setInvoiceId(++invoiceId);
			invoice.setInvoiceDate(Calendar.getInstance());
			invoice.setDueDate(Calendar.getInstance());
			invoice.getDueDate().add(Calendar.DAY_OF_MONTH, 14);
			invoice.setBalanceDue(invoice.getSubTotalLessDiscount() + invoice.getTotalTax() +invoice.getShippingFee());
			invoices.put(invoice.getInvoiceId(), invoice);
		}
		
		purchaseOrders.stream().forEach(purchaseOrder->{
			List<PurchaseOrderDTO> orders = PurchaseService.purchaseOrders.get(purchaseOrder.getUserID().getEmailAddress());
			orders.stream().forEach(order->{
				if(order.getPurchaseOrderID() == purchaseOrder.getPurchaseOrderID()) {
					order.setInvoice(invoices.get(invoiceId));
					order.setOrderStatus("Fullfilled");
					
				}	
			});
		});
		
		return true;
	}

	public boolean addPurchaseOrder(PurchaseOrderDTO purchaseOrder) {
		System.out.println(purchaseOrder);
		UserDTO userDTO = UserService.users.get(purchaseOrder.getUserID().getEmailAddress());
		purchaseOrder.setUserID(userDTO);
		purchaseOrder.setPurchaseOrderID(++purchaseOrderId);
		purchaseOrder.setOrderDate(Calendar.getInstance());
		purchaseOrder.setPurchaseTotalAmount((float) purchaseOrder.getProducts().stream()
				.mapToDouble(product -> product.getTotalAmount()).reduce(0, Double::sum));

		purchaseOrders.get(purchaseOrder.getUserID().getEmailAddress()).add(purchaseOrder);
		return true;
	}

	public List<ProductDTO> getAllProducts() {
		return products;
	}
	
	public List<PurchaseOrderDTO> getInvoiceDetails(int invoiceId){
		System.out.println("Invoice Id : " +  invoiceId);
		InvoiceDTO invoice = invoices.get(invoiceId);
		List<PurchaseOrderDTO> orders = new ArrayList<>();
		purchaseOrders.values().stream().forEach(order->orders.addAll(order));
		System.out.println("Size : " + orders.size());
		List<PurchaseOrderDTO> filteredOrders = orders.stream().filter(
					order-> {
						if(order.getInvoice() != null)
							return order.getInvoice().getInvoiceId() == invoiceId;
						else 
							return false;
					}
			).collect(Collectors.toList());
		System.out.println(filteredOrders);
		return filteredOrders;
	}
}
