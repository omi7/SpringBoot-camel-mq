package com.example.camel.api.rabbitcamelapi.route;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.camel.api.rabbitcamelapi.model.Employee;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
//import org.apache.camel.model.dataformat.JacksonXMLDataFormat;
import org.springframework.stereotype.Component;

@Component
public class MyFirstRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

//        JacksonXMLDataFormat jacksonXMLDataFormat = new JacksonXMLDataFormat();
//        jacksonXMLDataFormat.setJsonView(Employee.class);
        JacksonDataFormat jsonDataFormat = new JacksonDataFormat(Employee.class);

      /*  from("direct:startRabbitMQPoint").id("1").marshal(jsonDataFormat)
                .log("${body}")
            .to("rabbitmq://localhost:15672/receiverQueueExchange.exchange?queue=receiveQueue.queue&autoDelete=false").end();*/


        from("direct:startRabbitMQPoint").id("1").marshal(jsonDataFormat)
                .log("${body}")
                .to("log:first-timer-msg");


    }
}
