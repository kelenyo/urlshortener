package com.kelenyo.urlshortener.service.exception;

import org.springframework.http.HttpStatus;


public class AlreadyExistingCodeException extends ShortenerException {
    public AlreadyExistingCodeException() {
        super("DUPLICATE_CODE", HttpStatus.NOT_ACCEPTABLE);
    }
}