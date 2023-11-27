package com.employee.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.exception.DuplicatesException;
import com.employee.model.RegisterUser;
import com.employee.service.AuthenticationService;
import com.employee.service.AuthenticationServiceImpl;
import com.employee.user.service.UserServiceImpl;
import com.employee.util.CommonUtil;

/**
 * Servlet implementation class UserController
 */

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private AuthenticationService authService = null;

	private UserServiceImpl userService=null;

    public UserController() {
        super();
        // TODO Auto-generated constructor stub
        authService =new AuthenticationServiceImpl();
        userService = new UserServiceImpl();
}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String subAction=request.getParameter("subAction");
		
		if(!authService.isUserLoggedin(request, response)|| !CommonUtil.verifyAction(request, response)) {
			
			return;
	}
		
		
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		request.setAttribute("roles", userService.getRoles());
		String SUCCESS_VIEW=null;
		if("ADD_USER_SCREEN".equals(subAction)){
			 SUCCESS_VIEW="users/users.jsp";
	        }else if("ADD_USER".equals(subAction)) {
				 {
			       SUCCESS_VIEW="users/users.jsp";
			RegisterUser user=new RegisterUser();
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setConfirmPassword(request.getParameter("confirmpassword"));
			String role = request.getParameter("role");
			
			if(!user.isSamePassword()){
			errors.add("BE-403-->password and confirm password not matching" );
			}
			  if(!user.isValidPassword()){
				  
				errors.add("BE-404-->password not met the required critiria"
						+ "<br>1. P assword should be conatain minimum 8characters "
						+"<br> 2. Password should have atleat one captial alphabet and reamining small letters"
						+"<br> 3. Password should contain one special character ");
			}else {
			
				try {
					int count = userService.insertUser(user);
							if(count > 0)
							{
								userService.addRoleForUser(user.getUsername(), role);
								response.sendRedirect(request.getContextPath()+"/employees?subAction=ListEmployees");
								return;
							}
				}catch(DuplicatesException ex){
					errors.add("BE-405 alredy this username present");
				}catch(Exception ex) {
					errors.add("BE-406 some error occured contact administrator");
				}			
			}
			  
			 SUCCESS_VIEW="users/users.jsp";
			  
				 }
	        }
		RequestDispatcher view = request.getRequestDispatcher(SUCCESS_VIEW);
	view.forward(request, response);
   
}
}
