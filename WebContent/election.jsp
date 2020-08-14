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
	style="width: 60%; margin-left: 10%; position: fixed; top: 2%; z-index: 10;">
	<div class="w3-cell">
		<input type="text" placeholder="Create Election..." name="election"
			id="election" class="w3-border w3-input w3-round-large" />
	</div>
	<div class="w3-cell">
		<button type="button" class="w3-teal w3-button w3-center w3-circle"
			onclick="addelection()">
			<i class="fa fa-plus fa-fw"></i>
		</button>
	</div>
	<div class="w3-cell">
		<input type="text" placeholder="Search Election..." name="elec"
			id="elec" class="w3-border w3-input w3-round-large" />
	</div>
	<div class="w3-cell">
		<button type="button" class="w3-teal w3-button w3-center w3-circle"
			onclick="searchelection()">
			<i class="fa fa-search fa-fw"></i>
		</button>
	</div>
</div>
<div style="margin-top: 10%; width: 100%;">
	<%
		if (elections.size() != 0) {
	%>
	<div class="w3-row-padding">
		<%
			for (Election e : elections) {
		%>
		<div class="w3-col m4 w3-collapse w3-animate-right"
			style="margin-bottom: 10px;">
			<div class="w3-card w3-round w3-white">
				<div class="w3-container w3-padding w3-border-bottom">
					<div class="w3-left">
						<i class="fa fa-id-badge fa-fw w3-margin-right w3-text-theme"></i><%=e.getElectionId()%>
					</div>
					<div class="w3-right"><%=e.getElectionName()%></div>
				</div>
				<div class="w3-container w3-padding">
					<input type="button" value="Delete"
						onclick="deleteelection('<%=e.getElectionId()%>')"
						class="w3-round-large w3-button w3-red w3-left" />
					<%
						if (e.getStatus() == 0) {
					%>
					<input type="button" value="Start Nomination"
						onclick="startnomination('<%=e.getElectionId()%>')"
						class="w3-round-large w3-green w3-button w3-right" />
					<%
						} else if (e.getStatus() == 1) {
					%>
					<input type="button" value="Stop Nomination"
						onclick="stopnomination('<%=e.getElectionId()%>')"
						class="w3-round-large w3-teal w3-button w3-right" />
					<%
						} else if (e.getStatus() == 2) {
					%>
					<input type="button" value="Start Election"
						onclick="startelection('<%=e.getElectionId()%>')"
						class="w3-round-large w3-blue w3-button w3-right" />
					<%
						} else if (e.getStatus() == 3) {
					%>
					<input type="button" value="Stop Election"
						onclick="stopelection('<%=e.getElectionId()%>')"
						class="w3-round-large w3-teal w3-button w3-right" />
					<%
						} else if (e.getStatus() == 4) {
					%>
					<input type="button" value="View Result"
						onclick="viewresult('<%=e.getElectionId()%>')"
						class="w3-round-large w3-teal w3-button w3-right" />
					<%
						}
					%>
				</div>
			</div>
		</div>
		<%
			}
		%>
	</div>
	<%
		} else {
	%>
	<div
		class="w3-card-4 w3-padding w3-animate-zoom w3-white w3-round-large"
		style="width: 60%; margin-left: 20%;">
		<h2 class="w3-margin w3-center">No Election Available Right
			Now....</h2>
	</div>
	<%
		}
	%>
</div>