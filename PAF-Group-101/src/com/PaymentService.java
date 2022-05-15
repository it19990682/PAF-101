package com;

//For REST service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;  


//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

import models.Payment;

@Path("/payment")
public class PaymentService {
	Payment paymentObj = new Payment();
	
	// Get customer detailed bill before confirmation of customer paying amount
//	@GET
	@POST
	// for get methon add /{uid} last of path
	@Path("/billbeforepay")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	// for get method use @PathParam instead of @FormParam
	public String getCustomerDetails(@FormParam("uid") String uid) {
		String output = paymentObj.showPaymentCusDetails(uid);
		return output;
	}
	
	// confirm the customer paying amount
	@POST
	@Path("/paybill")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	// for get method use @PathParam instead of @FormParam
	public String confirmBill(
			@FormParam("uid") String uid,
			@FormParam("amount") String amount) {
		
//		if (uid.isEmpty()) {
//			return "Forgot to add uid to button";
//		} else if (amount.isEmpty()) {
//			return "Please Enter the amount to be you pay!";
//		} else if ((Double.parseDouble(amount)) <= 0) {
//			return "Please pay the valid amount";
//		} else {
			return paymentObj.payTheBill(uid,amount);
//		}
	}
	
	// Insert payment card details to database
	@POST
	@Path("/insertCard")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	// for get method use @PathParam instead of @FormParam
	public String insertCard(
			@FormParam("uid") String uid,
			@FormParam("pid") String pid,
			@FormParam("owner") String owner,
			@FormParam("cardNumber") String cardNumber,
			@FormParam("cvv") String cvv,
			@FormParam("expDate") String expDate) {
		
			return paymentObj.insertCardDetails(uid,pid, owner, cardNumber, cvv, expDate);
		
	}
	
	// update the payment billing address on payment
	@PUT
	@Path("/updateAddress")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	// for get method use @PathParam instead of @FormParam
	public String updatePaymentAddress(@FormParam("uid") String uid,
			@FormParam("address") String address) {
		
		if (uid.isEmpty()) {
			return "Forgot to add uid";
		} else if (address.isEmpty()) {
			return "Please Enter the new address";
		} else {
			return paymentObj.updatePaymentAddress(uid,address);
		}
	}
	
	// Delete the payment history
	@DELETE
	@Path("/deleteHistory")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	// for get method use @PathParam instead of @FormParam
	public String deletePaymentHistory(
			@FormParam("pid") String pid) {
		
		if (pid.isEmpty()) {
			return "Forgot to add payment id";
		} else {
			return paymentObj.deletePayHistory(pid);
		}
	}
	
	// Get payment history
//	@GET
	@POST
	// for get methon add /{uid} last of path
	@Path("/showHistory")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	// for get method use @PathParam instead of @FormParam
	public String getPaymentHistory(@FormParam("uid") String uid) {
		String output = paymentObj.showPaymentHistory(uid);
		return output;
	}
}



























