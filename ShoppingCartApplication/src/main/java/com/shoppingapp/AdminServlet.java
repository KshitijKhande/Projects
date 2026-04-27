package com.shoppingapp;

import java.io.IOException;
import java.util.List;
import com.services.AdminServices;
import com.servicesImpl.AdminServicesImpl;
import com.DAOImpl.AdminDAOImpl;
import com.DAOImpl.OrderDAOImpl;
import com.aspect.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.beans.Admin;
import com.beans.Order;
import com.beans.User;
import com.beans.Product;


public class AdminServlet extends HttpServlet {
    private AdminServices adminServices;

    @Override
    public void init() throws ServletException {
        adminServices = new AdminServicesImpl(new AdminDAOImpl(DBConnection.getSQLConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("viewUsers".equals(action)) {
            handleViewUsers(request, response);
        } else if ("viewProducts".equals(action)) {
            handleViewProducts(request, response);
        }else if ("viewOrders".equals(action)) {
            OrderDAOImpl orderDAO = new OrderDAOImpl(DBConnection.getSQLConnection());
            List<Order> orders = orderDAO.getAllOrders();
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("adminOrders.jsp").forward(request, response);
        }
        else if ("editProduct".equals(action)) {
            handleEditProduct(request, response);
        } else {
            response.sendRedirect("adminDashboard.jsp?error=unknownAction");
        }//        }else {
//            response.sendRedirect("error.jsp");
//        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action"); 

        switch (action) {
            case "login":
                handleLogin(request, response);
                break;
            case "viewUsers":
                handleViewUsers(request, response);
                break;
            case "addUser": 
            		handleAddUser(request, response); 
            		break; 
            case "updateUser": 
            		handleUpdateUser(request, response); 
            		break; 
            case "deleteUser": 
            		handleDeleteUser(request, response); 
            		break;
            case "viewProducts":
                handleViewProducts(request, response);
                break;
            case "addProduct":
                handleAddProduct(request, response);
                break;
            case "updateProduct":
                handleUpdateProduct(request, response);
                break;
            case "deleteProduct":
                handleDeleteProduct(request, response);
                break;
            case "editProduct":
                handleEditProduct(request, response);
                break;
            case "logout":
                handleLogout(request, response);
                break;
            case "blockUser":
                handleBlockUser(request, response);
                break;
            case "unblockUser":
                handleUnblockUser(request, response);
                break;
            case "updateOrderStatus":
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                String status = request.getParameter("status");
                adminServices.updateOrderStatus(orderId, status);
                response.sendRedirect("adminservlet?action=viewOrders");
                break;

            default:
                response.sendRedirect("adminDashboard.jsp?error=unknownAction");

        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Admin admin = adminServices.adminLogin(request.getParameter("username"), request.getParameter("password"));
        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);
            //session.setMaxInactiveInterval(30 * 60); // 30 minutes
            response.sendRedirect("adminDashboard.jsp");
        } else {
            response.sendRedirect("adminLogin.jsp?error=1");
        }
    }

    private void handleViewUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
            List<User> users = adminServices.viewAllUsers();
            request.setAttribute("users", users);
            RequestDispatcher rd = request.getRequestDispatcher("viewUsers.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("adminLogin.jsp?error=session");
        }
    }

//    private void handleViewProducts(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession(false);
//        if (session != null && session.getAttribute("admin") != null) {
//            List<Product> products = adminServices.viewAllProducts();
//            request.setAttribute("products", products);
//            RequestDispatcher rd = request.getRequestDispatcher("viewProducts.jsp");
//            rd.forward(request, response);
//        } else {
//            response.sendRedirect("adminLogin.jsp?error=session");
//        }
//    }
    
    private void handleViewProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
            List<Product> products = adminServices.viewAllProducts();
            request.setAttribute("products", products);
            RequestDispatcher rd = request.getRequestDispatcher("viewProducts.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("adminLogin.jsp?error=session");
        }
    }

    
    
    private void handleEditProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
            String idParam = request.getParameter("id");
            if (idParam != null && !idParam.isEmpty()) {
                int productId = Integer.parseInt(idParam);
                Product product = adminServices.getProductById(productId);
                request.setAttribute("product", product);
                RequestDispatcher rd = request.getRequestDispatcher("updateProduct.jsp");
                rd.forward(request, response);
            } else {
                response.sendRedirect("viewProducts.jsp?error=noId");
            }
        } else {
            response.sendRedirect("adminLogin.jsp?error=session");
        }
    }



    private void handleAddProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
            Product product = new Product(
                0,
                request.getParameter("name"),
                request.getParameter("description"),
                Double.parseDouble(request.getParameter("price")),
                request.getParameter("category"),
                request.getParameter("image"),
                Integer.parseInt(request.getParameter("quantity"))
            );
            boolean success = adminServices.addProduct(product);
            response.sendRedirect(success ? "adminservlet?action=viewProducts" : "addProduct.jsp?error=1");
        } else {
            response.sendRedirect("adminLogin.jsp?error=session");
        }
    }

