package com.cf.ferret;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.cf.dao.PurchaseDao;
import com.cf.dao.UserDao;
import com.cf.dao.impl.PurchaseDaoImpl;
import com.cf.dao.impl.UserDaoImpl;
import com.cf.dto.UserDTO;
import com.cf.entity.Product;
import com.cf.entity.PurchaseOrder;
import com.cf.entity.PurchaseProduct;
import com.cf.entity.User;
import com.cf.service.PurchaseService;
import com.cf.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonParser;

public class FerretApp {

	public static void main(String[] args) {
		FerretApp app = new FerretApp();
		app.sync();
	}
	
	public void sync() {
		PurchaseDao dao = new PurchaseDaoImpl();
		long refNum = dao.getLastSyncedRecord();
		try {
			String responseStr = getPurchaseOrdersfromFerretApp(refNum);
			System.out.println(responseStr);
			

			ObjectMapper mapper = new ObjectMapper();
			PurchaseService purservice = new PurchaseService();
			UserService userService = new UserService();
			PurchaseDao purDao = new PurchaseDaoImpl();
			UserDao userDao = new UserDaoImpl();
			
			StringReader reader = new StringReader(responseStr);
			JsonArray jArray = JsonParser.parseArray(reader);
			jArray.forEach(pOrder->{
				try {
					System.out.println(pOrder.toString());
					PurchaseOrder order = mapper.readValue(pOrder.toString(), PurchaseOrder.class);
					
					//Insert user if does not exists
					User user = userDao.getUser(order.getUserID().getEmailAddress());
					if(user == null) {
						UserDTO userDTO = new UserDTO();
						userDTO.setAddress(order.getUserID().getAddress());
						userDTO.setCompanyName(order.getUserID().getCompanyName());
						userDTO.setEmailAddress(order.getUserID().getEmailAddress());
						userDTO.setFirstName(order.getUserID().getFirstName());
						userDTO.setLastName(order.getUserID().getLastName());
						userDTO.setPassword(order.getUserID().getPassword());
						userDTO.setPhoneNumber(order.getUserID().getPhoneNumber());
						userDTO.setUserRole(order.getUserID().getUserRole());
						
						userService.registerUser(userDTO);
					}
					
					//Insert Product if does not exists
					order.getPurchaseProducts().parallelStream().forEach(purProd->{
						Product prod = purDao.getProduct(purProd.getProduct().getProductName(), purProd.getProduct().getUnitPrice());
						if(prod == null) {
							//insert into Product Table.
							prod = new Product();
							prod.setProductDescription(purProd.getProduct().getProductDescription());
							prod.setProductName(purProd.getProduct().getProductName());
							prod.setUnitPrice(purProd.getProduct().getUnitPrice());
							purDao.saveProduct(prod);
							prod = purDao.getProduct(purProd.getProduct().getProductName(), purProd.getProduct().getUnitPrice());
							purProd.setProduct(prod);
						}
						else {
							purProd.setProduct(prod);
						}
						purProd.setPurchaseProductId(0);
					});
					
					purservice.addPurchaseOrder(order);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	// HTTP GET request
	private String getPurchaseOrdersfromFerretApp(long refNum) throws Exception {
		final String USER_AGENT = "Mozilla/5.0";
		String url = "http://localhost:8080/Ferret/purchaseOrder/getPurchaeOrders?referenceNumber=" + refNum;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		
		return response.toString();

	}
}

