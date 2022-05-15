/**
 Payment
 */
// send the user id from profile to billbeforepay
$(document).on("click","#sendID",function(event) {
	if($("#puid").val().trim() != "") {
		
		$("#parseID").submit();
	
	} else {
		alert("Enter the User ID")
	}
		
});
// send the user id from profile to paymentHistory
$(document).on("click","#sendIDtoHistory",function(event) {
	if($("#puid1").val().trim() != "") {
	
		$("#parseID1").submit();
	
	} else {
		alert("Enter the User ID")
	}
		
});
// update billing address
$(document).on("click", "#sendidtoupdate", (e) => {
	if($("#puid2").val().trim() == "") {
		alert("Enter the User ID");
	} else if($("#pbillingAddress").val().trim() == "") {
		alert("Enter the billing Address to be update");
	} else {
		
		let uid = $("#puid2").val();
		let address = $("#pbillingAddress").val();
		let paymentfuncpages = "updatebillingaddress";
		
		let dataset = {uid, address, paymentfuncpages};
		console.log(dataset)
		
		$.ajax(
			{
				url: "/PAF-2022-Group-130/PaymentAPI",
				type: "PUT",
				data: JSON.stringify(dataset),
				dataType: "json",
				complete: function(response, status) {
					updateBillingAddress(response.responseText, status);
				}
			}
		)
	}
	
	function updateBillingAddress(responseText, status) {
		console.log(status);
		if(status == "success") {
			var result = JSON.parse(responseText);
			console.log(result);
		}
	}
	
})


 
 /**
 User
 */
 
 $(document).ready(( )=> {
	$("#login-error").hide();
})
 
 $(document).on("click", "#loginBtn", function(event) {
	let uname = document.getElementById("loginusername");
	let pass = document.getElementById("loginpassword");
	
	// check uname and pass is empty
	if(uname.value === "") {
		$("#login-error").text("Enter your username").show();
		uname.classList.add("pmf")
		uname.focus();
	} else if(pass.value === "") {
		uname.classList.remove("pmf")
		$("#login-error").text("Enter your password").show();
		pass.classList.add("pmf")
		pass.focus();
	} else {
		pass.classList.remove("pmf")
		$("#login-error").hide();
		
		var msg = null;
		var usertype = null;
		
		var data = $("#frm-login").serialize();
		
		// cred not empty run checking
		let username = uname.value;
		let password = pass.value;
		let method = "login";
		
		var credentials = { method, username, password };
		
		$.ajax (
			{
				url: "/PAF-2022-Group-130/UserAPI",
				type: "POST",
				data: data,
				dataType: "JSON",
				complete: function(data) {					
					
					
					msg = data.responseJSON[0].msg
					usertype = data.responseJSON[0].type
					console.log(data.responseJSON)

					if(msg == 1) {
						if(usertype == "User") {
							window.location.replace("pages/user/profile.jsp");
						} else if (usertype == "Manager") {
							window.location.replace("pages/user/UserDetailsView.jsp");
						}
						
					} else if(msg == 3) {
						$("#login-error").text("Your username or password incorrect").show();
					} else {
						$("#login-error").text("Unknown error while login").show();
					}
					
				}
			}
		)
		
	}
	
})
 
 
 /**
 Billing
 */
 
 /**
 Notice */
 // send the user id from profile to billbeforepay
$(document).on("click","#noticeID",function(event) {
	if($("#noticeUserid").val().trim() != "") {
	
		$("#noticeFormID").submit();
	
	} else {
		alert("Enter the User ID")
	}
		
});
 