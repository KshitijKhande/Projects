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
//import com.services.ProductServices;
//import com.servicesImpl.ProductServicesImpl;
//import com.DAOImpl.ProductDAOImpl;
//import com.aspect.DBConnection;
//
//@WebServlet("/CartServlet")
//public class CartServlet extends HttpServlet {
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
//        String action = request.getParameter("action"); // add / update / remove
//        String productIdStr = request.getParameter("productId");
//        String quantityStr = request.getParameter("quantity");
//
//        if (productIdStr == null || productIdStr.isEmpty()) {
//            response.sendRedirect("ProductsServlet?error=missingProduct");
//            return;
//        }
//
//        int productId = Integer.parseInt(productIdStr);
//        List<Product> cart = (List<Product>) session.getAttribute("cart");
//        if (cart == null) cart = new ArrayList<>();
//
//        if ("remove".equals(action)) {
//            cart.removeIf(p -> p.getId() == productId);
//
//        } else if ("update".equals(action)) {
//            if (quantityStr != null && !quantityStr.isEmpty()) {
//                int quantity = Integer.parseInt(quantityStr);
//                for (Product p : cart) {
//                    if (p.getId() == productId) {
//                        p.setQuantity(quantity);
//                        break;
//                    }
//                }
//            }
//
//        } else { // default: add to cart
//            if (quantityStr != null && !quantityStr.isEmpty()) {
//                int quantity = Integer.parseInt(quantityStr);
//                Product product = productServices.getProductById(productId);
//
//                boolean found = false;
//                for (Product p : cart) {
//                    if (p.getId() == productId) {
//                        p.setQuantity(p.getQuantity() + quantity);
//                        found = true;
//                        break;
//                    }
//                }
//                if (!found) {
//                    product.setQuantity(quantity);
//                    cart.add(product);
//                }
//            }
//        }
//
//        session.setAttribute("cart", cart);
//
//        // Redirect back to cart page if update/remove, else to products
//        if ("update".equals(action) || "remove".equals(action)) {
//            response.sendRedirect("cart.jsp");
//        } else {
//            response.sendRedirect("ProductsServlet");
//        }
//    }
//}
//


package com.shoppingapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

import com.beans.Product;
import com.services.ProductServices;
import com.servicesImpl.ProductServicesImpl;
import com.DAOImpl.ProductDAOImpl;
import com.aspect.DBConnection;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private ProductServices productServices;

    @Override
    public void init() throws ServletException {
        productServices = new ProductServicesImpl(new ProductDAOImpl(DBConnection.getSQLConnection()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp?error=session");
            return;
        }

//        String action = request.getParameter("action"); // add / update / remove
//        int productId = Integer.parseInt(request.getParameter("productId"));
//        int quantity = Integer.parseInt(request.getParameter("quantity"));
//
//        List<Product> cart = (List<Product>) session.getAttribute("cart");
//        if (cart == null) cart = new ArrayList<>();
//
//        Product dbProduct = productServices.getProductById(productId);
//
//        if ("remove".equals(action)) {
//            cart.removeIf(p -> p.getId() == productId);
//
//        } else if ("update".equals(action)) {
//            if (quantity > dbProduct.getQuantity()) {
//                response.sendRedirect("cart.jsp?error=outOfStock&productId=" + productId);
//                return;
//            }
//            for (Product p : cart) {
//                if (p.getId() == productId) {
//                    p.setQuantity(quantity);
//                    break;
//                }
//            }
//
//        } else { // add
//            if (quantity > dbProduct.getQuantity()) {
//                response.sendRedirect("ProductsServlet?error=outOfStock&productId=" + productId);
//                return;
//            }
//            boolean found = false;
//            for (Product p : cart) {
//                if (p.getId() == productId) {
//                    int newQty = p.getQuantity() + quantity;
//                    if (newQty > dbProduct.getQuantity()) {
//                        response.sendRedirect("cart.jsp?error=outOfStock&productId=" + productId);
//                        return;
//                    }
//                    p.setQuantity(newQty);
//                    found = true;
//                    break;
//                }
//            }
//            if (!found) {
//                dbProduct.setQuantity(quantity);
//                cart.add(dbProduct);
//            }
//        }
//
//        session.setAttribute("cart", cart);
//        response.sendRedirect("ProductServlet?action=viewAll");
        String action = request.getParameter("action");
        int productId = Integer.parseInt(request.getParameter("productId"));

        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();

        Product dbProduct = productServices.getProductById(productId);

        if ("remove".equals(action)) {
            cart.removeIf(p -> p.getId() == productId);

        } else if ("update".equals(action)) {
            String quantityStr = request.getParameter("quantity");
            if (quantityStr != null && !quantityStr.isEmpty()) {
                int quantity = Integer.parseInt(quantityStr);
                if (quantity > dbProduct.getQuantity()) {
                    response.sendRedirect("cart.jsp?error=outOfStock&productId=" + productId);
                    return;
                }
                for (Product p : cart) {
                    if (p.getId() == productId) {
                        p.setQuantity(quantity);
                        break;
                    }
                }
            }

        } else if ("add".equals(action)) {
            String quantityStr = request.getParameter("quantity");
            if (quantityStr != null && !quantityStr.isEmpty()) {
                int quantity = Integer.parseInt(quantityStr);
                if (quantity > dbProduct.getQuantity()) {
                    response.sendRedirect("ProductsServlet?error=outOfStock&productId=" + productId);
                    return;
                }
                boolean found = false;
                for (Product p : cart) {
                    if (p.getId() == productId) {
                        int newQty = p.getQuantity() + quantity;
                        if (newQty > dbProduct.getQuantity()) {
                            response.sendRedirect("cart.jsp?error=outOfStock&productId=" + productId);
                            return;
                        }
                        p.setQuantity(newQty);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    dbProduct.setQuantity(quantity);
                    cart.add(dbProduct);
                }
            }
        }

        session.setAttribute("cart", cart);

        // Redirect logic
        if ("update".equals(action) || "remove".equals(action)) {
            response.sendRedirect("cart.jsp");
        } else {
            response.sendRedirect("ProductsServlet");
        }

    }
}

