//package com.soni.service;
//
//import java.util.List; 
//
//import com.stripe.exception.StripeException;
//import com.soni.Exception.CartException;
//import com.soni.Exception.OrderException;
//import com.soni.Exception.RestaurantException;
//import com.soni.Exception.UserException;
//import com.soni.model.Order;
//import com.soni.model.PaymentResponse;
//import com.soni.model.User;
//import com.soni.request.CreateOrderRequest;
//
//public interface OrderService {
//	
//	 public PaymentResponse createOrder(CreateOrderRequest order, User user) throws UserException, RestaurantException, CartException, StripeException;
//	 
//	 public Order updateOrder(Long orderId, String orderStatus) throws OrderException;
//	 
//	 public void cancelOrder(Long orderId) throws OrderException;
//	 
//	 public List<Order> getUserOrders(Long userId) throws OrderException;
//	 
//	 public List<Order> getOrdersOfRestaurant(Long restaurantId) throws OrderException, RestaurantException;
//	 
//
//}
