package org.thesis.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class OrderHistory {
    @Id
    private String user;
    private List<Order> orders;
}
