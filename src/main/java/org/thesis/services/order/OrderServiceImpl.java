package org.thesis.services.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thesis.models.Cart;
import org.thesis.models.Order;
import org.thesis.models.OrderHistory;
import org.thesis.models.SimpleCart;
import org.thesis.repositories.OrderHistoryRepository;
import org.thesis.services.cart.CartService;
import org.thesis.services.items.ItemsService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ItemsService itemsService;

    @Override
    public OrderHistory saveOrder(String user) {
        SimpleCart simpleCart = cartService.getCartByUser(user);
        OrderHistory orderHistory = getOrderHistory(user);

        Cart cart = itemsService.getFullCart(simpleCart);

        Order order = Order.createOrder(cart, getTotalPrice(cart));
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

    private BigDecimal getTotalPrice (Cart cart) {
        AtomicReference<BigDecimal> totalPrice = new AtomicReference<>(BigDecimal.ZERO);

        cart.getItems().forEach((item, cartItem) -> {
            BigDecimal itemPrice = cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getAmount()));
            totalPrice.updateAndGet(currentTotal -> currentTotal.add(itemPrice));
        });

        return totalPrice.get();
    }
}
