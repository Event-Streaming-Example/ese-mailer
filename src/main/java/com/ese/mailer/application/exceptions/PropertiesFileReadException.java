package com.ese.mailer.application.exceptions;

public class PropertiesFileReadException extends RuntimeException {
    public PropertiesFileReadException(Throwable cause){
        super("Error while reading .properties file", cause);
    }
}
