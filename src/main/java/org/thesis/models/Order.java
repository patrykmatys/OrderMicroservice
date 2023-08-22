package org.thesis.models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Order {
    private Cart cart;
    private Date created;

    public static Order createOrder(Cart cart) {
        return Order.builder()
                .cart(cart)
                .created(new Date())
                .build();
    }
}
