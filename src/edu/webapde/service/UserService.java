package edu.webapde.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.webapde.bean.User;

public class UserService {
	public static boolean addUser(User u) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			em.persist(u);
			trans.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			if(trans != null)
				trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return false;
	}
	
	public static User getUser(String username, String password) {
		User u = null;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			u = em.find(User.class, username);
			trans.commit();
			if(u.getPassword().equals(password))
				return u;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return null;
	}
}
