package com.intern.addCust;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class addCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String fnameString = request.getParameter("fname");
		String lnameString = request.getParameter("lname");
		String streetString = request.getParameter("street");
		String addressString = request.getParameter("address");
		String cityString = request.getParameter("city");
		String stateString = request.getParameter("state");
		String emailString = request.getParameter("email");
		String phoneString = request.getParameter("phone");
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/intern","root","root");
			
			preparedStatement = connection.prepareStatement("insert into cust values(?,?,?,?,?,?,?,?);");
			preparedStatement.setString(1,fnameString);
			preparedStatement.setString(2, lnameString);
			preparedStatement.setString(3, streetString);
			preparedStatement.setString(4, addressString);
			preparedStatement.setString(5, cityString);
			preparedStatement.setString(6, stateString);
			preparedStatement.setString(7, emailString);
			preparedStatement.setString(8, phoneString);
			
			int status = -1;
			
			try {
				status = preparedStatement.executeUpdate();
			} catch (Exception e) {
				printWriter.println("Record already exist!!");
			}
			
			if(status==1) {
				RequestDispatcher rDispatcher = request.getRequestDispatcher("/screen");
				rDispatcher.forward(request, response);
			}
			
			connection.close();
			
		} catch (Exception e) {
			System.out.println(e);
			printWriter.println("Server problem!!");
		}
		
		printWriter.close();
		
	}

}
