package com.intern.screen;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/customer")

public class screen extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/intern","root","root");
			
			 printWriter.print ("<caption><b>Employee Details:</b></caption>");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from cust;");
			
			printWriter.print ("<table width=90% border=1>");
             
            printWriter.print ("</br></br>");
            
            printWriter.println(" <a href=\"http://localhost:8080/Intern/details.html\">\r\n"
            		+ "                <input type=\"button\" value=\"Add Customer\" style=\"background-color: deepskyblue;\">\r\n" 
            		+ "           </a> ");
            
            printWriter.print ("</br></br>");
            
            ResultSetMetaData rsmd = resultSet.getMetaData ();
            int total = rsmd.getColumnCount ();
            
            printWriter.print ("<tr>");
            
            for (int i = 1; i <= total; i++)
            {
                printWriter.print ("<th>" + rsmd.getColumnName (i) + "</th>");
            }
            
            printWriter.print ("<th>Action</th>");
            
            printWriter.print ("</tr>");
			
            while (resultSet.next ())
            {
                printWriter.print ("<tr><td>" + resultSet.getString (1) + "</td><td>" + resultSet.getString (2) + " </td><td>" + resultSet.getString(3) + "</td><td>"+ resultSet.getString(4) + "</td><td>" + resultSet.getString(5) + "</td><td>" + resultSet.getString(6) + "</td><td>"+ resultSet.getString(7)+ "</td><td>" + resultSet.getString(8) + "</td><td><a href='http://localhost:8080/Intern/update2.html'><button type=\"button\" style=\"background-color:green;\">edit</button></a>" + "</td><td><a href='delRecords?phone="+resultSet.getString(8)+"'><button type=\"button\" style=\"background-color:red;\">delete</button></a>" + "</td></tr>");
            }
             printWriter.print ("</table>");
           
			
			resultSet.close();
			connection.close();
			
		} catch (Exception e) {
			System.out.println(e);
			printWriter.println("Server Problem !!");
		}
		finally
        {
			printWriter.close();
        }
	}

}
