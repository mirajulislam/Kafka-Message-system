package com.example.demo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public class KafkaConsumer {
	private static Logger log = LogManager.getLogger(KafkaConsumer.class);
	//store to message display from consumer
    @KafkaListener(topics = "Kafka_Example", groupId = "group_id")
    public void consume(String message) {
       log.info ("Consumed message: " + message);
    }
    
	static boolean sleep = false;
	static int sleepMili = 5000;

	@KafkaListener(topics = "Kafka_Example_Separate", groupId = "group_id")
	public void consume1(String message) throws InterruptedException {
		
		//for testing purpose
		if (sleep) {
			Thread.sleep(sleepMili);
		}
		
		log.info("Consumed message(1): [{}]", message);
	}
    
}
