package org.thesis.services.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thesis.models.SimpleCart;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private String cartMicroserviceUrl;

    @Override
    public SimpleCart getCartByUser(String user) {
        String getCartUrl = cartMicroserviceUrl + "/" + user;
        ResponseEntity<SimpleCart> response = restTemplate.getForEntity(getCartUrl, SimpleCart.class);

        return response.getBody();
    }

    @Override
    public SimpleCart emptyCart(String user) {
        String emptyCartUrl = cartMicroserviceUrl + "/remove/" + user;
        ResponseEntity<SimpleCart> response = restTemplate.postForEntity(
                emptyCartUrl,
                null,
                SimpleCart.class);

        return response.getBody();
    }
}

