package com.kelenyo.urlshortener.service.exception;

import org.springframework.http.HttpStatus;

public class ShortenerException extends RuntimeException{

    private String key;
    private HttpStatus httpErrorCode;

    public ShortenerException(String key, HttpStatus httpErrorCode) {
        setKey(key);
        setHttpErrorCode(httpErrorCode);
    }

    public HttpStatus getHttpErrorCode() {
        return httpErrorCode;
    }

    private void setHttpErrorCode(HttpStatus httpErrorCode) {
        this.httpErrorCode = httpErrorCode;
    }

    public String getKey() {
        return key;
    }

    private void setKey(String key) {
        this.key = key;
    }
}