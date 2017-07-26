package edu.webapde.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class PhotoController
 */
@WebServlet(urlPatterns={"/photos", "/tags", "/uploadphoto", "/sharephoto", "/viewphoto", "/tagphoto", "/searchphoto"})
public class PhotoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String urlPattern = request.getServletPath();
		
		switch (urlPattern) {
		case "/photos":
			getAllPhotos(request, response);
			break;
		case "/tags":
			getAllTags(request, response);
			break;
		case "/uploadphoto":
			uploadPhoto(request, response);
			break;
		case "/sharephoto":
			sharePhoto(request, response);
			break;
		case "/viewphoto":
			viewPhoto(request, response);
			break;
		case "/tagphoto":
			tagPhoto(request, response);
			break;
		case "/searchphoto":
			searchPhoto(request, response);
			break;
		default:
			break;
		}
	}
	
	private void getAllPhotos(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	private void getAllTags(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	private void uploadPhoto(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	private void sharePhoto(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	private void viewPhoto(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	private void tagPhoto(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	private void searchPhoto(HttpServletRequest request, HttpServletResponse response) {
		
	}
}
