<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<%boolean userLoggedIn = false; %>
<%List<String> roles = (List<String>) request.getSession().getAttribute("userroles");%>
<%if(request.getSession().getAttribute("isUserLoggedIn") != null) {%>
	<%userLoggedIn = (Boolean) request.getSession().getAttribute("isUserLoggedIn");%>
<%} %>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/commonemp/navbar.css" />

<nav>

<h2>EMPLOYEE LOGIN SYSTEM</h2>
<div>
<%if(userLoggedIn){%>
<ul>
<%if(roles!=null && roles.contains("SYS_ADMIN")){ %>
      <li><a class="adduser" href="userController?subAction=ADD_USER_SCREEN"> Add User </a></li>
      <%} %>
        
<li><a href="employees?subAction=ListEmployees">List of Employees</a></li>

	<%if(roles!=null && roles.contains("APP_ADMIN")){ %>
			<li><a href="employees?subAction=ADD_EMPLOYEE"> Add Employees </a></li>
			<%} %>
<li><a href="employees?subAction=SEARCH_SCREEN"> Search Employees </a></li>
<li><a class="logout-btn" href="authenticationController?subAction=LOGOUT"> LOGOUT </a></li>

</ul>
 <%} %>
</div>
</nav>