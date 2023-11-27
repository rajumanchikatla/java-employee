<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.List" %>
<%@ page import="com.employee.model.Employee" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Employees</title>
<link rel="stylesheet" type="text/css" href="employee.css">
<%List<Employee> employees = (List<Employee>) request.getAttribute("employees"); %>
</head>
<body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/listemployee/employee.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/commonemp/navbar.css" />
<%List<String> successMessages = (List<String>) request.getAttribute("successMessages"); %>
<%List<String> errors = (List<String>) request.getAttribute("errors"); %>

<jsp:include page="../commonemp/navbar.jsp"></jsp:include>
<jsp:include page="../commonemp/commonstyles.jsp"></jsp:include>
<% if(successMessages!=null && successMessages.size() > 0) { %>
		<div class="success-container"> 
			<%for(String s: successMessages) {%>
	             <p class="success-message"><%=s%></p>
			<%}%>
		</div>
	<%}%>
	
	<% if(errors!=null && errors.size() > 0) { %>
		<div class="success-container"> 
			<%for(String s: errors) {%>
	             <p class="error-message"><%=s%></p>
			<%}%>
		</div>
	<%}%>
<table>
	<thead>
	<tr>
	<td>id</td>
	<td> Name </td>
	<td>department Name</td>
	<td>salary</td>
	<td>address</td>
	<td colspan="2">Actions</td>
	</tr>
	</thead>
<tbody>

  <% for(Employee e: employees) {%>
		  <tr>
				<td><%=e.getId() %></td>
				<td><%=e.getName() %></td>
				<td><%=e.getDept_name() %></td>
				<td><%=e.getSalary() %></td>
				<td><%=e.getAddress()%></td>
				<td><a href="${pageContext.servletContext.contextPath}/employees?subAction=UPDATE&employee_id=<%=e.getId()%>">update</a></td>
				<td><a href="${pageContext.servletContext.contextPath}/employees?subAction=DELETE&employee_id=<%=e.getId()%>">delete</a></td>
			</tr>
		 <%} %>	
</tbody>

</table>
<div>
<jsp:include page="../commonemp/pagination.jsp"></jsp:include>
</div>
</body>
</html>