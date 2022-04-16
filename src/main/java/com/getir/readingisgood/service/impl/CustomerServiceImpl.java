package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.dto.CustomerDto;
import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.entity.Log;
import com.getir.readingisgood.exception.CustomerIsAlreadyRegisteredException;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.service.CustomerService;
import com.getir.readingisgood.service.LogService;
import com.getir.readingisgood.util.LogEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CustomerServiceImpl extends BaseServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    LogService logService;

    public CustomerServiceImpl() {
        super(CustomerServiceImpl.class);
    }

    @Override
    public CustomerDto create(CustomerDto customerDto) {
        if (customerRepository.findByEmailAddress(customerDto.getEmail()).isPresent()) {
            logger.info("Email is already registered email:" + customerDto.getEmail());
            throw new CustomerIsAlreadyRegisteredException("Customer is already registered");
        } else {
            Customer customer = Customer.builder()
                    .id(UUID.randomUUID().toString())
                    .name(customerDto.getName())
                    .emailAddress(customerDto.getEmail())
                    .createDate(LocalDateTime.now())
                    .password(customerDto.getPassword())
                    .build();
            customerRepository.save(customer);
            logService.save(new Log(Customer.class, customer.getId(), LogEnum.Create));
            customerDto.setId(customer.getId());
            logger.info("New customer saved" + customerDto.getEmail());
            return customerDto;
        }

    }
}
