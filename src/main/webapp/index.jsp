<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Management System</title>
<link rel="stylesheet" href="./commonemp/navbar.css" />
<link rel="stylesheet" href="./listemployee/employee.css" />
<link rel="stylesheet" href="./styles/index.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/navbar.css" />
</head>
<body>
<jsp:include page="./commonemp/commonstyles.jsp"></jsp:include>
<%List<String> errors = (List<String>) request.getAttribute("errors"); %>
<jsp:include page="./commonemp/navbar.jsp"></jsp:include>
	<div class='main-conatiner'>
		<div class='text-box'>
		</div>
		<div class='login-box'>
			<div class="form-container form-container-bg-white form-container-sm" id="form-container">
	<% if(errors!=null && errors.size() > 0) { %>
		<div class="errors-container error-container-login"> 
			<%for(String e: errors) {%>
			<p class="error-message"><%=e%></p>
			<%}%>
		</div>
	<%}%>
				<form action="authenticationController" method="post" id="employee-form">
					<input name="subAction" value="LOGIN" hidden="true"/>
					<div class="form-control-group form-control-group-left"  >
						<label for="username">User name</label>
						<input 
							type="text" 
							id="username" 
							name="username" 
							oninvalid="setCustomValidity('Enter username')"
							oninput="this.setCustomValidity('')"
							required>
					</div>
					<div class="form-control-group form-control-group-left">
						<label for="password">Password</label>
						<input type="password" 
							id="password" 
							name="password" 
							oninvalid="setCustomValidity('Enter Password')"
							oninput="this.setCustomValidity('')"
							required>
					</div>
		
					  <div class="form-control-group form-control-group-left">
						  <input type="submit" value="Login" class="btn-submit">
					   </div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>