package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.OrderDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    List<OrderDto> getCustomerOrderList(String customerId, Pageable pageable);

    OrderDto create(OrderDto orderDto);

    OrderDto getOrderDetail(String orderId);

    List<OrderDto> getOrderListBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
}
