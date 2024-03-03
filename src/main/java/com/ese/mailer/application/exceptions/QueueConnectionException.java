package com.ese.mailer.application.exceptions;

public class QueueConnectionException extends RuntimeException {
    public QueueConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
