package com.employee.user.service;

import java.util.List;

import com.employee.dao.UserDao;
import com.employee.dao.UserDaoImpl;
import com.employee.model.User;

public class UserServiceImpl implements UserService{

		private UserDao userDao = null;
		
		public UserServiceImpl(){
			userDao = new UserDaoImpl();
	}

		@Override
		public User getUser(String user) {
			return userDao.getUser(user);
		}

		@Override
		public int insertUser(User user) {
			// TODO Auto-generated method stub
			return userDao.insertUser(user);
		}

		@Override
		public List<String> getRoles() {
			// TODO Auto-generated method stub
			return userDao.getRoles();
		}

		@Override
		public List<String> getUserRoles(String username) {
			// TODO Auto-generated method stub
			return userDao.getUserRoles(username);
		}

		@Override
		public int addRoleForUser(String username, String role) {
			// TODO Auto-generated method stub
			return userDao.addRole(username, role);
		}

		@Override
		public List<String> getSubActions(String username) {
			// TODO Auto-generated method stub
			return userDao.getUserSubActions(username);
		}
}
