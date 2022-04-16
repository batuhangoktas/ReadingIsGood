package com.getir.readingisgood.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Builder
@Document(collection = "customer")
public class Customer {
    @Id
    private String id;
    private String name;
    private String emailAddress;
    private String password;
    private LocalDateTime createDate;
}
