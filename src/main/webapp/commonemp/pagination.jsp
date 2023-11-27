<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/commonemp/page.css" />
	<%String url= (String) request.getAttribute("baseUrl"); %>
	
	<%
		int noOfPages = 0;
		int currentPage = 0;
		int rowsPerPage = 0;
		if(request.getAttribute("noOfPages") != null){
			noOfPages = Integer.valueOf((Integer)request.getAttribute("noOfPages"));
			currentPage = Integer.valueOf((Integer)request.getAttribute("currentPage"));
			rowsPerPage = Integer.valueOf((Integer)request.getAttribute("rowsPerPage"));
		}
	
	%>
	<script>
		window.onload = function(){
			const pageCountEl = document.getElementById("page-count");
			pageCountEl.addEventListener('change', function(){
				const pageCount = +pageCountEl.options[pageCountEl.selectedIndex].value;
				const linkEl = document.createElement("a");
				linkEl.href = "<%=url%>&pageNo=1&rowsPerPage="+pageCount;
				linkEl.click();
			})
		}
	</script>
<%if(noOfPages > 1) {%>
	<div class='main-contaner'>
	<div class='pagination-conatiner'>
		<%if(currentPage != 1 && noOfPages > 10){ %>
			<div class='pag-btn-container'>
				<a href="<%=url%>&pageNo=<%=noOfPages%>&rowsPerPage=<%=rowsPerPage%>" class='page-num-btn-ext') >
				<img src="images/svg/angle-double-left.svg" width="20"
					height="10">
				</a>
			</div>
		<%}%>
	
		<div class='pag-btn-container'>
			<%if(currentPage != 1 &&  noOfPages > 10){ %>
				<a href="<%=url%>&pageNo=<%=currentPage-1%>&rowsPerPage=<%=rowsPerPage%>" class='page-num-btn'>previous</a>
			<%}%>
			
				<%if(noOfPages <= 10) {%>
					<%for(int i=1; i<=noOfPages; i++){%>
						<%if(currentPage == i){ %>
							<a class='page-num-btn page-num-btn-active' href="<%=url%>&pageNo=<%=i%>&rowsPerPage=<%=rowsPerPage%>"><%=i%></a>
						<%}else{ %>
							<a class='page-num-btn' href="<%=url%>&pageNo=<%=i%>&rowsPerPage=<%=rowsPerPage%>"><%=i%></a>
						<%} %>
					<%} %>
				<%} else{%>
					<%if(currentPage >= 5 &&  noOfPages > 10){ %>
						<span class='dot-container'>....</span>
					<%} %>
					<%if(currentPage < 5) {%>
						<%for(int i=1; i<=10; i++){%>
							<%if(currentPage == i){ %>
								<a class='page-num-btn page-num-btn-active' href="<%=url%>&pageNo=<%=i%>&rowsPerPage=<%=rowsPerPage%>"><%=i%></a>
							<%}else{ %>
								<a class='page-num-btn' href="<%=url%>&pageNo=<%=i%>&rowsPerPage=<%=rowsPerPage%>"><%=i%></a>
							<%} %>
					<%} %>
					<%}else{ %>
						<%for(int i=currentPage-3; (i<=currentPage+3 && i <=noOfPages); i++){%>
							<%if(currentPage == i){ %>
								<a class='page-num-btn page-num-btn-active' href="<%=url%>&pageNo=<%=i%>&rowsPerPage=<%=rowsPerPage%>"><%=i%></a>
							<%}else{ %>
								<a class='page-num-btn' href="<%=url%>&pageNo=<%=i%>&rowsPerPage=<%=rowsPerPage%>"><%=i%></a>
							<%} %>
						<%} %>
					<%} %>
				<%} %>	
				
				<%if(currentPage+3 < noOfPages &&  noOfPages > 10){ %>	
					<span class='dot-container'>....</span>
				<%} %>
			<%if(currentPage != noOfPages &&  noOfPages > 10){ %>
				<a href="<%=url%>&pageNo=<%=currentPage+1%>&rowsPerPage=<%=rowsPerPage%>" class='page-num-btn'>next</a>
			<%}%>	 
		</div>
		<%if(currentPage != noOfPages && noOfPages > 10){ %>
			<div class='pag-btn-container'>
				<a href="<%=url%>&pageNo=<%=noOfPages%>&rowsPerPage=<%=rowsPerPage%>" class='page-num-btn-ext') >
					<img src="images/svg/angle-double-right.svg" width="20"
						height="10"></img>
				</a>
			</div>
		<%}%>	
	</div>
	<div class='page-count-selecter'>
		<select id="page-count">
			<option value='10'>10</option>
			<option value='20'>20</option>
			<option value='30'>30</option>
		</select>
	</div>
</div>

<% } %>