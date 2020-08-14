<%@ page import="java.util.ArrayList,voting.pojo.CandidateDetails"
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String userid = (String) session.getAttribute("userid");
	if (userid == null) {
		response.sendRedirect("accessdenied.html");
		return;
	}
	String result = (String) request.getAttribute("result");
	if (result.equals("yes")) {
		ArrayList<CandidateDetails> candidates = (ArrayList) request.getAttribute("candidates");
		if (candidates.size() == 0) {
%>
<div
	class="w3-card-4 w3-padding w3-animate-zoom w3-white w3-round-large"
	style="width: 60%; margin-left: 20%;">
	<h2 class="w3-margin w3-center">There is no any nominies for this
		election</h2>
</div>
<%
	} else {
%>
<div class="w3-row-padding">
	<%
		for (CandidateDetails candidate : candidates) {
	%>
	<div class="w3-col m6 w3-collapse w3-animate-right"
		style="margin-bottom: 10px;">
		<div class="w3-card w3-round w3-white">
			<div class="w3-cell-row  w3-padding w3-border-bottom">
				<div class="w3-cell w3-left">
					<span style="font-size: 20px;"><i
						class="fa fa-id-badge fa-fw w3-margin-right w3-text-theme"></i><%=candidate.getCandidateId()%></span>
				</div>
				<div class="w3-cell w3-right">
					<label>Click here to select</label> <input class="w3-radio"
						type="radio" name="candi" value="<%=candidate.getCandidateId()%>">
				</div>
			</div>
			<div class="w3-cell-row w3-padding">
				<div class="w3-cell">
					<img src="<%=candidate.getPhoto()%>" class="w3-circle"
						style="height: 106px; width: 106px" alt="Avatar">
					<h6 class="w3-center">Profile Photo</h6>
				</div>
				<div class="w3-cell">
					<img src="<%=candidate.getSymbol()%>" class="w3-circle"
						style="height: 106px; width: 106px" alt="Avatar">
					<h6 class="w3-center">Symbol</h6>
				</div>
				<div class="w3-cell w3-cell-middle w3-padding">
					<table style="text-align: left;">
						<tr>
							<th class="w3-text-teal">User Id</th>
							<td><%=candidate.getUserId()%></td>
						</tr>
						<tr>
							<th class="w3-text-teal">Name</th>
							<td><%=candidate.getUserName()%></td>
						</tr>
						<tr>
							<th class="w3-text-teal">Branch</th>
							<td><%=candidate.getBranch()%></td>
						</tr>
						<tr>
							<th class="w3-text-teal">Year</th>
							<td><%=candidate.getYear()%></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<%
		}
	%>
</div>
<%
	}
%>
<%
	} else {
		CandidateDetails candidate = (CandidateDetails) request.getAttribute("candidate");
%>
<div class="w3-padding w3-animate-zoom w3-round-large"
	style="width: 60%; margin-left: 20%;">
	<h2 class="w3-margin w3-center">You have given your vote to :</h2>
	<div class="w3-card w3-round w3-white">
		<div class="w3-cell-row  w3-padding w3-border-bottom">
			<div class="w3-cell w3-left">
				<span style="font-size: 20px;"><i
					class="fa fa-id-badge fa-fw w3-margin-right w3-text-theme"></i><%=candidate.getCandidateId()%></span>
			</div>
		</div>
		<div class="w3-cell-row w3-padding">
			<div class="w3-cell">
				<img src="<%=candidate.getPhoto()%>" class="w3-circle"
					style="height: 106px; width: 106px" alt="Avatar">
				<h6 class="w3-center">Profile Photo</h6>
			</div>
			<div class="w3-cell">
				<img src="<%=candidate.getSymbol()%>" class="w3-circle"
					style="height: 106px; width: 106px" alt="Avatar">
				<h6 class="w3-center">Symbol</h6>
			</div>
			<div class="w3-cell w3-cell-middle w3-padding">
				<table style="text-align: left;">
					<tr>
						<th class="w3-text-teal">User Id</th>
						<td><%=candidate.getUserId()%></td>
					</tr>
					<tr>
						<th class="w3-text-teal">Name</th>
						<td><%=candidate.getUserName()%></td>
					</tr>
					<tr>
						<th class="w3-text-teal">Branch</th>
						<td><%=candidate.getBranch()%></td>
					</tr>
					<tr>
						<th class="w3-text-teal">Year</th>
						<td><%=candidate.getYear()%></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<%
	}
%>