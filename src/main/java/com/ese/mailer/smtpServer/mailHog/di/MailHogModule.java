package com.ese.mailer.smtpServer.mailHog.di;

import com.ese.mailer.smtpServer.mailHog.config.MailHogConfig;
import dagger.Module;
import dagger.Provides;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.inject.Singleton;
import java.util.Properties;

@Module
public class MailHogModule {

    private static final String MAIL_HOG_HOST = "smtp.mailHog.host";
    private static final String MAIL_HOG_PORT = "smtp.mailHog.port";

    @Provides
    @Singleton
    public MailHogConfig provideMailHogConfig(Properties properties) {
        MailHogConfig config = new MailHogConfig();

        config.setHost(properties.getProperty(MAIL_HOG_HOST));
        config.setPort(Integer.parseInt(properties.getProperty(MAIL_HOG_PORT)));

        return config;
    }

    @Provides
    @Singleton
    public JavaMailSender provideJavaMailSender(MailHogConfig config) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(config.getHost());
        javaMailSender.setPort(config.getPort());

        return javaMailSender;
    }

}