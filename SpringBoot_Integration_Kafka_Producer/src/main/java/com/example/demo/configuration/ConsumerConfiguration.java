package com.example.demo.configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

import com.example.demo.consumer.config.ConsumerRebalancerListener;
import com.example.demo.consumer1.SmsConsumer;

@EnableKafka
@Configuration
public class ConsumerConfiguration {

	@Value("${bootstrap.servers}")
	private String bootstrapAddress;

	@Value("${group.id}")
	private String groupId;
	
	@Value("${topics.keys}")
	private String keys;


//	for String formate consumer configuration
	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> config = new HashMap<>();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

		return new DefaultKafkaConsumerFactory<>(config);
	}
//	for String formate consumer

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
		factory.setConsumerFactory(consumerFactory());
	
		return factory;
	}


	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory1() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
		factory.setConsumerFactory(consumerFactory());
		factory.setRecordFilterStrategy(new RecordFilterStrategy<String, String>() {

			@Override
			public boolean filter(ConsumerRecord<String, String> consumerRecord) {
				if (consumerRecord.key().equals(keys)) {
					return false;
				} else {
					return true;
				}
			}
		});
		return factory;
	}
	
	@Bean
	public static void readMessage() throws InterruptedException {

		KafkaConsumer<String, String> consumer = createConsumer();
		consumer.subscribe(Arrays.asList("Kafka_Example_Separate"), new ConsumerRebalancerListener(consumer));
		SmsConsumer.processRecords(consumer);
	}
	
	@Bean
	private static KafkaConsumer<String, String> createConsumer() {
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, "2000");
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "6001");
		props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "140");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new KafkaConsumer<String, String>(props);
	}
	
}
