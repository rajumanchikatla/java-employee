package com.employee.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.employee.model.Employee;

public interface EmployeeService {

	public  List<Employee> getEmployees(int pageNo,int rowsperpage,HttpServletRequest request );
	
	public int addemployee(Employee e);
	
	public Employee getEmployee(Long id);
	
	public int updateEmployee(Employee e);
	
	public int deleteEmployee(Long id);

	public List<Employee> searchEmployees(Employee employee);
	
	
}
