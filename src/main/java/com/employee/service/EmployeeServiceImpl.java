package com.employee.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.employee.dao.EmployeeDaoImpl;
import com.employee.model.Employee;


public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDaoImpl employeeDao;
	
	
	public EmployeeServiceImpl()
	{
		employeeDao = new EmployeeDaoImpl();
	}

	@Override
	public List<Employee> getEmployees(int pageNo, int rowsperpage, HttpServletRequest request) {
		int totalCount = employeeDao.getEmployeesCount();
		int noOfPages = totalCount % rowsperpage == 0 ? totalCount / rowsperpage : (totalCount / rowsperpage) +1;
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", pageNo);
		request.setAttribute("rowsPerPage", rowsperpage);
		return employeeDao.getEmployees(rowsperpage, (pageNo-1) * rowsperpage);
	}

	@Override
	public int addemployee(Employee e) {
		// TODO Auto-generated method stub
		return employeeDao.addEmployee(e);
	}

	@Override
	public Employee getEmployee(Long id) {
		// TODO Auto-generated method stub
		return employeeDao.getEmployee(id);
	}

	@Override
	public int updateEmployee(Employee e) {
		// TODO Auto-generated method stub
		return employeeDao.updateEmployee(e);
	}

	@Override
	public int deleteEmployee(Long id) {
		// TODO Auto-generated method stub
		return employeeDao.deleteEmployee(id);
	}

	public List<Employee> searchEmployees(Employee employee) {
		// TODO Auto-generated method stub
		return employeeDao.searchEmployees(employee);
	}





	

	
}
