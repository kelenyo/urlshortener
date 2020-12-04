package com.kelenyo.urlshortener.service.exception;

import org.springframework.http.HttpStatus;


public class UnknownUrlException extends ShortenerException {

    public UnknownUrlException() {
        super("UNKNOWN_URL", HttpStatus.NOT_FOUND);
    }

}