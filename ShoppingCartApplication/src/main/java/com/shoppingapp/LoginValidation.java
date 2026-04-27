package com.shoppingapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aspect.DBConnection;
import com.beans.User;

/**
 * Servlet implementation class LoginValidation
 */
@WebServlet("/LoginValidation")
public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginValidation() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
	    if ("logout".equalsIgnoreCase(action)) {
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.invalidate(); // clear session
	        }
	        response.sendRedirect("login.jsp?message=loggedout");
	        return; // stop here so login code doesn’t run
	    }
		
		
		
		String username=request.getParameter("username");
		String password=request.getParameter("password"); 
		PrintWriter out=response.getWriter();
		
		response.setContentType("text/html");
		
		Connection con=null;
		try {
			con=DBConnection.getSQLConnection();
			String insertQuery="SELECT * FROM usertable WHERE username=? AND password=?";
			PreparedStatement psmt=con.prepareStatement(insertQuery);
			
			psmt.setString(1, username);
			psmt.setString(2, password);
			
			ResultSet res=psmt.executeQuery();
			
			if(res.next()) {
//				response.sendRedirect("product.jsp");
//				response.getWriter().print("<h1>Login Succesfull<h1>");
				
				HttpSession ses=request.getSession();
				ses.setAttribute("user", username);
				
				response.sendRedirect("products.jsp");
			}else {
//				response.getWriter().print("<h1>Login Failed<h1>");
				response.getWriter().print("<h1>Invalid Credentials..TRY AGAIN</h1>");
				out.println("<a href='login.jsp'>Back To Login Page</a>");
				
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//	}
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        PrintWriter out = response.getWriter();
	        response.setContentType("text/html");

	        Connection con = null;
	        try {
	            con = DBConnection.getSQLConnection();
	            String query = "SELECT * FROM usertable WHERE username=? AND password=?";
	            PreparedStatement psmt = con.prepareStatement(query);
	            psmt.setString(1, username);
	            psmt.setString(2, password);

	            ResultSet res = psmt.executeQuery();

	            if (res.next()) {
	                // Build User object from DB result
	                User user = new User();
	                user.setId(res.getInt("id"));                // auto-generated id from DB
	                user.setUsername(res.getString("username"));
	                user.setPassword(res.getString("password"));
	                user.setEmail(res.getString("email"));
	                user.setMobileno(res.getString("mobileno"));
	                user.setSecurityques(res.getString("securityques"));
	                user.setSecquesans(res.getString("secquesans"));

	                //Store full user object in session
	                HttpSession ses = request.getSession();
	                ses.setAttribute("user", user);

	                response.sendRedirect("products.jsp");
	            } else {
	                out.println("<h1>Invalid Credentials..TRY AGAIN</h1>");
	                out.println("<a href='login.jsp'>Back To Login Page</a>");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (con != null) con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	
	

}