//    private void handleUpdateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        HttpSession session = request.getSession(false);
//        if (session != null && session.getAttribute("admin") != null) {
//            Product product = new Product(
//                Integer.parseInt(request.getParameter("id")),
//                request.getParameter("name"),
//                request.getParameter("description"),
//                Double.parseDouble(request.getParameter("price")),
//                request.getParameter("image"),
//                Integer.parseInt(request.getParameter("quantity"))
//            );
//            boolean success = adminServices.updateProduct(product);
//            response.sendRedirect(success ? "adminservlet?action=viewProducts" : "updateProduct.jsp?error=1");
//        } else {
//            response.sendRedirect("adminLogin.jsp?error=session");
//        }
//    }
//
    private void handleDeleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
       HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
            int productId = Integer.parseInt(request.getParameter("id"));
            boolean success = adminServices.deleteProduct(productId);
            response.sendRedirect(success ? "adminservlet?action=viewProducts" : "viewProducts.jsp?error=1");
        } else {
            response.sendRedirect("adminLogin.jsp?error=session");
        }
    }
    
    
    private void handleUpdateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {

            String idParam = request.getParameter("id");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String priceParam = request.getParameter("price");
            String category=request.getParameter("category");
            String image = request.getParameter("image");
            String quantityParam = request.getParameter("quantity");

            if (name == null || name.trim().isEmpty() || description == null || description.trim().isEmpty()) {
                response.sendRedirect("updateProduct.jsp?error=missingFields");
                return;
            }

            int id = 0;
            double price = 0.0;
            int quantity = 0;

            try {
                if (idParam != null && !idParam.isEmpty()) {
                    id = Integer.parseInt(idParam);
                }
                if (priceParam != null && !priceParam.isEmpty()) {
                    price = Double.parseDouble(priceParam);
                }
                if (quantityParam != null && !quantityParam.isEmpty()) {
                    quantity = Integer.parseInt(quantityParam);
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("updateProduct.jsp?error=invalidInput");
                return;
            }

            Product product = new Product(id, name, description, price,category, image, quantity);
            boolean success = adminServices.updateProduct(product);
            response.sendRedirect(success ? "adminservlet?action=viewProducts" : "updateProduct.jsp?error=updateFailed");

        } else {
            response.sendRedirect("adminLogin.jsp?error=session");
        }
    }



    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("adminLogin.jsp?logout=success");
    }
    
    
    private void handleAddUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
//            User user = new User(
//                request.getParameter("username"),
//                request.getParameter("password"),
//                request.getParameter("email"),
//                request.getParameter("mobileno"),
//                request.getParameter("securityques"),
//                request.getParameter("secquesans")
//            );
        	
        	User user = new User();
        	user.setId(Integer.parseInt(request.getParameter("id")));
        	user.setUsername(request.getParameter("username"));
        	user.setPassword(request.getParameter("password"));
        	user.setEmail(request.getParameter("email"));
        	user.setMobileno(request.getParameter("mobileno"));
        	user.setSecurityques(request.getParameter("securityques"));
        	user.setSecquesans(request.getParameter("secquesans"));

            boolean success = adminServices.addUser(user);
            response.sendRedirect(success ? "adminservlet?action=viewUsers" : "addUser.jsp?error=1");
        } else {
            response.sendRedirect("adminLogin.jsp?error=session");
        }
    }

    private void handleUpdateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
//            User user = new User(
//            		request.getParameter("id"),
//                request.getParameter("username"), // primary key
//                request.getParameter("password"),
//                request.getParameter("email"),
//                request.getParameter("mobileno"),
//                request.getParameter("securityques"),
//                request.getParameter("secquesans")
        	
        	
        	User user = new User();
        	//user.setId(Integer.parseInt(request.getParameter("id")));
        	user.setUsername(request.getParameter("username"));
        	user.setPassword(request.getParameter("password"));
        	user.setEmail(request.getParameter("email"));
        	user.setMobileno(request.getParameter("mobileno"));
        	user.setAddress(request.getParameter("address"));
        	user.setSecurityques(request.getParameter("securityques"));
        	user.setSecquesans(request.getParameter("secquesans"));
        	
        	String password = request.getParameter("password");
        	//String password = request.getParameter("password");
        	System.out.println("PASSWORD FROM FORM n = " + password);


            boolean success = adminServices.updateUser(user);
            response.sendRedirect(success ? "adminservlet?action=viewUsers" : "editUser.jsp?error=1&username=" + request.getParameter("username"));
        } else {
            response.sendRedirect("adminLogin.jsp?error=session");
        }
    }

    private void handleDeleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
            String username = request.getParameter("username");
            boolean success = adminServices.deleteUser(username);
            response.sendRedirect(success ? "adminservlet?action=viewUsers" : "viewUsers.jsp?error=1");
        } else {
            response.sendRedirect("adminLogin.jsp?error=session");
        }
    }
    
    private void handleBlockUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        adminServices.blockUser(userId);
        response.sendRedirect("adminservlet?action=viewUsers");
    }

    private void handleUnblockUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        adminServices.unblockUser(userId);
        response.sendRedirect("adminservlet?action=viewUsers");
    }
    
    

}

