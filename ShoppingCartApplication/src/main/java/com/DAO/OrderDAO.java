package com.DAO;

import java.util.List;
import com.beans.Order;

public interface OrderDAO {
//    void addOrder(Order order);
//    List<Order> getAllOrders();
//    List<Order> getOrdersByUserId(int userId);
//    void updateOrderStatus(int orderId, String status);
	
	int createOrder(Order order);
	//void addOrderItem(int orderId, int productId, int quantity);

	List<Order> getOrdersByUserId(int userId);
	void addOrderItem(int orderId, int productId, int quantity, double price);

	List<Order> getAllOrders();
}
