package com.zohar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zohar.producer.MessageProducer;

@RestController
@RequestMapping("/producer")
public class ProducerController {

	@Autowired
	private MessageProducer messageProducer;
	
	@GetMapping
	public String produce(@RequestParam String message) {
		messageProducer.sendMessege(message);
		return "Message sent to RabbitMQ, message: "+message;
		
	}
}
