package com.kelenyo.urlshortener.service.exception;

import org.springframework.http.HttpStatus;


public class UnknownUrlException extends ShortenerException {
    public UnknownUrlException() {
        super("BAD_URL", HttpStatus.BAD_REQUEST);
    }
}