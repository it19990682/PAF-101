package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.Notice;

@Path("/notice")
public class NoticeService {
	Notice notices=new Notice();
	

	// Get notice details
//	@GET 
	@POST
	// for get methon add /{uid} last of path
	@Path("/noticeDetails")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	// for get method use @PathParam instead of @FormParam
	public String getNoticeDetails(@FormParam("uid") String uid) {
		
		String output=notices.noticeDetails(uid);
		return output;
	}
	
	// add notice
		@POST
		@Path("/addNotice")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_HTML)
		// for get method use @PathParam instead of @FormParam
		public String AddNotice(
				@FormParam("uid") String uid,
				@FormParam("username") String username,
				@FormParam("date") String date,
				@FormParam("time") String time,
				@FormParam("type") String type,
				@FormParam("notice") String notice) {
			
			if (uid.isEmpty() || username.isEmpty() || date.isEmpty() || time.isEmpty() || type.isEmpty() || notice.isEmpty()) {
				return "Enter all required details.";
			} else {
				return notices.InsertNotice(uid,username,date,time,type,notice);
			}
		}
		
		// update notice details
		@POST
		@Path("/updatenotice")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_HTML)
		// for get method use @PathParam instead of @FormParam
		public String updatenotices(
				@FormParam("id") int id,
				@FormParam("userID") String userid,
				@FormParam("username") String username,
				@FormParam("date") String date,
				@FormParam("time") String time,
				@FormParam("type") String type,
				@FormParam("notice") String notice) {
			
			
			if (Integer.toString(id).isEmpty() || userid.isEmpty() || username.isEmpty() || date.isEmpty() || time.isEmpty() || type.isEmpty() || notice.isEmpty()) {
				return "Update your Details.";
			} else {
				return notices.updateNotice(id,userid,username,date,time,type,notice);
			}
		}
		
		// delete notice details
				@POST
				@Path("/deletenotice")
				@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
				@Produces(MediaType.TEXT_HTML)
				// for get method use @PathParam instead of @FormParam
				public String updatenotices(
						@FormParam("id") int id) {
					
					
					if (Integer.toString(id).isEmpty()) {
						return "Delete your Details.";
					} else {
						return notices.deleteNotice(id);
					}
				}
	
				
				// delete notice details
				@GET
				@Path("/noticetable")
				@Produces(MediaType.TEXT_HTML)
				// for get method use @PathParam instead of @FormParam
				public String updatenotices() {
					
					return notices.getallnoticedetails();
				}
		
}
