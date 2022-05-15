<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" type="text/css" href="../../Views/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="../../Views/user.css" />
<script src="../../Components/jquery-3.6.0.min.js"></script>
<script src="../../Components/main.js"></script>
<script src="../../Components/userJS.js"></script>
<title>SignUp</title>
<style>
#signupinto {
background-image: url("https://wallpapercave.com/wp/wp2857841.png");
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
<div id="signupinto">
<input type="hidden" id="pageselector" value="signuppage" />

	<div id="intro">
		<div class="container">
		<br>  
		<hr>
		
			<div class="card bg-light">
				<article class="card-body mx-auto" style="width: 600px;">
					<h4 class="card-title mt-3 text-center mb-3">Create Account</h4>
					<div class="d-flex justify-content-center">
					<p>
						<a href="https://accounts.google.com/servicelogin/signinchooser?flowName=GlifWebSignIn&flowEntry=ServiceLogin" class="btn btn-twitter"> <i class="fab fa-twitter"></i>   Sign in with Google</a>
						<a href="https://www.facebook.com/" class="btn  btn-facebook"> <i class="fab fa-facebook-f"></i>   Sign in with facebook</a>
					</p>
					</div>
					<p class="divider-text">
				        <span class="bg-light">OR</span>
				    </p>
					<form id="insertUserDetailsForm">
					
					<div class="form-group input-group">
				        <input name="cusname" class="form-control" placeholder="Enter your Name" type="text" id="cusname">
				    </div> <!-- form-group// -->
				    <div class="user_error hide" id="signupnameerror">Error</div>
				    <div class="form-group input-group">
				        <input name="cusaddress" class="form-control" placeholder="Enter your Address" type="text" id="cusaddress">
				    </div>
				    <div class="user_error hide" id="signupaddresserror">Error</div>
				     <div class="form-group input-group">
				        <input name="cusaccno" class="form-control" placeholder="Enter your AccountNumber" type="text" id="cusaccno">
				    </div>
				    <div class="user_error hide" id="signupaccnoerror">Error</div>
				     <div class="form-group input-group">
				        <input name="cusnic" class="form-control" placeholder="Enter your NIC Number" type="text" id="cusnic">
				    </div> 
				    <div class="user_error hide" id="signupnicerror">Error</div>
				     <div class="form-group input-group">
				        <input name="cusemail" class="form-control" placeholder="Email" type="email" id= "cusemail">
				    </div> 
				    <div class="user_error hide" id="signupemailerror">Error</div>
				    
				    <!-- form-group// -->
				    <div class="form-group input-group">
				    	<input name="cusphone" class="form-control" placeholder="Phone number" type="text" id="cusphone">
				    </div> <!-- form-group// -->
				    <div class="user_error hide" id="signuppnumerror">Error</div>
				    
				    <div class="form-group input-group">
						<select class="form-control" id="custype" name="custype">
							<option value="" >Select User Type</option>
							<option value="User">User</option>
							<option value="Manager">Manager</option>
						</select>
					</div> <!-- form-group end.// -->
					<div class="user_error hide" id="signuptypeerror">Error</div>
					
				    <div class="form-group input-group">
				        <input class="form-control" placeholder="Enter your username" type="text" id="cususername" name="cususername">
				    </div> <!-- form-group// -->
				    <div class="user_error hide" id="signupunameerror">Error</div>
				    <div class="form-group input-group mb-3">
				        <input class="form-control" placeholder="Enter your password" type="password" id="cuspassword" name="cuspassword">
				    </div> <!-- form-group// -->  
				    <div class="user_error hide" id="signuppasserror">Error</div>
				     <input type="hidden" value="insert" name="method" />                               
				    <div class="form-group d-flex justify-content-center">
				        <button type="button" id="createaccount" class="btn btn-primary d-grid gap-2 col-6 mx-auto  "> Create Account  </button>
				    </div> <!-- form-group// -->      
				    <p class="text-center">Have an account? <a href="../../">Log In</a> </p>                                                                 
				</form>
				</article>
			</div> <!-- card.// -->
			
		</div> 
		<!--container end.//-->
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