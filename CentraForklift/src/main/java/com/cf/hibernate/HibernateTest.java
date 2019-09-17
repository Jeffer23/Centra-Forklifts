package com.cf.hibernate;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cf.entity.Invoice;
import com.cf.entity.Product;
import com.cf.entity.PurchaseOrder;
import com.cf.entity.PurchaseProduct;
import com.cf.entity.User;

public class HibernateTest {

	public static void main(String[] args) {
		Product product = null;
		User user = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			product = (Product) session.get(Product.class, 6);
			user = (User) session.get(User.class, "pemelajefferson@gmail.com");
			
			  PurchaseOrder purchaseOrder = session.get(PurchaseOrder.class, 20013l);
			  System.out.println(product); 
			  System.out.println(user);
			  System.out.println(purchaseOrder);
			 
			
			/*
			  PurchaseOrder purchaseOrder = new PurchaseOrder();
			  purchaseOrder.setBillingAddress("Chennai"); 
			  purchaseOrder.setOrderDate(Calendar.getInstance());
			  purchaseOrder.setApprovedDate(null);
			  purchaseOrder.setOrderStatus("Unapproved");
			  purchaseOrder.setPurchaseTotalAmount(3000);
			  purchaseOrder.setShippingAddress("Chennai"); 
			  purchaseOrder.setUserID(user);
			  
			  PurchaseProduct purchaseProduct = new PurchaseProduct();
			  purchaseProduct.setProduct(product);
			  purchaseProduct.setQuantity(1); 
			  purchaseProduct.setTotalAmount(3000);
			  
			  purchaseOrder.getPurchaseProducts().add(purchaseProduct);
			  
			  Invoice invoice = new Invoice();
			  invoice.setBalanceDue(3000);
			  purchaseOrder.setInvoice(invoice);
			  purchaseOrder.setInvoice(invoice);
			  
			  // save the student objects 
			  session.save(purchaseOrder); 
			 */
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			/*
			 * if (transaction != null) { transaction.rollback(); }
			 */
			e.printStackTrace();
		}

		//user.getPurchaseOrders().stream().forEach(System.out::println);
		/*
		 * try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		 * List < PurchaseOrder > students = session.createQuery("from PurchaseOrder",
		 * PurchaseOrder.class).list(); students.forEach(s ->
		 * System.out.println(s.getPurchaseOrderID())); } catch (Exception e) {
		 * 
		 * if (transaction != null) { transaction.rollback(); }
		 * 
		 * e.printStackTrace(); }
		 */
	}

}
