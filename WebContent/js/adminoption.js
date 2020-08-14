
function choosefile() {
	$("#profile").click();
}

function uploadphoto() {
	if ($("#profile").val()) {
		var data = new FormData();
		var file = $("#profile")[0].files[0];
		data.append('file', file);
		$.ajax({
			type : "POST",
			enctype : "multipart/form-data",
			url : "UpdateProfileControllerServlet",
			data : data,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,
			success : function(responseText) {
				if (responseText.trim() === 'error') {
					Swal.fire("Error!", "Unable to change photo!", "error");
				} else {
					$("#avatar").attr("src", responseText);
				}
			},
			error : function(e) {
				Swal.fire("Admin!", e, "error");
			}
		});
	} else {
		return;
	}
}

function logout() {
	Swal.fire({
		  title: 'Are you sure?',
		  text: "You want to logout!",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Yes'
		}).then((result) => {
		  if (result.value) {
			  data = {
					  data : "logout"
			  };
			  $.post("AdminControllerServlet", data, function(responseText) {
						window.location = "index.html";
				});
		  }
		});
}
function getusers() {
	 data = {
			  data : "users"
	  };
	  $.post("AdminControllerServlet", data, function(responseText) {
			if(responseText === "error") {
				Swal.fire("Eroor!", "Unable to load users", "error");
			} else {
		  $("#main").html(responseText);
		  }
		});
}
function searchusers() {
	var userid = $("#userid").val();
	var username = $("#username").val();
	if(userid === "" && username === "") {
		Swal.fire("Eroor!", "At least fill one field", "error");
	}else {
		data = {
				  data : "search",
				  userid : userid,
				  username : username
		  };
		  $.post("AdminControllerServlet", data, function(responseText) {
				if(responseText === "error") {
					Swal.fire("Eroor!", "Unable to load users", "error");
				} else {
			  $("#main").html(responseText);
			  }
			});
	}
	
}

function disableme(userid) {
	data = {
			data : "disable",
			userid : userid
	};
	 $.post("UpdateUserControllerServlet", data, function(responseText) {
			if(responseText === "error") {
				Swal.fire("Eroor!", "Unable to update users", "error");
			} else {
				Swal.fire("Success!", "users updated successfully", "success");
		  $("#main").html(responseText);
		  }
		});
}
function enableme(userid) {
	data = {
			data : "enable",
			userid : userid
	};
	 $.post("UpdateUserControllerServlet", data, function(responseText) {
			if(responseText === "error") {
				Swal.fire("Eroor!", "Unable to update users", "error");
			} else {
				Swal.fire("Success!", "users updated successfully", "success");
		  $("#main").html(responseText);
		  }
		});
}
function changepassword() {
	data = {
			data : "changeform"
	};
	$.post("UpdateUserControllerServlet", data, function(responseText) {
	  $("#main").html(responseText);
	});
}

function change() {
	var pass = $("#opass").val();
	var npass = $("#npass").val();
	var npassa = $("#npassa").val();
	if(pass === "" || npass === "" || npassa === "") {
		Swal.fire("Error!", "All fields are necessary", "error");
		return;
	}else if(npass !== npassa) {
		Swal.fire("Error!", "Password does not match", "error");
		return;
	}else {
		Swal.fire({
			  title: 'Are you sure?',
			  text: "You want to change password!",
			  icon: 'question',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes'
			}).then((result) => {
			  if (result.value) {
				  data = {
							data : "change",
							pass : pass,
							npass : npass
					};
				  $.post("UpdateUserControllerServlet", data, function(responseText) {
					  if(responseText === "success") {
						  Swal.fire("Success!", "Password changed successfully", "success");
						  changepassword();
					  }else if(responseText === "wrong"){
						  Swal.fire("Error!", "Enter Correct Password", "error");
						  changepassword();
					  }
					});
			  }
			});
	}
	
}
function election() {
	data = {
			data : "electionform"
	};
	$.post("ElectionControllerServlet", data, function(responseText) {
	  $("#main").html(responseText);
	});
}

function addelection() {
	var elect = $("#election").val();
	if(elect === "") {
		Swal.fire("Error!", "Kindly fill the name", "error");
	}else {
		data = {
				data : "add",
				election : elect
		};
		$.post("ElectionControllerServlet", data, function(responseText) {
		  if(responseText === "success") {
			  Swal.fire("Success!", "Election added successfully", "sucess");
			  election();
		  } else if(responseText === "duplicate") {
			  Swal.fire("Error!", "Election already exist successfully", "error");
		  } else if(responseText === "error") {
			  Swal.fire("Error!", "Unable to add election", "error");
		  }
		});
	}
}

