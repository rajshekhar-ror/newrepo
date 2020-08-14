<%@ page language="java"
	import="java.util.ArrayList,voting.pojo.UserDetails"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	String userid = (String) session.getAttribute("userid");
	if (userid == null) {
		response.sendRedirect("accessdenied.html");
		return;
	}
	ArrayList<UserDetails> users = (ArrayList) request.getAttribute("users");
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
			onclick="searchusers()" style="margin-left: 10px;">
			<i class="fa fa-search fa-fw"></i>
		</button>
	</div>
</div>
<div style="width: 100%; margin-top: 10%;">
	<div class="w3-row-padding">
		<%
			for (UserDetails user : users) {
				String photo = user.getPhoto();
				if (photo.equals("")) {
					user.setPhoto("images/businessman-310819_640.png");
				} else {
					photo = "data:image/jpg;base64," + photo;
					user.setPhoto(photo);
				}
		%>
		<div class="w3-col m6 w3-collapse w3-animate-right"
			style="margin-bottom: 10px;">
			<div class="w3-card w3-round w3-white">
				<div class="w3-cell-row  w3-padding w3-border-bottom">
					<div class="w3-cell w3-left">
						<span style="font-size: 20px;"><i
							class="fa fa-id-badge fa-fw w3-margin-right w3-text-theme"></i><%=user.getUserId()%></span>
					</div>
					<div class="w3-cell w3-right">
						<%
							if (user.getActive().equalsIgnoreCase("y")) {
						%>
						<input type="button" value="Disable"
							onclick="disableme('<%=user.getUserId()%>')"
							class="w3-btn w3-round-large w3-red" />
						<%
							} else {
						%>
						<input type="button" value="Enable"
							onclick="enableme('<%=user.getUserId()%>')"
							class="w3-btn w3-round-large w3-green" />
						<%
							}
						%>
					</div>
				</div>
				<div class="w3-cell-row w3-padding">
					<div class="w3-cell">
						<img src="<%=user.getPhoto()%>" class="w3-circle"
							style="height: 106px; width: 106px" alt="Avatar">
					</div>
					<div class="w3-cell w3-cell-middle w3-padding">
						<table style="text-align: left;">
							<tr>
								<th class="w3-text-teal">Name</th>
								<td><%=user.getUserName()%></td>
							</tr>
							<tr>
								<th class="w3-text-teal">Branch</th>
								<td><%=user.getBranch()%></td>
							</tr>
							<tr>
								<th class="w3-text-teal">Year</th>
								<td><%=user.getYear()%></td>
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