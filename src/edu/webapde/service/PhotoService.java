package edu.webapde.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import edu.webapde.bean.Photo;

public class PhotoService {
	public static boolean addPhoto(Photo p) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction trans = em.getTransaction();
		
		if(!UserService.isUserFound(p.getUsername()))
			return false;

		try {
			trans.begin();
			em.persist(p);
			trans.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			if (trans != null)
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

	public static List<Photo> getAllPhotos() {
		List<Photo> photoList = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			// HQL (Hibernate query language)
			// use createNativeQuery if SQL
			TypedQuery<Photo> q = em.createQuery("FROM photo", Photo.class);
			photoList = q.getResultList();
			trans.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}

		return photoList;
	}
	
	public static List<Photo> getAllPhotos(String username) {
		List<Photo> photoList = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction trans = em.getTransaction();
		
		if(!UserService.isUserFound(username))
			return null;
		
		try {
			trans.begin();
			// HQL (Hibernate query language)
			// use createNativeQuery if SQL
			String hql = "SELECT username, photo.photoId, description, filepath, privacy, title FROM userphoto, photo WHERE userphoto.photoId = photo.photoId and userphoto.username = '"+ username +"';";
			Query q = em.createNativeQuery(hql, Photo.class);
			photoList = q.getResultList();
			trans.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}

		return photoList;
	}

	public static boolean addTagsToPhoto(int photoId, ArrayList<String> tags) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			// em.persist(p);
			trans.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			if (trans != null)
				trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return false;
	}

	public static boolean addAllowedUsersToPhoto(int photoId, ArrayList<String> allowedUsers) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			// em.persist(p);
			trans.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			if (trans != null)
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
			if (trans != null)
				trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return false;
	}
	
	public static void main(String[] args) {
		/*
		Photo p = new Photo("helloKitty1", "Yoho", "IAMFILEPHAT", "public");
		if(addPhoto(p))
			System.out.println(p.toString());
		else
			System.out.println("ERROR");
			*/
		List<Photo> list = getAllPhotos("helloKitty");
		System.out.println(list);
		//for(Photo p : list) {
		//	System.out.println(p.toString());
		//}
	}
}
