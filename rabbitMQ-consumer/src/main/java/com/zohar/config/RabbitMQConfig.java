package com.zohar.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zohar.consumer.MessageListener;

@Configuration
public class RabbitMQConfig {

	@Value("${rabbitmq.routing-key}")
	private String routingKey;

	@Value("${rabbitmq.topic-exchange}")
	private String exchange;

	@Bean
	Queue queue() {
		return new Queue(routingKey, true);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(exchange);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			                                 MessageListenerAdapter messageListenerAdapter) {

		SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
		listenerContainer.setConnectionFactory(connectionFactory);
		listenerContainer.setQueueNames(routingKey);
		listenerContainer.setMaxConcurrentConsumers(10);
		//listenerContainer.setConcurrency("2-10");
		listenerContainer.setMessageListener(messageListenerAdapter);

		return listenerContainer;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(MessageListener receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

}
