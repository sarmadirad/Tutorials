package com.example.rabbitmq.consumer;


import com.example.rabbitmq.producer.MessageParser;
import com.example.rabbitmq.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service

public class RabbitMQConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

@RabbitListener(queues ={"${rabbitmq.queue.name}"} )
@SendTo("${rabbitmq.reply.queue.name}")
 public Message  consume(Message message){

    Users user = (Users) MessageParser.deSerializeObjectFromByteArray(message.getBody());
    LOGGER.info("Recevie Messsage  : " + user.getId() + "  " + user.getName());
    user.setName("ali");
    return MessageBuilder.withBody(MessageParser.serializeObjectToByteArray(user)).build();

    }

}
