//package com.example.demo.consumer.config;
//
//import java.util.Arrays;
//import java.util.Properties;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.example.demo.consumer1.SmsConsumer;
//
//
//
//
//@Configuration
//public class ConsumerConfiguration {
//	
//
//	/*
//	 * @Bean public static void readMessage() throws InterruptedException {
//	 * 
//	 * KafkaConsumer<String, String> consumer = createConsumer();
//	 * consumer.subscribe(Arrays.asList("Kafka_Example_Separate"), new
//	 * ConsumerRebalancerListener(consumer)); SmsConsumer.processRecords(consumer);
//	 * }
//	 * 
//	 * @Bean private static KafkaConsumer<String, String> createConsumer() {
//	 * Properties props = new Properties();
//	 * props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//	 * props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
//	 * props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
//	 * props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, "2000");
//	 * props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "6001");
//	 * props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "140");
//	 * props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
//	 * StringDeserializer.class);
//	 * props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
//	 * StringDeserializer.class); return new KafkaConsumer<String, String>(props); }
//	 */
//}
