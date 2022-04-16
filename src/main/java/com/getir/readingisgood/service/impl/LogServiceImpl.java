package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.entity.Log;
import com.getir.readingisgood.repository.LogRepository;
import com.getir.readingisgood.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    @Autowired
    LogRepository logRepository;


    @Override
    public void save(Log log) {
        logRepository.save(log);
    }
}
