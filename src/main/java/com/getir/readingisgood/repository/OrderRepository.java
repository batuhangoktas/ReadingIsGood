package com.getir.readingisgood.repository;

import com.getir.readingisgood.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findAllByCustomerId(String customerId, Pageable page);

    List<Order> findAllByCreateDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Order> findAllByCustomerId(String customerId);
}
