package com;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {
	
	//A common method to connect to the DB
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{   
			Class.forName("com.mysql.jdbc.Driver"); 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/funddatadb", "root", ""); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		return con; 
	} 
	public String readFunds()
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
			output = "<table border='1'><tr><th width:50%>User ID</th><th>Project ID</th><th>Fund Amount</th>"
			+ "<th>Remark</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from funds";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String fundID = Integer.toString(rs.getInt("fundID"));
				String userID = Integer.toString(rs.getInt("userID")); 
				String projectID = Integer.toString(rs.getInt("projectID"));
				String famount = Double.toString(rs.getDouble("famount"));
				String remark = rs.getString("remark");
				// Add into the html table
				output += "<tr><td><input id='hidFundIDUpdate' name='hidFundIDUpdate' type='hidden' value='" + fundID
				+ "'>" + userID + "</td>";
				output += "<td>" + projectID + "</td>";
				output += "<td>" + famount + "</td>";
				output += "<td>" + remark + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn bg-success text-white ' data-itemid='" + fundID + "'></td>"
				+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-fundid='"+ fundID + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the funds.";
			System.err.println(e.getMessage());
		}
		return output;
	}
		
	public String insertFund(String userID, String projectID, String famount, String remark) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for inserting."; 
			} 
			String query = " insert into funds(`fundID`,`userID`,`projectID`,"
							+ "`famount`,`remark`,`creationdate`) values "
							+ "(?, ?, ?, ?, ?, SYSDATE())";
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setInt(2, Integer.parseInt(userID)); 
			preparedStmt.setInt(3, Integer.parseInt(projectID)); 
			preparedStmt.setFloat(4, Float.parseFloat(famount)); 
			preparedStmt.setString(5, remark);
			//preparedStmt.setInt(7, Integer.parseInt(modifiedby));
			//preparedStmt.setString(8, modifieddate);
			// execute the statement
			preparedStmt.execute();
			con.close(); 
			String newFunds = readFunds();
			output = "{\"status\":\"success\", \"data\":\"" + newFunds + "\"}"; 
		} 
		catch (Exception e) 
		{ 
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the fund.\"}";
			System.err.println(e.getMessage()); 
		} 
			return output; 
	}	
	public String updateFund(String fundID, String userID, String projectID, String famount, String remark)
	{ 
		String output = ""; 
		try
		{
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for updating."; 
			} 
			// create a prepared statement
			String query = "UPDATE funds SET userID=?,projectID=?,famount=?,remark=?,"
					+ "creationdate=SYSDATE(),modifiedby=?,modifieddate=? WHERE fundID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(userID)); 
			preparedStmt.setInt(2, Integer.parseInt(projectID)); 
			preparedStmt.setDouble(3, Double.parseDouble(famount)); 
			preparedStmt.setString(4, remark); 
			preparedStmt.setInt(8, Integer.parseInt(fundID));
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			String newFunds = readFunds();
			output = "{\"status\":\"success\", \"data\": \"" +
			newFunds + "\"}"; 
		} 
		catch (Exception e) 
		{ 
			output = "{\"status\":\"error\", \"data\":\"Error while updating the fund.\"}";
			System.err.println(e.getMessage());
		} 
		return output; 
	}
		
	public String deleteFund(String fundID) 
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
			String query = "delete from funds where fundID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(fundID)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			String newFunds = readFunds();
			output = "{\"status\":\"success\", \"data\": \"" +
			newFunds + "\"}";
		} 
		catch (Exception e) 
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the fund.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
	}
	
}
