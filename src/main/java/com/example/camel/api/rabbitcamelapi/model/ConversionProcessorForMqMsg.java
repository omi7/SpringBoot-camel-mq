package com.example.camel.api.rabbitcamelapi.model;

import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * class used when getting msg from rabbitMQ
 */
@Component
public class ConversionProcessorForMqMsg{

    Logger logger = LogManager.getLogger(ConversionProcessor.class);
    public Employee process(Employee employee){
        employee.setName(employee.getName().toUpperCase());
        logger.info("==employee name in UPPERCASE=="+ employee.getName());
        employee.setLastName(employee.getLastName().toUpperCase());
        logger.info("==employee lastName in UPPERCASE=="+ employee.getLastName());
        return employee;
    }
}