package com.kelenyo.urlshortener.service.exception;

import org.springframework.http.HttpStatus;


public class AlreadyExistingUrlException extends ShortenerException {
    public AlreadyExistingUrlException() {
        super("DUPLICATE_URL", HttpStatus.NOT_ACCEPTABLE);
    }
}