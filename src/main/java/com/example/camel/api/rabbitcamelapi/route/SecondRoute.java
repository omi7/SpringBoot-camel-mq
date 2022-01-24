package com.example.camel.api.rabbitcamelapi.route;

import com.example.camel.api.rabbitcamelapi.model.ConversionProcessor;
import com.example.camel.api.rabbitcamelapi.model.ConversionProcessorForMqMsg;
import com.example.camel.api.rabbitcamelapi.model.CreateEmployeeProcessor;
import com.example.camel.api.rabbitcamelapi.model.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecondRoute extends RouteBuilder {

    Logger logger = LogManager.getLogger(SecondRoute.class);

    @Autowired
    ConversionProcessor conversionProcessor;

    @Autowired
    ConversionProcessorForMqMsg conversionProcessorForMqMsg;

    @Autowired
    CreateEmployeeProcessor createEmployeeProcessor;

    /**
     * route with rabbitmq server running
     */
   /* @Override
    public void configure() throws Exception {

        restConfiguration().host("localhost").port(8080);

        from("rabbitmq:receiveQueue").unmarshal().json(JsonLibrary.Jackson, Employee.class)
                    .bean(ConversionProcessorForMqMsg)
                    .log("${body}")
                .setBody("${body}")
                .to("rest:post:/addEmployee");
    }
}*/

    /**
     *  route for local testing
     * @throws Exception
     */
   /* @Override
    public void configure() throws Exception {


        from("timer:rabbitmq-timer?period=10000")
                .transform().constant("Om Bhakre")
                .bean(conversionProcessor)
                .log("${body}")
                .to("log: received");
    }
}*/

    @Override
    public void configure() throws Exception {

        restConfiguration().host("localhost").port(8080);
        JacksonDataFormat jsonDataFormat = new JacksonDataFormat(Employee.class);

        from("direct:startRabbitMQPoint").id("1").unmarshal().json(JsonLibrary.Jackson, Employee.class)
                .bean(conversionProcessorForMqMsg)
                .log("${body}")
                .bean(createEmployeeProcessor)
                .log("${body}")
                .to("rest:post:/addEmployee");
    }
}




