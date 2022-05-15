<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../../Views/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="../../Views/payments.css" />
<script src="../../Components/jquery-3.6.0.min.js"></script>
<script src="../../Components/main.js"></script>
<script src="../../Components/noticeJS.js"></script>
<title>Insert title here</title>
<style>
      #intro {
        background-image: url(https://mdbootstrap.com/img/new/fluid/city/008.jpg);
        height: 100%;
      }
    </style>
    
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
<body>
	
	
<body>
	
	<% 
		
		if(session.getAttribute("loginID") == null) {
			response.sendRedirect("../../");
		}
	
	%>
	
	<a href="../logout.jsp" >logout</a>
	
	<div id="intro">
	
	
	<%String uid = session.getAttribute("loginID").toString(); %>
	
	
	<form id="sendNoticeID">
		<input type="hidden" name="findpage" id="findpage" value="singlenoticedetails" />
		<input type="hidden" name="noticeUserid" id="noticeUserid" value=<%=uid %> />	
	</form>
	
	
	<div class="container">
	<div class="d-flex justify-content-center" >
		<div class="col col-xl-10" >
		<div class="card" style="border-radius: 1rem;" >
		<div class="row g-3 p-5 " >
		<div class="card-body p-4 p-lg-5 text-black">
  <h2 class="mb-3">Customer Details</h2><br>
  <div class="container">        
	<form>
  <fieldset disabled>
    <div class="row">
	  <div class="col">
	  <label for="disabledTextInput" class="form-label">User ID</label>
	    <input id="usernoticeid" type="text" class="form-control" aria-label="userid">
	  </div>
	  <div class="col mb-3">
	   <label for="disabledTextInput" class="form-label">Username</label>
	    <input id="noticeusername" type="text" class="form-control"  aria-label="username">
	  </div>
	 </div>

  </fieldset>
  
</form>
</div>
</div>
</div>
</div>
</div>
</div>
</div>

	
	<hr>
	<div class="container">
	<div class="d-flex justify-content-center" >
		<div class="col col-xl-10" >
		<div class="card" style="border-radius: 1rem;" >
		<div class="row g-3 p-5 " >
		<div class="card-body p-4 p-lg-5 text-black">

  <div class="container">
	<div class="card-group ">
  <div class="card">
     
    <div class="card-body">
      <h5 class="card-title">Notice Detail</h5>
      <hr>
       <fieldset disabled>
       <div class="row">
	  <div class="col">
	  <label for="disabledTextInput" class="form-label">Date</label>
	    <input id="noticedate" type="text" class="form-control" aria-label="date">
	  </div>
	  <div class="col mb-3">
	   <label for="disabledTextInput" class="form-label">Time</label>
	    <input id="noticetime" type="text" class="form-control"  aria-label="time">
	  </div>
	 </div>
	 
       <div class="mb-3">
      <label for="disabledTextInput" class="form-label">Notice Type</label>
      <input type="text" id="noticetype" class="form-control" >
    </div>
    <div class="mb-3">
      <label for="disabledTextInput" class="form-label">Notice</label>
      <input type="text" id="noticeid" class="form-control input-lg">
      
    </div>
    
    </fieldset>
    
    <!-- 
    <form>
    <a href="../notice/NoticeUpdate.jsp" class="d-grid gap-2 col-6 mx-auto btn btn-success"  role="button">Update</a>
    </form>
	<br>
 	
    <button type="submit" class="d-grid gap-2 col-6 mx-auto btn btn-danger" id="remove">Delete</button>
    
     -->
     
    </div>
  </div>
  <div class="card">
   
    <div class="card-body">
      <h5 class="card-title">Payment Details</h5>
       <hr>
       <fieldset >
       <div class="mb-3">
	<fieldset>
	<div class="mb-3">
      <label for="disabledTextInput" class="form-label">Paid Status</label>
      <input type="text" id="paidstatusid" class="form-control" >
  </div>
  <div class="mb-3">
  	 <form id="parseID" action="../payment/billbeforepayment.jsp"   method="post">
				<input id="puid" type="hidden" name="puid" value="<%=uid%>" />
				<input type="button" name="sendID" id="sendID" class="btn-pay btn btn-primary mb-3" value="PAYMENT" />
		</form>
	
			<form id="parseID1" action="../payment/paymentHistory.jsp" method="post">
				<input id="puid1" type="hidden" name="puid" value="<%=uid%>"/>
				<input type="button" name="sendIDtoHistory" class="btn btn-primary mb-3" id="sendIDtoHistory" value="History" />
			</form>
  </div>
 </fieldset>
 </div>
 </fieldset>
 </div>
 
 </div>
 		<div class="card">   
	    <div class="card-body">
	      <h5 class="card-title">Billing Details</h5>
	      <hr>
	      <fieldset disabled>  
			    <div class="mb-3">
			      <label for="disabledTextInput" class="form-label">Bill Amount</label>
			      <input type="text" id="billamountid" class="form-control" >
			    </div>
	    </fieldset>
	    </div>
  </div>

</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
	<form id="updateAddress" >
		<input id="puid2" type="text" name="puid2" placeholder="User id to be update" />
		<input id="pbillingAddress" type="text" name="pbillingAddress" placeholder="Address to be update" />
		<input type="button" name="sendidtoupdate" id="sendidtoupdate" value="Update Address" />
	</form>
	
	
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