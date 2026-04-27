package com.shoppingapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.beans.Product;
import com.services.ProductServices;
import com.servicesImpl.ProductServicesImpl;
import com.DAOImpl.ProductDAOImpl;
import com.aspect.DBConnection;

@WebServlet("/ProductsServlet")
public class ProductsServlet extends HttpServlet {
    private ProductServices productServices;

    @Override
    public void init() throws ServletException {
        productServices = new ProductServicesImpl(
            new ProductDAOImpl(DBConnection.getSQLConnection())
        );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch products from DB
        List<Product> products = productServices.displayProducts();

        // Attach to request
        request.setAttribute("products", products);

        // Forward to JSP
        request.getRequestDispatcher("products.jsp").forward(request, response);
    }
}
