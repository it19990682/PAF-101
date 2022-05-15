package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import database.DBConnect;

public class Notice {
	
	//get notice details
	
	public String noticeDetails(String Userid) {
	String output="";

	try {
	//check database connection
	Connection con=DBConnect.connect();
	if(con==null) {return "Database not connected";}

	//validate username and password
	Statement stmt=con.createStatement();
	String sql="SELECT * FROM notices WHERE UserID='"+Userid+"'";
	ResultSet rs=stmt.executeQuery(sql);

	int ID=0;
	String UserID=null;
	String userName=null;
	String Date=null;
	String Time=null;
	String Type=null;
	String Notice=null;

	if(rs.next()) {
		ID=rs.getInt(1);
		UserID=rs.getString(2);
		userName=rs.getString(3);
		Date=rs.getString(4);
		Time=rs.getString(5);
		Type=rs.getString(6);
		Notice=rs.getString(7);
	}

	//html table open
//	output="<table border='1'>"
//	+ "<tr>"
//	+ "<th>ID</th>"
//	+ "<th>UserID</th>"
//	+ "<th>userName</th>"
//	+ "<th>Date</th>"
//	+ "<th>Time</th>"
//	+ "<th>Type</th>"
//	+ "<th>Notice</th>"
//	+ "</tr>"
//	+ "<tr>"
//	+ "<td>"+ID+"</td>"
//	+ "<td>"+UserID+"</td>"
//	+ "<td>"+userName+"</td>"
//	+ "<td>"+Date+"</td>"
//	+ "<td>"+Time+"</td>"
//	+ "<td>"+Type+"</td>"
//	+ "<td>"+Notice+"</td>"
//	+ "</tr>"
//	+ "</table>";
	
	JSONObject json = new JSONObject();
	json.put("id", ID);
	json.put("userid", UserID);
	json.put("username", userName);
	json.put("date", Date);
	json.put("time", Time);
	json.put("type", Type);
	json.put("notice", Notice);
	
	output = "{\"status\":\"success\",\"data\":"+json+"}";

	} catch (Exception e) {
	// TODO: handle exception
		output = "{\"status\":\"error\",\"data\":\"User not Registered\"}";
	
	System.err.println(e.getMessage());
	}
	return output;
	}

	public String InsertNotice(String uid, String username, String date, String time, String type, String notice) {
		// TODO Auto-generated method stub
		String output="";
		try {
			//check database connection
			Connection con=DBConnect.connect();
			if(con==null) {return "Database not connected";}

			//Insert notices
			Statement stmt=con.createStatement();
			String sql="INSERT INTO notices VALUES(0,'"+uid+"','"+username+"', '"+date+"', '"+time+"', '"+type+"', '"+notice+"')";
			int rs=stmt.executeUpdate(sql);
			
			if(rs>0) {
				output = "{\"status\":\"success\", \"data\":\"Notice Details Successfully Inserted!\"}";
			} else {
				output = "{\"status\":\"success\", \"data\":\"Notice Details Not Inserted!!\"}";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			output = "{\"status\":\"success\", \"data\":\"Notice Details Not Inserted!!\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String Updatenotices(String uid, String username, String date, String time, String type, String notice) {
		// TODO Auto-generated method stub
		String output="";
		try {
			//check database connection
			Connection con=DBConnect.connect();
			if(con==null) {return "Database not connected";}

			//Insert notices
			Statement stmt=con.createStatement();
			String sql="INSERT INTO notices VALUES(0,'"+uid+"','"+username+"', '"+date+"', '"+time+"', '"+type+"', '"+notice+"')";
			int rs=stmt.executeUpdate(sql);
			
			if(rs>0) {
				output="<h4>Notice Details Updated Successfully!</h4>";
			} else {
				output="<h4>Notice Details Not Updated!</h4>";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			output="Notice Details Not Updated!";
			System.err.println(e.getMessage());
		}
		return output;
		
	}
	
	// update notices
		public String updateNotice(int id, String userid, String username, String date, String time, String type, String notice) {
			String output = "";
			
			// update starting
			try {
				// check db connection
				Connection con = DBConnect.connect();
				if(con == null) { return "Error while connecting to the database.."; }
				
				Statement stmt = con.createStatement();
				
				String sql = "update notices set userName='"+username+"', Date='"+date+"', Time='"+time+"', Type='"+type+"', Notice='"+notice+"' where ID="+id+"";
				System.out.println("id " + id + "uid " + userid);
				int result = stmt.executeUpdate(sql);
				
				if (result > 0) {
					output = "{\"status\":\"success\", \"data\":\"Notice details successfully updated!!!\"}";
				} else {
					output = "{\"status\":\"error\", \"data\":\"Notice Details Not Updated!\"}";
				}
				
				
				
			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\":\"Notice Details Not Updated!\"}";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		// delete notices
				public String deleteNotice(int id) {
					String output = "";
					String erroutput = "";
					
					boolean isDeleted = false;
					
					// delete starting
					try {
						// check db connection
						Connection con = DBConnect.connect();
						if(con == null) { return "Error while connecting to the database.."; }
						
						Statement stmt = con.createStatement();
						
						String sql = "delete from notices where ID="+id+"";
						System.out.println("id " + id );
						int result = stmt.executeUpdate(sql);
						
						if (result > 0) {
							isDeleted = true;
						} else {
							isDeleted = false;
						}
						
						output = "<h4>Notice details successfully deleted!!!</h4>";
						
					} catch (Exception e) {
						output="Notice Details Not Deleted!";
						System.err.println(e.getMessage());
					}
					
					if (isDeleted == false) {
						erroutput = "Error while deleting details";
					}
					
					return output;
				}
				
		//Get All notices by Admin
		public String getallnoticedetails() {
			// TODO Auto-generated method stub
			String output="";
			try {
				// check database connection
				Connection con = DBConnect.connect();
				if(con == null) { return "Error while connecting to the database.."; }	
				
				//delete user details
				Statement stmt=con.createStatement();
				String sql = "SELECT * FROM notices";
				
				ResultSet rs=stmt.executeQuery(sql);
				
				JSONArray jsonAll = new JSONArray();
				
				int i = 0;
				
				
				while(rs.next()) {
				
				
					JSONObject json = new JSONObject();
					json.put("id", rs.getString(1));
					json.put("userid", rs.getString(2));
					json.put("username", rs.getString(3));
					json.put("date", rs.getString(4));
					json.put("time", rs.getString(5));
					json.put("type", rs.getString(6));
					json.put("notice", rs.getString(7));
					
					
					jsonAll.put(i,json);
					i = i+1;
					
					
				}
				 System.out.println(jsonAll);
				
				output = "{\"status\":\"success\", \"data\":"+jsonAll+"}";
								
			} catch (Exception e) {
				// TODO: handle exception
				output="{\"status\":\"error\", \"data\":\"Error while getting all notice details\"}";
				System.err.println(e.getMessage());
			}
			
			return output;
		}

				
}

