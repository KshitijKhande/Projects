package com.shoppingapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.aspect.DBConnection;
import com.beans.User;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/userservlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("updatePersonalInfo".equals(action)) {
            handleUpdatePersonalInfo(request, response);
        }
        // other actions...
    }

    private void handleUpdatePersonalInfo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp?error=session");
            return;
        }

        User user = (User) session.getAttribute("user");

        // Collect updated fields
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String mobileno = request.getParameter("mobileno");
        String address=request.getParameter("address");

        try (Connection con = DBConnection.getSQLConnection()) {
            String sql = "UPDATE usertable SET username=?, email=?, password=?, mobileno=?,address=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, mobileno);
            ps.setString(5, address);
            ps.setInt(6, user.getId());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                // Update session object too
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);
                user.setMobileno(mobileno);
                session.setAttribute("user", user);

                response.sendRedirect("personalDetails.jsp?update=success");
            } else {
                response.sendRedirect("personalDetails.jsp?update=fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("personalDetails.jsp?update=error");
        }
    }
}
