//package com.DAOImpl;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import com.beans.Product;
//import com.DAO.ProductDAO;
//
//public class ProductDAOImpl implements ProductDAO {
//    private Connection conn;
//
//    public ProductDAOImpl(Connection conn) {
//        this.conn = conn;
//    }
//
//    @Override
//    public List<Product> findAll() {
//        List<Product> products = new ArrayList<>();
//        String sql = "SELECT * FROM products";
//        try (Statement st = conn.createStatement();
//             ResultSet rs = st.executeQuery(sql)) {
//            while (rs.next()) {
//                Product product=new Product(
//                    rs.getInt("product_id"),
//                    rs.getString("name"),
//                    rs.getString("description"),
//                    rs.getDouble("price"),
//                    rs.getString("image")
//                );
//                product.setQuantity(1);
//                products.add(product);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return products;
//    }
//
//    @Override
//    public Product findById(int productId) {
//        String sql = "SELECT * FROM products WHERE product_id=?";
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, productId);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                Product product = new Product(
//                    rs.getInt("product_id"),
//                    rs.getString("name"),
//                    rs.getString("description"),
//                    rs.getDouble("price"),
//                    rs.getString("image")
//                );
//                product.setQuantity(1); // ✅ set default quantity
//                return product;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public boolean save(Product product) {
//        String sql = "INSERT INTO products(name, description, price, image) VALUES(?,?,?,?)";
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, product.getName());
//            ps.setString(2, product.getDescription());
//            ps.setDouble(3, product.getPrice());
//            ps.setString(4, product.getImage());
//            return ps.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    public boolean update(Product product) {
//        String sql = "UPDATE products SET name=?, description=?, price=?, image=? WHERE product_id=?";
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, product.getName());
//            ps.setString(2, product.getDescription());
//            ps.setDouble(3, product.getPrice());
//            ps.setString(4, product.getImage());
//            ps.setInt(5, product.getId());
//            return ps.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    public boolean delete(int productId) {
//        String sql = "DELETE FROM products WHERE product_id=?";
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, productId);
//            return ps.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//    
//    private Product mapRow(ResultSet rs) throws SQLException {
//        Product p = new Product();
//        p.setProductId(rs.getInt("product_id"));   // ✅ critical
//        p.setName(rs.getString("name"));
//        p.setPrice(rs.getDouble("price"));
//        p.setQuantity(rs.getInt("quantity"));
//        return p;
//    }
//
//}


package com.DAOImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.beans.Product;
import com.DAO.ProductDAO;

public class ProductDAOImpl implements ProductDAO {
    private Connection conn;

    public ProductDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                products.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product findById(int productId) {
        String sql = "SELECT * FROM products WHERE product_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Product product) {
        String sql = "INSERT INTO products(name, description, price, image, quantity,category) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getImage());
            ps.setInt(5, product.getQuantity());
            ps.setString(6, product.getCategory());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        String sql = "UPDATE products SET name=?, description=?, price=?, image=?, quantity=?,category=? WHERE product_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
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
    public boolean delete(int productId) {
        String sql = "DELETE FROM products WHERE product_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    public List<Product> getProductsByCategory(String category) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    
    
    @Override
    public List<Product> sortByPrice(String order) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY price " + 
                     ("desc".equalsIgnoreCase(order) ? "DESC" : "ASC");
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                products.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    
    @Override
    public List<Product> searchProducts(String keyword) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ? OR description LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public boolean updateQuantity(int productId, int newQty) {
        String sql = "UPDATE products SET quantity=? WHERE product_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newQty);
            ps.setInt(2, productId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private Product mapRow(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setId(rs.getInt("product_id"));   // ✅ consistent mapping
        p.setName(rs.getString("name"));
        p.setDescription(rs.getString("description"));
        p.setPrice(rs.getDouble("price"));
        p.setImage(rs.getString("image"));
        p.setQuantity(rs.getInt("quantity"));
        p.setCategory(rs.getString("category"));
        return p;
    }
}

