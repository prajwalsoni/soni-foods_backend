//package com.soni.service;
//
//import java.util.ArrayList; 
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.stripe.exception.StripeException;
//import com.soni.Exception.CartException;
//import com.soni.Exception.OrderException;
//import com.soni.Exception.RestaurantException;
//import com.soni.Exception.UserException;
//import com.soni.model.Address;
//import com.soni.model.Cart;
//import com.soni.model.CartItem;
//import com.soni.model.Order;
//import com.soni.model.OrderItem;
//import com.soni.model.PaymentResponse;
//import com.soni.model.Restaurant;
//import com.soni.model.User;
//import com.soni.repository.AddressRepository;
//import com.soni.repository.CartRepository;
//import com.soni.repository.OrderItemRepository;
//import com.soni.repository.OrderRepository;
//import com.soni.repository.RestaurantRepository;
//import com.soni.repository.UserRepository;
//import com.soni.request.CreateOrderRequest;
//@Service
//public class OrderServiceImplementation implements OrderService {
//	
//	@Autowired
//	private AddressRepository addressRepository;
//	@Autowired
//	private CartSerive cartService;
//	@Autowired
//	private OrderItemRepository orderItemRepository;
//	@Autowired
//	private OrderRepository orderRepository;
//	@Autowired
//	private RestaurantRepository restaurantRepository;
//	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	private PaymentService paymentSerive;
//	
//
//	
//
//	@Override
//	public PaymentResponse createOrder(CreateOrderRequest order,User user) throws UserException, RestaurantException, CartException, StripeException {
//		
//	    Address shippAddress = order.getDeliveryAddress();
//
//	    
//	    Address savedAddress = addressRepository.save(shippAddress);
//	    
//	    if(!user.getAddresses().contains(savedAddress)) {
//	    	user.getAddresses().add(savedAddress);
//	    }
//	    
//		
//		System.out.println("user addresses --------------  "+user.getAddresses());
//		   
//		 userRepository.save(user);
//	    
//	    Optional<Restaurant> restaurant = restaurantRepository.findById(order.getRestaurantId());
//	    if(restaurant.isEmpty()) {
//	    	throw new RestaurantException("Restaurant not found with id "+order.getRestaurantId());
//	    }
//	    
//	    Order createdOrder = new Order();
//	    
//	    createdOrder.setCustomer(user);
//	    createdOrder.setDeliveryAddress(savedAddress);
//	    createdOrder.setCreatedAt(new Date());
//	    createdOrder.setOrderStatus("PENDING");
//	    createdOrder.setRestaurant(restaurant.get());
//
//        Cart cart = cartService.findCartByUserId(user.getId());
//        
//	    List<OrderItem> orderItems = new ArrayList<>();
//	    
//	    for (CartItem cartItem : cart.getItems()) {
//	        OrderItem orderItem = new OrderItem();
//	       orderItem.setMenuItem(cartItem.getMenuItem());
//	       orderItem.setQuantity(cartItem.getQuantity());
//	        orderItem.setSubtotal(cartItem.getMenuItem().getPrice()* cartItem.getQuantity());
//
//	        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
//	        orderItems.add(savedOrderItem);
//	    }
//   
//	     Long totalPrice = cartService.calculateCartTotals(cart);
//
//	    createdOrder.setTotalAmount(totalPrice);
//	    createdOrder.setRestaurant(restaurant.get());
//  
//	    createdOrder.setItems(orderItems);
//	    Order savedOrder = orderRepository.save(createdOrder);
//
//	   restaurant.get().getOrders().add(savedOrder);
//	   
//	   restaurantRepository.save(restaurant.get());
//	   
//
//	   
//	   PaymentResponse res=paymentSerive.generatePaymentLink(savedOrder);
//	   return res;
//
//	}
//
//	@Override
//	public void cancelOrder(Long orderId) throws OrderException {
//           Order order =findOrderById(orderId);
//           if(order==null) {
//        	   throw new OrderException("Order not found with the id "+orderId);
//           }
//		
//		    orderRepository.deleteById(orderId);
//		
//	}
//	
//	public Order findOrderById(Long orderId) throws OrderException {
//		Optional<Order> order = orderRepository.findById(orderId);
//		if(order.isPresent()) return order.get();
//		
//		throw new OrderException("Order not found with the id "+orderId);
//	}
//
//	@Override
//	public List<Order> getUserOrders(Long userId) throws OrderException {
//		Optional<List<Order>> orders=orderRepository.findAllUserOrders(userId);
//		if(orders.isPresent()) {
//			return orders.get();
//		}
//		
//		throw new OrderException("orders not found");
//	}
//
//	@Override
//	public List<Order> getOrdersOfRestaurant(Long restaurantId) throws OrderException, RestaurantException {
//		 if(restaurantId!=null) {
//			 Optional<List<Order>> orders = orderRepository.findOrdersByRestaurantId(restaurantId);
//			 if(orders.isPresent()){
//				 return orders.get();
//			 }else {
//				 throw new OrderException("Orders not found");
//			 }
//		 }
//		 throw new RestaurantException("Restaurant not found with the id "+restaurantId);
//	}
//
//	@Override
//	public Order updateOrder(Long orderId, String orderStatus) throws OrderException {
//		Order order=findOrderById(orderId);
//		
//		System.out.println("--------- "+orderStatus);
//		
//		if(orderStatus.equals("PLACED") || orderStatus.equals("DELIVERED") 
//				|| orderStatus.equals("CONFIRMED")) {
//			order.setOrderStatus(orderStatus);
//			return orderRepository.save(order);
//		}
//		else throw new OrderException("Please Select A Valid Order Status");
//		
//		
//	}
//	
//	
//
//}
