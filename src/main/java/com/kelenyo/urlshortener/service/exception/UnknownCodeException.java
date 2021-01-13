package com.kelenyo.urlshortener.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class UnknownCodeException extends ResponseStatusException {
    public UnknownCodeException() {
        super(HttpStatus.NOT_ACCEPTABLE, "bad code");
    }
}