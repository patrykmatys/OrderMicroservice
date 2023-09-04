package org.thesis.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItem {
    private int amount;
    private BigDecimal price;
}
