package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.OrderDto;
import com.getir.readingisgood.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/order")
@Api(value = "Order Service Api")
public class OrderController extends BaseController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    @ApiOperation(value = "Create Order",
            notes = "Create Order Service Call",
            response = OrderDto.class)
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {

        return new ResponseEntity(orderService.create(orderDto), HttpStatus.CREATED);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Order Detail",
            notes = "Order Detail Service Call",
            response = OrderDto.class)
    public ResponseEntity<OrderDto> getOrderDetail(@PathVariable String id) {

        return new ResponseEntity(orderService.getOrderDetail(id), HttpStatus.OK);
    }

    @GetMapping("/list")
    @ApiOperation(value = "Order List",
            notes = "Order List Service Call",
            response = OrderDto.class)
    public ResponseEntity<List<OrderDto>> getOrderListByDate(@RequestParam(value = "startDate")
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                             @RequestParam(value = "endDate")
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return new ResponseEntity(orderService.getOrderListBetweenDates(startDate, endDate), HttpStatus.OK);
    }

}
