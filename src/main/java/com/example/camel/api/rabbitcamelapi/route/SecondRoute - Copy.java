package com.example.camel.api.rabbitcamelapi.route;

import com.example.camel.api.rabbitcamelapi.model.Employee;
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

        from("direct:startRabbitMQPoint").id("1").marshal(jsonDataFormat)
                .bean(conversionProcessorForMqMsg)
                .log("${body}")
                .setBody(Exchange)
                .to("rest:post:/addEmployee");
    }
}



@Component
class ConversionProcessorForMqMsg{

    Logger logger = LogManager.getLogger(ConversionProcessor.class);
    public Employee process(Employee employee){
        employee.setName(employee.getName().toUpperCase());
        logger.info("==employee name in UPPERCASE=="+ employee.getName());
        employee.setLastName(employee.getLastName().toUpperCase());
        logger.info("==employee lastName in UPPERCASE=="+ employee.getLastName());
        return employee;
    }
}

@Component
class ConversionProcessor{

    Logger logger = LogManager.getLogger(ConversionProcessor.class);
    public String process(String employeeName){
        logger.info("=emp name in UPPERCASE==>"+ employeeName.toUpperCase());
        return employeeName.toUpperCase();
    }
}
