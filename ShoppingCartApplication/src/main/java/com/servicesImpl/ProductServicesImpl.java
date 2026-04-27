package com.servicesImpl;


import java.util.List;
import com.beans.Product;
import com.DAO.ProductDAO;
import com.DAOImpl.ProductDAOImpl;
import com.services.ProductServices;

public class ProductServicesImpl implements ProductServices {
    private ProductDAO productDAO;

    public ProductServicesImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> displayProducts() {
        return productDAO.findAll();
    }

    @Override
    public Product getProductById(int productId) {
        return productDAO.findById(productId);
    }

    @Override
    public boolean addProduct(Product product) {
        return productDAO.save(product);
    }

    @Override
    public boolean updateProduct(Product product) {
        return productDAO.update(product);
    }

    @Override
    public boolean deleteProduct(int productId) {
        return productDAO.delete(productId);
    }
    
    @Override
    public List<Product> getProductsByCategory(String category) {
        return productDAO.getProductsByCategory(category);
    }

    @Override
    public List<Product> sortProductsByPrice(String order) {
        return productDAO.sortByPrice(order);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productDAO.searchProducts(keyword);
    }

    
}