function searchelection() {
	var elect = $("#elec").val();
	if(elect === "") {
		Swal.fire("Error!", "Kindly fill the name", "error");
	}else {
		data = {
				data : "search",
				election : elect
		};
		$.post("ElectionControllerServlet", data, function(responseText) {
			$("#main").html(responseText);
		});
	}
}
function deleteelection(elect) {
	data = {
			data : "delete",
			election : elect
	};
	$.post("ElectionControllerServlet", data, function(responseText) {
	  if(responseText === "success") {
		  Swal.fire("Success!", "Election deleted successfully", "sucess");
		  election();
	  } else if(responseText === "error") {
		  Swal.fire("Error!", "Unable to delete election", "error");
	  }
	});
} 

function startnomination(elect) {
	data = {
			data : "startnomination",
			election : elect
	};
	$.post("ElectionControllerServlet", data, function(responseText) {
	  if(responseText === "success") {
		  Swal.fire("Success!", "Nomination started successfully", "sucess");
		  election();
	  } else if(responseText === "error") {
		  Swal.fire("Error!", "Unable to start nomination", "error");
	  }
	});
}

function stopnomination(elect) {
	data = {
			data : "stopnomination",
			election : elect
	};
	$.post("ElectionControllerServlet", data, function(responseText) {
	  if(responseText === "success") {
		  Swal.fire("Success!", "Nomination stopped successfully", "sucess");
		  election();
	  } else if(responseText === "error") {
		  Swal.fire("Error!", "Unable to stop nomination", "error");
	  }
	});
}

function startelection(elect) {
	data = {
			data : "startelection",
			election : elect
	};
	$.post("ElectionControllerServlet", data, function(responseText) {
	  if(responseText === "success") {
		  Swal.fire("Success!", "Election started successfully", "sucess");
		  election();
	  } else if(responseText === "error") {
		  Swal.fire("Error!", "Unable to start election", "error");
	  }
	});
}

function stopelection(elect) {
	data = {
			data : "stopelection",
			election : elect
	};
	$.post("ElectionControllerServlet", data, function(responseText) {
	  if(responseText === "success") {
		  Swal.fire("Success!", "Election stopped successfully", "sucess");
		  election();
	  } else if(responseText === "error") {
		  Swal.fire("Error!", "Unable to stop election", "error");
	  }
	});
}

function getcandidate() {
	data = {
			data : "candidates"
	};
	$.post("CandidateControllerServlet", data, function(responseText) {
	  $("#main").html(responseText);
	});
}

function searchcandidate() {
	var userid = $("#userid").val();
	var username = $("#username").val();
	if(userid === "" && username === "") {
		Swal.fire("Eroor!", "At least fill one field", "error");
	}else {
		data = {
				  data : "search",
				  userid : userid,
				  username : username
		  };
		  $.post("CandidateControllerServlet", data, function(responseText) {
			  $("#main").html(responseText);
			});
	}
}

function deleteme(candidateid) {
	data = {
			data : "delete",
			candidateid : candidateid
	};
	$.post("CandidateControllerServlet", data, function(responseText) {
	  if(responseText === "success") {
		  Swal.fire("Success!", "Candidate Deleted successfully", "sucess");
		  getcandidate();
	  } else if(responseText === "error") {
		  Swal.fire("Error!", "Unable to Delete Candidate", "error");
	  }
	});
}
function enablecandidate(candidateid) {
	data = {
			data : "enable",
			candidateid : candidateid
	};
	$.post("CandidateControllerServlet", data, function(responseText) {
	  if(responseText === "success") {
		  Swal.fire("Success!", "Candidate enabled successfully", "sucess");
		  getcandidate();
	  } else if(responseText === "error") {
		  Swal.fire("Error!", "Unable to enable Candidate", "error");
	  }
	});
}
function disablecandidate(candidateid) {
	data = {
			data : "disable",
			candidateid : candidateid
	};
	$.post("CandidateControllerServlet", data, function(responseText) {
	  if(responseText === "success") {
		  Swal.fire("Success!", "Candidate Disabled successfully", "sucess");
		  getcandidate();
	  } else if(responseText === "error") {
		  Swal.fire("Error!", "Unable to Disable Candidate", "error");
	  }
	});
}

function result() {
	data = {
			data : "resultoptions"
	};
	$.post("VoteControllerServlet", data, function(responseText) {
	  $("#main").html(responseText);
	});
}

function getresult() {
	var elect = $("#option").val();
	data = {
			data : "result",
			electionid : elect
	};
	$.post("VoteControllerServlet", data, function(responseText) {
		 $("#result").html(responseText);
	});
}