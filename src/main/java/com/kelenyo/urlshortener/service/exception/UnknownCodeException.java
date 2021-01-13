package com.kelenyo.urlshortener.service.exception;

import org.springframework.http.HttpStatus;


public class UnknownCodeException extends ShortenerException {
    public UnknownCodeException() {
        super("BAD_CODE", HttpStatus.NOT_ACCEPTABLE);
    }
}