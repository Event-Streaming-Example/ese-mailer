package com.ese.mailer;

import com.ese.mailer.domain.usecase.external.SendEmail;
import com.ese.mailer.config.QueueConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Component
class MainRunner implements CommandLineRunner {

    private final QueueConfig queueConfig;
    private final SendEmail sendEmail;

    @Autowired
    MainRunner(QueueConfig queueConfig, SendEmail sendEmail) {
        this.queueConfig = queueConfig;
        this.sendEmail = sendEmail;
    }

    @Override
    public void run(String... args) throws Exception {
        Channel channel = getQueueChannel();
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = createCallback();
        channel.basicConsume(queueConfig.getName(), true, deliverCallback, consumerTag -> {
        });
    }

    private Channel getQueueChannel() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(queueConfig.getUrl());

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(queueConfig.getName(), false, false, false, null);

        return channel;
    }

    private DeliverCallback createCallback() {
        return (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            this.sendEmail.invoke(message);
        };
    }
}