package com.shoppingapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.DAO.UserDAO;
import com.DAOImpl.UserDAOImpl;
import com.aspect.DBConnection;
import com.beans.Admin;
import com.beans.User;
import com.services.LoginServices;
import com.servicesImpl.LoginServicesImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action"); // signup, signin, forgot, logout

        switch (action) {
            case "signin":
                handleSignIn(request, response);
                break;
            case "signup":
                handleSignUp(request, response);
                break;
            case "logout":
                handleLogout(request, response);
                break;
            case "reset":   // ✅ add this
                handleResetPassword(request, response);
                break;
            case "forgot":   // ✅ add this
                handleForgotPassword(request, response);
                break;
            // keep signup/forgot if you need them
            default:
                response.sendRedirect("error.jsp");
        }
    }

//    private void handleSignIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String role = request.getParameter("role"); // "user" or "admin"
//
//        try (Connection con = DBConnection.getSQLConnection()) {
//            PreparedStatement ps;
//            if ("admin".equalsIgnoreCase(role)) {
//                ps = con.prepareStatement("SELECT * FROM admins WHERE username=? AND password=?");
//            } else {
//                ps = con.prepareStatement("SELECT * FROM usertable WHERE username=? AND password=?");
//            }
//            ps.setString(1, username);
//            ps.setString(2, password);
//
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                HttpSession session = request.getSession();
//                session.setMaxInactiveInterval(30 * 60);
//
//                if ("admin".equalsIgnoreCase(role)) {
//                    // ✅ Build Admin bean
//                    com.beans.Admin admin = new Admin();
//                    admin.setUsername(rs.getString("username"));
//                    admin.setPassword(rs.getString("password"));
//                    // set other admin fields if needed
//                    session.setAttribute("admin", admin);
//                    response.sendRedirect("adminDashboard.jsp");
//                } else {
//                    // ✅ Build User bean
//                    User user = new User();
//                    user.setId(rs.getInt("id"));
//                    user.setUsername(rs.getString("username"));
//                    user.setPassword(rs.getString("password"));
//                    user.setEmail(rs.getString("email"));       
//                    user.setMobileno(rs.getString("mobileno"));
//                    user.setAddress(rs.getString("address")); 
//                    // set other user fields if needed
//                    session.setAttribute("user", user);
//                    response.sendRedirect("ProductsServlet?action=viewAll");
//                }
//            } else {
//                response.sendRedirect("login.jsp?error=1");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendRedirect("login.jsp?error=server");
//        }
//    }
    
