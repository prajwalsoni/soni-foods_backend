package com.soni.service;

import com.soni.Exception.CartException; 
import com.soni.Exception.CartItemException;
import com.soni.Exception.MenuItemException;
import com.soni.Exception.UserException;
import com.soni.model.Cart;
import com.soni.model.CartItem;
import com.soni.model.MenuItem;
import com.soni.model.User;
import com.soni.request.AddCartItemRequest;
import com.soni.request.UpdateCartItemRequest;

public interface CartSerive {

	public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws UserException, MenuItemException, CartException, CartItemException;

	public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws CartItemException;

	public Cart removeItemFromCart(Long cartItemId, String jwt) throws UserException, CartException, CartItemException;

	public Long calculateCartTotals(Cart cart) throws UserException;
	
	public Cart findCartById(Long id) throws CartException;
	
	public Cart findCartByUserId(Long userId) throws CartException, UserException;
	

	

	

}
