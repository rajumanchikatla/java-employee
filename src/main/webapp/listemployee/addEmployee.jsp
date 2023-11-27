<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Employee</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="employee.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/listemployee/employee.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/commonemp/navbar.css" />
	<jsp:include page="../commonemp/navbar.jsp"></jsp:include>
	<%List<String> errors = (List<String>) request.getAttribute("errors"); %>

	<% if(errors!=null && errors.size() > 0) { %>
		<div class="errors-container"> 
			<%for(String e: errors) {%>
			<p class="error-message"><%=e%></p>
			<%}%>
		</div>
	<%}%>
	<div class="form-container" id="form-container">
		<form action="employees" method="post" id="employee-form">
			<input name="subAction" value="ADD_SUBMIT" hidden="true"/>
			<div class="form-control-group">
				<label>Employee id</label>
				<input 
					type=number
					id="employee_id" 
					name="employee_id" 
					oninvalid="setCustomValidity('Enter Employee Id')"
					oninput="this.setCustomValidity('')"
					required/>
			</div>
			<div class="form-control-group">
				<label>Name</label>
				<input type="text" 
					id="Name" 
					name="Name" 
					oninvalid="setCustomValidity('Enter Name')"
					oninput="this.setCustomValidity('')"
					required>
			</div>
			<div class="form-control-group">
				<label>Department Name</label>
				<input type="text" 
						id="Dept_name" 
						name="Dept_name"
						oninvalid="setCustomValidity('Enter Deptname')"
						oninput="this.setCustomValidity('')"
						required>
			</div>
			<div class="form-control-group">
				<label>Salary</label>
				<input type="NUMBER" 
					   id="salary" 
					   name="salary" 
					   oninvalid="setCustomValidity('Enter salary')"
					   oninput="this.setCustomValidity('')"
					   required>
			</div>
			<div class="form-control-group">
				<label>Address</label>
				<input type="text" 
						id="address" 
						name="address" 
						oninvalid="setCustomValidity('Enter Address')"
						oninput="this.setCustomValidity('')"
					    required>
			</div>
			<div class="form-control-group">
				<input type="submit" value="Add Employee" class="btn-submit">
			</div>
		</form>
	</div>
</body>
</html>