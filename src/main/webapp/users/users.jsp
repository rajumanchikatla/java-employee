<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADD USER</title>
</head>
<body>
   <link rel="stylesheet" href="${pageContext.request.contextPath}/listemployee/employee.css"/>
	<%List<String> errors = (List<String>) request.getAttribute("errors"); %>
		<%List<String> roles = (List<String>) request.getAttribute("roles"); %>
	<jsp:include page="../commonemp/navbar.jsp"></jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/commonemp/navbar.css" />
	<% if(errors!=null && errors.size() > 0) { %>
		<div class="errors-container"> 
			<%for(String e: errors) {%>
			<p class="error-message"><%=e%></p>
			<%}%>
		</div>
	<%}%>
	<div class="form-container" id="form-container">
		<form action="userController" method="post" id="employee-form">
			<input name="subAction" value="ADD_USER" hidden="true"/>
			<div class="form-control-group">
				<label>User name</label>
				<input 
					type="text" 
					id="username" 
					name="username"
					oninvalid="setCustomValidity('username should be comtain 5 to 12 characters')" 
					oninput="this.setCustomValidity('')"
					
					required/>
			</div>
			
			<div class="form-control-group">
				<label>Password</label>
				<input type="password" 
					   id="password" 
					   name="password" 
					   oninvalid="setCustomValidity('Password should be minimum of 8 characters')"
					   oninput="this.setCustomValidity('')"
					  
					   required>
			</div>
			<div class="form-control-group">
				<label>confirm password</label>
				<input type="password" 
						id="password" 
						name="password" 
						oninvalid="setCustomValidity('Password should be minimum of 8 characters')"
						oninput="this.setCustomValidity('')"
						
					    required>
			</div>
			
			<div class="form-control-group">
				<label for="role">Role</label>
				<select id="role" name="role"> 
					<%for(String e: roles){ %>
	        				<option value='<%=e%>'><%=e %></option>
	        		<%} %>
				</select> 
			</div>
			
			<div class="form-control-group">
				<input type="submit" value="Add User" class="btn-submit">
			</div>
		</form>
	</div>
</body>
</html>