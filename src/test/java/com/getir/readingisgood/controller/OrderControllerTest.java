package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.OrderDto;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    OrderService orderService;

    @InjectMocks
    OrderController orderController;

    @Test
    void createOrder() {
        OrderDto expected = OrderDto.builder()
                .id(UUID.randomUUID().toString())
                .customerId("123456789")
                .build();

        when(orderService.create(any())).thenReturn(expected);

        OrderDto request = new OrderDto();
        ResponseEntity<OrderDto> response = orderController.createOrder(request);
        OrderDto actual = response.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(expected, actual),
                () -> assertEquals(expected.getCustomerId(), actual.getCustomerId())
        );
    }
}