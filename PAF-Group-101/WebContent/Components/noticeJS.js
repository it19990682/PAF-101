/**
 Notice
 */
 $(document).ready(function()
	{
	 $("#error").hide();

	 	 
	 $.ajax(
		{
			url: "/PAF-2022-Group-130/NoticeAPI",
			type: "POST",
			data: $("#sendNoticeID").serialize(),
			dataType: "text",
			complete: function(response,status)
			{
				if($("#findpage1").val()=="noticeUpdate"){
					LoadUpdatedetails(response.responseText,status)
				}else{
					onComplete(response.responseText,status)
				}
				
				
			}
		}
		
	)
	
		function onComplete(responseText,status){
			if(status == "success"){
				var result = JSON.parse(responseText)
				if(result.status == "success"){
					console.log(result.data)
					$("#usernoticeid").attr("value", result.data.userid).show();
					$("#noticeusername").attr("value", result.data.username).show();
					$("#noticedate").attr("value", result.data.date).show();
					$("#noticetime").attr("value", result.data.time).show();
					$("#noticetype").attr("value", result.data.type).show();
					$("#noticeid").attr("value", result.data.notice).show();
	  
				}
			}
		}
		
		// on load details to be update
		function LoadUpdatedetails(response,status){
			if(status == "success") {
				var result = JSON.parse(response);
				console.log(result)
				if(result.status == "success") {
					$("#nuid").attr("value", result.data.id).show();
					$("#nusername").attr("value", result.data.username).show();
					$("#nudate").attr("value", result.data.date).show();
					$("#nutime").attr("value", result.data.time).show();
					$("#nutype").attr("value", result.data.type).show();
					$("#nunotice").attr("value", result.data.notice).show();
				} else {
					alert(result.data);
				}
			}
		}

		
});

$(document).ready(function(){
	
	if($("#findpage").val() != "singlenoticedetails") {
		
	$.ajax(
		{
			url: "/PAF-2022-Group-130/NoticeAPI",
			type:	"GET",
			dataType:	"text",
			complete:	function(response,status){
				onNoticeLoaded(response.responseText,status);
			}
		}
	)
	
//function for get all notices details
function onNoticeLoaded(response, status) {
	
	var result = JSON.parse(response)
	let tbody = document.getElementById('user_table_notice');
	
	result.data.map((data)=> {
		let row = document.createElement('tr') ;
		row.className = "noticetr";
		
		let id = document.createElement('td');
		id.className = "noticetd";
		id.innerHTML = data.id;
		let userid = document.createElement('td');
		userid.className = "noticetd";
		userid.innerHTML = data.userid;
		let username = document.createElement('td');
		username.className = "noticetd";
		username.innerHTML = data.username;
		let date = document.createElement('td');
		date.className = "noticetd";
		date.innerHTML = data.date;
		let time = document.createElement('td');
		time.className = "noticetd";
		time.innerHTML = data.time;
		let type = document.createElement('td');
		type.className = "noticetd";
		type.innerHTML = data.type;
		let notice = document.createElement('td');
		notice.className = "noticetd";
		notice.innerHTML = data.notice;
		
		let form = document.createElement("form");
			form.action = "NoticeUpdate.jsp";
			form.method = "POST";
			form.id = "updateform";

					
			//update Button
			let updateBtn = document.createElement("input");
			updateBtn.classList.add("btn","btn-success","mb-3");
			updateBtn.value = "UPDATE";
			updateBtn.type = "button";
			updateBtn.onclick =(e)=>{
				senduidForUpdate(data.userid);
			}
			
			//Delete Button
			let deleteBtn = document.createElement("input");
			deleteBtn.classList.add("btn","btn-danger");
			deleteBtn.value = "DELETE";
			deleteBtn.type = "button";
			deleteBtn.onclick =(e)=>{
				DeleteRow(data.id);
			}
			
			
			//form.appendChild(uidValue);
			form.appendChild(updateBtn);
			
			let updateForm = document.createElement("td");
			updateForm.className = "noticetd";
			updateForm.appendChild(form);
			updateForm.appendChild(deleteBtn);

		
			row.appendChild(id);	
			row.appendChild(userid);
			row.appendChild(username);
			row.appendChild(date);
			row.appendChild(time);
			row.appendChild(type);
			row.appendChild(notice);
			row.appendChild(updateForm);
	
			
			
			tbody.appendChild(row);
		
		
	})
}	
	}

})
	
