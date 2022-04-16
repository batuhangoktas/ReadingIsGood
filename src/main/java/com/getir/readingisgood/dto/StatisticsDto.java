package com.getir.readingisgood.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDto {

    private String month;
    private int totalOrderCount;
    private int totalBookCount;
    private Double totalPurchasedAmount;

}
