package com.ese.mailer.smtpServer.mailHog;

import com.ese.mailer.application.interfaces.SMTPClient;
import com.ese.mailer.core.domain.entities.EmailRequest;
import org.slf4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.inject.Inject;

public class MailHogSMTPClient implements SMTPClient {

    private final JavaMailSender mailSender;
    private final Logger logger;

    @Inject
    public MailHogSMTPClient(JavaMailSender mailSender, Logger logger) {
        this.mailSender = mailSender;
        this.logger = logger;
    }

    @Override
    public void send(EmailRequest request) {
        SimpleMailMessage emailMessage = new SimpleMailMessage();

        emailMessage.setFrom(request.getFrom());
        emailMessage.setTo(request.getTo());
        emailMessage.setSubject(request.getSubject());
        emailMessage.setText(request.getContent());

        this.logger.info("Sending email --- [From: %s] | [To : %s] | [Subject : %s]".formatted(request.getFrom(), request.getTo(), request.getSubject()));
        this.mailSender.send(emailMessage);
    }
}
