package com.servicesImpl;

import java.util.List;
import com.beans.Admin;
import com.beans.User;
import com.beans.Product;
import com.DAO.AdminDAO;
import com.DAOImpl.AdminDAOImpl;
import com.services.AdminServices;

public class AdminServicesImpl implements AdminServices {
    private AdminDAO adminDAO;

    public AdminServicesImpl(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Override
    public Admin adminLogin(String username, String password) {
        return adminDAO.validateAdmin(username, password);
    }

    @Override
    public List<User> viewAllUsers() {
        return adminDAO.getAllUsers();
    }

    @Override
    public List<Product> viewAllProducts() {
        return adminDAO.getAllProducts();
    }

 // --- User CRUD --- 
    @Override 
    public boolean addUser(User user) { 
    		return adminDAO.addUser(user); 
    }
    
    @Override public boolean updateUser(User user) { 
    		return adminDAO.updateUser(user); 
    	} 
    
    @Override 
    public boolean deleteUser(String username) { 
    		return adminDAO.deleteUser(username); 
    	} 
    
    @Override 
    public User findUserByUsername(String username) { 
    		return adminDAO.findUserByUsername(username); 
    	}
    
    
    @Override
    public boolean addProduct(Product product) {
        return adminDAO.addProduct(product);
    }

    @Override
    public boolean deleteProduct(int productId) {
        return adminDAO.deleteProduct(productId);
    }

    @Override
    public boolean updateProduct(Product product) {
        return adminDAO.updateProduct(product);
    }
    
    @Override
    public Product getProductById(int productId) {
        return adminDAO.findProductById(productId);
    }

    @Override
    public boolean blockUser(int userId) {
        return adminDAO.blockUser(userId);
    }

    @Override
    public boolean unblockUser(int userId) {
        return adminDAO.unblockUser(userId);
    }
    
    @Override
    public boolean updateOrderStatus(int orderId, String status) {
        return adminDAO.updateOrderStatus(orderId,status);
    }
}
