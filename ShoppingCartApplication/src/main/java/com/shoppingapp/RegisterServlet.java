package com.shoppingapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.aspect.DBConnection;


@WebServlet("/registration")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("user");
		String password=request.getParameter("pass");
		String email=request.getParameter("email");
		String mobileno=request.getParameter("mobileno");
		String address=request.getParameter("address");
		
		
		Connection con=null;
		try {
			con=DBConnection.getSQLConnection();
			String insertQuery="INSERT INTO USERTABLE(username,password,email,mobileno,address) VALUES(?,?,?,?,?)";
			PreparedStatement psmt=con.prepareStatement(insertQuery);
			
			psmt.setString(1, username);
			psmt.setString(2, password);
			psmt.setString(3, email);
			psmt.setString(4, mobileno);
			psmt.setString(5, address);
			
			int res=psmt.executeUpdate();
			
			if(res>0) {
				response.sendRedirect("login.jsp");
			}else {
				response.getWriter().print("<h1>Registrstion Failed<h1>");
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
