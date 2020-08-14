<%@ page language="java"
	import="voting.pojo.UserDetails,voting.dao.UserDao"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	String userid = (String) session.getAttribute("userid");
	if (userid == null) {
		response.sendRedirect("accessdenied.html");
		return;
	}
	UserDetails user = UserDao.getDetails(userid);
	String photo = user.getPhoto();
	if (photo.equals("")) {
		user.setPhoto("images/businessman-310819_640.png");
	} else {
		photo = "data:image/jpg;base64," + photo;
		user.setPhoto(photo);
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>User Panel</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/main.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script src="js/useroptions.js"></script>
</head>
<body class="w3-light-grey w3-content" style="max-width: 1600px">
	<nav class="w3-sidebar w3-collapse w3-white w3-animate-left"
		style="z-index: 3; width: 300px; position: fixed; top: 0; margin-top: 0px;"
		id="mySidebar">
		<br>
		<div class="w3-container">
			<a href="#" onclick="w3_close()"
				class="w3-hide-large w3-right w3-jumbo w3-padding w3-hover-grey"
				title="close menu"> <i class="fa fa-remove"></i>
			</a>
			<div class="w3-card w3-round w3-white">
				<div class="w3-container">
					<div class="w3-cell-row">
						<div class="w3-cell w3-left">
							<h4 class="w3-center w3-left">My Profile</h4>
						</div>
						<div class="w3-cell w3-right">
							<a onclick="logout()"><i
								class="fa fa-sign-out w3-large w3-text-teal w3-button"
								style="margin-top: 8px;"></i></a>
						</div>
					</div>
					<p class="w3-center">
						<img src="<%=user.getPhoto()%>" class="w3-circle"
							style="height: 106px; width: 106px" alt="Avatar" id="avatar"
							onclick="choosefile()" />
					<form id="profileupload">
						<input type="file" hidden="" accept="image/*" id="profile"
							onchange="uploadphoto()" />
					</form>
					</p>
					<hr>
					<p>
						<i class="fa fa-id-badge fa-fw w3-margin-right w3-text-theme"></i><%=user.getUserId()%>
					</p>
					<p>
						<i class="fa fa-user fa-fw w3-margin-right w3-text-theme"></i><%=user.getUserName()%>
					</p>
				</div>
			</div>
			<h4>
				<b>User Options</b>
			</h4>
		</div>
		<div class="w3-bar-block">
			<a onclick="castvote()"
				class="w3-bar-item w3-button w3-padding w3-text-teal"><i
				class="fa fa-hand-o-up fa-fw w3-margin-right"></i>CAST VOTE</a> <a
				onclick="candidate()"
				class="w3-bar-item w3-button w3-padding w3-text-teal"><i
				class="fa fa-user fa-fw w3-margin-right"></i>BECOME CANDIDATE</a> <a
				onclick="result()"
				class="w3-bar-item w3-button w3-padding w3-text-teal"><i
				class="fa fa fa-line-chart fa-fw w3-margin-right"></i>RESULTS</a> <a
				onclick="changepassword()"
				class="w3-bar-item w3-button w3-padding w3-text-teal"><i
				class="fa fa-key fa-fw w3-margin-right"></i>CHANGE PASSWORD</a>
		</div>
	</nav>
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>
	<span class="w3-button w3-hide-large w3-xxlarge w3-hover-text-grey"
		onclick="w3_open()"><i class="fa fa-bars"></i></span>
	<div class="w3-main" style="margin-left: 300px;" id="main">
		<div
			class="w3-card-4 w3-round-large w3-padding-16 w3-animate-zoom w3-center"
			style="width: 60%; margin-left: 20%; margin-top: 20%; border: 5px solid teal">
			<h2>Welcome to University Voting Portal</h2>
		</div>
	</div>
	<script>
		function w3_open() {
			document.getElementById("mySidebar").style.display = "block";
			document.getElementById("myOverlay").style.display = "block";
		}
		function w3_close() {
			document.getElementById("mySidebar").style.display = "none";
			document.getElementById("myOverlay").style.display = "none";
		}
	</script>
</body>
</html>
