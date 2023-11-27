package com.employee.dao;

import java.util.List;

import com.employee.model.User;

    public interface UserDao{
	
	public User getUser(String user);
	
	public int insertUser(User user);
	
    public List<String> getRoles();
	
	public List<String> getUserRoles(String username);

	public int addRole(String username, String role);

	public List<String> getUserSubActions(String username);


}
