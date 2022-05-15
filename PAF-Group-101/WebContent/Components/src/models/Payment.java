package models;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import database.DBConnect;

public class Payment {
	
	// get customer details and their bill amount
	public ArrayList<String> getCustomerDetails(String userID) {
		ArrayList<String> paycus = new ArrayList<>();
		try {
			// Check database connection
			Connection con = DBConnect.connect();
			
			// declare open variables
			String uid =null;
			String name = null;
			String address = null;
			String accNumber = null;
			String nic = null;
			String email = null;
			String phone = null;
			
			String sDate = null;
			String eDate=null;
			String billNumber = null;
			String units = null;
			String totalpayable = null;
			
			// get details queries and statements
			Statement stmtc = con.createStatement();
			Statement stmtb = con.createStatement();
			String CusD = "select * from customer where UserID='"+userID+"'";
			String BillD = "select StartDate, EndDate, BillNumber, NoofUnit, BillAmount from billing where UserID='"+userID+"'";
			ResultSet crs = stmtc.executeQuery(CusD);
			ResultSet brs = stmtb.executeQuery(BillD);
			
			// customer rs
			if(crs.next()) {
				uid = String.valueOf(crs.getInt(1));
				name = crs.getString(2);
				address = crs.getString(3);
				accNumber = Integer.toString(crs.getInt(4)); 
				nic = crs.getString(5);
				email = crs.getString(6);
				phone = crs.getString(7);
				
				
				
			}
			
			// bill rs
			if (brs.next()) {
				sDate = brs.getString(1);
				eDate = brs.getString(2);
				billNumber = Integer.toString(brs.getInt(3));
				units = Integer.toString(brs.getInt(4));
				totalpayable = Double.toString(brs.getDouble(5));
			}
			
			paycus.add(uid);
			paycus.add(name);
			paycus.add(address);
			paycus.add(accNumber);
			paycus.add(nic);
			paycus.add(email);
			paycus.add(phone);
			paycus.add(sDate);
			paycus.add(eDate);
			paycus.add(billNumber);
			paycus.add(units);
			paycus.add(totalpayable);
			

			con.close();
			stmtc.close();
			stmtb.close();
		}
		
		catch (Exception e) {
			System.err.println("Error while fetching the customer details."); 
			System.err.println(e.getMessage());
		}
		
		return paycus;
	}
	
