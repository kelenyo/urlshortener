package com.kelenyo.urlshortener.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class AlreadyExistingCodeException extends ResponseStatusException {
    public AlreadyExistingCodeException() {
        super(HttpStatus.NOT_ACCEPTABLE, "Code already existing");
    }
}