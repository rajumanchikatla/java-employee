package com.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.employee.constants.SQLConstants;
import com.employee.exception.DuplicatesException;
import com.employee.model.Employee;
import com.employee.util.DbUtil;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


public class EmployeeDaoImpl implements EmployeeDao {

 private DbUtil dbUtil;
	 
	 public EmployeeDaoImpl(){
		 dbUtil = new DbUtil();
	 }
	 public List<Employee> getEmployees(int rowsPerPage, int offSet){
		 Connection conn = null;
			List<Employee> employeeList = new ArrayList<Employee>();
			try {
				conn = dbUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQLConstants.LIST_EMPLOYEE);
				ps.setInt(1,rowsPerPage); 
				ps.setInt(2,offSet);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					Employee e = new Employee();
					e.setId(rs.getLong(1));
					e.setName(rs.getString(2));
					e.setDept_name(rs.getString(3));
					e.setSalary(rs.getLong(4));
					e.setAddress(rs.getString(5));
					employeeList.add(e);
				}
			}catch(SQLException ex) {
				System.out.println("Failed to fetch logs");
			}finally {
				if(conn != null)
					dbUtil.closeConnection(conn);
			}
			return employeeList;
		 
		 
	 }
	 //id ,f_name,Dept_name,salary,address
	@Override
	public int addEmployee(Employee e) {
		// TODO Auto-generated method stub
		int count = -1;
		Connection conn = null;
		try {
			conn = dbUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQLConstants.ADD_QUERY);
			ps.setLong(1, e.getId());
			ps.setString(2, e.getName());
			ps.setString(3, e.getDept_name());
			ps.setLong(4, e.getSalary());
			ps.setString(5, e.getAddress());
			count = ps.executeUpdate();
		}catch(MySQLIntegrityConstraintViolationException ex) {
			throw new DuplicatesException();	
		}catch(SQLException ex) {
			System.out.println("Failed to fetch logs");
		}finally {
			if(conn != null)
				dbUtil.closeConnection(conn);
		}
		
		return count;
	}
	public Employee getEmployee(Long id) {
		Connection conn = null;
		Employee employee = null;
		try {
			conn = dbUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQLConstants.GET_EMPLOYEE);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				employee = new Employee();
				employee.setId(rs.getLong(1));
				employee.setName(rs.getString(2));
				employee.setDept_name(rs.getString(3));
				employee.setSalary(rs.getLong(4));
				employee.setAddress(rs.getString(5));
			}
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}finally {
			if(conn != null)
				dbUtil.closeConnection(conn);
		}
		return employee;
	}
	public int updateEmployee(Employee e) {
		int count = -1;
		Connection conn = null;
		try {
			conn = dbUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQLConstants.UPDATE_EMPLOYEE);
			ps.setString(1, e.getName());
			ps.setString(2, e.getDept_name());
			ps.setLong(3, e.getSalary());
			ps.setString(4, e.getAddress());
			ps.setLong(5, e.getId());
			count = ps.executeUpdate();
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failed to fetch logs");
		}finally {
			if(conn != null)
				dbUtil.closeConnection(conn);
		}
		
		return count;
	}
	
	public int deleteEmployee(Long id) {
		Connection conn = null;
		try {
			conn = dbUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQLConstants.DELETE_EMPLOYEE);
			ps.setLong(1, id);
			return ps.executeUpdate();
		}catch(Exception ex) {
			System.out.println("Failed to Fetch Logs");
		}finally {
			if(conn !=null)
				dbUtil.closeConnection(conn);
		}
		
		return -1;
	}

		public List<Employee> searchEmployees(Employee Employee) {
			Connection conn = null;
			List<Employee> EmployeeList = new ArrayList<Employee>();
			String query = SQLConstants.SEARCH_EMPLOYEE;
		//select id ,f_name,dept_name,salary,address from listemployees where id!='0'";

			if(Employee.getId() != 0) 
				query += " and id like '"+ Employee.getId()+"%'";
			if(Employee.getName() != null && !Employee.getName().trim().isEmpty())
				query += " and f_name like '%"+Employee.getName()+"%'";
			if(Employee.getDept_name() != null && !Employee.getDept_name().trim().isEmpty())
				query += " and dept_name like '%"+Employee.getDept_name()+"%'";
			if(Employee.getSalary() != 0)
				query += " and salary like '"+Employee.getSalary()+"%'";
			if(Employee.getAddress() != null && !Employee.getAddress().trim().isEmpty())
				query += " and address like '%"+Employee.getAddress()+"%'";
			
			try {
				conn = dbUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Employee e = new Employee();
					e.setId(rs.getLong(1));
					e.setName(rs.getString(2));
					e.setDept_name(rs.getString(3));
					e.setSalary(rs.getLong(4));
					e.setAddress(rs.getString(5));
					EmployeeList.add(e);
				}
			}catch(SQLException ex) {

			}finally {
				if(conn != null)
					dbUtil.closeConnection(conn);
			}
		return EmployeeList;
	}
		@Override
		public int getEmployeesCount() {
			Connection conn = null;
			int count = 0;
			try {
				
				conn = dbUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQLConstants.LIST_STUDENTS_COUNT);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					count = rs.getInt(1);
				}
				
			}catch(SQLException ex) {
				
			}finally {
				if(conn != null)
					dbUtil.closeConnection(conn);
			}
			return count;
		}

}	

	

