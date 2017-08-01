package edu.webapde.service;

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
			 * String hql =
			 * "SELECT * FROM photo p, userphoto up WHERE p.photoId = up.photoId AND p.photoId = "
			 * + id + ";"; Query q = em.createNativeQuery(hql, Photo.class); p =
			 * (Photo) q.getResultList().get(0);
			 */
			p = em.find(Photo.class, id);
			trans.commit();
			System.out.println("Result from getPhotos(id): " + p);
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
			String hql = "SELECT * FROM photo p, tagphoto tp WHERE tp.photoId = p.photoId AND tp.tag = '" + tag
					+ "' ORDER BY p.photoId DESC;";
			Query q = em.createNativeQuery(hql, Photo.class);
			photoList = q.getResultList();
			trans.commit();
			System.out.println("Result from getPhotoByTag(tag): " + photoList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}
		return photoList;
	}
	
	public static List<Photo> getPublicPhotosByTag(String tag) {
		List<Photo> photoList = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			String hql = "SELECT * FROM photo p, tagphoto tp WHERE tp.photoId = p.photoId AND tp.tag = '" + tag
					+ "' AND privacy = 'public' ORDER BY p.photoId DESC;";
			Query q = em.createNativeQuery(hql, Photo.class);
			photoList = q.getResultList();
			trans.commit();
			System.out.println("Result from getPublicPhotoByTag(tag): " + photoList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}
		return photoList;
	}
	
	public static List<Photo> getSharedPhotosByTag(String tag, String username) {
		List<Photo> photoList = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			String hql = "SELECT * FROM photo p, tagphoto tp, allowedusers a WHERE p.photoId = tp.photoId AND p.photoId = a.photoId AND tp.tag = '" + tag
					+ "' AND a.username = '" + username + "' AND privacy = 'private' ORDER BY p.photoId DESC;";
			Query q = em.createNativeQuery(hql, Photo.class);
			photoList = q.getResultList();
			trans.commit();
			System.out.println("Result from getSharedPhotoByTag(tag): " + photoList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}
		return photoList;
	}
	
	public static List<Photo> getMyPhotosByTag(String tag, String username) {
		List<Photo> photoList = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			String hql = "SELECT * FROM photo p, tagphoto tp WHERE tp.photoId = p.photoId AND tp.tag = '" + tag
					+ "' AND p.username = '" + username + "' ORDER BY p.photoId DESC;";
			Query q = em.createNativeQuery(hql, Photo.class);
			photoList = q.getResultList();
			trans.commit();
			System.out.println("Result from getMyPhotoByTag(tag): " + photoList);
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
			String hql = "SELECT * FROM photo p ORDER BY p.photoId DESC;";
			Query q = em.createNativeQuery(hql, Photo.class);
			photoList = q.getResultList();
			trans.commit();
			System.out.println("Result from getAllPhotos(): " + photoList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}

		return photoList;
	}

	public static List<Photo> getAllSharedPhotos(String username) {
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
			String hql = "SELECT * FROM photo p, allowedUsers a WHERE p.photoId = a.photoId AND a.username = '" + username + "' AND p.privacy = 'private' ORDER BY p.photoId DESC;";
			Query q = em.createNativeQuery(hql, Photo.class);
			photoList = q.getResultList();
			trans.commit();
			System.out.println("Result from getAllPhotos(username): " + photoList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}

		return photoList;
	}
	
	public static List<Photo> getAllSharedPhotos(String username, String otherUsername) {
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
			String hql = "SELECT * FROM photo p, allowedUsers a WHERE p.photoId = a.photoId AND p.username = '" + otherUsername + "' AND (a.username = '" + username + "' OR privacy = 'public') ORDER BY p.photoId DESC;";
			Query q = em.createNativeQuery(hql, Photo.class);
			photoList = q.getResultList();
			trans.commit();
			System.out.println("Result from getAllPhotos(username, otherUsername): " + photoList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}

		return photoList;
	}
	
	public static List<Photo> getAllMyPhotos(String username) {
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
			String hql = "SELECT * FROM photo p WHERE p.username = '" + username + "' ORDER BY p.photoId DESC;";
			Query q = em.createNativeQuery(hql, Photo.class);
			photoList = q.getResultList();
			trans.commit();
			System.out.println("Result from getAllPhotos(username): " + photoList);
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
			String hql = "SELECT * FROM photo p WHERE privacy = 'public' ORDER BY p.photoId DESC;";
			Query q = em.createNativeQuery(hql, Photo.class);
			photoList = q.getResultList();
			trans.commit();
			System.out.println("Result from getAllPublicPhotos(): " + photoList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}

		return photoList;
	}
	
	public static List<Photo> getAllPublicPhotos(String username) {
		List<Photo> photoList = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			String hql = "SELECT * FROM photo p WHERE privacy = 'public' AND p.username = '" + username + "' ORDER BY p.photoId DESC;";
			Query q = em.createNativeQuery(hql, Photo.class);
			photoList = q.getResultList();
			trans.commit();
			System.out.println("Result from getAllPublicPhotos(): " + photoList);
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

	public static boolean addAllowedUserToPhoto(int photoId, String username) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction trans = em.getTransaction();

		boolean b = false;

		try {
			trans.begin();
			Photo p = em.find(Photo.class, photoId);
			p.addAllowedUser(username);
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
		 * if(addPhoto(p)) System.out.println(p); else
		 * System.out.println("ERROR");
		 */

		Photo photo = new Photo("krizia", "Star", "A picture of the game.", "img/sv.png", "private", "Thu Jul 27 2017 18:10:09 GMT+0800 (Malay Peninsula Standard Time)");
		Photo photo2 = new Photo("krizia", "Pogiman", "A picture of a POGI.", "img/j.jpg", "private", "Thu Jul 27 2017 18:10:09 GMT+0800 (Malay Peninsula Standard Time)");
		
		// addTagsToPhoto(3, "Cute");
		System.out.println(getPhoto(1));
		List<Photo> list = getAllPublicPhotos();
		for (Photo p : list) {
			System.out.println(p);
			addTagsToPhoto(p.getPhotoId(), "LOVE");
		}
		list = getAllPhotos();
		for (Photo p : list) {
			System.out.println(p);
		}
		list = getAllMyPhotos("mae");
		for (Photo p : list) {
			System.out.println(p);
		}
		list = getPhotosByTag("Cute");
		for (Photo p : list) {
			System.out.println(p);
		}
		/*
		System.out.println(addPhoto(photo2));
		System.out.println(addPhoto(photo));
		System.out.println(addAllowedUserToPhoto(9, "mae"));
		System.out.println(addAllowedUserToPhoto(10, "mae"));
		*/
		//System.out.println(addTagsToPhoto(9, "Game"));
		//System.out.println(addTagsToPhoto(10, "POGI"));
		
		// addTagsToPhoto(1, "Hello1");
		// System.out.println(getPhoto(1));

	}
}
