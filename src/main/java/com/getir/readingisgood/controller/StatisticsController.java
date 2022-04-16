package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.StatisticsDto;
import com.getir.readingisgood.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@Api(value = "Statistics Service Api")
public class StatisticsController extends BaseController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/monthly/{customerId}")
    @ApiOperation(value = "Monthly Statistics",
            notes = "Monthly Statistics Service Call",
            response = StatisticsDto.class)
    public ResponseEntity<StatisticsDto> getMonthlyStaticticsByCustomer(@PathVariable String customerId) {

        return new ResponseEntity(statisticsService.getMonthlyStatisticsByCustomer(customerId), HttpStatus.OK);
    }
}
