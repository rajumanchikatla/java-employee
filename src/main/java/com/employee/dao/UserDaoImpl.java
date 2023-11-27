package com.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.employee.constants.SQLConstants;
import com.employee.exception.DuplicateYUserException;
import com.employee.exception.DuplicatesException;
import com.employee.exception.UserNotFoundException;
import com.employee.model.User;
import com.employee.util.CommonUtil;
import com.employee.util.DbUtil;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class UserDaoImpl implements UserDao{
	
	private DbUtil dbUtil=null;
	
	public UserDaoImpl() {
		dbUtil=new DbUtil ();
		}

	@Override
	public User getUser(String username) {
		Connection conn = null;
		User user = new User();
		
		try {
			conn = dbUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQLConstants.GET_USER);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));
			}
		}catch(Exception ex) {
			
		}finally {
			if(conn != null)
				dbUtil.closeConnection(conn);
		}
		
		if(user.getUsername() == null)
			throw new UserNotFoundException();
		
		return user;
	}
	
	@Override
	public int insertUser(User user) {
		Connection conn = null;
		int count = -1;
		
		try {
			conn = dbUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQLConstants.INSERT_USER);
			ps.setString(1, user.getUsername());
			ps.setString(2, CommonUtil.encodePassword(user.getPassword()));
			count = ps.executeUpdate();
		}catch(MySQLIntegrityConstraintViolationException ex) {
			  throw new DuplicatesException();
		}catch(Exception ex) {
			  throw new RuntimeException();
		}finally {
			if(conn != null)
				dbUtil.closeConnection(conn);
		}
		return count;

	}

	@Override
	public List<String> getRoles() {
		// TODO Auto-generated method stub
		Connection conn = null;

		List<String> roles = new ArrayList<String>();
		try {
			conn = dbUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQLConstants.GET_ROLES);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				roles.add(rs.getString(1));
			}
		}catch(Exception ex) {
			
		}finally {
			if(conn != null)
				dbUtil.closeConnection(conn);
		}
		
		return roles;
	}

	@Override
	public List<String> getUserRoles(String username) {
		// TODO Auto-generated method stub
		Connection conn = null;

		List<String> roles = new ArrayList<String>();
		try {
			conn = dbUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQLConstants.GET_ROLES_FOR_USER);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				roles.add(rs.getString(1));
			}
		}catch(Exception ex) {
			
		}finally {
			if(conn != null)
				dbUtil.closeConnection(conn);
		}
		
		return roles;

	}

	@Override
	public int addRole(String username, String role) {
		// TODO Auto-generated method stub

		Connection conn = null;
		int count = -1;
		
		try {
			conn = dbUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQLConstants.INSERT_USER_ROLE);
			ps.setString(1, username);
			ps.setString(2, role);
			count = ps.executeUpdate();
		}catch(MySQLIntegrityConstraintViolationException ex) {
			  throw new DuplicateYUserException();
		}catch(Exception ex) {
			  throw new RuntimeException();
		}finally {
			if(conn != null)
				dbUtil.closeConnection(conn);
		}
		return count;

	}

	
	public List<String> getUserSubActions(String username) {
		Connection conn = null;
		List<String> roles = new ArrayList<String>();
		try {
			conn = dbUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQLConstants.GET_USER_SUBACTION);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				roles.add(rs.getString(1));
				
			}
		}catch(Exception ex) {
			
		}finally {
			if(conn != null)
				dbUtil.closeConnection(conn);
		}
		
		return roles;
	}
}
