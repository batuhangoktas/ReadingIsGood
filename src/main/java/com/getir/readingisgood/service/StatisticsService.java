package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.StatisticsDto;

import java.util.List;

public interface StatisticsService {
    List<StatisticsDto> getMonthlyStatisticsByCustomer(String customerId);
}
