package com.ese.mailer.core.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;

@Getter
@Setter
public class EmailRequest {

    private String from;
    private String to;
    private String subject;
    private String content;

    @Inject
    public EmailRequest() {
    }
}
