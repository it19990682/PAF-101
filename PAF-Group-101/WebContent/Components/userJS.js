//Get All user Details
$(document).ready(function(){

	$("#signuperror").hide();
	
	// load on UserDetailsView.jsp page only
		if($("#pageselector").val().trim() === "userdetailspage") {
			$.ajax(
			{
				url:	"/PAF-2022-Group-130/UserAPI",
				type:	"GET",
				dataType:	"text",
				complete:	function(response,status){
					OnComplete(response.responseText,status);
				}
			}
		)	
	}
	// view oncomplete
	function OnComplete(response,status) { 
		if(status == "success"){
			var result = JSON.parse(response);
			if(result.status.trim()== "success"){
				console.log (result)
				let tBody = document.getElementById("user_table_nikela");
				
				result.data.map((data)=>{
					let row = document.createElement("tr");
					row.className = "usertr";
					
					let uid = document.createElement("td");
					uid.className = "usertd";
					uid.innerHTML = data.uid;
					let name = document.createElement("td");
					name.className = "usertd";
					name.innerHTML = data.name;
					let address = document.createElement("td");
					address.className = "usertd";
					address.innerHTML = data.address;
					let accno = document.createElement("td");
					accno.className = "usertd";
					accno.innerHTML = data.accno;
					let nic = document.createElement("td");
					nic.className = "usertd";
					nic.innerHTML = data.nic;
					let email = document.createElement("td");
					email.className = "usertd";
					email.innerHTML = data.email;
					let phone = document.createElement("td");
					phone.className = "usertd";
					phone.innerHTML = data.phone;
					let type = document.createElement("td");
					type.className = "usertd";
					type.innerHTML = data.type;
					let username = document.createElement("td");
					username.className = "usertd";
					username.innerHTML = data.username;
					let password = document.createElement("td");
					password.className = "usertd";
					password.innerHTML = data.password;
					
					
					let form = document.createElement("form");
					form.action = "userUpdate.jsp";
					form.method = "POST";
					form.id = "updateform";
	
					//update Button
					let updateBtn = document.createElement("input");
					updateBtn.classList.add("btn","btn-success","mb-3");
					updateBtn.value = "UPDATE";
					updateBtn.type = "button";
					updateBtn.onclick =(e)=>{
						senduidForUpdate(data.uid);
					}
					
					//Delete Button
					let deleteBtn = document.createElement("input");
					deleteBtn.classList.add("btn","btn-danger");
					deleteBtn.value = "DELETE";
					deleteBtn.type = "button";
					deleteBtn.onclick =(e)=>{
						DeleteRow(data.uid);
					}
					
					form.appendChild(updateBtn);
					
					let updateForm = document.createElement("td");
					updateForm.className = "usertd";
					updateForm.appendChild(form);
					updateForm.appendChild(deleteBtn);
					
					
					row.appendChild(uid);
					row.appendChild(name);
					row.appendChild(address);
					row.appendChild(accno);
					row.appendChild(nic);
					row.appendChild(email);
					row.appendChild(phone);
					row.appendChild(type);
					row.appendChild(username);
					row.appendChild(password);
					row.appendChild(updateForm);
					
					tBody.appendChild(row);
				})
			}
			//Display error message
			else{
				alert(result.data)
			}
		}
	}
	
})

//send uid for update
function senduidForUpdate(uid){
	
	//send uid for update
	let uidValue = document.createElement("input");
	uidValue.hidden = true;
	uidValue.name = "userID";
	uidValue.value = uid;
	
	let form = document.getElementById("updateform");
	
	//console.log(form)
	
	form.appendChild(uidValue);
	
	//$("#updateform").submit();
	
	window.location.href = "userUpdate.jsp?uid="+uid;
	
}

//delete user function
function DeleteRow(uid){
	
	let method = "delete";
	
	var deleteData = { uid, method};
	
	
	
	console.log(deleteData)
	$.ajax(
		{
			url:	"/PAF-2022-Group-130/UserAPI",
			type:	"POST",
			data:	deleteData,
			dataType:	"json",
			complete:	function(response,status){
				OnDelete(response.responseText,status);
			}
		}
			
	)
	function OnDelete(responseText,status){
		var result = JSON.parse(responseText);
		console.log(result)
		if(result.status === "success") {
			console.log(status)
			alert ("successfull deleted!!!")
			window.location.reload();	
		} else {
			alert (result.data)
			window.location.reload();
		}
	
	}
}	

