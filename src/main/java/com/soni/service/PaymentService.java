package com.soni.service;

import com.stripe.exception.StripeException;

import com.soni.model.Order;
import com.soni.model.PaymentResponse;

public interface PaymentService {
	
	public PaymentResponse generatePaymentLink(Order order) throws StripeException;

}
