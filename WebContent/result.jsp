<%@ page language="java"
	import="java.util.ArrayList,voting.pojo.Election"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	String userid = (String) session.getAttribute("userid");
	if (userid == null) {
		response.sendRedirect("accessdenied.html");
		return;
	}
	ArrayList<Election> elections = (ArrayList) request.getAttribute("elections");
%>
<div
	class="w3-card-4 w3-cell-row w3-padding w3-center w3-round-large w3-light-grey w3-collapse w3-animate-top"
	style="width: 40%; margin-left: 20%; position: fixed; top: 2%; z-index: 10;">
	<div class="w3-cell">
		<select class="w3-select w3-round-large" name="option"
			onchange="getresult()" id="option">
			<option value="" disabled selected>Choose your option</option>
			<%
				for (Election e : elections) {
			%>
			<option value="<%=e.getElectionId()%>"><%=e.getElectionName()%></option>
			<%
				}
			%>
		</select>
	</div>
</div>
<div style="margin-top: 10%; width: 100%;" id="result">
	<div
		class="w3-card-4 w3-padding w3-animate-zoom w3-white w3-round-large"
		style="width: 60%; margin-left: 20%;">
		<h2 class="w3-margin w3-center">Please Select Any Election</h2>
	</div>
</div>