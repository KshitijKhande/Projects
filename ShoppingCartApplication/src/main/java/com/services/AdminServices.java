package com.services;

import java.util.List;

import com.beans.Admin;
import com.beans.Product;
import com.beans.User;

public interface AdminServices {
	 Admin adminLogin(String username,String password);
	
	// Admin-only operations 
	List<User> viewAllUsers(); 
	// return list of users 
	List<Product> viewAllProducts(); 
	// return list of products 
	
	// --- User CRUD --- 
	boolean addUser(User user);
	boolean updateUser(User user); 
	boolean deleteUser(String username); 
	User findUserByUsername(String username);
	Product getProductById(int productId);

	
	// Product management 
	boolean addProduct(Product product); 
	boolean deleteProduct(int productId); 
	boolean updateProduct(Product product);
	
	 boolean blockUser(int userId);
	 boolean unblockUser(int userId);
	 boolean updateOrderStatus(int orderId, String status);
}
