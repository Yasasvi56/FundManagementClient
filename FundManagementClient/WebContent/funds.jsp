<%@page import="com.Fund"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fund Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.3.1.min.js"></script>
<script src="Components/funds.js"></script>
</head>
<body background="white-trianglify.jpg">
<nav class="navbar  navbar-dark bg-dark">
  <h2 class="navbar-brand">Fund Management</h2>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
</nav>

<div class="col-md-12 ">
<div class="container" style="height: 463px "><div class="row"><div class="col-md-6" style="margin-top: 50px;">
<form id="formFund" name="formFund">
 User ID:
 <input id="userid" name="userid" placeholder="Enter User ID" type="text"
 class="form-control form-control-sm">
 <br> Project ID:
 <input id="projectid" name="projectid" placeholder="Enter Project ID" type="text"
 class="form-control form-control-sm">
 <br> Fund Amount:
 <input id="fundamount" name="fundamount" placeholder="Enter Fund Amount" type="text"
 class="form-control form-control-sm">
 <br> Remark:
 <input id="remark" name="remark" placeholder="Enter Remark" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidFundIDSave" name="hidFundIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success" ></div>
<div id="alertError" class="alert alert-danger" ></div>
<br></div>
<div id="divFundsGrid" style="margin-left: 50px; margin-top: 50px;"><div class="col-md-12"> 
 <%
 Fund fundObj = new Fund();
 out.print(fundObj.readFunds());
 %>
</div>
</div> </div> </div><div></div>

<footer class="bg-dark text-center " style="width: 102.2%; height: 130px ; margin-left: -15px;">
  <!-- Copyright -->
   <a class="text-light " href=#> © 2020 Copyright:tiromika.ya</a>
   
  </div>
  <!-- Copyright -->
</footer>

</body>
</html>