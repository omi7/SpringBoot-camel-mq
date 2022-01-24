package com.example.camel.api.rabbitcamelapi.model;

import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * this class used for local endpoints demo
 */
@Component
public class ConversionProcessor{

    Logger logger = LogManager.getLogger(ConversionProcessor.class);
    public String process(String employeeName){
        logger.info("=emp name in UPPERCASE==>"+ employeeName.toUpperCase());
        return employeeName.toUpperCase();
    }
}