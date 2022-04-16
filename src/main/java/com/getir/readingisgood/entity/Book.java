package com.getir.readingisgood.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    private String name;

    private String author;

    private Double price;

    private Integer stock;

    public void setId(String id) {
        this.id = id;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
