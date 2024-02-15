//package com.soni.controller;
//
//import java.util.List; 
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
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
//import com.soni.service.OrderService;
//import com.soni.service.UserService;
//
//@RestController
//@RequestMapping("/api/admin")
//public class AdminOrderController {
//	
//	@Autowired
//	private OrderService orderService;
//	
//	@Autowired
//	private UserService userService;
//	
//	
//    @DeleteMapping("/order/{orderId}")
//    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) throws OrderException{
//    	if(orderId!=null) {
//    		orderService.cancelOrder(orderId);
//    	return ResponseEntity.ok("Order deleted with id)"+orderId);
//    }else return new ResponseEntity<String>(HttpStatus.BAD_REQUEST) ;
//    }
//    
//    
//    @GetMapping("/order/restaurant/{restaurantId}")
//    public ResponseEntity<List<Order>> getAllRestaurantOrders(@PathVariable Long restaurantId) throws OrderException, RestaurantException{
//    	if(restaurantId!=null) {
//    		List<Order> orders = orderService.getOrdersOfRestaurant(restaurantId);
//    		return ResponseEntity.ok(orders);
//    		
//    	}
//    	else {
//    		return new ResponseEntity<List<Order>>(HttpStatus.BAD_REQUEST);
//    	}
//    }
//    
//    @PutMapping("/orders/{orderId}/{orderStatus}")
//    public ResponseEntity<Order> updateOrders(@PathVariable Long orderId,@PathVariable String orderStatus) throws OrderException, RestaurantException{
//    	
//    		Order orders = orderService.updateOrder(orderId, orderStatus);
//    		return ResponseEntity.ok(orders);
//    		
//    }
//
//}
