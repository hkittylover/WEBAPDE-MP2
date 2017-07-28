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
		
		u.setUsername(u.getUsername().toLowerCase());
		
		// if the username already exist in the DB
		if(isUserFound(u.getUsername())) {
			System.out.println("From addUser(u): username already exist.");
			return false;
		}
		
		boolean b = false;
		
		try {
			trans.begin();
			em.persist(u);
			trans.commit();
			b = true;
		} catch (Exception e) {
			// TODO: handle exception
			if(trans != null)
				trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return b;
	}
	
	public static User getUser(String username, String password) {
		return getUser(username, password.toCharArray());
	}
	
	public static User getUser(String username, char[] password) {
		User u = null;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			u = em.find(User.class, username.toLowerCase());
			trans.commit();
			
			if(u != null) {
				// check if the password is equal
				if(!u.isPasswordEqual(password)) {
					u = null;
					System.out.println("ERROR: Password does not match!");
				}
				else{
					System.out.println("Found user!");
				}
			}
			else {
				System.out.println("User not found...");
			}
				// System.out.println("ERROR: Password does not match!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return u;
	}
	
	public static boolean isUserFound(String username) {
		User u = null;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		boolean b = false;
		
		try {
			trans.begin();
			u = em.find(User.class, username.toLowerCase());
			trans.commit();
			
			// if the user is found
			if(u != null)
				b = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return b;
	}
	
	public static void main(String[] args) {
		User u = new User("Mae", "allyzaheh");
		if(addUser(u))
			System.out.println(u.toString());
		else
			System.out.println("ERROR");
		
		System.out.println(getUser("Mae", "allyzahehe"));
	}
}
