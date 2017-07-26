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
		
		// if the username already exist in the DB
		if(isUserFound(u.getUsername())) {
			System.out.println("BAKIT KA NANDITO");
			return false;
		}
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
			
			if(u == null)
				return u;
			
			// check if the password is equal
			if(u.isPasswordEqual(password))
				return u;
			else
				System.err.println("ERROR: Password does not match!");
				// System.out.println("ERROR: Password does not match!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return null;
	}
	
	public static boolean isUserFound(String username) {
		User u = null;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			u = em.find(User.class, username);
			trans.commit();
			
			// if the user is found
			if(u != null)
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		User u = new User("helloKitty", "Hay");
		if(addUser(u))
			System.out.println(u.toString());
		else
			System.out.println("ERROR");
		
		//System.out.println(getUser("helloKitty", "Hay"));
	}
}
