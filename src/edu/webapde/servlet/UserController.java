package edu.webapde.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.webapde.bean.Photo;
import edu.webapde.bean.User;
import edu.webapde.service.PhotoService;
import edu.webapde.service.UserService;

/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns={"/mainpage", "/login", "/register", "/logout"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
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
		case "/mainpage":
			goToMainpage(request, response);
			break;
		case "/login":
			loginUser(request, response);
			break;
		case "/register":
			registerUser(request, response);
			break;
		case "/logout":
			logoutUser(request, response);
			break;
		default:
			break;
		}
	}
	
	private void goToMainpage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// find cookie for user
		
		// if not found go public
		
		System.out.println("AM I HERE???????????");
		List<Photo> publicPhotoList = PhotoService.getAllPublicPhotos();
		request.setAttribute("pList", publicPhotoList);
		request.setAttribute("pListSize", publicPhotoList.size());
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}
	
	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User u = UserService.getUser(username, password);
		
		// if the user is found
		if(u != null) {
			// set session for username
			HttpSession session = request.getSession();
			session.setAttribute("sUsername", username);
			
			// set attributes to request
			request.setAttribute("username", username);
			
			request.setAttribute("description", u.getDescription());
			
			List<Photo> photoList = PhotoService.getAllPhotos(username);
			request.setAttribute("photoList", photoList);
			
			// create cookie
			Cookie usernameCookie = new Cookie("username", username);
			usernameCookie.setMaxAge(60*60*24*365*3);
			response.addCookie(usernameCookie);
			
			// forward to success page or page if success
			RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
			rd.forward(request, response);
		}
		
		// if the user is not found or the password is wrong
		else {
			// go to failed page or same page
			response.sendRedirect("index.html");
		}
	}
	
	private void registerUser(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String description = request.getParameter("description");
		
		User u = new User();
		u.setUsername(username);
		u.setPassword(password.toCharArray());
		u.setDescription(description);
		
		boolean flag = UserService.addUser(u);
		
		// if the user registered successfully
		if(flag) {
			
		}
		
		// if the username exists or registered failed
		else {
			
		}
	}
	
	private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// kill cookie username
		Cookie[] cookies = request.getCookies();
		
		for(Cookie c : cookies) {
			// find username cookie and kill it
			if(c.getName().equals("username")){
				// kill it
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
		
		// redirect to non-logged in page
		response.sendRedirect("index.html");
	}
}
