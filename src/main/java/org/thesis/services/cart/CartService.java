package org.thesis.services.cart;

import org.thesis.models.SimpleCart;

public interface CartService{
    SimpleCart getCartByUser(String user);
    SimpleCart emptyCart(String user);
}
