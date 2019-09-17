package com.ferret.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.cf.hibernate.HibernateUtil;
import com.ferret.dao.PurchaseDao;
import com.ferret.entity.PurchaseOrder;

public class PurchaseDaoImpl implements PurchaseDao{

	@Override
	public List<PurchaseOrder> getPurchaseProductsAfter(long referenceNumber) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<PurchaseOrder> purchaseOrders = session.createCriteria(PurchaseOrder.class)
					.add(Restrictions.gt("referenceNumber", referenceNumber))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			return purchaseOrders;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
