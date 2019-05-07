package com.zohar.producer;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${rabbitmq.routing-key}")
	private String routingKey;

	public void sendMessege(String messege) {
		System.out.println(new Date());
		rabbitTemplate.convertAndSend(routingKey, messege);
		System.out.println("Is listener returned: "+rabbitTemplate.isReturnListener());
		System.out.println(new Date());
	}
	
	
}
