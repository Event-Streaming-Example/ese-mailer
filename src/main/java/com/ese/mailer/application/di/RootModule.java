package com.ese.mailer.application.di;

import com.ese.mailer.application.MainApplication;
import com.ese.mailer.application.exceptions.PropertiesFileReadException;
import com.ese.mailer.application.interfaces.QueueClient;
import com.ese.mailer.application.interfaces.SMTPClient;
import com.ese.mailer.queue.rabbitmq.di.DaggerRabbitMQComponent;
import com.ese.mailer.smtpServer.mailHog.di.DaggerMailHogComponent;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.Properties;

@Module
public class RootModule {

    private static final String APPLICATION_FILE = "application.properties";

    @Provides
    @Singleton
    public Properties provideProperties() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream(APPLICATION_FILE));
            return props;
        } catch (IOException e) {
            throw new PropertiesFileReadException(e);
        }
    }

    @Provides
    @Singleton
    public Logger provideLogger() {
        return LoggerFactory.getLogger(MainApplication.class);
    }

    @Provides
    @Singleton
    public ObjectMapper provideObjectMapper() {
        return new ObjectMapper();
    }

    @Provides
    @Singleton
    public QueueClient provideQueueClient(Logger logger, Properties properties) {
        return DaggerRabbitMQComponent.builder()
                .properties(properties)
                .logger(logger)
                .build()
                .provideRabbitMQClient();
    }

    @Provides
    @Singleton
    public SMTPClient provideSMTPClient(Logger logger, Properties properties) {
        return DaggerMailHogComponent.builder()
                .logger(logger)
                .properties(properties)
                .build()
                .provideMailHogSMTPClient();
    }

}
