package com.kelenyo.urlshortener.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class UnknownUrlException extends ResponseStatusException {
    public UnknownUrlException() {
        super(HttpStatus.BAD_REQUEST, "Invalid URL");
    }
}