package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.BookDto;
import com.getir.readingisgood.dto.CustomerDto;
import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.service.BookService;
import com.getir.readingisgood.service.CustomerService;
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
class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    @Test
    void createCustomer() {
        CustomerDto expected = CustomerDto.builder()
                .id(UUID.randomUUID().toString())
                .name("Batuhan Göktaş")
                .email("batuhangoktas@hotmail.com")
                .password("123456")
                .build();

        when(customerService.create(any())).thenReturn(expected);

        CustomerDto request = new CustomerDto();
        ResponseEntity<CustomerDto> response = customerController.createCustomer(request);
        CustomerDto actual = response.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(expected, actual),
                () -> assertEquals(expected.getId(), actual.getId())
        );
    }
}