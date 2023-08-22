package org.thesis.services.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thesis.models.Cart;
import org.thesis.models.Order;
import org.thesis.models.OrderHistory;
import org.thesis.repositories.OrderHistoryRepository;
import org.thesis.services.cart.CartService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private CartService cartService;

    @Override
    public OrderHistory saveOrder(String user) {
        Cart cart = cartService.getCartByUser(user);
        OrderHistory orderHistory = getOrderHistory(user);

        Order order = Order.createOrder(cart);
        List<Order> orders = orderHistory.getOrders();
        orders.add(order);

        cartService.emptyCart(user);
        return orderHistoryRepository.save(orderHistory);
    }

    @Override
    public OrderHistory getOrderHistory(String user) {
        OrderHistory orderHistory = orderHistoryRepository.findOrderHistoryByUser(user);

        if (orderHistory == null) {
            orderHistory = new OrderHistory();
            orderHistory.setUser(user);
            orderHistory.setOrders(new ArrayList<>());
        }

        return orderHistory;
    }
}
