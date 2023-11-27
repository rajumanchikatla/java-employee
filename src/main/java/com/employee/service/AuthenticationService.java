package com.employee.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthenticationService {
	
	public boolean authenticateUser(String username, String password);
	
	public boolean isUserLoggedin(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException;
	
}

