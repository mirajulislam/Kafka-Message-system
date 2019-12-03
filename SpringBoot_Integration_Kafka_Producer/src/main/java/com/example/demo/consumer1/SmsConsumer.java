package com.example.demo.consumer1;

import java.time.Duration;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.example.demo.configuration.ConsumerConfiguration;
import com.example.demo.consumer.config.ConsumerRebalancerListener;



public class SmsConsumer {

	private static Logger log = LogManager.getLogger(SmsConsumer.class);

	@Autowired
	static ConsumerConfiguration consumerConfig;

	@Value("${topics.keys}")
	private static String keys;

	static boolean sleep = false;
	static int sleepMili = 5000;
	static Duration timeout = Duration.ofMillis(100);

	public static void processRecords(KafkaConsumer<String, String> consumer) {
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(timeout);
			for (ConsumerRecord<String, String> record : records) {
				if (record.key().equals("AIBL")) {
					log.info("offset = {}, key = {}, value = {}", record.offset(), record.key(), record.value());
					// Save processed offset in external storage.
					ConsumerRebalancerListener.offsetManager.saveOffsetInExternalStore(record.topic(),
							record.partition(), record.offset());
				}
			}
		}
	}
}
