<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String userid = (String) session.getAttribute("userid");
	if (userid == null) {
		response.sendRedirect("accessdenied.html");
		return;
	}
	Exception ex = (Exception) request.getAttribute("exception");
	System.out.println(ex);
	out.print(ex.getMessage());
%>