$(document).ready(() => {
	
	//function for button Create Account
	$("#createaccount").click(function() {
		let username = document.getElementById("cusname");
		let useraddress = document.getElementById("cusaddress");
		let useraccno = document.getElementById("cusaccno");
		let usernic = document.getElementById("cusnic");
		let useremail = document.getElementById("cusemail");
		let userphone = document.getElementById("cusphone");
		let usertype = document.getElementById("custype");
		let Uusername = document.getElementById("cususername");
		let Upassword = document.getElementById("cuspassword");
		
		
		// Signup form validations
		if(username.value === "") {
			$("#signupnameerror").text("Enter Your Name").show();
			username.classList.add("pmf")
			username.focus();
		}
		else if(useraddress.value === "") {
			$("#signupnameerror").hide();
			username.classList.remove("pmf")
			useraddress.classList.add("pmf")
			useraddress.focus();
			$("#signupaddresserror").text("Enter Your Address").show();
		}
	    else if(useraccno.value === "") {
		$("#signupaddresserror").hide();
			useraddress.classList.remove("pmf")
			useraccno.classList.add("pmf")
			useraccno.focus();
			$("#signupaccnoerror").text("Enter Your AccountNumber").show();
		}
		else if(usernic.value === "") {
			$("#signupaccnoerror").hide();
			useraccno.classList.remove("pmf")
			usernic.classList.add("pmf")
			usernic.focus();
			$("#signupnicerror").text("Enter Your NIC number").show();
		}
		else if(useremail.value === "") {
			$("#signupnicerror").hide();
			usernic.classList.remove("pmf")
			useremail.classList.add("pmf")
			useremail.focus();
			$("#signupemailerror").text("Enter Your Email address").show();
		}
		else if(userphone.value === "") {
			$("#signupemailerror").hide();
			useremail.classList.remove("pmf")
			userphone.classList.add("pmf")
			userphone.focus();
			$("#signuppnumerror").text("Enter Your Phone Number").show();
		}
		else if (usertype.value === "") {
			$("#signuppnumerror").hide();
			userphone.classList.remove("pmf")
			usertype.classList.add("pmf")
			usertype.focus();
			$("#signuptypeerror").text("Select user typer").show();
		}
		else if(Uusername.value === "") {
			$("#signuptypeerror").hide();
			usertype.classList.remove("pmf")
			Uusername.classList.add("pmf")
			Uusername.focus();
			$("#signupunameerror").text("Enter Your Username").show();
		}
		else if(Upassword.value === "") {
			$("#signupunameerror").hide();
			Uusername.classList.remove("pmf")
			Upassword.classList.add("pmf")
			Upassword.focus();
			$("#signuppasserror").text("Enter Your Password").show();
		} else {
			$("#signuppasserror").hide();
			
			// send data to servlet
			$.ajax(
				{
					url:	"/PAF-2022-Group-130/UserAPI",
					type:	"POST",
					dataType:	"text",
					data:	$("#insertUserDetailsForm").serialize(),
					complete:	(response, status) => {
						onInsertComplete(response.responseText, status);
					}
				}
			)
		}
		
	 })
})

// if insert successful
function onInsertComplete(responseText, status) {
	if(status == "success") {
		var result = JSON.parse(responseText);
		if (result.status === "success") {
			alert("You are registered customer now. Use login page.");
			window.location.replace("/PAF-2022-Group-130/");
		} else {
			alert(result.data);
		}
	}
}

$(document).ready(() => {
	
	if($("#method").val() === "singlecusdetails") {
		
		let method = $("#method").val();
		let uidtobeupdate = $("#uidtobeupdate").val();
		
		var dataset = { method, uidtobeupdate };
		
		$.ajax(
			{
				url:	"/PAF-2022-Group-130/UserAPI",
				type:	"POST",
				data: dataset,
				dataType:	"json",
				complete:	function(response,status){
					onSingleUserLoaded(response.responseText,status);
				}
			}
		)
		
		function onSingleUserLoaded(responseText, status) {
			if(status === "success") {
				var result = JSON.parse(responseText);
				
				if(result.status === "success") {
					console.log(result)
					userID.value = result.data.uid;
					cusname.value = result.data.name;
					cusaddr.value = result.data.address;
					cusaccno.value = result.data.accno;
					cusnic.value = result.data.nic;
					cusemail.value = result.data.email;
					cuspno.value = result.data.phone;
					custype.value = result.data.type;
					cusuname.value = result.data.uname;
					cuspass.value = result.data.pass;
				}
			}
		}
		
	}
	
	$("#updateCusBtn").click(function() {
		let uid = userID.value;
		let name = cusname.value;
		let addr = cusaddr.value;
		let accno = cusaccno.value;
		let nic = cusnic.value;
		let email = cusemail.value;
		let phone = cuspno.value;
		let type = custype.value;
		let uname = cusuname.value;
		let pass = cuspass.value;
		
		var updateset = { uid,name, addr, accno, nic, email, phone, type, uname, pass };
		
		$.ajax(
			{
				url: "/PAF-2022-Group-130/UserAPI",
				type:	"PUT",
				data:	JSON.stringify(updateset),
				dataType: "json",
				complete: function(response, status) {
					onUserUpdateComplete(response.responseText, status);
				}
			}
		)
		
		// when updated
		function onUserUpdateComplete(responseText, status) {
			if(status == "success") {
				var result = JSON.parse(responseText);
				
				if(result.status == "success") {
					alert(result.data)
					window.location.reload();
				} else {
					alert(result.data)
					window.location.reload();
				}
			}
		}
		
	})
	
})




















