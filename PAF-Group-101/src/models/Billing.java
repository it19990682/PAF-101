package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import database.DBConnect;

public class Billing {
	
	public String InsertBillingDetails(String uid, String name, String sdate, String edate, int acconumber, int bilnumber,
			int unit, Float amount ) {
		// TODO Auto-generated method stub
		String output="";
		
		try {
			//check database connection
			Connection con=DBConnect.connect();
			if(con==null) {return "Database not connected";}
			
			//Insert into  billing details
			Statement stmt=con.createStatement();
			String sql="INSERT INTO billing VALUES ('"+uid+"','"+name+"','"+sdate+"','"+edate+"','"+acconumber+"','"+bilnumber+"','"+unit+"','"+amount+"')";
		    int rs=stmt.executeUpdate(sql);
		    
		    if(rs>0) {
		    	output = "{\"status\":\"success\", \"data\":\"Bill Details Successfully Inserted!\"}";
		    }else {
		    	output = "{\"status\":\"success\", \"data\":\"Bill Details Not Inserted!!\"}";
		    }
			
		} catch (Exception e) {
			// TODO: handle exception
			
			output = "{\"status\":\"success\", \"data\":\"Bill Details Not Inserted!!\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	//update billing details
	
	public String UpdateBillingDetails(String name, String sdate, String edate, int acconumber, int unit,
			Float amount ) {
		// TODO Auto-generated method stub
		String output="";
		
		
		try {
			// check db connection
						Connection con = DBConnect.connect();
						if(con == null) { return "Error while connecting to the database.."; }
						
						Statement stmt1 = con.createStatement();
						
						
						String sql1 = "update billing set Name='"+name+"', StartDate='"+sdate+"', EndDate='"+edate+"', NoofUnit='"+unit+"', BillAmount='"+amount+"' where AccountNumber="+acconumber+"";					
						int result = stmt1.executeUpdate(sql1);
						
						if (result > 0) {
							output = "{\"status\":\"success\", \"data\":\"Billing details successfully updated!!!\"}";
						} else {
							output = "{\"status\":\"error\", \"data\":\"Billing Details Not Updated!\"}";
						}
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Notice Details Not Updated!\"}";
			System.err.println(e.getMessage());
			
		}
		return output;

	}
	
	
	//delete billing details
	
	public String DeleteBillingDetails(String acconumber) {
		// TODO Auto-generated method stub
		String output="";
		String erroutput="";
		boolean  isDeleted=false;
		
		try {
			// check db connection
						Connection con = DBConnect.connect();
						if(con == null) { return "Error while connecting to the database.."; }
						
						Statement stmt1 = con.createStatement();
						
						
						String sql1 = "delete from billing where AccountNumber="+acconumber+"";					
						int result = stmt1.executeUpdate(sql1);
						
						if (result > 0) {
							isDeleted = true;
						} else {
							isDeleted = false;
						}
						
						output = "<h4>Billing Details successfully deleted!!!</h4>";
							
			
		} catch (Exception e) {
			// TODO: handle exception

			erroutput="Billing Details Delete";
			System.err.println(e.getMessage());
			
		}
		if (isDeleted) {
			return output;
			
			}
				
			
		
		return erroutput="Billing Details Delete";
	}
	
	
//get billing details.......................................................
	//login
	public String billingDetails(String acconumber) {
	String output="";



	try {
	//check database connection
	Connection con=DBConnect.connect();
	if(con==null) {return "Database not connected";}



	//validate username and password
	Statement stmt=con.createStatement();
	String sql="SELECT * FROM billing WHERE AccountNumber='"+acconumber+"'";
	ResultSet rs=stmt.executeQuery(sql);



	String UserID=null;
	String Name=null;
	String StartDate=null;
	String EndDate=null;
	String AccountNumber=null;
	String BillNumber=null;
	String NoofUnit=null;
	Float BillAmount=(float) 0;



	if(rs.next()) {
		UserID=rs.getString(1);
	Name=rs.getString(2);
	StartDate=rs.getString(3);
	EndDate=rs.getString(4);
	AccountNumber=rs.getString(5);
	BillNumber=rs.getString(6);
	NoofUnit=rs.getString(7);
	BillAmount=rs.getFloat(8);
	}



	//html table open
//	output="<table border='1'>"
//	+ "<tr>"
//	+ "<th>UserID</th>"
//	+ "<th>Name</th>"
//	+ "<th>StartDate</th>"
//	+ "<th>EndDate</th>"
//	+ "<th>AccountNumber</th>"
//	+ "<th>BillNumber</th>"
//	+ "<th>NoofUnit</th>"
//	+ "<th>BillAmount</th>"
//	+ "</tr>"
//	+ "<tr>"
//	+ "<td>"+UserID+"</td>"
//	+ "<td>"+Name+"</td>"
//	+ "<td>"+StartDate+"</td>"
//	+ "<td>"+EndDate+"</td>"
//	+ "<td>"+AccountNumber+"</td>"
//	+ "<td>"+BillNumber+"</td>"
//	+ "<td>"+NoofUnit+"</td>"
//	+ "<td>"+BillAmount+"</td>"
//	+ "</tr>"
//	+ "</table>";
	
	JSONObject json = new JSONObject();
	json.put("uid", UserID);
	json.put("name", Name);
	json.put("sdate", StartDate);
	json.put("edate", EndDate);
	json.put("accno", AccountNumber);
	json.put("billnum", BillNumber);
	json.put("units", NoofUnit);
	json.put("billamount", BillAmount);


	output = "{\"status\":\"success\", \"data\":"+json+"}";


	} catch (Exception e) {
	// TODO: handle exception
	output = "{\"status\":\"success\", \"data\":\"Bill not Found\"}";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	
	//Get All billing details by Admin
	public String getallbilldetails() {
		// TODO Auto-generated method stub
		String output="";
		try {
			// check database connection
			Connection con = DBConnect.connect();
			if(con == null) { return "Error while connecting to the database.."; }	
			
			//delete user details
			Statement stmt=con.createStatement();
			String sql = "SELECT * FROM billing";
			
			ResultSet rs=stmt.executeQuery(sql);
			
			JSONArray jsonAll = new JSONArray();
			
			int i = 0;
			
			
			while(rs.next()) {
			
			
				JSONObject json = new JSONObject();
				json.put("userid", rs.getString(1));
				json.put("name", rs.getString(2));
				json.put("startdate", rs.getString(3));
				json.put("enddate", rs.getString(4));
				json.put("accno", rs.getString(5));
				json.put("billno", rs.getString(6));
				json.put("noofunits", rs.getString(7));
				json.put("billamount", rs.getString(8));
				
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
