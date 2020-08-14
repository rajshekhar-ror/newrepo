<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String userid = (String) session.getAttribute("userid");
	if (userid == null) {
		response.sendRedirect("accessdenied.html");
		return;
	}
	String message = (String) request.getAttribute("message");
%>
<div
	class="w3-card-4 w3-white w3-animate-zoom w3-round-large w3-center w3-container w3-border"
	style="width: 40%; margin-left: 30%; margin-top: 20%;">
	<h3><%=message%></h3>
</div>