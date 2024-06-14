package com.example.rabbitmq.consumer;

import com.example.rabbitmq.model.Users;
import com.example.rabbitmq.producer.MessageParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class LastConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(LastConsumer.class);

    @RabbitListener(queues ={"${rabbitmq.reply.queue.name}"} )

    public void  consume(Message message){

        Users user = (Users) MessageParser.deSerializeObjectFromByteArray(message.getBody());
        LOGGER.info("Last Consumer : " + user.getId() + "  " + user.getName());
    }
}
