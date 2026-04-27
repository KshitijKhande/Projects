package com.DAOImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.beans.Admin;
import com.beans.User;
import com.beans.Product;
import com.DAO.AdminDAO;

public class AdminDAOImpl implements AdminDAO {
    private Connection con;

    public AdminDAOImpl(Connection con) {
        this.con = con;
    }

    @Override
    public Admin validateAdmin(String username, String password) {
        String sql = "SELECT * FROM admins WHERE username=? AND password=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Admin(rs.getString("username"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // invalid credentials
    }

//    @Override
//    public List<User> getAllUsers() {
//        List<User> users = new ArrayList<>();
//        String sql = "SELECT * FROM usertable";
//        try (Statement st = con.createStatement();
//             ResultSet rs = st.executeQuery(sql)) {
//            while (rs.next()) {
//                users.add(new User(
//                		rs.getInt("id"),
//                    rs.getString("username"),
//                    rs.getString("password"),
//                    rs.getString("email"),
//                    rs.getString("mobileno"),
//                    rs.getString("address"),
//                    rs.getString("securityques"),
//                    rs.getString("secquesans")
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return users;
//    }
    
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM usertable";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("mobileno"),
                    rs.getString("address"),
                    rs.getString("securityques"),
                    rs.getString("secquesans")
                );
                user.setStatus(rs.getString("status")); // ✅ add this
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    // --- USER CRUD ---
    
    @Override
    public boolean addUser(User user) {
//        String sql = "INSERT INTO usertable(username, password, email, mobileno,address,securityques, secquesans) VALUES(?,?,?,?,?,?,?)";
//        try (PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setString(1, user.getUsername());
//            ps.setString(2, user.getPassword());
//            ps.setString(3, user.getEmail());
//            ps.setString(4, user.getMobileno());
//            ps.setString(5, user.getAddress());
//            ps.setString(6, user.getSecurityques());
//            ps.setString(7, user.getSecquesans());
//            return ps.executeUpdate() > 0;
    	
    	String sql = "INSERT INTO usertable(username, password, email, mobileno, address, securityques, secquesans, status) VALUES(?,?,?,?,?,?,?,?)";
    	try (PreparedStatement ps = con.prepareStatement(sql)) {
    	    ps.setString(1, user.getUsername());
    	    ps.setString(2, user.getPassword());
    	    ps.setString(3, user.getEmail());
    	    ps.setString(4, user.getMobileno());
    	    ps.setString(5, user.getAddress());
    	    ps.setString(6, user.getSecurityques());
    	    ps.setString(7, user.getSecquesans());
    	    ps.setString(8, "active"); // default status
    	    return ps.executeUpdate() > 0;
    	

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE usertable SET password=?, email=?, mobileno=?,address=?, securityques=?, secquesans=? WHERE username=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getMobileno());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getSecurityques());
            ps.setString(6, user.getSecquesans());
            ps.setString(7, user.getUsername());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(String username) {
        String sql = "DELETE FROM usertable WHERE username=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    @Override
//    public User findUserByUsername(String username) {
//        String sql = "SELECT * FROM usertable WHERE username=?";
//        try (PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setString(1, username);
//            ResultSet rs = ps.executeQuery();
//            
//            if (rs.next()) {
//                return new User(
//                		rs.getInt("id"),
//                    rs.getString("username"),
//                    rs.getString("password"),
//                    rs.getString("email"),
//                    rs.getString("mobileno"),
//                    rs.getString("address"),
//                    rs.getString("securityques"),
//                    rs.getString("secquesans")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    
    @Override
    public User findUserByUsername(String username) {
        String sql = "SELECT * FROM usertable WHERE username=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("mobileno"),
                    rs.getString("address"),
                    rs.getString("securityques"),
                    rs.getString("secquesans")
                );
                user.setStatus(rs.getString("status")); // ✅ add this
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    
    
    
    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getString("category"),
                    rs.getString("image")
                ));
                products.get(products.size()-1).setQuantity(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO products(name, description, price, image) VALUES(?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getImage());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE product_id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, productId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        String sql = "UPDATE products SET name=?, description=?, price=?,image=?,quantity=?,category=? WHERE product_id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getImage());
            ps.setInt(5, product.getQuantity());
            ps.setString(6, product.getCategory());
            ps.setInt(7, product.getId());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    @Override
    public Product findProductById(int productId) {
        String sql = "SELECT * FROM products WHERE product_id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product product = new Product(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getString("category"),
                    rs.getString("image"),
                    rs.getInt("quantity")
                );
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public boolean blockUser(int userId) {
        String sql = "UPDATE usertable SET status='blocked' WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean unblockUser(int userId) {
        String sql = "UPDATE usertable SET status='active' WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE ordertable SET status=? WHERE order_id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, orderId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}



