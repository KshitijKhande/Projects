package com.DAO;

import java.util.List;

import com.beans.Product;

public interface ProductDAO {
	List<Product> findAll(); 
	
	Product findById(int productId);
	
	boolean save(Product product); 
	 
	boolean update(Product product); 
	
	boolean delete(int productId);
	
	List<Product> getProductsByCategory(String category);
	
	List<Product> sortByPrice(String order);

	List<Product> searchProducts(String keyword);

	boolean updateQuantity(int productId, int newQty);
}