	// Show customer details
	public String showPaymentCusDetails(String userID) {
		String output = "";
		String output2 = "";
		
		ArrayList<String> payCus = new ArrayList<String>();
		payCus = getCustomerDetails(userID);
		// demostrate List
		String uid = payCus.get(0);
		String name = payCus.get(1);
		String address = payCus.get(2);
		int accNumber = Integer.parseInt(payCus.get(3)); 
		String nic = payCus.get(4);
		String email = payCus.get(5);
		String phone = payCus.get(6);
		String sDate = payCus.get(7);
		String eDate = payCus.get(8);
		int billNumber = Integer.parseInt(payCus.get(9));
		int units = Integer.parseInt(payCus.get(10));
		Double totalpayable = Double.parseDouble(payCus.get(11));
		
		try {
			// prepare view table
			output = "<table>"
					+ 	"<tr>"
					+ 		"<th align='left'>Name</th><td>"+name+"</td>"
					+ 	"</tr>"
					+ 	"<tr>"
					+ 		"<th align='left'>Address</th><td>"+address+"</td>"
					+	 "</tr>"
					+ 	"<tr>"
					+ 		"<th align='left'>Account Number</th><td>"+accNumber+"</td>"
					+	 "</tr>"
					+ 	"<tr>"
					+ 		"<th align='left'>National ID</th><td>"+nic+"</td>"
					+	 "</tr>"
					+ 	"<tr>"
					+ 		"<th align='left'>Email</th><td>"+email+"</td>"
					+	 "</tr>"
					+ 	"<tr>"
					+ 		"<th align='left'>Phone</th><td>"+phone+"</td>"
					+	 "</tr>"
					+ 	"<tr>"
					+ 		"<th align='left'>Total Payable</th><td>"+totalpayable+"</td>"
					+	 "</tr>";
			
			// buttons
			output += "<form method='post' action='http://localhost:8080/PAF-2022-Group-130/ElectronicServices/payment/confirmbill'>"
					+ 	"<tr>"
					+ 		"<td><input type='hidden' name='uid' value="+uid+" />"
					+			"<input type='submit' value='PROCEED' />"
					+ 		"</td>"
					+ 	"</tr>"
					+ "</form>";
			
			output += "</table>";
			
			// create a json format
//			String json = "{uid:\""+uid+"\","
//						 + "name:\""+name+"\","
//						 + "address:\""+address+"\","
//						 + "accNumber:\""+accNumber+"\","
//						 + "nic:\""+nic+"\","
//						 + "email:\""+email+"\","
//						 + "phone:\""+phone+"\","
//						 + "totalpayable:\""+totalpayable+"\"}";
			
			JSONObject json = new JSONObject();
			json.put("uid", uid);
			json.put("name", name);
			json.put("address", address);
			json.put("accNumber", accNumber);
			json.put("nic", nic);
			json.put("email", email);
			json.put("phone", phone);
			json.put("sDate", sDate);
			json.put("eDate", eDate);
			json.put("billNumber",billNumber);
			json.put("units", units);
			json.put("totalpayable", totalpayable);
			
			output2 = "{\"status\":\"success\", \"data\": "+json+"}";
			
			System.out.println(output2);
			
		} catch (Exception e) {
			//output = "Error while showing the customer details."; 
			output2 = "{\"status\":\"Error\", \"data\": \"Error while fetching the details.\"}";
			System.err.println(e.getMessage());
		}
		
		return output2;
	}
	
	// ----------------------------------------------------------------------------------------------------- //
	
	// retrieve last payment id for id generation 
		public static int getLastPaymentID() {
		
			String pidcount = "0";
			int pid = 0;
			String pidS = "0";
		
			// userid validate
			try {
				// Check database connection
				Connection con = DBConnect.connect();
				// get count of rows in payment table
				Statement stmt = con.createStatement();
				String sql = "select count(*) from payment";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				if(rs.next()) {
					pidcount = rs.getString(1);
				}
				
				// get payment id
				String sql1 = "select PaymentID from payment where id='"+pidcount+"'";
				ResultSet rs1 = stmt.executeQuery(sql1);
				
				if(rs1.next()) {
					pidS = rs1.getString(1);
				}
				
				// convert string to int
				pid = Integer.parseInt(pidS.substring(1));
				
				} catch(Exception e) {
					e.printStackTrace();
				}
		
			return pid;
		
		}