//    private void handleSignIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String role = request.getParameter("role"); // "user" or "admin"
//
//        try (Connection con = DBConnection.getSQLConnection()) {
//            PreparedStatement ps;
//            if ("admin".equalsIgnoreCase(role)) {
//                ps = con.prepareStatement("SELECT * FROM admins WHERE username=? AND password=?");
//            } 
//            else {
//                // ✅ Only allow active users
//                ps = con.prepareStatement("SELECT * FROM usertable WHERE username=? AND password=? AND status='active'");
//            }
//            ps.setString(1, username);
//            ps.setString(2, password);
//
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                HttpSession session = request.getSession();
//                //session.setMaxInactiveInterval(30 * 60);
//
//                if ("admin".equalsIgnoreCase(role)) {
//                    Admin admin = new Admin();
//                    admin.setUsername(rs.getString("username"));
//                    admin.setPassword(rs.getString("password"));
//                    session.setAttribute("admin", admin);
//                    response.sendRedirect("adminDashboard.jsp?login=success");
//                } else {
//                    User user = new User();
//                    user.setId(rs.getInt("id"));
//                    user.setUsername(rs.getString("username"));
//                    user.setPassword(rs.getString("password"));
//                    user.setEmail(rs.getString("email"));
//                    user.setMobileno(rs.getString("mobileno"));
//                    user.setAddress(rs.getString("address"));
//                    user.setStatus(rs.getString("status")); // ✅ store status in bean
//                    session.setAttribute("user", user);
//                    response.sendRedirect("ProductsServlet?action=viewAll&login=success");
//                }
//            } else {
//                // ✅ If blocked, query returns no rows
//                response.sendRedirect("login.jsp?login=blocked");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendRedirect("login.jsp?error=server");
//        }
//    }
    
    private void handleSignIn(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role"); // "user" or "admin"

        try (Connection con = DBConnection.getSQLConnection()) {

            // =========================================================
            // ✅ ADMIN LOGIN
            // =========================================================
            if ("admin".equalsIgnoreCase(role)) {

                String sql = "SELECT * FROM admins WHERE username=? AND password=?";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    HttpSession session = request.getSession();

                    Admin admin = new Admin();
                    admin.setUsername(rs.getString("username"));
                    admin.setPassword(rs.getString("password"));

                    session.setAttribute("admin", admin);

                    response.sendRedirect("adminDashboard.jsp?login=success");

                } else {
                    // ❌ Wrong admin credentials
                    response.sendRedirect("login.jsp?login=wrong");
                }

                return;
            }

            // =========================================================
            // ✅ USER LOGIN
            // =========================================================

            // Step 1: Check username + password (WITHOUT status)
            String sql = "SELECT * FROM usertable WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String status = rs.getString("status");

                // ❌ BLOCKED USER
                if ("blocked".equalsIgnoreCase(status)) {
                    response.sendRedirect("login.jsp?login=blocked");
                    return;
                }

                // ✅ SUCCESS LOGIN
                HttpSession session = request.getSession();

                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setMobileno(rs.getString("mobileno"));
                user.setAddress(rs.getString("address"));
                user.setStatus(status);

                session.setAttribute("user", user);

                response.sendRedirect("ProductServlet?action=viewAll&login=success");

            } else {
                // ❌ WRONG USERNAME OR PASSWORD
                response.sendRedirect("login.jsp?login=wrong");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=server");
        }
    }


    
    
//    private void handleSignUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
//      
//    	 	UserDAO userDAO = new UserDAOImpl(DBConnection.getSQLConnection()); // adjust to your connection code
//    	    LoginServices loginServices = new LoginServicesImpl(userDAO);
//    	boolean success = LoginServices.signUp(
//      		
//          0, request.getParameter("username"),
//          request.getParameter("password"),
//          request.getParameter("email"),
//          request.getParameter("mobileno"),
//          request.getParameter("address"),
//          request.getParameter("securityques"),
//          request.getParameter("secquesans")
//          
//      );
//      if (success) {
//          response.sendRedirect("login.jsp?signup=success");
//      } else {
//          response.sendRedirect("register.jsp?error=1");
//      }
//  }
    
    private void handleSignUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDAO userDAO = new UserDAOImpl(DBConnection.getSQLConnection());
        LoginServices loginServices = new LoginServicesImpl(userDAO);

        boolean success = loginServices.signUp(   // ✅ instance call
            0,
            request.getParameter("username"),
            request.getParameter("password"),
            request.getParameter("email"),
            request.getParameter("mobileno"),
            request.getParameter("address"),
            request.getParameter("securityques"),
            request.getParameter("secquesans")
        );

        if (success) {
            response.sendRedirect("login.jsp?signup=success");
        } else {
            response.sendRedirect("register.jsp?error=1");
        }
    }

    
    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp?logout=success");
    }
    
    private void handleResetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");

        UserDAO userDAO = new UserDAOImpl(DBConnection.getSQLConnection());
        boolean success = userDAO.updatePassword(username, newPassword);

        if (success) {
            response.sendRedirect("login.jsp?reset=success");
        } else {
            response.sendRedirect("reset.jsp?error=1");
        }
    }

    
    private void handleForgotPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String securityQues = request.getParameter("securityques");
        String secQuesAns = request.getParameter("secquesans");

        UserDAO userDAO = new UserDAOImpl(DBConnection.getSQLConnection());
        String password = userDAO.forgotPassword(username, securityQues, secQuesAns);

        if (password != null) {
            // Option 1: Show password directly (not secure, but matches your DAO)
            response.getWriter().println("Your password is: " + password);

            // Option 2 (better): Redirect to login with a success flag
            // response.sendRedirect("login.jsp?forgot=success");
        } else {
            response.sendRedirect("forgot.jsp?error=1");
        }
    }

    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


}

