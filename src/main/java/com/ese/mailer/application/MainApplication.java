package com.ese.mailer.application;

import com.ese.mailer.application.di.DaggerRootComponent;
import com.ese.mailer.application.di.RootComponent;
import com.ese.mailer.application.interfaces.PollingFunction;
import com.ese.mailer.application.interfaces.QueueClient;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class MainApplication implements CommandLineRunner {

    private final List<PollingFunction> pollingFunctions;
    private final QueueClient queueClient;
    private final Logger logger;

    @Inject
    MainApplication() {
        RootComponent component = DaggerRootComponent.create();

        List<PollingFunction> functions = new ArrayList<>();
        functions.add(component.provideTriggerEmailOnMessageReceive());

        this.pollingFunctions = functions;
        this.queueClient = component.provideQueueClient();
        this.logger = component.provideLogger();
    }

    @Override
    public void run(String... args) {
        logger.info("Starting queue consumption");
        this.queueClient.startPolling(this.pollingFunctions);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutdown hook triggered");
            this.queueClient.stopPolling();
        }));
    }
}
