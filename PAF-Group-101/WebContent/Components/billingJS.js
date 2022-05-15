/**  */
 $(document).ready(function()
	{
	 $("#alertSuccess").hide();
	 $("#alertError").hide();
	 
	 // viewing single bill for update
	 if($("#page").val() === "viewsinglebill") {
		
			$.ajax(
			{
				url: "/PAF-2022-Group-130/BillingAPI",
				type: "POST",
				data: $("#sendBillID").serialize(),
				dataType: "text",
				complete: function(response,status)
				{
					if(status == "success") {
						var result = JSON.parse(response.responseText);
						if(result.status == "success") {
							console.log(result.data)
							billinguserid.value = result.data.uid;
							billingname.value = result.data.name;
							billingsdate.value = result.data.sdate;
							billingedate.value = result.data.edate;
							billingaccno.value = result.data.accno;
							billingbillno.value = result.data.billnum;
							billingunit.value = result.data.units;
							billingamount.value = result.data.billamount;
						}
					}
				}
			}
			
		)		
	}
	
		
$(document).ready(function(){
	
	 if($("#page").val() === "viewallbills") {
		
		$.ajax(
			{
				url: "/PAF-2022-Group-130/BillingAPI",
				type:	"GET",
				dataType:	"text",
				complete:	function(response,status){
					onBillingLoaded(response.responseText,status);
				}
			}
		)
		
	}
	
		//function for get all billing details
function onBillingLoaded(response, status) {
	var result = JSON.parse(response)
	let tbody = document.getElementById('user_table_billing');
	
	result.data.map((data)=> {
		console.log(data);
		let row = document.createElement('tr') ;
		row.className = "billingtr";
		
		
		let userid = document.createElement('td');
		userid.className = "billingtd";
		userid.innerHTML = data.userid;
		let name = document.createElement('td');
		name.className = "billingtd";
		name.innerHTML = data.name;
		let startdate = document.createElement('td');
		startdate.className = "billingtd";
		startdate.innerHTML = data.startdate;
		let enddate = document.createElement('td');
		enddate.className = "billingtd";
		enddate.innerHTML = data.enddate;
		let accno = document.createElement('td');
		accno.className = "billingtd";
		accno.innerHTML = data.accno;
		let billno = document.createElement('td');
		billno.className = "billingtd";
		billno.innerHTML = data.billno;
		let noofunits = document.createElement('td');
		noofunits.className = "billingtd";
		noofunits.innerHTML = data.noofunits;
		let billamount = document.createElement('td');
		billamount.className = "billingtd";
		billamount.innerHTML = data.billamount;
		
		let form = document.createElement("form");
			form.action = "billingupdate.jsp";
			form.method = "POST";
			form.id = "updateform";

					
			//update Button
			let updateBtn = document.createElement("input");
			updateBtn.classList.add("btn","btn-success","mb-3");
			updateBtn.value = "UPDATE";
			updateBtn.type = "button";
			updateBtn.onclick =(e)=>{
				senduidForUpdate(data.accno);
			}
			
			//Delete Button
			let deleteBtn = document.createElement("input");
			deleteBtn.classList.add("btn","btn-danger");
			deleteBtn.value = "DELETE";
			deleteBtn.type = "button";
			deleteBtn.onclick =(e)=>{
				DeleteRow(data.accno);
			}
			
			
			//form.appendChild(uidValue);
			form.appendChild(updateBtn);
			
			let updateForm = document.createElement("td");
			updateForm.className = "billingtd";
			updateForm.appendChild(form);
			updateForm.appendChild(deleteBtn);

			
		
		row.appendChild(userid);	
		row.appendChild(name);
		row.appendChild(startdate);
		row.appendChild(enddate);
		row.appendChild(accno);
		row.appendChild(billno);
		row.appendChild(noofunits);
		row.appendChild(billamount);
		row.appendChild(updateForm);
		
		
		
		tbody.appendChild(row);
		
		
	})
}
		
});
	
//send uid for update
function senduidForUpdate(accNo){
	
	//send uid for update
	let uidValue = document.createElement("input");
	uidValue.hidden = true;
	uidValue.id = "uidvalue"
	uidValue.name = "accnotobeupdate"
	uidValue.value = accNo;
	
	let form = document.getElementById("updateform");
	
	form.appendChild(uidValue)

	$("#updateform").submit();
}

	
//delete user function
function DeleteRow(accno){
	var deleteData = {accno};
	console.log(deleteData)
	$.ajax(
		{
			url:	"/PAF-2022-Group-130/BillingAPI",
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
	
	$("#btnUpdate").click(function() {
		let name = $("#billingname").val();
		let sdate = $("#billingsdate").val();
		let edate = $("#billingedate").val();
		let accno = $("#billingaccno").val();
		let billunit = $("#billingunit").val();
		let billamount = $("#billingamount").val();
		
		var updateSet = {  name, sdate, edate, accno, billunit, billamount }
		
		$.ajax(
			{
				url: "/PAF-2022-Group-130/BillingAPI",
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
			console.log(responseText)
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

$("#billinserterror").hide();

//function for button make a payment
$("#billingbtnSave").click(function() {
	
	let billinguid = document.getElementById("billinguid");
	let billingusername = document.getElementById("billingusername");
	let billingstartdate = document.getElementById("billingstartdate");
	let billingenddate = document.getElementById("billingenddate");
	let billingaccountnumber = document.getElementById("billingaccountnumber");
	let billno = document.getElementById("billno");
	let billingunit = document.getElementById("billingunit");
	let billingamount = document.getElementById("billingamount");
	
	// payment method form validations
	if(billinguid.value === "") {
		$("#billinserterror").text("Enter User ID").show();
		billinguid.classList.add("pmf")
		billinguid.focus();
	}
	else if(billingusername.value === "") {
		billinguid.classList.remove("pmf")
		billingusername.classList.add("pmf")
		billingusername.focus();
		$("#billinserterror").text("Enter User Name").show();
	}
	else if(billingstartdate.value === "") {
		billingusername.classList.remove("pmf")
		billingstartdate.classList.add("pmf")
		billingstartdate.focus();
		$("#billinserterror").text("Enter Start Date").show();
	}
	else if(billingenddate.value === "") {
		billingstartdate.classList.remove("pmf")
		billingenddate.classList.add("pmf")
		billingenddate.focus();
		$("#billinserterror").text("Enter End Date").show();
	}
	else if(billingaccountnumber.value === "") {
		billingenddate.classList.remove("pmf")
		billingaccountnumber.classList.add("pmf")
		billingaccountnumber.focus();
		$("#billinserterror").text("Enter Account number").show();
	}
	else if(billno.value === "") {
		billingaccountnumber.classList.remove("pmf")
		billno.classList.add("pmf")
		billno.focus();
		$("#billinserterror").text("Enter Bill Number").show();
	}
	else if(billingunit.value === "") {
		billno.classList.remove("pmf")
		billingunit.classList.add("pmf")
		billingunit.focus();
		$("#billinserterror").text("Enter units").show();
	}
	
	else if(billingamount.value === "") {
		billingunit.classList.remove("pmf")
		billingamount.classList.add("pmf")
		billingamount.focus();
		$("#billinserterror").text("Enter Amount").show();
	}
	else {

		// no errors
		billingamount.classList.remove("pmf")
		$("#billinserterror").hide();
		
		// data inserting
		$.ajax(
			{
				url: "/PAF-2022-Group-130/BillingAPI",
				type: "POST",
				data: $("#insertBills").serialize(),
				dataType: "text",
				complete: function(response, status) {
					onInsertComplete(response.responseText, status);
				}
			}
		)
		
		// when insert request complete
		function onInsertComplete(responseText, status) {
			console.log(responseText)
			if(status == "success") {
				var result = JSON.parse(responseText);
				if (result.status == "success") {
					alert(result.data);
					window.location = "../user/UserDetailsView.jsp";
				} else {
					alert(result.data);
				}
			}
		}	
			
	}
	
})
	
	
})	
	
	
