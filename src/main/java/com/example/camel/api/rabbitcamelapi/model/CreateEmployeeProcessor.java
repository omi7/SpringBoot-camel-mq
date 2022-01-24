package com.example.camel.api.rabbitcamelapi.model;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 *  class to create and set body for POST call
 */
@Component
public class CreateEmployeeProcessor{

        public void process(Exchange exchange, Employee employee) throws Exception {
            exchange.getIn().setBody(employee);
        }
    }