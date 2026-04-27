package com.shoppingapp;

import java.io.IOException;
import java.util.List;
import com.services.ProductServices;
import com.servicesImpl.ProductServicesImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.DAOImpl.ProductDAOImpl;
import com.aspect.DBConnection;
import com.beans.Product;


@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
    private ProductServices productServices;

    public void init() throws ServletException {
        productServices = new ProductServicesImpl(new ProductDAOImpl(DBConnection.getSQLConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action"); // viewAll, viewOne, add, update, delete, logout

        switch (action) {
            case "viewAll":
                handleViewAllProducts(request, response);
                break;
            case "viewOne":
                handleViewProduct(request, response);
                break;
            case "add":
                handleAddProduct(request, response);
                break;
            case "update":
                handleUpdateProduct(request, response);
                break;
            case "delete":
                handleDeleteProduct(request, response);
                break;
            case "viewByCategory":   // ✅ new case
                handleViewByCategory(request, response);
                break;
            case "sortByPrice":
                handleSortByPrice(request, response);
                break;
            case "search":
                handleSearch(request, response);
                break;

            default:
                response.sendRedirect("error.jsp");
        }
    }

    private void handleViewAllProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            List<Product> products = productServices.displayProducts();
            request.setAttribute("products", products);
            RequestDispatcher rd = request.getRequestDispatcher("products.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("login.jsp?error=session");
        }
    }

    private void handleViewProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            int productId = Integer.parseInt(request.getParameter("id"));
            Product product = productServices.getProductById(productId);
            request.setAttribute("product", product);
            RequestDispatcher rd = request.getRequestDispatcher("productDetails.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("login.jsp?error=session");
        }
    }

//    private void handleAddProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        HttpSession session = request.getSession(false);
//        if (session != null && session.getAttribute("admin") != null) {
//            Product product = new Product(
//                0,
//                request.getParameter("name"),
//                request.getParameter("description"),
//                Double.parseDouble(request.getParameter("price")),
//                request.getParameter("image"),
//                Integer.parseInt(request.getParameter("quantity"))
//            );
//            boolean success = productServices.addProduct(product);
//            response.sendRedirect(success ? "productservlet?action=viewAll" : "addProduct.jsp?error=1");
//        } else {
//            response.sendRedirect("adminLogin.jsp?error=session");
//        }
//    }
    private void handleAddProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {

            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String priceParam = request.getParameter("price");
            String category=request.getParameter("category");
            String image = request.getParameter("image");
            String quantityParam = request.getParameter("quantity");

            // Validate numeric inputs
            double price = 0.0;
            int quantity = 0;
            try {
                if (priceParam != null && !priceParam.isEmpty()) {
                    price = Double.parseDouble(priceParam);
                }
                if (quantityParam != null && !quantityParam.isEmpty()) {
                    quantity = Integer.parseInt(quantityParam);
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("addProduct.jsp?error=invalidInput");
                return;
            }

            Product product = new Product(0, name, description, price,category, image, quantity);
            boolean success = productServices.addProduct(product);
            response.sendRedirect(success ? "adminservlet?action=viewProducts" : "addProduct.jsp?error=1");
        } else {
            response.sendRedirect("adminLogin.jsp?error=session");
        }
    }


    private void handleUpdateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
            Product product = new Product(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("name"),
                request.getParameter("description"),
                Double.parseDouble(request.getParameter("price")),
                request.getParameter("category"),
                request.getParameter("image"),
                Integer.parseInt(request.getParameter("quantity"))
            );
            boolean success = productServices.updateProduct(product);
            response.sendRedirect(success ? "productServlet?action=viewAll" : "updateProduct.jsp?error=1");
        } else {
            response.sendRedirect("adminLogin.jsp?error=session");
        }
    }

    private void handleDeleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
            int productId = Integer.parseInt(request.getParameter("id"));
            boolean success = productServices.deleteProduct(productId);
            response.sendRedirect(success ? "productservlet?action=viewAll" : "viewProducts.jsp?error=1");
        } else {
            response.sendRedirect("adminLogin.jsp?error=session");
        }
    }
    
    private void handleViewByCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            String category = request.getParameter("category");
            List<Product> products;

            if (category == null || category.equalsIgnoreCase("all")) {
                products = productServices.displayProducts();
            } else {
                products = productServices.getProductsByCategory(category); // ✅ new service method
            }

            request.setAttribute("products", products);
            RequestDispatcher rd = request.getRequestDispatcher("products.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("login.jsp?error=session");
        }
    }
    
    private void handleSortByPrice(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            String order = request.getParameter("order"); // asc or desc
            List<Product> products = productServices.sortProductsByPrice(order);
            request.setAttribute("products", products);
            RequestDispatcher rd = request.getRequestDispatcher("products.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("login.jsp?error=session");
        }
    }


    private void handleSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            String keyword = request.getParameter("keyword");
            List<Product> products = productServices.searchProducts(keyword);
            request.setAttribute("products", products);
            RequestDispatcher rd = request.getRequestDispatcher("products.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("login.jsp?error=session");
        }
    }

}
