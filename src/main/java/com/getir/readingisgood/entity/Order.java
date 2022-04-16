package com.getir.readingisgood.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@Document(collection = "order")
public class Order {
    @Id
    private String id;
    private String customerId;
    private List<BookOrder> bookOrderList;
    private LocalDateTime createDate;

}
