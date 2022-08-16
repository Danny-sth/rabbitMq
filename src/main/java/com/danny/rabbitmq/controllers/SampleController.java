package com.danny.rabbitmq.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SampleController {

    Logger log = LoggerFactory.getLogger(SampleController.class);

    private final RabbitTemplate template;

    public SampleController(RabbitTemplate template) {
        this.template = template;
    }

    @PostMapping(path = "/emit")
    public ResponseEntity<String> emit(@RequestBody Map<String, String> map) {
        log.info("Emit to firstQueue");

        template.convertAndSend(map.get("key"), map.get("message"));

        return ResponseEntity.ok("Success emit to queue");
    }
}
