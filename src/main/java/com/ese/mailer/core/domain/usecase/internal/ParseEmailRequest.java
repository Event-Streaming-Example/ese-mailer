package com.ese.mailer.core.domain.usecase.internal;

import com.ese.mailer.application.exceptions.MessageParsingException;
import com.ese.mailer.core.domain.entities.EmailRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;

public class ParseEmailRequest {

    private final ObjectMapper objectMapper;

    @Inject
    public ParseEmailRequest(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public EmailRequest invoke(String request) {
        try {
            return this.objectMapper.readValue(request, EmailRequest.class);
        } catch (JsonProcessingException e) {
            throw new MessageParsingException(e);
        }
    }
}
