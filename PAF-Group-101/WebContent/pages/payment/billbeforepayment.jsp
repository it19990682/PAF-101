<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
<link rel="stylesheet" type="text/css" href="../../Views/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="../../Views/payments.css"/>
<script src="../../Components/jquery-3.6.0.min.js"></script>
<script src="../../Components/main.js"></script>
<script src="../../Components/paymentsJS.js"></script>
<meta charset="ISO-8859-1">
<title>Bill Verify</title>
<nav class="navbar navbar-dark bg-dark"">
 
  <div class="container-fluid">
    <a class="navbar-brand" href="#">ElectroGrid</a>
   
    
      <div class="navbar-brand">
        <a style="float: right; width: 200px;" class="btn btn-outline-primary" href="../notice/NoticeTable.jsp" >NOTICE DETAILS</a>
		<a style="float: right; width: 200px; margin-right: 10px;" class="btn btn-outline-primary" href="../notice/noticeDetails.jsp" >ADD NOTICE</a>
		<a style="float: right; width: 200px; margin-right: 10px;" class="btn btn-outline-primary"  href="../billing/billingdetailsview.jsp" >VIEW BILL</a>
		<a style="float: right; width: 200px; margin-right: 10px;" class="btn btn-outline-primary" href="../billing/billinginsert.jsp" >ADD BILL</a>
      </div>
    
 
