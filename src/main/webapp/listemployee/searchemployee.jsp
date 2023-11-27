<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Employees</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/commonemp/navbar.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/listemployee/employee.css"/>

</head>
<body>
	<jsp:include page="../commonemp/navbar.jsp"></jsp:include>
	<%List<String> errors = (List<String>) request.getAttribute("errors"); %>
	<% if(errors!=null && errors.size() > 0) { %>
		<div class="errors-container"> 
			<%for(String e: errors) {%>
				<p class="error-message"><%=e%></p>
			<%}%>
		</div>
	<%} %>
	<div class="form-container" id="form-container">
		<form action="employees" method="post" id="employee-form">
			<input name="subAction" value="SEARCH_SUBMIT" hidden="true"/>
			<div class="form-control-group">
				<label>Employee id</label>
				<input 
					type="tel" 
					id="employee_id" 
					name="employee_id" >
			</div>
			<div class="form-control-group">
				<label>Name</label>
				<input type="text" 
					id="Name" 
					name="Name" >
			</div>
			<div class="form-control-group">
				<label>Department Name</label>
				<input type="text" 
						id="Dept_name" 
						name="Dept_name">
			</div>
			<div class="form-control-group">
				<label>Salary</label>
				<input 
						type="tel" 
					   id="salary" 
					   name="salary">
			</div>
			<div class="form-control-group">
				<label>Address</label>
				<input type="text" 
						id="address" 
						name="address" >
			</div>
			<div class="form-control-group">
				<input type="submit" value="SEARCH" class="btn-submit">
			</div>
		</form>
	</div>

</body>
</html>