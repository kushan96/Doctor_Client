<%@page import="com.*"%>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
  
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- head code meta tag -->
<meta name ="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="Views/bootstrap.min.css">


<!-- java script files -->
<script src="components/jquery-3.2.1.min.js" ></script>
<script src="components/main1.js"></script>
<title>Doctor Management</title>
</head>
<body>
<div class="container">
	<div class="row">
	
		<div class="col-6">
<h1>Doctor Management</h1>
<form id="formItem" name="formItem">
Doctor name: <input id="dName" name="dName" type="text" class="form-control form-control"><br> 
Address: <input id="address" name="address" type="text" class="form-control form-control"><br> 
E-mail:<input id="email" name="email" type="text" class="form-control form-control"><br> 
Phone no: <input id="phoneNo" name="phoneNo" type="text" class="form-control form-control"><br>
Specialization: <input id="specialization" name="specialization" type="text" class="form-control form-control"><br> 
<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
<input type="hidden" id="hiddIDSave" name="hiddIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
<%
Doctor docObj = new Doctor();
out.print(docObj.readDoctor());
%>
</div>


</div>
</div>
</div>


</body>
</html>