package org.thesis.services.cart;

import org.thesis.models.Cart;

public interface CartService {
    Cart getCartByUser(String user);
    Cart emptyCart(String user);
}
