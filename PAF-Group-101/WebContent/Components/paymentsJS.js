// on load page and ready to view
$(document).ready(function() {

	$("#pmethoderror").hide();
	$("#pcardname").text("");
	$("#pcardnum").text("");
	$("#pcardcvv").text("");
	$("#pcardexpDate").text("");
	
	// hide saving loader
	$("#loaderBG").hide();
	$("#payment-accept").hide();
	
	$.ajax(
		{
			url:	"/PAF-2022-Group-130/PaymentAPI",
			type:	"POST",
			data:	$("#billBeforePay").serialize(),
			dataType:	"text",
			complete:	function(response, status) {
				if($("#puid").val().trim() != ""){
					//response for billbeforepayment page
					onComplete(response.responseText, status);
				} else {
					//response for paymentHistory page
					onHistoryLoaded(response.responseText, status);
				}
			}
		}
	)
	
	let totalpayable = 0;
	
	//function for get a bill details and user details
	function onComplete(response, status) {

		if(status == "success") {
				var result = JSON.parse(response);
				if(result.status.trim() == "success") {
					console.log(result)
					$("#pfd1name").attr("value", result.data.name).show();
					$("#pfd1accnumber").attr("value", result.data.accNumber).show();
					$("#pfd1billno").attr("value", result.data.billNumber).show();
					$("#pfd1address").attr("value", result.data.address).show();
					$("#pfd1nic").attr("value", result.data.nic).show();
					$("#pfd1email").attr("value", result.data.email).show();
					$("#pfd1phone").attr("value", result.data.phone).show();
					$("#pfd1sDate").attr("value", result.data.sDate).show();
					$("#pfd1eDate").attr("value", result.data.eDate).show();
					$("#pfd1units").attr("value", result.data.units).show();
					
					totalpayable = parseFloat(result.data.totalpayable);
					let units = parseInt(result.data.units);
					$("#pfd1totalpayable").attr("value", "Rs. "+ totalpayable.toFixed(2)).show();
					
					// calc ppu
					let ppu = totalpayable/units;
					
					$("#pfd1ppu").attr("value", "Rs. "+ppu.toFixed(2)).show();
				}
			
		} else if(status == "error") {
			alert("Error")
		} else {
			alert("Other")
		}
}

//function for get all histories
function onHistoryLoaded(response, status) {
	var result = JSON.parse(response)
	let tbody = document.getElementById('historyTbody');
	
	result.data.map((data)=> {
		console.log(data);
		let row = document.createElement('tr') ;
		
		let pid = document.createElement('td');
		pid.innerHTML = data.pid;
		let userid = document.createElement('td');
		userid.innerHTML = data.userid;
		let name = document.createElement('td');
		name.innerHTML = data.name;
		let accno = document.createElement('td');
		accno.innerHTML = data.accno;
		let address = document.createElement('td');
		address.innerHTML = data.billaddr;
		let email = document.createElement('td');
		email.innerHTML = data.email;
		let phone = document.createElement('td');
		phone.innerHTML = data.phone;
		let total = document.createElement('td');
		total.innerHTML = data.total;
		let cash = document.createElement('td');
		cash.innerHTML = data.cash;
		let balance = document.createElement('td');
		balance.innerHTML = data.balance;
		let status = document.createElement('td');
		status.innerHTML = data.status;
		let exp = document.createElement('td');
		exp.innerHTML = data.expDate;
		
		let pidval = data.pid;
		
		let deletebtn = document.createElement("input");
		deletebtn.value = "clear";
		deletebtn.classList.add("btn", "btn-clear", "m-2");
		deletebtn.type = "button";
		deletebtn.onclick = (e,pidval) => {
			clearHistory(data.pid);
		}
		
		row.appendChild(pid);
		row.appendChild(userid);
		row.appendChild(name);
		row.appendChild(accno);
		row.appendChild(address);
		row.appendChild(email);
		row.appendChild(phone);
		row.appendChild(total);
		row.appendChild(cash);
		row.appendChild(balance);
		row.appendChild(status);
		row.appendChild(exp);
		row.appendChild(deletebtn);
		
		tbody.appendChild(row);
		
		
	})
}

//function for button make a payment
$("#makepayment").click(function() {
	let pcardname = document.getElementById("pcardname");
	let pcardnum = document.getElementById("pcardnum");
	let pcardcvv = document.getElementById("pcardcvv");
	let pcardexpDate = document.getElementById("pcardexpDate");
	
	// payment method form validations
	if(pcardname.value === "") {
		$("#pmethoderror").text("Enter debit/credit card owner name").show();
		pcardname.classList.add("pmf")
		pcardname.focus();
	}
	else if(pcardnum.value === "") {
		pcardname.classList.remove("pmf")
		pcardnum.classList.add("pmf")
		pcardnum.focus();
		$("#pmethoderror").text("Enter debit/credit card number").show();
	}
	else if(pcardnum.value.length != 19) {
		pcardnum.focus();
		$("#pmethoderror").text("Enter card number with this pattern. (0000-0000-0000-0000)").show();
	}
	else if(pcardcvv.value === "") {
		pcardnum.classList.remove("pmf")
		pcardcvv.classList.add("pmf")
		pcardcvv.focus();
		$("#pmethoderror").text("Enter cvv number code").show();
	}
	else if(pcardcvv.value.length != 3) {
		pcardcvv.focus();
		$("#pmethoderror").text("Enter the cvv correctly. (***)").show();
	}
	else if(pcardexpDate.value === "") {
		pcardcvv.classList.remove("pmf")
		pcardexpDate.classList.add("pmf")
		pcardexpDate.focus();
		$("#pmethoderror").text("Enter expiration date of debit/credit card").show();
	}
	else if(pcardexpDate.value.length != 5) {
		pcardexpDate.focus();
		$("#pmethoderror").text("Enter expiration month/year").show();
	}else if(pcardexpDate.value.length === 5) {

			if(pcardexpDate.value.charAt(0) != "0" && pcardexpDate.value.charAt(0) != "1") {
				pcardexpDate.focus();
				$("#pmethoderror").text("Wrong month").show();
			}
			else{
				if(pcardexpDate.value.charAt(0) === "1" && pcardexpDate.value.charAt(1) > "2") {
					pcardexpDate.focus();
					$("#pmethoderror").text("Wrong month").show();
				}
				else{
					if(pcardexpDate.value.charAt(3) <"2" || pcardexpDate.value.charAt(4) <"2") {
						pcardexpDate.focus();
						$("#pmethoderror").text("Wrong year").show();
					}
					else {
						pcardexpDate.classList.remove("pmf")
						$("#pmethoderror").hide();
						// show saving loader
						$("#loaderBG").show();
						// get values by id
						let uid = $("#puid").val();
						let amount = totalpayable;
						let cardName = $("#pcardname").val();
						let cardNumber = $("#pcardnum").val();
						let cardCvv = $("#pcardcvv").val();
						let cardExpD = $("#pcardexpDate").val();
						let paymentfuncpages = "insertpaymentdetails";
						
						var dataset = { uid, amount, cardName, cardNumber, cardCvv, cardExpD, paymentfuncpages };
						
						$.ajax(
							{
								url:	"/PAF-2022-Group-130/PaymentAPI",
								type:	"POST",
								data:	dataset,
								dataType:	"JSON",
								complete:	function(response, status) {
									onInsertComplete(response.responseText, status);
								}
							}
						)
						
						function onInsertComplete(response, status) {
							if(status === "success") {
								var result = JSON.parse(response);
								console.log(result);
								if(result.status.trim() === "success") {
									setTimeout(() => {
										// hide saving loader after few seconds
										
										$("#loader").hide();
										$("#payment-accept").show();
									
									  // 👇️ hides element (still takes up space on page)
									  // box.style.visibility = 'hidden';
									}, 2000); // 👈️ time in milliseconds
									
									// redirecting to home page after payment success;
									setTimeout(() => {
										// hide saving loader after few seconds
										
										window.location = "../../";
									
									  // 👇️ hides element (still takes up space on page)
									  // box.style.visibility = 'hidden';
									}, 2500); // 👈️ time in milliseconds

								}
								
								else {
									// payment unsuccess
								}
							}
						}
					}
				}
			}
			
	}
	
})

})

