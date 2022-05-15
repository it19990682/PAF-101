package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.Billing;

@Path("/billing")
public class BillingService {

	Billing billingObj=new Billing();
	// insert bill details
//	@GET
	@POST
	// for get method add /{uid} last of path
	@Path("/insertbill")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	// for get method use @PathParam instead of @FormParam
	public String insertBillDetails(@FormParam("uid") String uid,
								 @FormParam("name") String name, 
								 @FormParam("sdate") String sdate,
								 @FormParam("edate") String edate,
								 @FormParam("acconumber") String acconumber ,
								 @FormParam("bilnumber") String bilnumber,
								 @FormParam("unit") String unit,
								 @FormParam("amount") Float amount ) {
		
		String output= billingObj.InsertBillingDetails(uid, name, sdate, edate, acconumber, bilnumber, unit, amount );
		
		return output;
	}
	
	
	// update Billing Details
	
	@POST
	@Path("/updatebilling")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	// for get method use @PathParam instead of @FormParam
	public String updateBillingDetails(
			@FormParam("uid") String uid,
			@FormParam("name") String name,
			@FormParam("sdate") String sdate,
			@FormParam("edate") String edate,
			@FormParam("acconumber") String acconumber,
		    @FormParam("bilnumber") String bilnumber,
			@FormParam("unit") String unit,
			@FormParam("amount") Float amount) {
		
		if (uid.isEmpty() || name.isEmpty() || sdate.isEmpty() ||edate.isEmpty() || acconumber.isEmpty() ||
				bilnumber.isEmpty() || unit.isEmpty() || (Float.toString(amount)).isEmpty()) {
		return "Update to the details";
	}  else {
			return billingObj.UpdateBillingDetails(uid,name,sdate,edate,acconumber,bilnumber,unit,amount);
		}
}
	
	
	// Delete Billing Details.....................................
	
		@POST
		@Path("/deletebilling")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_HTML)
		// for get method use @PathParam instead of @FormParam
		public String deleteBillingDetails(
				@FormParam("acconumber") String acconumber) 
		{
			
			if ( acconumber.isEmpty() ) {
			return "Delete to the details";
		}  else {
				return billingObj.DeleteBillingDetails(acconumber);
			}
	}
	
		
		// Get billing details
		// @GET
		@POST
		// for get methon add /{uid} last of path
		@Path("/billingDetails")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_HTML)
		// for get method use @PathParam instead of @FormParam
		public String getBillingDetails(@FormParam("acconumber") String acconumber) {

		String output=billingObj.billingDetails(acconumber);
		return output;
		}	
		
}
