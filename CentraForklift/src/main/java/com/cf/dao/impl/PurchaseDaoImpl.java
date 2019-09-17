package com.cf.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.cf.dao.PurchaseDao;
import com.cf.entity.Invoice;
import com.cf.entity.Product;
import com.cf.entity.PurchaseOrder;
import com.cf.entity.User;
import com.cf.hibernate.HibernateUtil;

public class PurchaseDaoImpl implements PurchaseDao {

	@Override
	public List<PurchaseOrder> getViewPurchaseOrderDetails(String userId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			/*
			 * Query query = session.
			 * createQuery("From PurchaseOrder po where po.userID.emailAddress=:userId and po.orderStatus='Approved'or po.orderStatus='Unapproved'"
			 * ); query.setParameter("userId", userId); List<PurchaseOrder> purchaseOrders =
			 * query.getResultList();
			 */

			List<PurchaseOrder> purchaseOrders = session.createCriteria(PurchaseOrder.class)
					.add(Restrictions.and(Restrictions.eq("userID.emailAddress", userId),
							Restrictions.or(Restrictions.eq("orderStatus", "Approved"),
									Restrictions.eq("orderStatus", "Unapproved"))))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

			return purchaseOrders;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<PurchaseOrder> getAllViewPurchaseOrderDetails() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<PurchaseOrder> purchaseOrders = session.createCriteria(PurchaseOrder.class)
					.add(Restrictions.or(Restrictions.eq("orderStatus", "Approved"),
							Restrictions.eq("orderStatus", "Unapproved")))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			return purchaseOrders;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PurchaseOrder> getFullfillmentDetails(String userId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<PurchaseOrder> purchaseOrders = session.createCriteria(PurchaseOrder.class)
					.add(Restrictions.and(Restrictions.eq("userID.emailAddress", userId),
							Restrictions.or(Restrictions.eq("orderStatus", "Approved"),
									Restrictions.eq("orderStatus", "Fullfilled"))))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			return purchaseOrders;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PurchaseOrder> getAllFullfillmentDetails() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<PurchaseOrder> purchaseOrders = session.createCriteria(PurchaseOrder.class)
					.add(Restrictions.or(Restrictions.eq("orderStatus", "Approved"),
							Restrictions.eq("orderStatus", "Fullfilled")))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			return purchaseOrders;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PurchaseOrder getPurchaseOrder(long PurchaseOrderId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			PurchaseOrder purchaseOrder = session.get(PurchaseOrder.class, PurchaseOrderId);
			return purchaseOrder;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updatePurchaseOrder(PurchaseOrder purchaseOrder) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(purchaseOrder);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		PurchaseDao dao = new PurchaseDaoImpl();
		System.out.println(dao.getProduct("FB10HCA - Mitsubishi 4 wheel", 2000));
	}

	@Override
	public void addPurchaseOrder(PurchaseOrder purchaseOrder) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.save(purchaseOrder);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Product> getAllproducts() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Product> products = session.createCriteria(Product.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PurchaseOrder> getInvoiceDetails(int invoiceId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<PurchaseOrder> purchaseOrders = session.createCriteria(PurchaseOrder.class)
					.add(Restrictions.eq("invoice.invoiceId", invoiceId))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			return purchaseOrders;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long getLastSyncedRecord() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				PurchaseOrder purchaseOrders = (PurchaseOrder) session.createCriteria(PurchaseOrder.class)
				    .addOrder(Order.desc("referenceNumber"))
				    .setMaxResults(1)
				    .uniqueResult();
				
				return purchaseOrders.getReferenceNumber();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		return 0;
	}

	@Override
	public Product getProduct(String productName, float unitPrice) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Product product = (Product) session.createCriteria(Product.class)
					.add(Restrictions.and(Restrictions.eq("productName", productName),Restrictions.eq("unitPrice", unitPrice)))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			return product;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveProduct(Product product) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.save(product);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
