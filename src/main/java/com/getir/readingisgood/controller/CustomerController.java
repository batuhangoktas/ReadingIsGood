package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.CustomerDto;
import com.getir.readingisgood.service.CustomerService;
import com.getir.readingisgood.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Api(value = "Customer Service Api")
public class CustomerController extends BaseController {

    @Autowired
    public CustomerService customerService;

    @Autowired
    public OrderService orderService;

    @PostMapping("/create")
    @ApiOperation(value = "Create Customer",
            notes = "Create Customer Service Call",
            response = CustomerDto.class)
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity(customerService.create(customerDto), HttpStatus.CREATED);
    }

    @GetMapping("/orders/{id}")
    @ApiOperation(value = "Customer's Order List",
            notes = "Customer's Order List Service Call",
            response = CustomerDto.class)
    public ResponseEntity<List<CustomerDto>> getCustomerOrderList(@PathVariable String id,
                                                                  @RequestParam("pagesize") int pageSize,
                                                                  @RequestParam("offset") int offset) {

        Pageable pageable = PageRequest.of(offset, pageSize);
        return new ResponseEntity(orderService.getCustomerOrderList(id, pageable), HttpStatus.OK);
    }

}
