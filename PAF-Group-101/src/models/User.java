package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import database.DBConnect;

public class User {
	
//login
	public String login(String username,String password) {
		String output="";
		
		
		try {
			//check database connection
			Connection con=DBConnect.connect();
			if(con==null) {return "Database not connected";}
			
			//validate username and password
			Statement stmt=con.createStatement();
			String sql="SELECT * FROM customer WHERE Username='"+username+"' AND Password='"+password+"'";
			ResultSet rs=stmt.executeQuery(sql);
			
			String UID=null;
			String name=null;
			String address=null;
			int accnumber=0;
			String nic=null;
			String email=null;
			String phone=null;
			String type=null;
			String uname=null;
			String pass=null;
			
			if(rs.next()) {
				UID=rs.getString(1);
				name=rs.getString(2);
				address=rs.getString(3);
				accnumber=rs.getInt(4);
				nic=rs.getString(5);
				email=rs.getString(6);
				phone=rs.getString(7);
				type=rs.getString(8);
				uname=rs.getString(9);
				pass=rs.getString(10);
			}
			
			//html table open
//			output="<table border='1'>"
//					+ "<tr>"
//					+ "<th>UserID</th>"
//					+ "<th>Name</th>"
//					+ "<th>Address</th>"
//					+ "<th>Account Number</th>"
//					+ "<th>National ID</th>"
//					+ "<th>Email</th>"
//					+ "<th>Phone Number</th>"
//					+ "<th>Type</th>"
//					+ "<th>Username</th>"
//					+ "<th>Password</th>"
//					+ "</tr>"
//					+ "<tr>"
//					+ "<td>"+UID+"</td>"
//					+ "<td>"+name+"</td>"
//					+ "<td>"+address+"</td>"
//					+ "<td>"+accnumber+"</td>"
//					+ "<td>"+nic+"</td>"
//					+ "<td>"+email+"</td>"
//					+ "<td>"+phone+"</td>"
//					+ "<td>"+type+"</td>"
//					+ "<td>"+uname+"</td>"
//					+ "<td>"+pass+"</td>"
//					+ "</tr>"
//					+ "</table>";	
			
			
			
			if(UID == null) {
				output = "{\"status\":\"error\", \"data\": \"Login failed\"}";
			} else {
				output = "{\"status\":\"success\", \"data\": '"+UID+"', \"type\":"+type+"}";
			}
			
			
			
		
			
		} catch (Exception e) {
			// TODO: handle exception
		
			output = "{\"status\":\"error\", \"data\": \"Login failed\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	

	public String InsertUserDetails(String name, String address, int accno, String nic, String email,
			String phone, String type, String username, String password) {
		// TODO Auto-generated method stub
		String output="";
		
		try {
			//check database connection
			Connection con=DBConnect.connect();
			if(con==null) {return "Database not connected";}
			
			//Insert into  user details
			Statement stmt=con.createStatement();
			String sql="INSERT INTO customer VALUES (0,'"+name+"','"+address+"','"+accno+"','"+nic+"','"+email+"','"+phone+"','"+type+"','"+username+"','"+password+"')";
		    int rs=stmt.executeUpdate(sql);
		    
		    if(rs>0) {
		    	output = "{\"status\":\"success\", \"data\":\"User Details Successfully Inserted !!!\"}";
		    }else {
		    	output = "{\"status\":\"error\", \"data\":\"User Details not Insert\"}";
		    }
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Catch User Details not Insert\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	
	public String UpdateUserDetails(int uid, String name, String address, int accno, String nic, String email, String phone,
			String type, String username, String password) {
		// TODO Auto-generated method stub
		String output="";
		boolean  isUpdated=false;
		
		try {
			// check database connection
						Connection con = DBConnect.connect();
						if(con == null) { return "Error while connecting to the database.."; }
						
						//update user details
						Statement stmt1 = con.createStatement();
						String sql1 = "update customer set Name='"+name+"', Address='"+address+"', AccountNumber="+accno+", NIC='"+nic+"', Email='"+email+"', Phone="+phone+", Type='"+type+"', Username='"+username+"', password='"+password+"' where UserID="+uid+"";
						
						int result = stmt1.executeUpdate(sql1);
						
						if (result > 0) {
							output = "{\"status\":\"success\", \"data\":\"User Details successfully updated!!!\"}";
						} else {
							output = "{\"status\":\"success\", \"data\":\"User Details not Updated\"}";
						}
							
			
		} catch (Exception e) {
			// TODO: handle exception

			output = "{\"status\":\"success\", \"data\":\"User Details not Updated\"}";
			System.err.println(e.getMessage());
			
		}
		
		
		return output;
	}
	
	

	public String DeleteUserDetails(String uid) {
		// TODO Auto-generated method stub
		String output="";
		boolean  isDeleted=false;
		try {
			// check database connection
			Connection con = DBConnect.connect();
			if(con == null) { return "Error while connecting to the database.."; }	
			
			//delete user details
			Statement stmt=con.createStatement();
			String sql = "DELETE FROM customer WHERE UserID="+uid+"";
			
			int result = stmt.executeUpdate(sql);
			
			if (result > 0) {
				isDeleted = true;
			} else {
				isDeleted = false;
			}
			
			output = "{\"status\":\"success\", \"data\":\"User Details successfully Deleted!!\"}";
			System.out.println(output);
				
			
			
		} catch (Exception e) {
			// TODO: handle exception
			output = "{\"status\":\"success\", \"data\":\"User Details not Deleted\"}";
			System.err.println(e.getMessage());
			
		}
		
	
		return output;
	}
	

	//Get All users by Admin
	public String getalluserdetails() {
		// TODO Auto-generated method stub
		String output="";
		try {
			// check database connection
			Connection con = DBConnect.connect();
			if(con == null) { return "Error while connecting to the database.."; }	
			
			//delete user details
			Statement stmt=con.createStatement();
			String sql = "SELECT * FROM customer";
			
			ResultSet rs=stmt.executeQuery(sql);
			
			

			JSONArray jsonAll = new JSONArray();
			
			int i = 0;
			
			
			while(rs.next()) {
			
			
				JSONObject json = new JSONObject();
				json.put("uid", rs.getString(1));
				json.put("name", rs.getString(2));
				json.put("address", rs.getString(3));
				json.put("accno", rs.getString(4));
				json.put("nic", rs.getString(5));
				json.put("email", rs.getString(6));
				json.put("phone", rs.getString(7));
				json.put("type", rs.getString(8));
				json.put("username", rs.getString(9));
				json.put("password", rs.getString(10));
				
				
				jsonAll.put(i,json);
				i = i+1;
				
				
			}
			
			output = "{\"status\":\"success\", \"data\":"+jsonAll+"}";
							
		} catch (Exception e) {
			// TODO: handle exception
			output="{\"status\":\"error\", \"data\":\"Error while getting all customers details\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	// view single cus details
	public String singleUserDetails(int uid) {
		String output = "";
		try {
			// check database connection
			Connection con = DBConnect.connect();
			if(con == null) { return "Error while connecting to the database.."; }	
			
			//delete user details
			Statement stmt=con.createStatement();
			String sql = "SELECT * FROM customer WHERE UserID = "+uid+"";
			
			ResultSet rs=stmt.executeQuery(sql);
			JSONObject json = new JSONObject();
			
			if(rs.next()) {
				int userid = rs.getInt(1);
				String name = rs.getString(2);
				String addr = rs.getString(3);
				int accno = rs.getInt(4);
				String nic = rs.getString(5);
				String email = rs.getString(6);
				String phone = rs.getString(7);
				String type = rs.getString(8);
				String uname = rs.getString(9);
				String pass = rs.getString(10);
				
				//write like json
				json.put("uid", userid);
				json.put("name", name);
				json.put("address", addr);
				json.put("accno", accno);
				json.put("nic", nic);
				json.put("email", email);
				json.put("phone", phone);
				json.put("type", type);
				json.put("uname", uname);
				json.put("pass", pass);
			}
			
			output = "{\"status\":\"success\", \"data\":"+json+"}";
			
		} catch (Exception e) {
			output="{\"status\":\"error\", \"data\":\"Error while getting a customer data to be update\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
		
}
