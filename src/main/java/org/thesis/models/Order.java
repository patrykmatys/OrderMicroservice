package org.thesis.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class Order {
    private Cart cart;
    private Date created;
    private BigDecimal price;

    public static Order createOrder(Cart cart, BigDecimal price) {
        return Order.builder()
                .cart(cart)
                .created(new Date())
                .price(price)
                .build();
    }
}
