package com.employee.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.exception.InvalidUserCredentials;
import com.employee.exception.UserNotFoundException;
import com.employee.service.AuthenticationService;
import com.employee.service.AuthenticationServiceImpl;
import com.employee.user.service.UserService;
import com.employee.user.service.UserServiceImpl;
/**
 * Servlet implementation class AuthenticationController
 */
public class AuthenticationController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
      
	private AuthenticationService authService;
	
	private UserService userService;
    
    public AuthenticationController() {
        super();
        authService = new AuthenticationServiceImpl();
        userService= new UserServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String subAction = request.getParameter("subAction");
		List<String> errors = new ArrayList<String>();
		List<String> successMessages = new ArrayList<String>();
		
		request.setAttribute("errors", errors);
		
		String SUCCESS_VIEW = "";
		if("LOGIN".equals(subAction)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			try {
			if(authService.authenticateUser(username, password)) {
				request.getSession().setAttribute("isUserLoggedIn", true);
				request.getSession().setAttribute("username",username);
				request.getSession().setAttribute("userroles",userService.getUserRoles(username));
				request.getSession().setAttribute("subActions",userService.getSubActions(username));
				response.sendRedirect(request.getContextPath()+"/employees?subAction=ListEmployees");
			
			}else{
				throw new InvalidUserCredentials();
			}
			return;
			}catch(UserNotFoundException | InvalidUserCredentials ex){
				SUCCESS_VIEW = "index.jsp";
				errors.add("BE[401] - Invalid Username / Password");
			}
		}else if("LOGOUT".equals(subAction)) {
			request.getSession().setAttribute("isUserLoggedIn", false);
		}
		
		RequestDispatcher view = request.getRequestDispatcher(SUCCESS_VIEW);
		view.forward(request, response);
	}

}
