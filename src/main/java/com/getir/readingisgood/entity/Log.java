package com.getir.readingisgood.entity;

import com.getir.readingisgood.util.LogEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;


@Document(collection = "log")
public class Log {
    private String className;
    private String objectId;
    private LogEnum type;
    private LocalDateTime date;

    public Log(Class aClass, String objectId, LogEnum type) {
        this.className = aClass.getName();
        this.objectId = objectId;
        this.type = type;
        this.date = LocalDateTime.now();
    }
}