// format card number while enter
function formats(ele, e) {
	if(ele.value.length<19){
      ele.value= ele.value.replace(/\W/gi, '').replace(/(.{4})/g, '$1-');
      return true;
    }else{
      return false;
    }
}

function numberValidation(e){
   e.target.value = e.target.value.replace(/[^\d\- ]/g,'');
   return false;
}

// format and validate cvv
function formatcvv(ele, e) {
	if(ele.value.length<3){
		return true;
	}else {
		return false;
	}
}

function numberOnlyCvv(e) {
	e.target.value = e.target.value.replace(/[^\d\ ]/g,'');
   return false;
}

// format and validate expiration date
function formatexpdate(ele, e) {
	if(ele.value.length<5){
      ele.value= ele.value.replace(/\W/gi, '').replace(/(.{2})/g, '$1/');
      return true;
    }else{
      return false;
    }
}

function mmyyformat(e) {
	e.target.value = e.target.value.replace(/[^\d\/ ]/g,'');
   return false;
}

//Clear payment history(delete function)
function clearHistory(pid) {
	let paymentfuncpages = "delete";
	
	let deleteThis = {pid, paymentfuncpages}
	
	console.log(deleteThis);
	
	$.ajax(
		{
			url:	"/PAF-2022-Group-130/PaymentAPI",
			type: "DELETE",
			dataType: "json",
			data: JSON.stringify(deleteThis),
			complete:	(response, status) => {
				deleteComplete(response.responseText, status);
			}
		}
	)
}

// delete completed
function deleteComplete(responseText, status) {
	if(status === "success") {
		var result = JSON.parse(responseText);
		console.log(result);
		if(result.status === "success") {
			alert(result.data);
			window.location.reload(true);
		} else {
			alert(result.data);
		}
	}
}

















	




