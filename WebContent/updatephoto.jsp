<%@ page import="java.sql.Blob" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	String userid = (String) session.getAttribute("userid");
	if (userid == null) {
		response.sendRedirect("accessdenied.html");
		return;
	}
	boolean result = (Boolean) request.getAttribute("result");
	if (result) {
		String photo = (String) request.getAttribute("photo");
		photo = "data:image/jpg;base64," + photo;
		out.print(photo);
	} else {
		out.print("error");
	}
%>