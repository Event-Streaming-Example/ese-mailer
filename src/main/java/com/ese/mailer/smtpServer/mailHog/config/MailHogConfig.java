package com.ese.mailer.smtpServer.mailHog.config;

import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;

@Getter
@Setter
public class MailHogConfig {

    private String host;
    private Integer port;

    @Inject
    public MailHogConfig() {
    }
}
