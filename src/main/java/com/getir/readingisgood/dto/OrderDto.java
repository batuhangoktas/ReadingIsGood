package com.getir.readingisgood.dto;

import com.getir.readingisgood.entity.BookOrder;
import com.getir.readingisgood.entity.Order;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    @ApiModelProperty(hidden = true)
    private String id;
    private String customerId;
    private List<BookOrder> bookOrderList;

    public void setId(String id) {
        this.id = id;
    }

    public static OrderDto toDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .bookOrderList(order.getBookOrderList())
                .build();
    }
}
