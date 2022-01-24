package com.example.camel.api.rabbitcamelapi.controller;

import com.example.camel.api.rabbitcamelapi.model.Employee;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {

    @Produce(uri = "direct:startRabbitMQPoint")
    private ProducerTemplate template;

    @GetMapping("/employee")
    public String createEmployee(@RequestParam int id, @RequestParam String name, @RequestParam String lastName){
        Employee employee = new Employee(id,name,lastName);
        template.asyncSendBody(template.getDefaultEndpoint(), employee);
        return "msg send";
    }

}
