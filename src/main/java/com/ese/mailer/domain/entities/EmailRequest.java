package com.ese.mailer.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class EmailRequest {

    private String from;
    private String to;
    private String subject;
    private String content;
}
