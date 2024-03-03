package com.ese.mailer.smtpServer.mailHog.di;

import com.ese.mailer.smtpServer.mailHog.MailHogSMTPClient;
import dagger.BindsInstance;
import dagger.Component;
import org.slf4j.Logger;

import javax.inject.Singleton;
import java.util.Properties;

@Singleton
@Component(modules = MailHogModule.class)
public interface MailHogComponent {

    MailHogSMTPClient provideMailHogSMTPClient();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder properties(Properties properties);

        @BindsInstance
        Builder logger(Logger logger);

        MailHogComponent build();
    }

}
