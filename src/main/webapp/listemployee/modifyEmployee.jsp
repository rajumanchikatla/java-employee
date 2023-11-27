<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page import="com.employee.model.Employee" %>
    
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>update employee</title>
<body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/listemployee/employee.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/commonemp/navbar.css" />
    	<jsp:include page="../commonemp/navbar.jsp"></jsp:include>
    
    <link rel="stylesheet" type="text/css" href="employee.css">

		<%Employee employee = (Employee)request.getAttribute("employee"); %>
	
	<%if(employee == null){ %>
		<p>Unable to fetch Employee Data</p>
	<%} else {%>
	
	
	<div class="form-container" id="form-container">
		<form action="employees" method="post" id="employee-form">
			<input name="subAction" value="MODIFY_SUBMIT" hidden="true"/>
			<div class="form-control-group">
				<label>Employee id</label>
				<input 
					type="tel" 
					id="employee_id" 
					name="employee_id" 
					oninvalid="setCustomValidity('Enter Employee Id')"
					oninput="this.setCustomValidity('')"
					value="<%=employee.getId()%>"
					readonly		
					required>	
			</div>
			   <div class="form-control-group">
				<label>Name</label>
				<input type="text" 
					id="Name" 
					name="Name" 
					oninvalid="setCustomValidity('Enter First Name')"
					value="<%=employee.getName()%>"
					oninput="this.setCustomValidity('')"				
					required>
			   </div>
			   <div class="form-control-group">
				<label>Department name</label>
				<input type="text" 
						id="Dept_name" 
						name="Dept_name"
						oninvalid="setCustomValidity('Enter department name')"
						value="<%=employee.getDept_name()%>"
						oninput="this.setCustomValidity('')"
						required>
			   </div>
			<div class="form-control-group">
				<label>Salary</label>
				<input type="number" 
					   id="salary" 
					   name="salary" 
					   oninvalid="setCustomValidity('Enter Grade')"
					    value="<%=employee.getSalary()%>"
					   oninput="this.setCustomValidity('')"
					   required>
			</div>
			<div class="form-control-group">
				<label>Address</label>
				<input type="text" 
						id="address" 
						name="address" 
						oninvalid="setCustomValidity('enter address')"
						value="<%=employee.getAddress() %>"
						oninput="this.setCustomValidity('')"
					    required>
			</div>
			<div class="form-control-group">
				<input type="submit" value="update employee" class="btn-submit">
			</div>
		</form>
	</div>
	<% }%>
</body>
</html>