package org.thesis.services.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thesis.models.Cart;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private String cartMicroserviceUrl;

    @Override
    public Cart getCartByUser(String user) {
        String getCartUrl = cartMicroserviceUrl + "/" + user;
        ResponseEntity<Cart> response = restTemplate.getForEntity(getCartUrl, Cart.class);

        return response.getBody();
    }

    @Override
    public Cart emptyCart(String user) {
        String emptyCartUrl = cartMicroserviceUrl + "/remove/" + user;
        ResponseEntity<Cart> response = restTemplate.postForEntity(
                emptyCartUrl,
                null,
                Cart.class);

        return response.getBody();
    }
}

