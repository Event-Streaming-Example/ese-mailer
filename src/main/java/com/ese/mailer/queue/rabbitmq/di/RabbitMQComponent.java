package com.ese.mailer.queue.rabbitmq.di;

import com.ese.mailer.queue.rabbitmq.RabbitMQClient;
import dagger.BindsInstance;
import dagger.Component;
import org.slf4j.Logger;

import javax.inject.Singleton;
import java.util.Properties;

@Singleton
@Component(modules = RabbitMQModule.class)
public interface RabbitMQComponent {

    RabbitMQClient provideRabbitMQClient();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder properties(Properties properties);

        @BindsInstance
        Builder logger(Logger logger);

        RabbitMQComponent build();
    }

}
