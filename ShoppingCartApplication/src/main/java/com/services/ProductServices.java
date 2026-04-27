package com.services;

import java.util.List;
import com.beans.Product;

public interface ProductServices {
    List<Product> displayProducts();   
    
    Product getProductById(int id);    
    
    boolean addProduct(Product product);  
    
    boolean updateProduct(Product product);  
    
    boolean deleteProduct(int productId);
    
    List<Product> getProductsByCategory(String category);
    
    List<Product> sortProductsByPrice(String order);
    
    List<Product> searchProducts(String keyword);


}
