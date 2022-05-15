package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.User;

@Path("/user")
public class UserService {

	User userObj=new User();
	
	//Loging user
	
//		@GET
		@POST
		// for get methon add /{uid} last of path
		@Path("/login")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_HTML)
		// for get method use @PathParam instead of @FormParam
		public String getUserDetails(@FormParam("uname") String username,
									 @FormParam("pass") String password) {
			
			String output= userObj.login(username, password);
			
			return output;
		}
		
		//Get all users by admin
		@GET
		// for get methon add /{uid} last of path
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		// for get method use @PathParam instead of @FormParam
		public String getAllUserDetails() {
			
			String output= userObj.getalluserdetails();
			
			return output;
		}
		
		
		//ADD user details
		
		@POST
		@Path("/userDetails")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_HTML)
		// for get method use @PathParam instead of @FormParam
		public String addform(
				@FormParam("uid") String uid,
				@FormParam("name") String name,
				@FormParam("address") String address,
				@FormParam("accno") int accno,
				@FormParam("nic") String nic,
				@FormParam("email") String email,
				@FormParam("phone") String phone,
				@FormParam("type") String type,
				@FormParam("username") String username,
				@FormParam("password") String password) {
			
			if (uid.isEmpty() || name.isEmpty() || address.isEmpty() || (Integer.toString(accno)).isEmpty() ||
					nic.isEmpty() || email.isEmpty() || phone.isEmpty() || type.isEmpty() || username.isEmpty() || password.isEmpty()) {
				return "Please enter the all details";
			} else {
				return userObj.InsertUserDetails(name,address,accno,nic,email,phone,type,username,password);
			}
		}
		
		
		
		// update User Details
		
		@POST
		@Path("/updateuser")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_HTML)
		// for get method use @PathParam instead of @FormParam
    	public String updateUserDetails(
    			@FormParam("uid") int uid,
				@FormParam("name") String name,
				@FormParam("address") String address,
				@FormParam("accno") int accno,
				@FormParam("nic") String nic,
  		    	@FormParam("email") String email,
				@FormParam("phone") String phone,
				@FormParam("type") String type,
				@FormParam("username") String username,
				@FormParam("password") String password) {
			
			if (uid != 0 || name.isEmpty() || address.isEmpty() || (Integer.toString(accno)).isEmpty() ||
					nic.isEmpty() || email.isEmpty() || phone.isEmpty() || type.isEmpty() || username.isEmpty() || password.isEmpty()) {
			return "Update to the details";
		}  else {
				return userObj.UpdateUserDetails(uid,name,address,accno,nic,email,phone,type,username,password);
			}
    }

	
		// delete User Details
		@DELETE
		@Path("/deleteuser")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_HTML)
		// for get method use @PathParam instead of @FormParam
    	public String DeleteUserDetails(
    			@FormParam("uid") String uid) {
			
			if (uid.isEmpty() ) {
			return "Delete user details";
		}  else {
				return userObj.DeleteUserDetails(uid);
			}
		}			
}