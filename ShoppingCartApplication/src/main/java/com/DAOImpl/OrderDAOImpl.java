//package com.DAOImpl;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import com.DAO.OrderDAO;
//import com.beans.Order;
//import com.aspect.DBConnection;
//
//public class OrderDAOImpl implements OrderDAO {
//    private Connection conn;
//
//    public OrderDAOImpl(Connection conn) {
//        this.conn = conn;
//    }
//
////    @Override
////    public void addOrder(Order order) {
////        String sql = "INSERT INTO ordertable (product_id,user_id, fullname, address,mobileno, paymentMethod, status,quantity) VALUES (?,?,?, ?, ?, ?,?, ?)";
////        try (PreparedStatement ps = conn.prepareStatement(sql)) {
////            ps.setInt(1, order.getProductId());
////            ps.setInt(2, order.getUserId());
////            ps.setString(3, order.getFullname());
////            ps.setString(4, order.getAddress());
//////            ps.setString(5, order.getCity());
//////            ps.setString(6, order.getPostalcode());
////            ps.setString(5, order.getMobileno());
////            ps.setString(6, order.getPaymentMethod());
////            ps.setString(7, order.getStatus());
////            ps.setInt(8, order.getQuantity());
////            ps.executeUpdate();
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////    }
////
////    @Override
////    public List<Order> getAllOrders() {
////        List<Order> orders = new ArrayList<>();
////        String sql = "SELECT * FROM ordertable ORDER BY orderDate DESC";
////        try (PreparedStatement ps = conn.prepareStatement(sql);
////             ResultSet rs = ps.executeQuery()) {
////            while (rs.next()) {
////                orders.add(mapRow(rs));
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return orders;
////    }
////
//////    @Override
//////    public List<Order> getOrdersByUserId(int userId) {
//////        List<Order> orders = new ArrayList<>();
//////        String sql = "SELECT * FROM ordertable WHERE user_id=? ORDER BY orderDate DESC";
//////        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//////            ps.setInt(1, userId);
//////            try (ResultSet rs = ps.executeQuery()) {
//////                while (rs.next()) {
//////                    orders.add(mapRow(rs));
//////                }
//////            }
//////        } catch (SQLException e) {
//////            e.printStackTrace();
//////        }
//////        return orders;
//////    }
////    
////    
////    @Override
////    public List<Order> getOrdersByUserId(int userId) {
////        List<Order> orders = new ArrayList<>();
////        String sql = "SELECT o.*, p.name AS product_name, p.image AS product_image " +
////                     "FROM ordertable o " +
////                     "JOIN products p ON o.product_id = p.product_id " +
////                     "WHERE o.user_id=? ORDER BY o.orderDate DESC";  // ✅ camelCase
////
////        try (PreparedStatement ps = conn.prepareStatement(sql)) {
////            ps.setInt(1, userId);
////            ResultSet rs = ps.executeQuery();
////            while (rs.next()) {
////                Order order = new Order();
////                order.setOrderId(rs.getInt("order_id"));
////                order.setUserId(rs.getInt("user_id"));
////                order.setProductId(rs.getInt("product_id"));
////                order.setQuantity(rs.getInt("quantity"));
////                order.setFullname(rs.getString("fullname"));
////                order.setAddress(rs.getString("address"));
////                order.setMobileno(rs.getString("mobileno"));
////                order.setPaymentMethod(rs.getString("paymentMethod")); // ✅ camelCase
////                order.setStatus(rs.getString("status"));
////                order.setOrderDate(rs.getTimestamp("orderDate"));      // ✅ camelCase
////                order.setProductName(rs.getString("product_name"));
////                order.setProductImage(rs.getString("product_image"));
////                orders.add(order);
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return orders;
////    }
////
////
////
////    @Override
////    public void updateOrderStatus(int orderId, String status) {
////        String sql = "UPDATE ordertable SET status=? WHERE order_id=?";
////        try (PreparedStatement ps = conn.prepareStatement(sql)) {
////            ps.setString(1, status);
////            ps.setInt(2, orderId);
////            ps.executeUpdate();
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////    }
////
////    // Helper method to map ResultSet → Order bean
//////    private Order mapRow(ResultSet rs) throws SQLException {
//////        Order o = new Order();
//////        o.setOrderId(rs.getInt("order_id"));
//////        o.setUserId(rs.getInt("user_id"));
//////        o.setFullname(rs.getString("fullname"));
//////        o.setAddress(rs.getString("address"));
//////        o.setCity(rs.getString("city"));
//////        o.setPostalcode(rs.getString("postalcode"));
//////        o.setMobileno(rs.getString("mobileno"));
//////        o.setPaymentMethod(rs.getString("paymentMethod"));
//////        o.setStatus(rs.getString("status"));
//////        o.setOrderDate(rs.getTimestamp("orderDate"));
//////        return o;
//////    }
////    
////    private Order mapRow(ResultSet rs) throws SQLException {
////        Order o = new Order();
////        o.setOrderId(rs.getInt("order_id"));
////        o.setUserId(rs.getInt("user_id"));
////        o.setProductId(rs.getInt("product_id"));
////        o.setFullname(rs.getString("fullname"));
////        o.setAddress(rs.getString("address"));
//////        o.setCity(rs.getString("city"));
//////        o.setPostalcode(rs.getString("postalcode"));
////        o.setMobileno(String.valueOf(rs.getLong("mobileno"))); // BIGINT → String
////        o.setPaymentMethod(rs.getString("paymentMethod"));
////        o.setStatus(rs.getString("status"));
////        o.setQuantity(rs.getInt("quantity"));
////        o.setOrderDate(rs.getTimestamp("orderDate"));
////        return o;
////    }
//    
//    @Override
//    public int createOrder(Order order) {
//        int orderId = 0;
//
//        String sql = "INSERT INTO ordertable (user_id, fullname, address, mobileno, paymentMethod, status) VALUES (?, ?, ?, ?, ?, ?)";
//
//        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//
//            ps.setInt(1, order.getUserId());
//            ps.setString(2, order.getFullname());
//            ps.setString(3, order.getAddress());
//            ps.setString(4, order.getMobileno());
//            ps.setString(5, order.getPaymentMethod());
//            ps.setString(6, order.getStatus());
//
//            ps.executeUpdate();
//
//            ResultSet rs = ps.getGeneratedKeys();
//            if (rs.next()) {
//                orderId = rs.getInt(1);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return orderId;
//    }
//
//    
//    @Override
//    public void addOrderItem(int orderId, int productId, int quantity) {
//
//        String sql = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";
//
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, orderId);
//            ps.setInt(2, productId);
//            ps.setInt(3, quantity);
//
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}

