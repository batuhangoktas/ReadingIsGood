package com.getir.readingisgood.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class BaseServiceImpl {

    protected Logger logger;

    public BaseServiceImpl(Class classs) {

        logger = LoggerFactory.getLogger(classs);
    }
}
