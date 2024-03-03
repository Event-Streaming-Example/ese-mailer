package com.ese.mailer.application.interfaces;

import com.ese.mailer.core.domain.entities.EmailRequest;

public interface SMTPClient {

    void send(EmailRequest request);

}
