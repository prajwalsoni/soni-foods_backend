package com.soni.repository;

import org.springframework.data.jpa.repository.JpaRepository; 

import com.soni.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