package com.DAOImpl;

import java.sql.*;
import java.util.*;
import com.DAO.OrderDAO;
import com.beans.Order;

public class OrderDAOImpl implements OrderDAO {
    private Connection conn;

    public OrderDAOImpl(Connection conn) {
        this.conn = conn;
    }

    // ✅ CREATE MAIN ORDER (returns order_id)
    @Override
    public int createOrder(Order order) {
        int orderId = 0;

        String sql = "INSERT INTO ordertable (user_id, fullname, address, mobileno, paymentMethod, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getUserId());
            ps.setString(2, order.getFullname());
            ps.setString(3, order.getAddress());
            ps.setString(4, order.getMobileno());
            ps.setString(5, order.getPaymentMethod());
            ps.setString(6, order.getStatus());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderId;
    }

    // ✅ INSERT ITEMS INTO order_items
//    @Override
//    public void addOrderItem(int orderId, int productId, int quantity) {
//
//        String sql = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";
//
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, orderId);
//            ps.setInt(2, productId);
//            ps.setInt(3, quantity);
//
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    
    @Override
    public void addOrderItem(int orderId, int productId, int quantity, double price) {

        String sql = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ✅ (OPTIONAL) GET ORDERS WITH ITEMS (for later use)
    @Override
    public List<Order> getOrdersByUserId(int userId) {

        List<Order> orders = new ArrayList<>();

//        String sql = "SELECT o.*, oi.product_id, oi.quantity, p.name, p.image " +
//                     "FROM ordertable o " +
//                     "JOIN order_items oi ON o.order_id = oi.order_id " +
//                     "JOIN products p ON oi.product_id = p.product_id " +
//                     "WHERE o.user_id=? ORDER BY o.orderDate DESC";

        String sql = "SELECT o.order_id, o.user_id, o.fullname, o.address, o.mobileno, " +
                "o.paymentMethod, o.status, o.orderDate, " +
                "oi.product_id, oi.quantity, oi.price, p.name, p.image " +
                "FROM ordertable o " +
                "JOIN order_items oi ON o.order_id = oi.order_id " +
                "JOIN products p ON oi.product_id = p.product_id " +
                "WHERE o.user_id=? ORDER BY o.order_id DESC";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order o = new Order();

                o.setOrderId(rs.getInt("order_id"));
                o.setUserId(rs.getInt("user_id"));
                o.setFullname(rs.getString("fullname"));
                o.setAddress(rs.getString("address"));
                o.setMobileno(rs.getString("mobileno"));
                o.setPaymentMethod(rs.getString("paymentMethod"));
                o.setStatus(rs.getString("status"));
                o.setOrderDate(rs.getTimestamp("orderDate"));

                o.setProductId(rs.getInt("product_id"));
                o.setQuantity(rs.getInt("quantity"));
                o.setProductName(rs.getString("name"));
                o.setProductImage(rs.getString("image"));
                o.setPrice(rs.getDouble("price"));


                orders.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
    
    @Override
    public List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();

        String sql = "SELECT o.order_id, o.user_id, o.fullname, o.address, o.mobileno, " +
                     "o.paymentMethod, o.status, o.orderDate, " +
                     "oi.product_id, oi.quantity, oi.price, " +
                     "p.name, p.image " +
                     "FROM ordertable o " +
                     "JOIN order_items oi ON o.order_id = oi.order_id " +
                     "JOIN products p ON oi.product_id = p.product_id " +
                     "ORDER BY o.order_id DESC";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Order o = new Order();

                o.setOrderId(rs.getInt("order_id"));
                o.setUserId(rs.getInt("user_id"));
                o.setFullname(rs.getString("fullname"));
                o.setAddress(rs.getString("address"));
                o.setMobileno(rs.getString("mobileno"));
                o.setPaymentMethod(rs.getString("paymentMethod"));
                o.setStatus(rs.getString("status"));
                o.setOrderDate(rs.getTimestamp("orderDate"));

                o.setProductId(rs.getInt("product_id"));
                o.setQuantity(rs.getInt("quantity"));
                o.setPrice(rs.getDouble("price"));

                o.setProductName(rs.getString("name"));
                o.setProductImage(rs.getString("image"));

                orders.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

}

