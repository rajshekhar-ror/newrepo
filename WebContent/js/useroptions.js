function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function(e) {
      $('#symbol').attr('src', e.target.result);
    }
    
    reader.readAsDataURL(input.files[0]);
  }
}

function choosefile() {
	$("#profile").click();
}
function choosefile1() {
	$("#photo").click();
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

function candidate() {
	data =  {
		data : "candidateform"
	}
	$.post("CandidateControllerServlet", data, function(responseText) {
		  $("#main").html(responseText);
		});
}

function deleteme(candidateid) {
	data = {
			data : "delete",
			candidateid : candidateid
	};
	$.post("CandidateControllerServlet", data, function(responseText) {
	  if(responseText === "success") {
		  Swal.fire("Success!", "Candidate Deleted successfully", "sucess");
		  candidate();
	  } else if(responseText === "error") {
		  Swal.fire("Error!", "Unable to Delete Candidate", "error");
	  }
	});
}

function become() {
	var elect = $("#election").val();
	if ($("#photo").val() && elect !== "") {
		var data = new FormData();
		var file = $("#photo")[0].files[0];
		data.append('file', file);
		data.append('election', elect);
		$.ajax({
			type : "POST",
			enctype : "multipart/form-data",
			url : "AddCandidateControllerServlet",
			data : data,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,
			success : function(responseText) {
				if (responseText.trim() === 'error') {
					Swal.fire("Error!", "Unable to add candidate!", "error");
				} else if (responseText.trim() === 'duplicate') {
					Swal.fire("Error!", "You have already enrolled to this election!", "error");
				} else {
					Swal.fire("Success!", "You have successfully enrolled to this election!", "success");
					candidate();
				}
			},
			error : function(e) {
				Swal.fire("Admin!", e, "error");
			}
		});
	} else {
		Swal.fire("Error!", "Please give all the necessary information!", "error");
	}
}

function castvote() {
	data = {
			data : "options"
	};
	$.post("VoteControllerServlet", data, function(responseText) {
	  $("#main").html(responseText);
	});
}

function getnominies() {
	var elect = $("#option").val();
	data = {
			data : "nominies",
			electionid : elect
	};
	$.post("VoteControllerServlet", data, function(responseText) {
		 $("#result").html(responseText);
	});
}

function cast() {
	var id = $('input[type=radio][name=candi]:checked').attr('value');
	var elect = $("#option").val();
	if(id === undefined) {
		Swal.fire("Error!", "Please select any candidate!", "error");
	} else if(elect === null) {
		Swal.fire("Error!", "Please select any Election!", "error");
	} else {
		data = {
				data : "checked",
				electionid : elect,
				candidateid : id
		};
		$.post("VoteControllerServlet", data, function(responseText) {
			 if(responseText === "success") {
				 Swal.fire("Success!", "You have given your vote!", "success");
				 getnominies()
			 }else if(responseText === "error"){
				 Swal.fire("Error!", "Unable to cast vote right now!", "error");
			 }else {
				 $("#result").html(responseText);
			 }
		});
	}
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