</div>
</nav>
</head>
<body class="pbody">
	<!-- user id from profile.jsp -->
	<% String uid = request.getParameter("puid"); %>
	<!-- automatic send form for get bill details -->
	<form id="billBeforePay" name="billBeforePay">
		<input id="paymentfuncpages" name="paymentfuncpages" type="hidden" value="paymentbill" />
		<input type="hidden" name="puid" id="puid" value=<%=uid%> />
	</form>
	
	<div class="pbc1">
		<h1 id="pbillh1" >Confirm your bill to pay the payment.</h1>
		
		<div id="pbillformleft">	
			<form id="pbill" name="pbill">
				<div class="container">
					<h4 class="pblefth4">Person Details</h4>
					<div class="form-group">
				    	<label class="pfd1lfsize" for="pfd1name">Name</label>
				   		<input type="text" class="form-control pfi1" id="pfd1name" name="pfd1name" readonly="readonly">
					</div>
					
					<div class="row pfd1" >
					  <div class="col">
						  	<div class="form-group">
							    <label class="pfd1lfsize" for="pfd1accnumber">Account Number</label>
							    <input type="text" class="form-control pfi1" id="pfd1accnumber" name="pfd1accnumber" readonly="readonly">
							</div>
						</div>
						<div class="col">
								<div class="form-group">
						    	<label class="pfd1lfsize" for="pfd1billno">Bill No</label>
						   		<input type="text" class="form-control pfi1" id="pfd1billno" name="pfd1billno" readonly="readonly">
						  	</div>
					  </div>
					</div>
				
					<div class="form-group pfd1">
					    <label class="pfd1lfsize" for="pfd1address">Home Address</label>
					    <input type="text" class="form-control pfi1" id="pfd1address" name="pfd1address" readonly="readonly">
					</div>
					<div class="form-group pfd1">
					    <label class="pfd1lfsize" for="pfd1nic">National ID</label>
					    <input type="text" class="form-control pfi1" id="pfd1nic" name="pfd1nic" readonly="readonly">
					</div>
					<div class="form-group pfd1">
					    <label class="pfd1lfsize" for="pfd1email">Email</label>
					    <input type="text" class="form-control pfi1" id="pfd1email" name="pfd1email" readonly="readonly">
					</div>
					<div class="form-group pfd1">
					    <label class="pfd1lfsize" for="pfd1phone">Phone</label>
					    <input type="text" class="form-control pfi1" id="pfd1phone" name="pfd1phone" readonly="readonly">
					</div>
				</div>
			</form>
		</div>
		
		<div id="pbillformright">
			<div class="upper">
				<form id="pbillupper" name="pbillupper">
					<div class="pContainer">
						<h4 class="pbupperh4">Payment Summary</h4>
						
						<div class="row pfd1" >
						  <div class="col">
							  	<div class="form-group pfduppu">
								    <label class="pfd1lu" for="pfd1ppu">Price Per Unit (PPU)</label>
								    <input type="text" class="form-control pfi1" id="pfd1ppu" name="pfd1ppu" readonly="readonly">
								</div>
							</div>
							<div class="col">
									<div class="form-group pfduu">
							    	<label class="pfd1lu" for="pfd1units">NO Units</label>
							   		<input type="text" class="form-control pfi1" id="pfd1units" name="pfd1units" readonly="readonly">
							  	</div>
						  </div>
						</div>
						
						<div class="row pfd1" >
						  <div class="col">
							  	<div class="form-group">
								    <label class="pfd1lu" for="pfd1sDate">Start Date</label>
								    <input type="text" class="form-control pfi1" id="pfd1sDate" name="pfd1sDate" readonly="readonly">
								</div>
							</div>
							<div class="col">
									<div class="form-group">
							    	<label class="pfd1lu" for="pfd1eDate">End Date</label>
							   		<input type="text" class="form-control pfi1" id="pfd1eDate" name="pfd1eDate" readonly="readonly">
							  	</div>
						  </div>
						</div>
						
					</div>
					
						<div class="form-group pfd1 totPayble">
					    <label class="pfd1lu" for="pfd1totalpayable">Total Payable</label>
					    <input type="text" class="form-control pfi1" id="pfd1totalpayable" name="pfd1totalpayable" readonly="readonly">
						</div>
					
				</form>
			</div>
			<div class="lower">
				<div style="padding: 20px; width: 85%; margin-top: -50px; margin-right: 60px;">
					<div class="paymethod">
						<h4 class="pflpmh4">Payment Method</h4>
						<img style="margin-top: 10px;" src="../../images/visa-logo.png" width="70px" alt="visa" title="visa" />
						<img style="margin-top: 10px;" src="../../images/Master_Card-logo.png" width="70px" alt="master" title="master" />
					</div>
					<div class="pcardDetails">
						<form>
							<div class="form-group">
								<label for="pcardname">Name on Card</label>
								<input type="text" class="form-control" id="pcardname" name="pcardname" placeholder="Jhon Smith"/>
							</div>
							
							<div class="row pfld">
								<div class="col">
									<div class="form-group">
										<label for="pcardnum">Card Number</label>
										<input type="text" class="form-control" id="pcardnum" name="pcardnum" onkeypress='return formats(this, event)' onkeyup='return numberValidation(event)' placeholder="0000-0000-0000-0000"/>
									</div>
								</div>
								<div class="col">
									<div class="form-group">
										<label for="pcardcvv">CVV</label>
										<input type="text" class="form-control" id="pcardcvv" name="pcardcvv" onkeypress='return formatcvv(this, event)' onkeyup='return numberOnlyCvv(event)' placeholder="***"/>
									</div>
								</div>
							</div>
							
							<div class="form-group pfld">
								<label for="pcardexpDate">Expiration Date</label>
								<input type="text" class="form-control" id="pcardexpDate" name="pcardexpDate" onkeypress='return formatexpdate(this, event)' onkeyup='return mmyyformat(event)' placeholder="MM/YY"/>
							</div>
							
							<div class="pfld" id="pmethoderror">Error</div>
							
							<button type="button" class="btn" id="makepayment">make a payment   <i class="bi bi-arrow-right"></i></button>
														
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="loaderBackgroung" id="loaderBG">
			<div class="loader" id="loader">
				<div class="ball"></div>
				<div class="ball"></div>
				<div class="ball"></div>
				<span>Saving...</span>
			</div>
			<div class="payment-accept" id="payment-accept">
				<img class="green-check" src="../../images/success-green-check-mark.png" />
				<span>
					Payment<br>accepted
				</span>
			</div>
		</div>
	</div>
	
	<!-- Footer -->


  <footer class="text-white text-center text-lg-start" style="background-color: #23242a;">
    <!-- Grid container -->
    <div class="container p-4">
      <!--Grid row-->
      <div class="row mt-4">
        <!--Grid column-->
        <div class="col-lg-4 col-md-12 mb-4 mb-md-0">
          <h5 class="text-uppercase mb-4">About company</h5>

          <p>
            The Electro grid is the largest electricity company in Sri Lanka. With a market share of nearly 100%,
             it controls all major functions of electricity generation, transmission, distribution, and retailing in Sri Lanka.
          </p>

          <p>
            The Electro Grid is the sole supplier of electrical power in Mauritius. 
            The mandate of the utility is to prepare and carry out development schemes with the general object of promoting, 
            coordinating, and improving the generation, transmission, distribution, and sale of electricity.
          </p>

          <div class="mt-4">
            <!-- Facebook -->
            <a type="button" class="btn btn-floating btn-warning btn-lg"><i class="fab fa-facebook-f"></i></a>
            <!-- Dribbble -->
            <a type="button" class="btn btn-floating btn-warning btn-lg"><i class="fab fa-dribbble"></i></a>
            <!-- Twitter -->
            <a type="button" class="btn btn-floating btn-warning btn-lg"><i class="fab fa-twitter"></i></a>
            <!-- Google + -->
            <a type="button" class="btn btn-floating btn-warning btn-lg"><i class="fab fa-google-plus-g"></i></a>
            <!-- Linkedin -->
          </div>
        </div>
        <!--Grid column-->

        <!--Grid column-->
        <div class="col-lg-4 col-md-6 mb-4 mb-md-0">
          <h5 class="text-uppercase mb-4 pb-1">Search something</h5>

          <div class="form-outline form-white mb-4">
            <input type="text" id="formControlLg" class="form-control form-control-lg">
            <label class="form-label" for="formControlLg" style="margin-left: 0px;">Search</label>
          <div class="form-notch"><div class="form-notch-leading" style="width: 9px;"></div><div class="form-notch-middle" style="width: 48.8px;"></div><div class="form-notch-trailing"></div></div></div>

          <ul class="fa-ul" style="margin-left: 1.65em;">
            <li class="mb-3">
              <span class="fa-li"><i class="fas fa-home"></i></span><span class="ms-2">Colombo 07, Sri-Lanka</span>
            </li>
            <li class="mb-3">
              <span class="fa-li"><i class="fas fa-envelope"></i></span><span class="ms-2">info@electro.com</span>
            </li>
            <li class="mb-3">
              <span class="fa-li"><i class="fas fa-phone"></i></span><span class="ms-2">+ 94 234 567 88</span>
            </li>
            <li class="mb-3">
              <span class="fa-li"><i class="fas fa-print"></i></span><span class="ms-2">+ 94 234 567 89</span>
            </li>
          </ul>
        </div>
        <!--Grid column-->

        <!--Grid column-->
        <div class="col-lg-4 col-md-6 mb-4 mb-md-0">
          <h5 class="text-uppercase mb-4">Opening hours</h5>

          <table class="table text-center text-white">
            <tbody class="font-weight-normal">
              <tr>
                <td>Mon - Thu:</td>
                <td>8am - 9pm</td>
              </tr>
              <tr>
                <td>Fri - Sat:</td>
                <td>8am - 1am</td>
              </tr>
              <tr>
                <td>Sunday:</td>
                <td>9am - 10pm</td>
              </tr>
            </tbody>
          </table>
        </div>
        <!--Grid column-->
      </div>
      <!--Grid row-->
    </div>
    <!-- Grid container -->

    <!-- Copyright -->
    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
      © 2020 Copyright:
      <a class="text-white" href="https://mdbootstrap.com/">EGelectro.com</a>
    </div>
    <!-- Copyright -->
  </footer>
  

<!-- End of footer -->
	
</body>
</html>








