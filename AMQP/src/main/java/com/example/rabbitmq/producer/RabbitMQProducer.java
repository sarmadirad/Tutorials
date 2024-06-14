package com.example.rabbitmq.producer;

import com.example.rabbitmq.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);
    public void sendMessage(Users message){

        rabbitTemplate.convertAndSend(exchange, routingKey, MessageParser.serializeObjectToByteArray(message));
    // Message receivedMsg = rabbitTemplate.receive("javaguides");
    //   Users u =(Users) deSerializeObjectFromByteArray(receivedMsg.getBody());
    //   LOGGER.info("Recevie Messsage : " + u.getId() + "  " + u.getName());
    }

}
