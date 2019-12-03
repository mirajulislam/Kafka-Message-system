package com.example.demo.Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Topics;
import com.example.demo.model.User;
import com.example.demo.service.TopicImp;


@RestController
@RequestMapping("/kafka")
public class KafkaController {
	private static Logger log = LogManager.getLogger(KafkaController.class);
	@Autowired
	private TopicImp topicImp;
	
	@Autowired
	KafkaTemplate<String, User>kafkaTemplate;
	
	private static final String TOPIC ="Kafka_Example";

	//create topics
	@RequestMapping(value = "/topics/create", method = RequestMethod.POST)
	public String CreateTopic(@RequestBody Topics topic) {

		topicImp.createTopices(topic.getTopicName(), topic.getPartition(), topic.getRefactor());
		return "Successfully Create topics";
	}

	//delete topics
	@RequestMapping(value = "/topics/delete", method = RequestMethod.POST)
	public String DeleteTopic(@RequestBody Topics topic) {

		topicImp.deleteTopices(topic.getTopicName());
		return "Successfully Delete topics";
	}
	
	//producer to send message in json formate
	@GetMapping("/publish/{message}")
    public String post(@PathVariable("message")final String name) {
		
		kafkaTemplate.send(TOPIC,new User(name,"Engineer",1200L));
		return "publised Successfully";
	}
}
