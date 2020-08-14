<%@ page language="java"
	import="java.util.ArrayList,voting.pojo.Election,voting.pojo.Candidate"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	String userid = (String) session.getAttribute("userid");
	if (userid == null) {
		response.sendRedirect("accessdenied.html");
		return;
	}
	ArrayList<Election> elections = (ArrayList) request.getAttribute("elections");
	ArrayList<Candidate> candidates = (ArrayList) request.getAttribute("candidates");
%>
<div
	class="w3-card-4 w3-padding w3-margin-top w3-margin-bottom w3-animate-top"
	style="margin-left: 10%; width: 80%;">

	<header class="w3-container w3-blue w3-padding">
		<h3 class="w3-left">List of Enrolled Elections</h3>
		<a onclick="document.getElementById('id01').style.display='block'"
			class="w3-right w3-xlarge w3-button w3-round-large w3-center"><i
			class="fa fa-plus"></i></a>
	</header>
	<div class="w3-container w3-margin-top">
		<%
			if (candidates.size() != 0) {
		%>
		<table class="w3-table w3-bordered w3-centered">
			<tr class="w3-green">
				<th>Candidate Id</th>
				<th>Election Name</th>
				<th>Symbol</th>
				<th>Status</th>
				<th>Option</th>
			</tr>
			<%
				for (Candidate candidate : candidates) {
			%>
			<tr>
				<td><%=candidate.getCandidateId()%></td>
				<td><%=candidate.getElectionId()%></td>
				<td><img src="<%=candidate.getSymbol()%>" width="100px;"
					height="100px;"></td>
				<%
					if (candidate.getActive().equals("n")) {
				%>
				<td class="w3-text-red">Blocked</td>
				<%
					} else {
				%>
				<td class="w3-text-green">Active</td>
				<%
					}
				%>
				<td><button class="w3-button w3-red w3-margin w3-center"
						onclick="deleteme('<%=candidate.getCandidateId()%>')">Delete</button></td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			} else {
		%>
		<h4>
			You haven't enrolled to any election if you want then press the above
			<i class="fa fa-plus w3-large w3-margin-left w3-margin-right"></i>
			icon...
		</h4>
		<%
			}
		%>
	</div>

	<div id="id01" class="w3-modal">
		<div class="w3-modal-content w3-card-4 w3-animate-zoom w3-round-large"
			style="max-width: 500px">
			<%
				if (elections.size() != 0) {
			%>
			<div class="w3-center">
				<br> <span
					onclick="document.getElementById('id01').style.display='none'"
					class="w3-button w3-xlarge w3-hover-red w3-display-topright"
					title="Close Modal">&times;</span> <img
					src="images/icons8-add-100.png" alt="Avatar" id="symbol"
					style="width: 20%" class="w3-circle w3-margin-top"
					onclick="choosefile1()"> <input type="file" hidden=""
					accept="image/*" id="photo" onchange="readURL(this)" />
				<h4 class="w3-center">Select Symbol</h4>
			</div>

			<div class="w3-container">
				<div class="w3-section">
					<select class="w3-select w3-border w3-margin-bottom" name="option"
						required="required" id="election">
						<option value="" disabled selected>Select election</option>
						<%
							for (Election election : elections) {
						%>
						<option value="<%=election.getElectionId()%>"><%=election.getElectionName()%></option>
						<%
							}
						%>
					</select>
					<button class="w3-button w3-block w3-green w3-section w3-padding"
						onclick="become()">Become Candidate</button>
				</div>
			</div>
			<%
				} else {
			%>
			<div class="w3-center">
				<br> <span
					onclick="document.getElementById('id01').style.display='none'"
					class="w3-button w3-xlarge w3-hover-red w3-display-topright"
					title="Close Modal">&times;</span>
				<h2 class="w3-center w3-margin">Sorry Sir no election available
					at the moment</h2>
			</div>
			<%
				}
			%>
		</div>
	</div>
</div>