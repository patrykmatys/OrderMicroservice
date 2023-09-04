package org.thesis.services.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thesis.models.Cart;
import org.thesis.models.CartItem;
import org.thesis.models.Item;
import org.thesis.models.SimpleCart;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

@Service
public class ItemsServiceImpl implements ItemsService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private String itemsMicroserviceUrl;

    @Override
    public Cart getFullCart(SimpleCart simpleCart) {
        Cart cart = new Cart();
        cart.setUser(simpleCart.getUser());

        HashMap<UUID, Integer> simpleItems = simpleCart.getItems();
        HashMap<UUID, CartItem> cartItems = new HashMap<>();

        simpleItems.forEach((item, amount) -> {
            CartItem cartItem = new CartItem();
            cartItem.setAmount(amount);
            cartItem.setPrice(getItemPrice(item));

            cartItems.put(item, cartItem);
        });

        cart.setItems(cartItems);

        return cart;
    }

    private BigDecimal getItemPrice(UUID item) {
        String getItemUrl = itemsMicroserviceUrl + "/" + item;
        ResponseEntity<Item> response = restTemplate.getForEntity(getItemUrl, Item.class);

        return response.getBody().getPrice();
    }
}
