<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String userid = (String) session.getAttribute("userid");
	if (userid == null) {
		response.sendRedirect("accessdenied.html");
		return;
	}
%>
<div
	class="w3-card-4 w3-white w3-animate-zoom w3-round-large w3-center w3-container w3-border"
	style="width: 40%; margin-left: 30%; margin-top: 15%;">
	<h3 class="w3-teal w3-round-large w3-center">Change Password</h3>
	<div class="w3-container w3-center w3-padding">
		<input type="password" placeholder="Enter Old Password"
			class="w3-input w3-margin-bottom" id="opass" /> <input
			type="password" placeholder="Enter New Password"
			class="w3-input w3-margin-bottom" id="npass" /> <input
			type="password" placeholder="Enter New Password Again"
			class="w3-input w3-margin-bottom" id="npassa" /> <input
			type="button" value="Change"
			class="w3-button w3-red w3-round-large w3-margin-bottom"
			onclick="change()" />
	</div>
</div>