package com.employee.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.dao.UserDao;
import com.employee.dao.UserDaoImpl;
import com.employee.model.User;
import com.employee.util.CommonUtil;

public class AuthenticationServiceImpl implements AuthenticationService{

	private UserDao userDao;
	
	public AuthenticationServiceImpl(){
		userDao = new UserDaoImpl();
	}
	
	@Override
	public boolean authenticateUser(String username, String password) {
		
		User user = userDao.getUser(username);
		
		if(username.equals(user.getUsername()) && CommonUtil.matches(password, user.getPassword()))
			
			return true;
		return false;
	}
	
	

	@Override
	public boolean isUserLoggedin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("isUserLoggedIn") != null 
				&& ((Boolean) request.getSession().getAttribute("isUserLoggedIn") == true)) {
			return true;
		}else {
			List<String> errors = new ArrayList<String>();
			errors.add("[BE-402] - Invalid Session. Please Login...!");
			request.setAttribute("errors", errors);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
			return false;
		}
	}

}