	// insert to payment table
	public String payTheBill(String userID,String amount) {
		String output = "";
		String erroutput = "";
		
		String newPID = null;
		String gpid1;
		
		boolean isSuccess = false;
		
		try {
			// Check database connection
			Connection con = DBConnect.connect();
			if (con == null) { return "Error while connecting to the database.."; }

			// payment id for generate pid
			int PID = getLastPaymentID();
			int id = PID+1;
			
			// next payment id generation
			if (PID == 0) {
				newPID = "P001";
			} else {
				int gpid = PID+1;
				gpid1 = String.valueOf(gpid);
				if (gpid >= 10) {
					newPID = "P0"+gpid1;
				} else {
					newPID = "P00"+gpid1;
				}
			}
			
			// get common bill and customer details
			ArrayList<String> payCus = new ArrayList<String>();
			payCus = getCustomerDetails(userID);
			// demostrate List
			int curruntUid =Integer.parseInt( payCus.get(0));
			String name = payCus.get(1);
			String billAddress = payCus.get(2);
			int accNumber = Integer.parseInt(payCus.get(3)); 
			String nic = payCus.get(4);
			String email = payCus.get(5);
			String phone = payCus.get(6);
			Double totalpayable = Double.parseDouble(payCus.get(11));
			String paidStats = null;
			String expDate = payCus.get(8);
			
			// generate balance
			Double cashD = Double.parseDouble(amount);
			Double balanceD = cashD - totalpayable;
			
			if (totalpayable==cashD) {
				paidStats = "Paid";
			} else if (totalpayable<cashD) {
				paidStats = "Paid";
			} else if (cashD == 0) {
				paidStats = "unpaid";
			} else {
				paidStats = "HalfPaid";
				balanceD = 0.00;
			}
			
			// Inserting to database
			Statement stmt =con.createStatement();
			String sql = "insert into payment values('"+id+"','"+newPID+"','"+curruntUid+"','"+name+"','"+accNumber+"','"+billAddress+"','"+email+"','"+phone+"','"+totalpayable+"','"+cashD+"','"+balanceD+"',"
					+ "'"+paidStats+"','"+expDate+"')";
			int result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				isSuccess = true;
			} else {
				isSuccess = false;
			}
			
//			output = "<div align='center'>"
//					+"<table border='1'>"
//					+ 	"<tr>"
//					+ 		"<th align='left'>Payment ID</th>"
//					+ 		"<td>"+newPID+"</td>"
//					+ 	"</tr>"
//					+ 	"<tr>"
//					+ 		"<th align='left'>Customer Name</th>"
//					+ 		"<td>"+name+"</td>"
//					+ 	"</tr>"
//					+ 	"<tr>"
//					+ 		"<th align='left'>Customer paid amount</th>"
//					+ 		"<td>"+cashD+"</td>"
//					+ 	"</tr>"
//					+ 	"<tr>"
//					+ 		"<th align='left'>Total payable amount</th>"
//					+ 		"<td>"+totalpayable+"</td>"
//					+ 	"</tr>"
//					+ 	"<tr>"
//					+ 		"<th align='left'>Balance amount</th>"
//					+ 		"<td>"+balanceD+"</td>"
//					+ 	"</tr>"
//					+ "</table>"
//					+ "</div>"
//					+ "<h4 align='center'>Payment Success</h4>"
//					+ "<h6 align='center'>Thank you</h6>";
			
			JSONObject json = new JSONObject();
			json.put("paymentID", newPID);
			
			output = "{\"status\":\"success\", \"data\": "+json+"}";
			
			stmt.close();
			con.close();
			
		} catch(Exception e) {
			output = "{\"status\":\"success\", \"data\": \"Error while fetching the customer details and inserting payment details.\"}";
			System.err.println(e.getMessage());
		}
		
		if (isSuccess == false) {
			return erroutput = "Error while inserting payment details! Payment Failed!!!";
		}
		
