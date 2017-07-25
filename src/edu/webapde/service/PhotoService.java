package edu.webapde.service;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.webapde.bean.Photo;

public class PhotoService {
	public static boolean addPhoto(Photo p) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			em.persist(p);
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
	
	public static Photo getPhoto(int id) {
		Photo p = null;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			p = em.find(Photo.class, id);
			trans.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return p;
	}
	
	public static boolean addTagsToPhoto(int photoId, ArrayList<String> tags) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			//em.persist(p);
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
	
	public static boolean addAllowedUsersToPhoto(int photoId, ArrayList<Integer> allowedUsers) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			//em.persist(p);
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
	
	public static boolean setPhotoPrivacy(int photoId, String privacy) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			Photo p = em.find(Photo.class, photoId);
			p.setPrivacy(privacy);
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
}
