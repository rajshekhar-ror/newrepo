<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String result = (String) request.getAttribute("result");
	String userid = (String) request.getAttribute("userid");
	if (result != null) {
		if (result.equalsIgnoreCase("blocked")) {
			out.println("blocked");
		} else {
			session.setAttribute("userid", userid);
			if (result.equalsIgnoreCase("admin")) {
				String url = "adminpanel.jsp;jsessionid=" + session.getId();
				out.println(url);
			} else {
				String url = "userpanel.jsp;jsessionid=" + session.getId();
				out.println(url);
			}
		}
	} else {
		out.println("error");
	}
%>