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
//import java.util.ArrayList;
//import java.util.List;
//
//import com.beans.Product;
//import com.beans.User;
//import com.beans.Order;
//import com.services.ProductServices;
//import com.servicesImpl.ProductServicesImpl;
//import com.DAO.OrderDAO;
//import com.DAOImpl.OrderDAOImpl;
//import com.DAOImpl.ProductDAOImpl;
//import com.aspect.DBConnection;
//
//@WebServlet("/OrderServlet")
//public class OrderServlet extends HttpServlet {
//    private ProductServices productServices;
//
//    @Override
//    public void init() throws ServletException {
//        productServices = new ProductServicesImpl(new ProductDAOImpl(DBConnection.getSQLConnection()));
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("user") == null) {
//            response.sendRedirect("login.jsp?error=session");
//            return;
//        }
//
//        User user = (User) session.getAttribute("user");
//        String paymentMethod = request.getParameter("paymentMethod");
//
//        // --- Card confirmation flow ---
//        if ("card".equalsIgnoreCase(paymentMethod) && session.getAttribute("pendingOrders") != null) {
//            List<Order> pendingOrders = (List<Order>) session.getAttribute("pendingOrders");
//            String cardNumber = request.getParameter("cardNumber");
//
//            OrderDAO orderDAO = new OrderDAOImpl(DBConnection.getSQLConnection());
//            for (Order order : pendingOrders) {
//                if (cardNumber != null && cardNumber.length() >= 4) {
//                    order.setPaymentMethod("Card ****" + cardNumber.substring(cardNumber.length() - 4));
//                }
//                order.setStatus("Confirmed");
//                orderDAO.addOrder(order);
//            }
//
//            session.removeAttribute("pendingOrders");
//            request.setAttribute("purchasedItems", session.getAttribute("purchasedItems"));
//            request.setAttribute("message", "Order placed successfully with Credit Card!");
//            request.getRequestDispatcher("orderConfirmation.jsp").forward(request, response);
//            return;
//        }
//
//        // --- Checkout flow ---
//        List<Product> purchasedItems = new ArrayList<>();
//        String productIdParam = request.getParameter("productId");
//        String quantityParam = request.getParameter("quantity");
//
//        if (productIdParam != null && request.getParameter("fullname") == null) {
//            // Stage 1: Buy Now clicked, but checkout form not yet filled
//            int productId = Integer.parseInt(productIdParam);
//            int quantity = Integer.parseInt(quantityParam);
//            Product product = productServices.getProductById(productId);
//            product.setQuantity(quantity);
//            purchasedItems.add(product);
//
//            session.setAttribute("buyNowItems", purchasedItems);
//            request.getRequestDispatcher("checkout.jsp").forward(request, response);
//            return;
//        } else {
//            // Stage 2: Checkout form submitted (cart or buy now)
//            List<Product> cart = (List<Product>) session.getAttribute("cart");
//            if (cart != null && productIdParam == null) {
//                purchasedItems.addAll(cart);
//                session.removeAttribute("cart");
//            } else if (session.getAttribute("buyNowItems") != null) {
//                purchasedItems.addAll((List<Product>) session.getAttribute("buyNowItems"));
//                session.removeAttribute("buyNowItems");
//            }
//        }
//
//        // Collect checkout details
//        String fullname = request.getParameter("fullname");
//        String address = request.getParameter("address");
//        String mobileno = request.getParameter("mobileno");
//
//        // --- Handle payment flows ---
//        if ("card".equalsIgnoreCase(paymentMethod)) {
//            List<Order> pendingOrders = new ArrayList<>();
//            for (Product p : purchasedItems) {
//                Order order = new Order();
//                order.setUserId(user.getId());
//                order.setFullname(fullname);
//                order.setAddress(address);
//                order.setMobileno(mobileno);
//                order.setPaymentMethod("Card"); // temporary
//                order.setStatus("Pending");
//                order.setProductId(p.getId());        // ✅ use Product.getId()
//                order.setQuantity(p.getQuantity());
//                pendingOrders.add(order);
//            }
//            session.setAttribute("pendingOrders", pendingOrders);
//            session.setAttribute("purchasedItems", purchasedItems);
//            request.getRequestDispatcher("cards.jsp").forward(request, response);
//            return;
//        }
//
//        // COD or UPI flow → insert immediately
//        OrderDAO orderDAO = new OrderDAOImpl(DBConnection.getSQLConnection());
//        for (Product p : purchasedItems) {
//            Order order = new Order();
//            order.setUserId(user.getId());
//            order.setFullname(fullname);
//            order.setAddress(address);
//            order.setMobileno(mobileno);
//            order.setPaymentMethod(paymentMethod);
//            order.setStatus("Placed");
//            order.setProductId(p.getId());        // ✅ consistent
//            order.setQuantity(p.getQuantity());
//            orderDAO.addOrder(order);
//
//            System.out.println("Persisting order: productId=" + p.getId() + ", quantity=" + p.getQuantity());
//        }
//
//        request.setAttribute("purchasedItems", purchasedItems);
//        request.setAttribute("message", "Order placed successfully!");
//        request.getRequestDispatcher("orderConfirmation.jsp").forward(request, response);
//    }
//}
//


//package com.shoppingapp;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.*;
//import java.io.IOException;
//import java.util.*;
//
//import com.beans.Product;
//import com.beans.User;
//import com.beans.Order;
//import com.services.ProductServices;
//import com.servicesImpl.ProductServicesImpl;
//import com.DAO.OrderDAO;
//import com.DAOImpl.OrderDAOImpl;
//import com.DAOImpl.ProductDAOImpl;
//import com.aspect.DBConnection;
//
//@WebServlet("/OrderServlet")
//public class OrderServlet extends HttpServlet {
//    private ProductServices productServices;
//
//    @Override
//    public void init() throws ServletException {
//        productServices = new ProductServicesImpl(new ProductDAOImpl(DBConnection.getSQLConnection()));
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("user") == null) {
//            response.sendRedirect("login.jsp?error=session");
//            return;
//        }
//
//        User user = (User) session.getAttribute("user");
//        String paymentMethod = request.getParameter("paymentMethod");
//
//        List<Product> purchasedItems = (List<Product>) session.getAttribute("cart");
//        if (purchasedItems == null) purchasedItems = new ArrayList<>();
//
//        String fullname = request.getParameter("fullname");
//        String address = request.getParameter("address");
//        String mobileno = request.getParameter("mobileno");
//
//        OrderDAO orderDAO = new OrderDAOImpl(DBConnection.getSQLConnection());
//        ProductDAOImpl productDAO = new ProductDAOImpl(DBConnection.getSQLConnection());
//
//        for (Product p : purchasedItems) {
//            Product dbProduct = productDAO.findById(p.getId());
//
//            if (p.getQuantity() > dbProduct.getQuantity()) {
//                response.sendRedirect("cart.jsp?error=outOfStock&productId=" + p.getId());
//                return;
//            }
//
//            Order order = new Order();
//            order.setUserId(user.getId());
//            order.setFullname(fullname);
//            order.setAddress(address);
//            order.setMobileno(mobileno);
//            order.setPaymentMethod(paymentMethod);
//            order.setStatus("Placed");
//            order.setProductId(p.getId());
//            order.setQuantity(p.getQuantity());
//
////            boolean success = orderDAO.addOrder(order);
////            if (success) {
////                productDAO.updateQuantity(p.getId(), dbProduct.getQuantity() - p.getQuantity());
////            }
//            orderDAO.addOrder(order);
//         // then continue with stock deduction
//         productDAO.updateQuantity(p.getId(), dbProduct.getQuantity() - p.getQuantity());
//
//        }
//
//        session.removeAttribute("cart");
//        request.setAttribute("purchasedItems", purchasedItems);
//        request.setAttribute("message", "Order placed successfully!");
//        request.getRequestDispatcher("orderConfirmation.jsp").forward(request, response);
//    }
//}
//


//package com.shoppingapp;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.*;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.util.*;
//
//import com.beans.Product;
//import com.beans.User;
//import com.beans.Order;
//import com.DAO.OrderDAO;
//import com.DAOImpl.OrderDAOImpl;
//import com.DAOImpl.ProductDAOImpl;
//import com.aspect.DBConnection;
//
//@WebServlet("/OrderServlet")
//public class OrderServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        HttpSession session = request.getSession(false);
//
//        if (session == null || session.getAttribute("user") == null) {
//            response.sendRedirect("login.jsp?error=session");
//            return;
//        }
//
//        User user = (User) session.getAttribute("user");
//
//        List<Product> purchasedItems = (List<Product>) session.getAttribute("cart");
//        if (purchasedItems == null || purchasedItems.isEmpty()) {
//            response.sendRedirect("cart.jsp?error=emptyCart");
//            return;
//        }
//
//        String fullname = request.getParameter("fullname");
//        String address = request.getParameter("address");
//        String mobileno = request.getParameter("mobileno");
//        String paymentMethod = request.getParameter("paymentMethod");
//
//        Connection conn = DBConnection.getSQLConnection();
//
//        try {
//            conn.setAutoCommit(false); // ✅ TRANSACTION START
//
//            OrderDAO orderDAO = new OrderDAOImpl(conn);
//            ProductDAOImpl productDAO = new ProductDAOImpl(conn);
//
//            // ✅ 1. Create ONE order
//            Order order = new Order();
//            order.setUserId(user.getId());
//            order.setFullname(fullname);
//            order.setAddress(address);
//            order.setMobileno(mobileno);
//            order.setPaymentMethod(paymentMethod);
//            order.setStatus("Placed");
//
//            int orderId = orderDAO.createOrder(order);
//
//            if (orderId == 0) {
//                conn.rollback();
//                response.sendRedirect("cart.jsp?error=orderFailed");
//                return;
//            }
//
//            // ✅ 2. Insert ALL items under SAME order_id
//            for (Product p : purchasedItems) {
//
//                Product dbProduct = productDAO.findById(p.getId());
//
//                if (p.getQuantity() > dbProduct.getQuantity()) {
//                    conn.rollback();
//                    response.sendRedirect("cart.jsp?error=outOfStock&productId=" + p.getId());
//                    return;
//                }
//
//                // insert into order_items
//                orderDAO.addOrderItem(orderId, p.getId(), p.getQuantity(), dbProduct.getPrice());
//
//                // update stock
//                productDAO.updateQuantity(
//                        p.getId(),
//                        dbProduct.getQuantity() - p.getQuantity()
//                );
//            }
//
//            conn.commit(); // ✅ SUCCESS
//
//            session.removeAttribute("cart");
//
//            request.setAttribute("message", "Order placed successfully!");
//            request.setAttribute("orderId", orderId);
//            request.setAttribute("purchasedItems", purchasedItems);
//
//            request.getRequestDispatcher("orderConfirmation.jsp").forward(request, response);
//
//        } catch (Exception e) {
//            try {
//                conn.rollback(); // ❌ FAILURE
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            e.printStackTrace();
//            response.sendRedirect("cart.jsp?error=exception");
//        }
//    }
//}



//package com.shoppingapp;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.*;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.util.List;
//
//import com.beans.Product;
//import com.beans.User;
//import com.beans.Order;
//import com.DAO.OrderDAO;
//import com.DAOImpl.OrderDAOImpl;
//import com.DAOImpl.ProductDAOImpl;
//import com.aspect.DBConnection;
//
//@WebServlet("/OrderServlet")
//public class OrderServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        HttpSession session = request.getSession(false);
//
//        // ✅ 1. Session validation
//        if (session == null || session.getAttribute("user") == null) {
//            response.sendRedirect("login.jsp?error=session");
//            return;
//        }
//
//        User user = (User) session.getAttribute("user");
//
//        // ✅ 2. Cart validation
//        List<Product> cart = (List<Product>) session.getAttribute("cart");
//
//        if (cart == null || cart.isEmpty()) {
//            response.sendRedirect("cart.jsp?error=emptyCart");
//            return;
//        }
//
//        String fullname = request.getParameter("fullname");
//        String address = request.getParameter("address");
//        String mobileno = request.getParameter("mobileno");
//        String paymentMethod = request.getParameter("paymentMethod");
//
//        String cardNumber = request.getParameter("cardNumber");
//
//        // =====================================================
//        // ✅ STEP 1: Redirect to card page if needed
//        // =====================================================
//        if ("card".equalsIgnoreCase(paymentMethod) &&
//            (cardNumber == null || cardNumber.trim().isEmpty())) {
//
//            request.setAttribute("fullname", fullname);
//            request.setAttribute("address", address);
//            request.setAttribute("mobileno", mobileno);
//
//            request.getRequestDispatcher("cards.jsp").forward(request, response);
//            return;
//        }
//
//        Connection conn = DBConnection.getSQLConnection();
//
//        try {
//            conn.setAutoCommit(false);
//
//            OrderDAO orderDAO = new OrderDAOImpl(conn);
//            ProductDAOImpl productDAO = new ProductDAOImpl(conn);
//
//            // =====================================================
//            // ✅ STEP 2: Create order
//            // =====================================================
//            Order order = new Order();
//            order.setUserId(user.getId());
//            order.setFullname(fullname);
//            order.setAddress(address);
//            order.setMobileno(mobileno);
//
//            // ✅ Payment handling
//            if ("card".equalsIgnoreCase(paymentMethod)) {
//
//                if (cardNumber == null || cardNumber.length() < 4) {
//                    response.sendRedirect("cards.jsp?error=invalidCard");
//                    return;
//                }
//
//                order.setPaymentMethod("Card ****" +
//                        cardNumber.substring(cardNumber.length() - 4));
//
//            } else {
//                order.setPaymentMethod(paymentMethod);
//            }
//
//            order.setStatus("Placed");
//
//            int orderId = orderDAO.createOrder(order);
//
//            if (orderId == 0) {
//                conn.rollback();
//                response.sendRedirect("cart.jsp?error=orderFailed");
//                return;
//            }
//
//            // =====================================================
//            // ✅ STEP 3: Insert order items
//            // =====================================================
//            double cartTotal = 0;
//
//            for (Product p : cart) {
//
//                Product dbProduct = productDAO.findById(p.getId());
//
//                if (p.getQuantity() > dbProduct.getQuantity()) {
//                    conn.rollback();
//                    response.sendRedirect("cart.jsp?error=outOfStock");
//                    return;
//                }
//
//                double price = dbProduct.getPrice();
//
//                orderDAO.addOrderItem(
//                        orderId,
//                        p.getId(),
//                        p.getQuantity(),
//                        price
//                );
//
//                productDAO.updateQuantity(
//                        p.getId(),
//                        dbProduct.getQuantity() - p.getQuantity()
//                );
//
//                cartTotal += price * p.getQuantity();
//            }
//
//            conn.commit();
//
//            // =====================================================
//            // ✅ STEP 4: Cleanup + forward
//            // =====================================================
//            session.removeAttribute("cart");
//
//            request.setAttribute("orderId", orderId);
//            request.setAttribute("cartTotal", cartTotal);
//            request.setAttribute("purchasedItems", cart);
//            request.setAttribute("message", "Order placed successfully!");
//
//            request.getRequestDispatcher("orderConfirmation.jsp")
//                   .forward(request, response);
//
//        } catch (Exception e) {
//
//            try {
//                conn.rollback();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//
//            e.printStackTrace();
//            response.sendRedirect("cart.jsp?error=exception");
//        }
//    }
//}

package com.shoppingapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.beans.Product;
import com.beans.User;
import com.beans.Order;
import com.DAO.OrderDAO;
import com.DAOImpl.OrderDAOImpl;
import com.DAOImpl.ProductDAOImpl;
import com.aspect.DBConnection;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // =========================
        // ✅ SESSION CHECK
        // =========================
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp?error=session");
            return;
        }

        User user = (User) session.getAttribute("user");

        String productIdParam = request.getParameter("productId");
        String quantityParam = request.getParameter("quantity");

        String fullname = request.getParameter("fullname");
        String address = request.getParameter("address");
        String mobileno = request.getParameter("mobileno");
        String paymentMethod = request.getParameter("paymentMethod");

        // =====================================================
        // ✅ STEP 1: BUY NOW FLOW (FIRST ENTRY)
        // =====================================================
        if (productIdParam != null && fullname == null) {

            int productId = Integer.parseInt(productIdParam);
            int quantity = Integer.parseInt(quantityParam);

            ProductDAOImpl productDAO = new ProductDAOImpl(DBConnection.getSQLConnection());
            Product product = productDAO.findById(productId);

            // ❌ STOCK CHECK
            if (product == null || product.getQuantity() < quantity) {
                response.sendRedirect("productservlet?action=viewAll&error=outOfStock");
                return;
            }

            product.setQuantity(quantity);

            List<Product> buyNowCart = new ArrayList<>();
            buyNowCart.add(product);

            session.setAttribute("buyNowCart", buyNowCart);

            response.sendRedirect("checkout.jsp");
            return;
        }

        // =====================================================
        // ✅ STEP 2: GET CART (BUY NOW OR NORMAL)
        // =====================================================
        List<Product> cart = (List<Product>) session.getAttribute("buyNowCart");

        if (cart == null) {
            cart = (List<Product>) session.getAttribute("cart");
        }

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp?error=emptyCart");
            return;
        }

        // =====================================================
        // ✅ STEP 3: CARD STEP 1 → REDIRECT TO cards.jsp
        // =====================================================
        if ("card".equalsIgnoreCase(paymentMethod)
                && request.getParameter("cardNumber") == null) {

            request.setAttribute("fullname", fullname);
            request.setAttribute("address", address);
            request.setAttribute("mobileno", mobileno);

            request.getRequestDispatcher("cards.jsp").forward(request, response);
            return;
        }

        Connection conn = DBConnection.getSQLConnection();

        try {
            conn.setAutoCommit(false); // ✅ START TRANSACTION

            OrderDAO orderDAO = new OrderDAOImpl(conn);
            ProductDAOImpl productDAO = new ProductDAOImpl(conn);

            // =====================================================
            // ✅ STEP 4: CREATE ORDER
            // =====================================================
            Order order = new Order();
            order.setUserId(user.getId());
            order.setFullname(fullname);
            order.setAddress(address);
            order.setMobileno(mobileno);

            // 💳 HANDLE PAYMENT
            if ("card".equalsIgnoreCase(paymentMethod)) {

                String cardNumber = request.getParameter("cardNumber");

                if (cardNumber == null || cardNumber.length() < 4) {
                    response.sendRedirect("cards.jsp?error=invalidCard");
                    return;
                }

                order.setPaymentMethod("Card ****" +
                        cardNumber.substring(cardNumber.length() - 4));

            } else {
                order.setPaymentMethod(paymentMethod); // COD / UPI
            }

            order.setStatus("Placed");

            int orderId = orderDAO.createOrder(order);

            if (orderId == 0) {
                conn.rollback();
                response.sendRedirect("cart.jsp?error=orderFailed");
                return;
            }

            // =====================================================
            // ✅ STEP 5: PROCESS CART ITEMS
            // =====================================================
            double cartTotal = 0;

            for (Product p : cart) {

                Product dbProduct = productDAO.findById(p.getId());

                // ❌ STOCK CHECK AGAIN (SAFETY)
                if (p.getQuantity() > dbProduct.getQuantity()) {
                    conn.rollback();
                    response.sendRedirect("cart.jsp?error=outOfStock&productId=" + p.getId());
                    return;
                }

                double price = dbProduct.getPrice();

                // ✅ INSERT ORDER ITEM
                orderDAO.addOrderItem(orderId, p.getId(), p.getQuantity(), price);

                // ✅ UPDATE STOCK
                productDAO.updateQuantity(
                        p.getId(),
                        dbProduct.getQuantity() - p.getQuantity()
                );

                cartTotal += price * p.getQuantity();
            }

            conn.commit(); // ✅ SUCCESS

            // =====================================================
            // ✅ STEP 6: CLEAN SESSION
            // =====================================================
            session.removeAttribute("cart");
            session.removeAttribute("buyNowCart");

            // =====================================================
            // ✅ STEP 7: FORWARD TO CONFIRMATION
            // =====================================================
            request.setAttribute("orderId", orderId);
            request.setAttribute("cartTotal", cartTotal);
            request.setAttribute("purchasedItems", cart);
            request.setAttribute("message", "Order placed successfully!");

            request.getRequestDispatcher("orderConfirmation.jsp")
                   .forward(request, response);

        } catch (Exception e) {

            try {
                conn.rollback(); // ❌ FAILURE
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
            response.sendRedirect("cart.jsp?error=exception");
        }
    }
}

