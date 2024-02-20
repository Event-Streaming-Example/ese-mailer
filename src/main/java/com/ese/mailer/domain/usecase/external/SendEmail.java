package com.ese.mailer.domain.usecase.external;

import com.ese.mailer.domain.usecase.internal.ParseMessage;
import com.ese.mailer.domain.entities.EmailRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {

    private final ParseMessage parseMessage;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    public SendEmail(ParseMessage parseMessage) {
        this.parseMessage = parseMessage;
    }

    public void invoke(String message) throws JsonProcessingException {
        System.out.printf("Received message : %s%n", message);

        EmailRequest request = parseMessage.parseRequest(message);
        SimpleMailMessage emailMessage = formatMessage(request);

        this.mailSender.send(emailMessage);
    }

    private SimpleMailMessage formatMessage(EmailRequest request) {
        SimpleMailMessage emailMessage = new SimpleMailMessage();

        emailMessage.setFrom(request.getFrom());
        emailMessage.setTo(request.getTo());
        emailMessage.setSubject(request.getSubject());
        emailMessage.setText(request.getContent());

        return  emailMessage;
    }

}
