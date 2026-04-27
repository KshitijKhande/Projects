package com.DAO;

import java.util.List;
import com.beans.Admin;
import com.beans.User;
import com.beans.Product;

public interface AdminDAO {
    Admin validateAdmin(String username, String password); // admin login validation
    
    List<User> getAllUsers();   
    
    boolean addUser(User user); 
    
    boolean updateUser(User user);
    
    boolean deleteUser(String username);
    
    User findUserByUsername(String username);
    
    Product findProductById(int productId);

    List<Product> getAllProducts(); 
    
    boolean addProduct(Product product);
    
    boolean deleteProduct(int productId);
    
    boolean updateProduct(Product product);
    
    public boolean blockUser(int userId);
    
    public boolean unblockUser(int userId);
    
    public boolean updateOrderStatus(int orderId, String status);
}

