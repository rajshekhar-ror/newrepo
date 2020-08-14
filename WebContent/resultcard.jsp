<%@ page language="java"
	import="java.util.ArrayList,voting.pojo.ResultCard"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	String userid = (String) session.getAttribute("userid");
	if (userid == null) {
		response.sendRedirect("accessdenied.html");
		return;
	}
	ArrayList<ResultCard> result = (ArrayList) request.getAttribute("results");
	int count = (Integer) request.getAttribute("count");
	if (result.size() != 0) {
		for (ResultCard rs : result) {
			double c = (rs.getCount() / (double) count) * 100;
%>
<div
	class="w3-card-4 w3-padding w3-animate-zoom w3-white w3-round-large"
	style="width: 80%; margin-left: 10%; margin-bottom: 10px;">
	<div class="w3-cell-row  w3-padding w3-border-bottom">
		<div class="w3-cell w3-left">
			<span style="font-size: 20px;"><i
				class="fa fa-id-badge fa-fw w3-margin-right w3-text-theme"></i><%=rs.getCandidateId()%></span>
		</div>
	</div>
	<div class="w3-cell-row w3-padding">
		<div class="w3-cell">
			<img src="<%=rs.getSymbol()%>" class="w3-circle"
				style="height: 106px; width: 106px" alt="Avatar">
		</div>
		<div class="w3-cell w3-cell-middle w3-padding">
			<div class="w3-cell-row">
				<div class="w3-cell w3-left">
					<span class="w3-text-teal w3-margin-right">User Id</span><%=rs.getUserId()%>
				</div>
				<div class="w3-cell w3-right">
					<span class="w3-text-teal w3-margin-right">Name</span><%=rs.getUserName()%>
				</div>
			</div>
			<div class="w3-cell-row">
				<div class="w3-cell w3-left">
					<span class="w3-text-teal w3-margin-right">Branch</span><%=rs.getBranch()%>
				</div>
				<div class="w3-cell w3-right">
					<span class="w3-text-teal w3-margin-right">Year</span><%=rs.getYear()%>
				</div>
			</div>
			<div class="w3-light-grey w3-cell-row w3-margin-top">
				<div class="w3-container  w3-padding-large w3-red w3-center"
					style="width:<%=c%>%;"><%=c%>%
				</div>
			</div>
			<br>
		</div>
	</div>
</div>
<%
	}
	} else {
%>
<div
	class="w3-card-4 w3-padding w3-animate-zoom w3-white w3-round-large"
	style="width: 60%; margin-left: 20%;">
	<h2 class="w3-margin w3-center">No result found.....!</h2>
</div>
<%
	}
%>