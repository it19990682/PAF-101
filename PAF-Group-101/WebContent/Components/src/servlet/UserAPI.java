package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import models.User;

/**
 * Servlet implementation class UserAPI
 */
@WebServlet("/UserAPI")
public class UserAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   User cus = new User();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = cus.getalluserdetails();
		response.getWriter().write(output);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//get page
		String method = request.getParameter("method");
		
		// Login
		if(method.equals("login")) {
			
			PrintWriter out = response.getWriter();
			
			// get session para
			HttpSession session = request.getSession();
			String output = "";
			JSONArray list = new JSONArray();
			
			//get credentials
			String username = request.getParameter("loginusername");
			String password = request.getParameter("loginpassword");
			
			String authStatus = cus.login(username, password);
			
			// login success and unsuccess
			JSONObject json = new JSONObject(authStatus);
			String status = json.getString("status");
			String UID  = json.getString("data");
			String tp1  = json.getString("type");
			
			//System.out.println(UID);
			
			JSONObject obj = new JSONObject(authStatus);
			
			String msg;
			String type;
			
			// if credentials success
			if(status.equals("success")) {
				
				session.setAttribute("loginID", UID);
				msg = "1";
				type = tp1;
				obj.put("msg", msg);
				obj.put("type", type);
				list.put(obj);
				out.println(list.toString());
				out.flush();
				
			} else {
				// if credentials not success
				msg = "3";
				obj.put("msg", msg);
				list.put(obj);
				out.println(list.toString());
				out.flush();
			}
			
		}
		
		// clicked button delete
		else if (method.equals("delete")) {
			String userId = request.getParameter("uid");
			String output = cus.DeleteUserDetails(userId);
			System.out.println(userId);
			response.getWriter().write(output);
		}
		
		
		// clicked insert button
		else if(method.equals("insert")) {
			String name = request.getParameter("cusname");
			String address = request.getParameter("cusaddress");
			String accno = request.getParameter("cusaccno");
			String nic = request.getParameter("cusnic");
			String email = request.getParameter("cusemail");
			String phone = request.getParameter("cusphone");
			String type = request.getParameter("custype");
			String uname = request.getParameter("cususername");
			String pass = request.getParameter("cuspassword");
			
			int AccNo = Integer.parseInt(accno);
			
			String output = cus.InsertUserDetails(name, address, AccNo, nic, email, phone, type, uname, pass);
			response.getWriter().write(output);
		}
		
		//single user details
		else if(method.equals("singlecusdetails")) {
			
			//get user id to view
			int uid = Integer.parseInt(request.getParameter("uidtobeupdate"));
			
			String output = cus.singleUserDetails(uid);
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
		
		int uid = Integer.parseInt(json.getString("uid"));
		String name = json.getString("name");
		String address = json.getString("addr");
		int accno = Integer.parseInt(json.getString("accno"));
		String nic = json.getString("nic");
		String email = json.getString("email");
		String phone = json.getString("phone");
		String type = json.getString("type");
		String uname = json.getString("uname");
		String pass = json.getString("pass");
		
		String output = cus.UpdateUserDetails(uid, name, address, accno, nic, email, phone, type, uname, pass);
		System.out.println(output);
		response.getWriter().write(output);
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
