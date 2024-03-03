package com.ese.mailer.application.exceptions;

public class MessageParsingException extends RuntimeException {
    public MessageParsingException(Throwable cause){
        super("Error parsing message using Jackson. Likely not in JSON format", cause);
    }
}
