package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import models.Notice;


@WebServlet("/NoticeAPI")
public class NoticeAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Notice notice = new Notice();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = notice.getallnoticedetails();
		System.out.println(output);
		response.getWriter().write(output);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//check page
		String page = request.getParameter("findpage");
		
		if(page.equals("noticeinsertpage")) {
			// insert details
			String noticeuid = request.getParameter("noticeuid");
			String noticeusername = request.getParameter("noticeusername");
			String noticedate = request.getParameter("noticedate");
			String noticetime = request.getParameter("noticetime");
			String noticetype = request.getParameter("noticetype");
			String noticemsg = request.getParameter("noticemsg");
			
			String output = notice.InsertNotice(noticeuid, noticeusername, noticedate, noticetime, noticetype, noticemsg);
			response.getWriter().write(output);
			
		} else if(page.equals("singlenoticedetails")) {
			// get details
			String UserID = request.getParameter("noticeUserid");
			String output = notice.noticeDetails(UserID);
			System.out.println(UserID);
			response.getWriter().write(output);
		}
	}


	// Converter
		private static String inputStreamToString(InputStream inputStream) {
		      Scanner scanner = new Scanner(inputStream, "UTF-8");
		      return scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
		  }
		
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Convert request body to json
				String jsonString = inputStreamToString(request.getInputStream());
				JSONObject json = new JSONObject(jsonString);
				
				//parameters
				int Id = Integer.parseInt(json.getString("Id"));
				String userID = json.getString("userID");
				String username = json.getString("username");
				String date = json.getString("date");
				String time = json.getString("time");
				String type = json.getString("type");
				String noticetext = json.getString("notice");
				
				String output = notice.updateNotice(Id, userID, username, date, time, type, noticetext);
				response.getWriter().write(output);
				
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Convert request body to json
		String jsonString = inputStreamToString(request.getInputStream());
		JSONObject json = new JSONObject(jsonString);
		
		//parameters
		int id = json.getInt("id");
		
		//clear history
	    System.out.println(id);
		String output = notice.deleteNotice(id);
		response.getWriter().write(output);
				
	}

}
