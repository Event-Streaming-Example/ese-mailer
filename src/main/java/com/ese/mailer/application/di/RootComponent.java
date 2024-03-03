package com.ese.mailer.application.di;

import com.ese.mailer.application.interfaces.QueueClient;
import com.ese.mailer.core.domain.usecase.external.TriggerEmailOnMessageReceive;
import dagger.Component;
import org.slf4j.Logger;

import javax.inject.Singleton;

@Singleton
@Component(modules = RootModule.class)
public interface RootComponent {

    TriggerEmailOnMessageReceive provideTriggerEmailOnMessageReceive();

    QueueClient provideQueueClient();
    Logger provideLogger();

}
