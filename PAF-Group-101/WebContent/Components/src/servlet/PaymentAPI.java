package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Scanner;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.protobuf.ByteString.Output;

import models.Payment;

@WebServlet("/PaymentAPI")
public class PaymentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Payment paymentObj = new Payment();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//check which page
		String method = request.getParameter("paymentfuncpages");
		
		String uid = request.getParameter("puid");
		String parseuidfH = request.getParameter("parseuidfH");
		
		String userIdentity = request.getParameter("uid");
		String insert = request.getParameter("insert");
		String holderName = request.getParameter("cardName");
		String cNumber = request.getParameter("cardNumber");
		String cardNumber = null;
		String cvv = request.getParameter("cardCvv");
		String CardExpDate = request.getParameter("cardExpD");
		String amount = request.getParameter("amount");
		
		//payment button clicked
		if(method.equals("paymentbill")) {
			String output = paymentObj.showPaymentCusDetails(uid);
			response.getWriter().write(output);
		}
		
		//pay the bill make payment button clicked
		if(method.equals("insertpaymentdetails")) {
			// insert payment
			String output = paymentObj.payTheBill(userIdentity, amount);
			// convert output to json
			JSONObject jo1 = new JSONObject(output);
			System.out.println(jo1.get("status"));
			// if payment status success
			if(jo1.get("status").equals("success")) {
				// get inserted last payment id
				String CurruntPid = paymentObj.showPaymentHistory(userIdentity);
				JSONObject jo2 = new JSONObject(CurruntPid);
				JSONArray jarr = jo2.getJSONArray("data");
				
				// get payment last id
				int i = jarr.length();
				int rotate = 0;
				String paymentID = null;
				
				while(rotate <= i) {
					if(rotate == i) {
						paymentID = jarr.getJSONObject(rotate-1).getString("pid");
					}
					rotate = rotate+1;
				}
				System.out.println(paymentID);
				
				// drop '-' in Card Number
				if (cardNumber != "") {
					cardNumber= cNumber.replaceAll("[^0-9]", "");
				}
				
				// inserting debit card details
				String insertDebitcard = paymentObj.insertCardDetails(userIdentity, paymentID, holderName, cardNumber, cvv, CardExpDate);
				response.getWriter().write(insertDebitcard);
			}
		}
		
		if(method.equals("paymentHistory")) {
			String output = paymentObj.showPaymentHistory(parseuidfH);
			response.getWriter().write(output);
		}
		
	}
	
	// Converter
	private static String inputStreamToString(InputStream inputStream) {
	      Scanner scanner = new Scanner(inputStream, "UTF-8");
	      return scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
	  }

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// update billing address
		
		// Convert request body to json
		String jsonString = inputStreamToString(request.getInputStream());
		JSONObject json = new JSONObject(jsonString);
		
		//check which page loaded
		String method = json.getString("paymentfuncpages");
		
		// parameters
		String uidtobeupdate = json.getString("uid");
		String billAddress = json.getString("address");
		
		
		// update bill address
		if(method.equals("updatebillingaddress")) {
			String output = paymentObj.updatePaymentAddress(uidtobeupdate, billAddress);
			System.out.println(output);
			response.getWriter().write(output);
		}
		
		
	}

	// clear history
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Convert request body to json
		String jsonString = inputStreamToString(request.getInputStream());
		JSONObject json = new JSONObject(jsonString);
		
		//check which page loaded
		String method = json.getString("paymentfuncpages");
		
		//parameters
		String pid = json.getString("pid");
		
		//clear history
		if(method.equals("delete")) {
			System.out.println(pid);
			String output = paymentObj.deletePayHistory(pid);
			response.getWriter().write(output);
		}
	}

}
