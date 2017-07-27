package edu.webapde.service;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.webapde.bean.Photo;

public class PhotoService {
	public static boolean addPhoto(Photo p) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction trans = em.getTransaction();

		if (!UserService.isUserFound(p.getUsername()))
			return false;
		
		boolean b = false;
		
		try {
			trans.begin();
			em.persist(p);
			trans.commit();
			b = true;
		} catch (Exception e) {
			// TODO: handle exception
			if (trans != null)
				trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return b;
	}

	public static Photo getPhoto(int id) {
		Photo p = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			/*
			String hql = "SELECT * FROM photo p, userphoto up WHERE p.photoId = up.photoId AND p.photoId = " + id + ";";
			Query q = em.createNativeQuery(hql, Photo.class);
			p = (Photo) q.getResultList().get(0);
			*/
			p = em.find(Photo.class, id);
			trans.commit();
			System.out.println("Result from getPhotos(id): " + p.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}
		return p;
	}

	public static List<Photo> getPhotosByTag(String tag) {
		List<Photo> photoList = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			String hql = "SELECT * FROM photo p, userphoto up, tagphoto tp WHERE p.photoId = up.photoId AND tp.photoId = p.photoId AND tp.tag = '" + tag + "';";
			Query q = em.createNativeQuery(hql, Photo.class);
			photoList = q.getResultList();
			trans.commit();
			System.out.println("Result from getPhotoByTag(tag): " + photoList.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}
		return photoList;
	}

	public static List<Photo> getAllPhotos() {
		List<Photo> photoList = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			String hql = "SELECT * FROM photo p, userphoto up WHERE p.photoId = up.photoId;";
			Query q = em.createNativeQuery(hql, Photo.class);
			photoList = q.getResultList();
			trans.commit();
			System.out.println("Result from getAllPhotos(): " + photoList.toString());
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

		if (!UserService.isUserFound(username))
			return null;

		try {
			trans.begin();
			// HQL (Hibernate query language)
			// use createNativeQuery if SQL
			String hql = "SELECT * FROM photo p, userphoto up WHERE p.photoId = up.photoId and up.username = '"
					+ username + "';";
			Query q = em.createNativeQuery(hql, Photo.class);
			photoList = q.getResultList();
			trans.commit();
			System.out.println("Result from getAllPhotos(username): " + photoList.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}

		return photoList;
	}
	
	public static List<Photo> getAllPublicPhotos() {
		List<Photo> photoList = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			String hql = "SELECT * FROM photo p, userphoto up WHERE p.photoId = up.photoId AND privacy = 'public';";
			Query q = em.createNativeQuery(hql, Photo.class);
			photoList = q.getResultList();
			trans.commit();
			System.out.println("Result from getAllPublicPhotos(): " + photoList.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}

		return photoList;
	}

	public static boolean addTagsToPhoto(int photoId, String tag) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction trans = em.getTransaction();
		boolean b = false;
		try {
			trans.begin();
			Photo p = em.find(Photo.class, photoId);
			p.addTag(tag);
			trans.commit();
			b = true;
		} catch (Exception e) {
			// TODO: handle exception
			if (trans != null)
				trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return b;
	}

	public static boolean addAllowedUserToPhoto(int photoId, String allowedUser) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction trans = em.getTransaction();

		boolean b = false;
		
		try {
			trans.begin();
			Photo p = em.find(Photo.class, photoId);
			p.addAllowedUser(allowedUser);
			trans.commit();
			b = true;
		} catch (Exception e) {
			// TODO: handle exception
			if (trans != null)
				trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return b;
	}

	public static boolean setPhotoPrivacy(int photoId, String privacy) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction trans = em.getTransaction();

		boolean b = false;
		
		try {
			trans.begin();
			Photo p = em.find(Photo.class, photoId);
			p.setPrivacy(privacy);
			trans.commit();
			b = true;
		} catch (Exception e) {
			// TODO: handle exception
			if (trans != null)
				trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return b;
	}

	public static void main(String[] args) {
		/*
		 * Photo p = new Photo("helloKitty1", "Yoho", "IAMFILEPHAT", "public");
		 * if(addPhoto(p)) System.out.println(p.toString()); else
		 * System.out.println("ERROR");
		 */
		
		//Photo photo = new Photo("mae", "StarDew", "A picture of the game.", "img/sv.png", "public", "Thu Jul 27 2017 18:10:09 GMT+0800 (Malay Peninsula Standard Time)");
		//Photo photo2 = new Photo("mae", "Gentleman", "A picture of a POGI.", "img/j.jpg", "public", "Thu Jul 27 2017 18:10:09 GMT+0800 (Malay Peninsula Standard Time)");
		
		addTagsToPhoto(3, "Cute");
		System.out.println(getPhoto(1).toString());
		List<Photo> list = getAllPublicPhotos();
		for (Photo p : list) {
			System.out.println(p.toString());
		}
		list = getAllPhotos();
		for (Photo p : list) {
			System.out.println(p.toString());
		}
		list = getAllPhotos("mae");
		for (Photo p : list) {
			System.out.println(p.toString());
		}
		list = getPhotosByTag("Hello1");
		for (Photo p : list) {
			System.out.println(p.toString());
		}
		
		
		
		// addTagsToPhoto(1, "Hello1");
		// System.out.println(getPhoto(1));
		
	}
}
