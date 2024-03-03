package com.ese.mailer.queue.rabbitmq.di;

import com.ese.mailer.application.exceptions.QueueConnectionException;
import com.ese.mailer.queue.rabbitmq.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import dagger.Module;
import dagger.Provides;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

@Getter
@Setter
@Module
public class RabbitMQModule {

    private static final String QUEUE_NAME = "queue.rabbitmq.name";
    private static final String QUEUE_URL = "queue.rabbitmq.url";

    @Provides
    @Singleton
    public RabbitMQConfig provideRabbitMQConfig(Properties properties) {
        RabbitMQConfig config = new RabbitMQConfig();

        config.setName(properties.getProperty(QUEUE_NAME));
        config.setUrl(properties.getProperty(QUEUE_URL));

        return config;
    }

    @Provides
    @Singleton
    public Channel provideChannel(RabbitMQConfig config) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(config.getUrl());

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(config.getName(), false, false, false, null);
            return channel;

        } catch (TimeoutException | IOException e) {
            throw new QueueConnectionException("Error connecting to queue", e);
        }
    }


}
