package org.thesis.services.order;

import org.thesis.models.OrderHistory;

public interface OrderService {
    OrderHistory saveOrder(String user);
    OrderHistory getOrderHistory(String user);
}
