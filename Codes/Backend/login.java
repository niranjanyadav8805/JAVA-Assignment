package com.intern.login;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		
		String userString = request.getParameter("uname");
		String passString = request.getParameter("password");
		
		if(userString.equals("test@sunbasedata.com") && passString.equals("Test@123")) {
			RequestDispatcher rDispatcher = request.getRequestDispatcher("/screen");
			rDispatcher.forward(request, response);
		}
		else {
			printWriter.println("Error");
		}
		
		
	}

}
