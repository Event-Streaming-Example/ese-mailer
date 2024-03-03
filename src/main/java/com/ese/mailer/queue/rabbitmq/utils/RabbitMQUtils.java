package com.ese.mailer.queue.rabbitmq.utils;

import com.ese.mailer.application.exceptions.QueueConnectionException;
import com.ese.mailer.application.interfaces.PollingFunction;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Getter
@Setter
public class RabbitMQUtils {

    private final Channel channel;

    @Inject
    public RabbitMQUtils(Channel channel) {
        this.channel = channel;
    }

    public DeliverCallback createCallback(PollingFunction function) {
        return (consumerTag, message) -> {
            String request = new String(message.getBody(), StandardCharsets.UTF_8);
            function.execute(request);
        };
    }

    public void invokeConsumption(String queueName, DeliverCallback callback) throws IOException {
        Channel channel = this.channel;
        channel.basicConsume(queueName, true, callback, consumerTag -> {
        });
    }

    public void stopConsumption() {
        if (this.channel.isOpen()) {
            try {
                this.channel.close();
            } catch (IOException | TimeoutException e) {
                throw new QueueConnectionException("Error closing channel", e);
            }
        }
    }
}
