package com.employee.dao;

import java.util.List;

import com.employee.model.Employee;

public interface EmployeeDao {
	

public List<Employee> getEmployees(int rowsPerPage, int offSet);


//crud operations

public int addEmployee(Employee e);

public Employee getEmployee(Long id);

public int updateEmployee(Employee e);

public int deleteEmployee(Long id);

public List<Employee> searchEmployees(Employee employee);

public int getEmployeesCount();



}
