<%@ page language="java"
	import="java.util.ArrayList,voting.pojo.CandidateDetails"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	String userid = (String) session.getAttribute("userid");
	if (userid == null) {
		response.sendRedirect("accessdenied.html");
		return;
	}
	ArrayList<CandidateDetails> candidates = (ArrayList) request.getAttribute("candidates");
%>
<div
	class="w3-card-4 w3-cell-row w3-padding w3-center w3-round-large w3-light-grey w3-collapse w3-animate-top"
	style="width: 60%; margin-left: 10%; position: fixed; top: 2%; z-index: 10;">
	<div class="w3-cell">
		<input type="text" placeholder="Enter User Id..." name="userid"
			id="userid" class="w3-border w3-input w3-round-large" />
	</div>
	<div class="w3-cell">
		<span class="w3-text-black">-OR-</span>
	</div>
	<div class="w3-cell">
		<input type="text" placeholder="Enter User Name..." name="username"
			id="username" class="w3-border w3-input w3-round-large" />
	</div>
	<div class="w3-cell">
		<button type="button" class="w3-teal w3-button w3-center w3-circle"
			onclick="searchcandidate()" style="margin-left: 10px;">
			<i class="fa fa-search fa-fw"></i>
		</button>
	</div>
</div>
<div style="width: 100%; margin-top: 10%;">
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
						<input type="button" value="Delete"
							onclick="deleteme('<%=candidate.getCandidateId()%>')"
							class="w3-btn w3-round-large w3-red" />
						<%
							if (candidate.getActive().equals("n")) {
						%>
						<input type="button" value="Enable"
							onclick="enablecandidate('<%=candidate.getCandidateId()%>')"
							class="w3-btn w3-round-large w3-green" />
						<%
							} else {
						%>
						<input type="button" value="Disable"
							onclick="disablecandidate('<%=candidate.getCandidateId()%>')"
							class="w3-btn w3-round-large w3-yellow" />
						<%
							}
						%>
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
							<tr>
								<th class="w3-text-teal">Election</th>
								<td><%=candidate.getElectionId()%></td>
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
</div>