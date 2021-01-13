package com.kelenyo.urlshortener.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class AlreadyExistingUrlException extends ResponseStatusException {
    public AlreadyExistingUrlException() {
        super(HttpStatus.NOT_ACCEPTABLE, "Duplicate url. Url already existing");
    }
}