package com.employee.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.exception.DuplicatesException;
import com.employee.model.Employee;
import com.employee.service.AuthenticationService;
import com.employee.service.AuthenticationServiceImpl;
import com.employee.service.EmployeeService;
import com.employee.service.EmployeeServiceImpl;
import com.employee.util.CommonUtil;

	/**
	 * Servlet implementation class EmployeeController
	 */
	public class EmployeeController extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private AuthenticationService authService = null;

	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
		
		EmployeeService employeeService = null;
	    public EmployeeController() {
	        super();
	        employeeService=new EmployeeServiceImpl();
	        authService = new AuthenticationServiceImpl();

	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
		}
		
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			if(!authService.isUserLoggedin(request, response) || !CommonUtil.verifyAction(request, response)) 
				
				
				return;	
			
			String subAction = request.getParameter("subAction");
			String SUCCESS_VIEW = null;
			List<String> errors = new ArrayList<String>();
			List<String> successMessages = new ArrayList<String>();
			request.setAttribute("errors", errors);
			request.setAttribute("successMessages", successMessages);
			if("ListEmployees".equals(subAction)) {
				request.setAttribute("baseUrl","employees?subAction=ListEmployees");
				SUCCESS_VIEW=getEmployee(request);
			}else if("ADD_EMPLOYEE".equals(subAction)){ 
				SUCCESS_VIEW ="listemployee/addEmployee.jsp";
			}else if("ADD_SUBMIT".equals(subAction)) {
				Employee e = new Employee();
				e.setId(Long.valueOf(request.getParameter("employee_id")));
				e.setName(request.getParameter("Name"));
				e.setDept_name(request.getParameter("Dept_name"));
				e.setSalary(Long.valueOf(request.getParameter("salary")));
				e.setAddress(request.getParameter("address"));
				try {
					employeeService.addemployee(e);
					successMessages.add("Employee "+e.getId()+" successfully added ");
					SUCCESS_VIEW=getEmployee(request);
				}catch(DuplicatesException ex) {
					SUCCESS_VIEW ="listemployee/addEmployee.jsp";
					errors.add("BE-101 Employee id"+e.getId()+" already present");
				}catch(Exception ex) {
					SUCCESS_VIEW ="listemployee/addEmployee.jsp";
					errors.add("some unknown error occured");
				}
			}
		      else if("UPDATE".equals(subAction)) {
				Long Id = Long.valueOf(request.getParameter("employee_id"));
				Employee e = employeeService.getEmployee(Id);
				request.setAttribute("employee", e);
				SUCCESS_VIEW = "listemployee/modifyEmployee.jsp";
			}else if("MODIFY_SUBMIT".equals(subAction)) {
				Employee e = new Employee();
				e.setId(Long.valueOf(request.getParameter("employee_id")));
				e.setName(request.getParameter("Name"));
				e.setDept_name(request.getParameter("Dept_name"));
				e.setSalary(Long.valueOf(request.getParameter("salary")));
				e.setAddress(request.getParameter("address"));
				try {
				employeeService.updateEmployee(e);
				successMessages.add("employee "+e.getId()+" updated");
				SUCCESS_VIEW=getEmployee(request);
				}catch (Exception ex){	
				}
			}else if("DELETE".equals(subAction)) {
				
				Long Id = Long.valueOf(request.getParameter("employee_id"));
				try{
					int deleteCount=employeeService.deleteEmployee(Id);
					if(deleteCount == 1) {
						successMessages.add("Employee  "+ Id +" deleted from the database");
						successMessages.add(+deleteCount +"  employees deleted");
						SUCCESS_VIEW=getEmployee(request);
					}
					}catch (Exception ex) {	
						
					}
		}else if("SEARCH_SCREEN".equals(subAction)){
					     SUCCESS_VIEW = "listemployee/searchemployee.jsp";
				}else if("SEARCH_SUBMIT".equals(subAction)) {
					Employee e = new Employee();
					if(!request.getParameter("employee_id").trim().isEmpty())
						e.setId(Long.valueOf(request.getParameter("employee_id")));
					e.setName(request.getParameter("Name"));
					e.setDept_name(request.getParameter("Dept_name"));
					if(!request.getParameter("salary").trim().isEmpty())
						e.setSalary(Long.valueOf(request.getParameter("salary")));
					e.setAddress(request.getParameter("address"));
					if(e.isValidForSearch()) {
						List<Employee> employee = employeeService.searchEmployees(e);
						if(employee.size() == 0)
							errors.add("BE_102 - No Employees Found for given Criteria......!");
						request.setAttribute("employees", employee);
						SUCCESS_VIEW = "listemployee/employee.jsp";
					}else {
						errors.add("BE_103 - Please provide aleast one field for search....!");
						SUCCESS_VIEW = "listemployee/searchemployee.jsp";
					}
				}
			RequestDispatcher view = request.getRequestDispatcher(SUCCESS_VIEW);
			view.forward(request, response);
		}
		private String getEmployee(HttpServletRequest request) {
			int pageNo = 1;
			int rowsPerPage = 5;
			if(request.getParameter("pageNo") != null)
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			if(request.getParameter("rowsPerPage") != null)
				rowsPerPage = Integer.parseInt(request.getParameter("rowsPerPage"));	
			List<Employee> employees=employeeService.getEmployees(pageNo, rowsPerPage, request);
			
			request.setAttribute("employees", employees);
			return "listemployee/employee.jsp";
		}
		
	}

