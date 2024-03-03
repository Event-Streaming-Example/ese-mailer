package com.ese.mailer.queue.rabbitmq;

import com.ese.mailer.application.interfaces.PollingFunction;
import com.ese.mailer.application.interfaces.QueueClient;
import com.ese.mailer.queue.rabbitmq.config.RabbitMQConfig;
import com.ese.mailer.queue.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

public class RabbitMQClient implements QueueClient {

    private final RabbitMQConfig config;
    private final RabbitMQUtils utils;
    private final Logger logger;

    @Inject
    public RabbitMQClient(RabbitMQConfig config, RabbitMQUtils utils, Logger logger) {
        this.config = config;
        this.utils = utils;
        this.logger = logger;
    }

    @Override
    public void startPolling(List<PollingFunction> functions) {
        functions.forEach((func) -> {
            DeliverCallback callback = utils.createCallback(func);
            this.logger.info("Starting consumption for %s".formatted(func.display()));
            try {
                utils.invokeConsumption(config.getName(), callback);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void stopPolling() {
        this.utils.stopConsumption();
        this.logger.info("Consumption of messaged Stopped. Channel closing.");
    }
}
