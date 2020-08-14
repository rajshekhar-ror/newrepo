var userid, password;
function loginme() {
	userid = $("#userid").val();
	password = $("#password").val();
	if (validate() === false) {
		swal("Access Denied!", "Please enter userid/password!", "error");
		return;
	}
	var data = {
		userid : userid,
		password : password
	};
	var jqxhr = $.post("LoginControllerServlet", data, processresponse);
	jqxhr.error(handleError);
}

function processresponse(responseText) {
	responseText = responseText.trim();
	if (responseText === "error") {
		swal("Access Denied!", "Please enter valid userid/password", "error");
	} else if (responseText.indexOf("jsessionid") !== -1) {
		swal( {icon: 'success',
				  title: 'Login Accepted',
				  showConfirmButton: false,
				  timer: 500});
		setTimeout(function() {
			window.location = responseText;
		}, 500);
	} else if(responseText === "blocked"){
		swal("Access Denied!", "Your account has been blocked ! Kindly contact to admin", "error");
	}
}

function handleError(xhr) {
	swal("Error!", "Error occured during your request : " + xhr.status + ' '
			+ statusText, "error");
}

function validate() {
	if (userid === "" || password === "")
		return false;
	return true;
}