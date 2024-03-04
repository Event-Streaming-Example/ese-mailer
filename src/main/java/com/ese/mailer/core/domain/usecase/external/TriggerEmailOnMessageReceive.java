package com.ese.mailer.core.domain.usecase.external;

import com.ese.mailer.application.interfaces.PollingFunction;
import com.ese.mailer.application.interfaces.SMTPClient;
import com.ese.mailer.core.domain.entities.EmailRequest;
import com.ese.mailer.core.domain.usecase.internal.ParseEmailRequest;

import javax.inject.Inject;

public class TriggerEmailOnMessageReceive implements PollingFunction {

    private final ParseEmailRequest parseEmailRequest;
    private final SMTPClient mailClient;

    @Inject
    public TriggerEmailOnMessageReceive(ParseEmailRequest parseEmailRequest, SMTPClient mailClient) {
        this.parseEmailRequest = parseEmailRequest;
        this.mailClient = mailClient;
    }

    @Override
    public void execute(String request) {
        EmailRequest emailRequest = this.parseEmailRequest.invoke(request);
        this.mailClient.send(emailRequest);
    }

    @Override
    public String display() {
        return this.getClass().getName();
    }
}
