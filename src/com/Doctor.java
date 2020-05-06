package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Doctor {
	
	public Connection connect()
	{
	java.sql.Connection con = null;
	try
	{
	Class.forName("com.mysql.jdbc.Driver");
	con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospital","root", "");
	//For testing
	System.out.print("Successfully connected");
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	
	return (Connection) con;
	
	}
	
	public String insertDoctor(String name, String address, String email,String phone,String specialization)
	{
		
	String output = "";
	
	try
	{
	Connection con = connect();
	if (con == null)
	{
	return "Error while connecting to the database";
	}
	// create a prepared statement
	String query = " insert into doctors (`dID`,`dName`,`address`,`email`,`phoneNo`,`specialization`)"
	+ " values (?, ?, ?, ?, ?, ?)";
	java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, 0);
	preparedStmt.setString(2, name);
	preparedStmt.setString(3, address);
	preparedStmt.setString(4, email);
	preparedStmt.setString(5, phone);
	preparedStmt.setString(6, specialization);
	
	//execute the statement
	preparedStmt.execute();
	con.close();
	 String newDoctor = readDoctor();    
	 output = "{\"status\":\"success\", \"data\": \"" +  newDoctor + "\"}";   
	 }   
	 catch (Exception e)   
	 {    
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the doctor.\"}"; 
	     System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	public String readDoctor()
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{
	return "Error while connecting to the database for reading.";
	}
	// Prepare the html table to be displayed
	output = "<table border=\'1\'><tr><th>Doctor Name</th><th>Address</th><th>E-mail</th><th>Phone_No</th><th>Specialization</th><th>Update</th><th>Remove</th></tr>";
	String query = "select * from doctors";
   

	java.sql.Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	
	
	// iterate through the rows in the result set
	while (rs.next())
	{
	String dID = Integer.toString(rs.getInt("dID"));
	String dName = rs.getString("dName");
	String address = rs.getString("address");
	String email = rs.getString("email");
	String phoneNo = rs.getString("phoneNO");
	String specialization = rs.getString("specialization");
	
	
	
	// Add into the html table
	
	output += "<tr><td><input id=\'hidItemIDUpdate\' name=\'hidItemIDUpdate\' type=\'hidden\' value=\'" + dID 
			+ "'>" + dName + "</td>";
	output += "<td>" + address + "</td>";
	output += "<td>" + email + "</td>";
	output += "<td>" + phoneNo + "</td>";
	output += "<td>" + specialization + "</td>";
	
	// buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
		 		+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-did='"      
		 		+ dID + "'>" + "</td></tr>";
	}
	con.close();
	// Complete the html table
	output += "</table>";
	}
	catch (Exception e)
	{
	output = "Error while reading the items.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	
	public String deleteDoctor(String dID)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{
	return "Error while connecting to the database for deleting.";
	}
	// create a prepared statement
	String query = "delete from doctors where dID=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, Integer.parseInt(dID));
	// execute the statement
	preparedStmt.execute();
	con.close();
	   String newDoctor = readDoctor();    
	   output = "{\"status\":\"success\", \"data\": \"" +  newDoctor + "\"}";    
	   }   
	  	catch (Exception e)   
	  {    
	  		output = "{\"status\":\"error\", \"data\": \"Error while deleting the doctor.\"}";    
	        System.err.println(e.getMessage());   
	  } 
	 
	  return output;  }
	
	public String updateDoctor(String ID, String name,String address, String email, String phoneNo, String specialization)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{
		return "Error while connecting to the database for updating."; }
	
	// create a prepared statement
	String query = "UPDATE doctors SET dName=?,address=?,email=?,phoneNo=?,specialization=? WHERE dID=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	
	// binding values
	preparedStmt.setString(1, name);
	preparedStmt.setString(2, address);
	preparedStmt.setString(3, email);
	preparedStmt.setString(4, phoneNo);
	preparedStmt.setString(5, specialization);
	preparedStmt.setInt(6, Integer.parseInt(ID));
	
	// execute the statement
	preparedStmt.execute();
	con.close();
	   String newDoctor = readDoctor();    
	   output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";  
	   }   
	  catch (Exception e)   
	  {    
		  output = "{\"status\":\"error\", \"data\": \"Error while updating the doctor.\"}";     
		  System.err.println(e.getMessage());   
	 } 
	 
	  return output;  
	  } 

}