		return output;
	}
	
	// ----------------------------------------------------------------------------------------------------- //

	// update address
	public String updatePaymentAddress(String uid, String address) {
		String output = "";
		
		// update starting
		try {
			// check db connection
			Connection con = DBConnect.connect();
			if(con == null) { return "Error while connecting to the database.."; }
			
			Statement stmt1 = con.createStatement();
			
			String sql1 = "update payment set BillingAddress='"+address+"' where UserID="+uid+"";
			
			int result1 = stmt1.executeUpdate(sql1);
			
			if (result1 > 0) {
				output ="{\"status\":\"success\", \"data\":\"Billing address update success\"}";
			} else {
				output ="{\"status\":\"error\", \"data\":\"Error while updating the billing address\"}";
			}
			
		} catch (Exception e) {
			output ="{\"status\":\"error\", \"data\":\"Catch Error while updating the billing address\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	// ----------------------------------------------------------------------------------------------------- //
	
	// delete payment history
	public String deletePayHistory(String pid) {
		String output = "";
		
		try {
			// check db connection
			Connection con = DBConnect.connect();
			if(con == null) { return "Error while connecting to the database.."; }
			
			
			String sql = "DELETE FROM payment WHERE PaymentID=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, pid);
			int result = stmt.executeUpdate();
			
			System.out.print(result);
			
			if (result > 0) {
				output = "{\"status\":\"success\", \"data\":\"Delete Success\"}";
			} else {
				output = "{\"status\":\"error\", \"data\":\"Delete Unsuccess\"}";
			}

			
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Delete Unsuccess\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	// ----------------------------------------------------------------------------------------------------- //
	
	// Paymnet History
	public String showPaymentHistory(String uid) {
		String output = "";
		
		try {
			// check db connection
			Connection con = DBConnect.connect();
			if(con == null) { return "Error while connecting to the database.."; }
			
			String sql = "select * from payment where UserID="+uid+"";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			// table start
//			output = "<table border='1'>"
//					+ 	"<tr>"
//					+ 		"<th>Payment ID</th>"
//					+ 		"<th>User ID</th>"
//					+ 		"<th>Name</th>"
//					+ 		"<th>Account Number</th>"
//					+ 		"<th>Billing Address</th>"
//					+ 		"<th>Email</th>"
//					+ 		"<th>Phone</th>"
//					+ 		"<th>Total</th>"
//					+ 		"<th>Cash</th>"
//					+ 		"<th>Balance</th>"
//					+ 		"<th>Paid Status</th>"
//					+ 		"<th>Expire Date</th>"
//					+ 	"</tr>";
			
			JSONArray jarr = new JSONArray();
			
			Boolean rsCount = rs.next();
			int i = 0;
			
			while(rsCount) {
				
				String pid = rs.getString(2);
				String userid = rs.getString(3);
				String name = rs.getString(4);
				int accno = rs.getInt(5);
				String billaddr = rs.getString(6);
				String email = rs.getString(7);
				String phone = rs.getString(8);
				Double total = rs.getDouble(9);
				Double cash = rs.getDouble(10);
				Double balance = rs.getDouble(11);
				String status = rs.getString(12);
				String expDate = rs.getString(13);
				
//				output += "<tr>"
//						+ 	"<td>"+pid+"</td>"
//						+ 	"<td>"+userid+"</td>"
//						+ 	"<td>"+name+"</td>"
//						+ 	"<td>"+accno+"</td>"
//						+ 	"<td>"+billaddr+"</td>"
//						+ 	"<td>"+email+"</td>"
//						+ 	"<td>"+phone+"</td>"
//						+ 	"<td>"+total+"</td>"
//						+ 	"<td>"+cash+"</td>"
//						+ 	"<td>"+balance+"</td>"
//						+ 	"<td>"+status+"</td>"
//						+ 	"<td>"+expDate+"</td>"
//						+ "</tr>";
				
				JSONObject obj =  new JSONObject();
				obj.put("pid", pid);
				obj.put("userid", userid);
				obj.put("name", name);
				obj.put("accno", accno);
				obj.put("billaddr", billaddr);
				obj.put("email", email);
				obj.put("phone", phone);
				obj.put("total", total);
				obj.put("cash", cash);
				obj.put("balance", balance);
				obj.put("status", status);
				obj.put("expDate", expDate);
				
				jarr.put(i, obj);
				
				i = i + 1;
				rsCount = rs.next();
			}
			
			output = "{\"status\":\"success\",\"data\":"+jarr+"}";
			
			
//			output += "</table>";

			
		} catch (Exception e) {
			output = "Error while showing payment history."; 
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	// insert card details
	public String insertCardDetails(String uid, String pid, String owner, String cardNumber, String cvv,
			String expDate) {
		
		String output = "";
		try {
			// check db connection
			Connection con = DBConnect.connect();
			if(con == null) { return "Error while connecting to the database.."; }
			
			// insert sql
			String sql = "insert into payment_methods values(0, '"+uid+"', '"+pid+"', '"+owner+"', '"+cardNumber+"', '"+cvv+"', '"+expDate+"')";
			Statement stmt = con.createStatement();
			int result = stmt.executeUpdate(sql);
			
			boolean isInserted = false;
			if(result > 0) {
				output = "{\"status\":\"success\", \"data\":\"Debit card successfully saved.\"}";
			} else {
				output = "{\"status\":\"error\", \"data\":\"Debit card not saved.\"}";
			}
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while saving debit card details.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	

	
	
	
}