//send uid for update
function senduidForUpdate(userID){
	
	//send uid for update
	let uidValue = document.createElement("input");
	uidValue.hidden = true;
	uidValue.id = "uidvalue"
	uidValue.name = "uidtobeupdate"
	uidValue.value = userID;
	
	let form = document.getElementById("updateform");
	
	form.appendChild(uidValue)
	
	$("#updateform").submit();
}

	
//delete user function
function DeleteRow(id){
	var deleteData = {id};
	console.log(deleteData)
	$.ajax(
		{
			url:	"/PAF-2022-Group-130/NoticeAPI",
			type:	"DELETE",
			data:	JSON.stringify(deleteData),
			dataType:	"json",
			complete:	function(response,status){
				OnDelete(response.responseText,status);
			}
		}
			
	)
	function OnDelete(responseText,status){
		
		
			alert ("successfull deleted!!!")
			window.location.reload();
	
	}
}


// update
$(document).ready( ()=> {
	
	$("#btnnoticeUpdate").click(function() {
		let Id = $("#nuid").val();
		let userID = $("#nuuid").val();
		let username = $("#nusername").val();
		let date = $("#nudate").val();
		let time = $("#nutime").val();
		let type = $("#nutype").val();
		let notice = $("#nunotice").val();
		
		var updateSet = { Id, userID, username, date, time, type, notice }
		
		$.ajax(
			{
				url: "/PAF-2022-Group-130/NoticeAPI",
				type:	"PUT",
				data:	JSON.stringify(updateSet),
				dataType: "json",
				complete: function(response, status) {
					onUpdateComplete(response.responseText, status);
				}
			}
		)
		
		// when updated
		function onUpdateComplete(responseText, status) {
			if(status == "success") {
				var result = JSON.parse(responseText);
				
				if(result.status == "success") {
					alert(result.data)
					window.location.reload();
				} else {
					alert(result.data)
				}
			}
		}
		
	})

})

$(document).ready(() => {
	
	//function for button make a payment
$("#noticebtnSave").click(function() {
	let noticeuid = document.getElementById("noticeuid");
	let noticeusername = document.getElementById("noticeusername");
	let noticedate = document.getElementById("noticedate");
	let noticetime = document.getElementById("noticetime");
	let noticetype = document.getElementById("noticetype");
	let noticemsg = document.getElementById("noticemsg");
	
	$("#error").show()
	
	// payment method form validations
	if(noticeuid.value === "") {
		$("#error").text("Enter User ID").show();
		noticeuid.classList.add("pmf")
		noticeuid.focus();
	}
	else if(noticeusername.value === "") {
		noticeuid.classList.remove("pmf")
		noticeusername.classList.add("pmf")
		noticeusername.focus();
		$("#error").text("Enter User Name").show();
	}
	else if(noticedate.value === "") {
		noticeusername.classList.remove("pmf")
		noticedate.classList.add("pmf")
		noticedate.focus();
		$("#error").text("Enter Date").show();
	}
	else if(noticetime.value === "") {
		noticedate.classList.remove("pmf")
		noticetime.classList.add("pmf")
		noticetime.focus();
		$("#error").text("Enter Time").show();
	}
	else if(noticetype.value === "") {
		noticetype.classList.add("pmf")
		noticetype.focus();
		$("#error").text("Enter Notice Type").show();
	}
	else if(noticemsg.value === "") {
		noticetype.classList.remove("pmf")
		noticemsg.classList.add("pmf")
		noticemsg.focus();
		$("#error").text("Enter Notice Description").show();
	} else {
		// no errors
		noticemsg.classList.remove("pmf")
		$("#error").hide();
		
		// data inserting
		$.ajax(
			{
				url: "/PAF-2022-Group-130/NoticeAPI",
				type: "POST",
				data: $("#insertNotices").serialize(),
				dataType: "text",
				complete: function(response, status) {
					onInsertComplete(response.responseText, status);
				}
			}
		)
		
		// when insert request complete
		function onInsertComplete(responseText, status) {
			if(status == "success") {
				var result = JSON.parse(responseText);
				if (result.status == "success") {
					alert(result.data);
					window.location = "../../";
				} else {
					alert(result.data);
				}
			}
		}
		
	}
	
})
	
})
	
	
	
	
	
