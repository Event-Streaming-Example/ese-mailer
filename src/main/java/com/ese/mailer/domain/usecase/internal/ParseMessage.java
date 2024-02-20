package com.ese.mailer.domain.usecase.internal;

import com.ese.mailer.domain.entities.EmailRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParseMessage {

    private final ObjectMapper objectMapper;

    @Autowired
    public ParseMessage(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public EmailRequest parseRequest(String message) throws JsonProcessingException {
        return this.objectMapper.readValue(message, EmailRequest.class);
    }

}
