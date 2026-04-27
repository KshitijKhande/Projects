//package com.shoppingapp;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.util.List;
//
//import com.DAO.OrderDAO;
//import com.beans.Order;
//import com.beans.User;
//import com.DAOImpl.*;
//import com.aspect.DBConnection;
//
//@WebServlet("/OrderHistoryServlet")
//public class OrderHistoryServlet extends HttpServlet {
//    private OrderDAO orderDAO;
//
//    @Override
//    public void init() throws ServletException {
//        orderDAO = new OrderDAOImpl(DBConnection.getSQLConnection());
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("user") == null) {
//            response.sendRedirect("login.jsp?error=session");
//            return;
//        }
//
//        User user = (User) session.getAttribute("user");
//        List<Order> orders = orderDAO.getOrdersByUserId(user.getId());  // ✅ use instance
//
//        System.out.println("Orders fetched: " + orders.size()); // debug
//        request.setAttribute("orders", orders);
//        request.getRequestDispatcher("orderHistory.jsp").forward(request, response);
//    }
//}

package com.shoppingapp;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.*;

import com.DAO.OrderDAO;
import com.DAOImpl.OrderDAOImpl;
import com.beans.Order;
import com.beans.User;
import com.aspect.DBConnection;

@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {

    private OrderDAO orderDAO;

    @Override
    public void init() {
        orderDAO = new OrderDAOImpl(DBConnection.getSQLConnection());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp?error=session");
            return;
        }

        User user = (User) session.getAttribute("user");

        List<Order> orderList = orderDAO.getOrdersByUserId(user.getId());

        // ✅ GROUP BY ORDER ID
        Map<Integer, List<Order>> groupedOrders = new LinkedHashMap<>();

        for (Order o : orderList) {
            groupedOrders.computeIfAbsent(o.getOrderId(), k -> new ArrayList<>()).add(o);
        }

        request.setAttribute("groupedOrders", groupedOrders);

        request.getRequestDispatcher("orderHistory.jsp").forward(request, response);
    }
}
