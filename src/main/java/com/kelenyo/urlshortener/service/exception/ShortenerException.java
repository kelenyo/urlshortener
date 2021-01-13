package com.kelenyo.urlshortener.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ShortenerException extends ResponseStatusException {
    public ShortenerException(String key, HttpStatus httpErrorCode) {
        super(httpErrorCode, key);
    }
}