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

import models.Billing;
import models.Notice;




@WebServlet("/BillingAPI")
public class BillingAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Billing billing = new Billing();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = billing.getallbilldetails();
		System.out.println(output);
		response.getWriter().write(output);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = request.getParameter("page");
		
		// view single update details
		if (page.equals("viewsinglebill")) {
			String accountNumber = request.getParameter("upaccnumber");
			
			String output = billing.billingDetails(accountNumber);
			response.getWriter().write(output);
		}

		
		if(page.equals("billinsertpage")) {
			// insert details
			String billinguid = request.getParameter("billinguid");
			String billingusername = request.getParameter("billingusername");
			String billingstartdate = request.getParameter("billingstartdate");
			String billingenddate = request.getParameter("billingenddate");
			int billingaccountnumber = Integer.parseInt(request.getParameter("billingaccountnumber"));
			int billingbill = Integer.parseInt(request.getParameter("billno"));
			int billingunit = Integer.parseInt(request.getParameter("billingunit"));
			Float billingamount = Float.parseFloat(request.getParameter("billingamount"));
			
			String output = billing.InsertBillingDetails(billinguid, billingusername, billingstartdate, billingenddate, billingaccountnumber, billingbill, billingunit, billingamount);
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
		String name = json.getString("name");
		String startdate = json.getString("sdate");
		String enddate = json.getString("edate");
		int accno = json.getInt("accno");
		int noofunits = json.getInt("billunit");
		Float billamount = Float.parseFloat(json.getString("billamount"));
		
		
		String output = billing.UpdateBillingDetails(name, startdate, enddate, accno, noofunits, billamount);
		response.getWriter().write(output);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Convert request body to json
				String jsonString = inputStreamToString(request.getInputStream());
				JSONObject json = new JSONObject(jsonString);
				
				//parameters
				String accno = json.getString("accno");
				
				//clear history
			    System.out.println(accno);
				String output = billing.DeleteBillingDetails(accno);
				response.getWriter().write(output);
				
	}

}
