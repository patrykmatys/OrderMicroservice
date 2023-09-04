package org.thesis.services.items;

import org.thesis.models.Cart;
import org.thesis.models.SimpleCart;

public interface ItemsService {
    Cart getFullCart(SimpleCart simpleCart);
}
