package com.example.demo.consumer.config;

import java.util.Collection;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.TopicPartition;


public class ConsumerRebalancerListener implements org.apache.kafka.clients.consumer.ConsumerRebalanceListener{

	public static OffsetManager offsetManager = new OffsetManager("OffsetManagerStorage");
	private Consumer<String, String> consumer;

	public ConsumerRebalancerListener(Consumer<String, String> consumer) {
		this.consumer = consumer;
	}

	@Override
	public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
		for (TopicPartition partition : partitions) {
			offsetManager.saveOffsetInExternalStore(partition.topic(), partition.partition(),
					consumer.position(partition));
		}
		
	}

	@Override
	public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
		for (TopicPartition partition : partitions) {
			// consumer.seek(topicPartition,offset) to control offset which messages to be
			// read.
			consumer.seek(partition,
					offsetManager.readOffsetFromExternalStore(partition.topic(), partition.partition()));
		}

	}
}
