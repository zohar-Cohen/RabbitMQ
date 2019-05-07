package com.zohar.consumer;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class MessageListenerImpl implements MessageListener {

	@Override
	public void receiveMessage(String message) {
		System.out.println("Thread: "+Thread.currentThread().getName());
	    System.out.println(new Date());
	    try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    System.out.println("Message Received : "+message);
	    System.out.println(new Date());
		
	}

}
