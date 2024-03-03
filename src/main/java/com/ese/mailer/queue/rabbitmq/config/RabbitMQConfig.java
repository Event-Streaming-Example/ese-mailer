package com.ese.mailer.queue.rabbitmq.config;

import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;

@Getter
@Setter
public class RabbitMQConfig {

    private String url;
    private String name;

    @Inject
    public RabbitMQConfig() {
    }
}
