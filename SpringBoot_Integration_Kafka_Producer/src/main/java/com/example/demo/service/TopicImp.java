package com.example.demo.service;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.CreateTopicsOptions;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.configuration.ProducerConfiguration;

@Service
public class TopicImp implements Topics{

	@Value("${bootstrap.servers}")
	private String bootstrapAddress;
	
	@Autowired
	ProducerConfiguration producerConfiguration;
	
		@Override
	public void createTopices(String topicName, int partition, int refactor) {
			
		try {
			
			KafkaFuture<Void> future = producerConfiguration.client()
					.createTopics(Collections.singleton(new NewTopic(topicName, partition, (short) refactor)),
							new CreateTopicsOptions().timeoutMs(10000))
					.all();
			future.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully created");
	}

	@Override
	public void deleteTopices(String topicName) {
		KafkaFuture<Void> future = producerConfiguration.client().deleteTopics(Collections.singleton(topicName)).all();
		try {
			future.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully Deleted");
	}
	


}
