package org.thesis.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.thesis.models.OrderHistory;

@Repository
public interface OrderHistoryRepository extends MongoRepository<OrderHistory, String> {
    OrderHistory findOrderHistoryByUser(String user);
}