package com.employee.user.service;

import java.util.List;

import com.employee.model.User;

    public interface UserService {

    public User getUser(String user);
	
	public int insertUser(User user);
	
	public List<String> getRoles();
	
	public List<String> getUserRoles(String username);

	public int addRoleForUser(String username, String role);

	public List<String> getSubActions(String username);
	
}
