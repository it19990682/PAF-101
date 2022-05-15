<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../../Views/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="../../Views/notices.css" />
<script src="../../Components/jquery-3.6.0.min.js"></script>
<script src="../../Components/main.js"></script>
<script src="../../Components/noticeJS.js"></script>
<title>Notices_View</title>

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
<style>
.usertable  {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

.noticetd {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

.usertr:nth-child(even) {
  background-color: #dddddd;
}
</style>
<body>
<h1 class="mb-3 d-flex justify-content-center" > Notice Details</h1>
	<div class=container>
	<table class= "usertable">
	  <tr class="noticetr">
	  <th class="noticetd">ID</th>
	    <th class="noticetd">UserID</th>
	    <th class="noticetd">Username</th>
	    <th class="noticetd">Date</th>
	    <th class="noticetd">Time</th>
	    <th class="noticetd">Type</th>
	    <th class="noticetd">Notice</th>
	    <th class="noticetd">Action</th>
	    
	  </tr>
	  <tbody id="user_table_notice"></tbody>
	  
	<!--    
	    <td class="usertd">
	    <a href="../user/userUpdate.jsp" class=" btn btn-success" role="button">Update</a>
	    <button type="submit" class="btn btn-danger" id="remove">Delete</button>
	   
	    </td>
	    -->  
	 
	
	</table>
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