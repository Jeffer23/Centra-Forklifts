package com.cf.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cf.dao.UserDao;
import com.cf.entity.User;
import com.cf.hibernate.HibernateUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public User getUser(String userId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			User user = (User) session.get(User.class, userId);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean saveUser(User user) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			User userDB = (User) session.get(User.class, user.getEmailAddress());
			if(userDB == null) {
				Transaction transaction = session.beginTransaction();
				session.save(user);
				transaction.commit();
				return true;
			} else {
				System.out.println("user Id already regitered. -->" + user.getEmailAddress());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<User> getAllUsers() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<User> users = session.createCriteria(User.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static void main(String[] args) {
		UserDao dao = new UserDaoImpl();
		User user = new User();
		user.setAddress("Chennai");
		user.setCompanyName("Infy");
		user.setEmailAddress("pemela@gmail.com");
		user.setFirstName("Pemela");
		user.setLastName("Jefferson");
		user.setPassword("12345");
		user.setPhoneNumber(988442505);
		user.setUserRole("dealer");
		
		System.out.println(dao.getUser("t.isaacjefferson@gmail.com"));
	}
}
