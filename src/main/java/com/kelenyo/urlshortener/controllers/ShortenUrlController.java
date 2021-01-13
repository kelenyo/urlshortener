package com.kelenyo.urlshortener.controllers;

import com.kelenyo.urlshortener.dto.UrlRequest;
import com.kelenyo.urlshortener.service.ShortenUrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import java.net.URI;

@RestController
public class ShortenUrlController {

    private final ShortenUrlService shortenUrlService;

    public ShortenUrlController(ShortenUrlService shortenUrlService) {
        this.shortenUrlService = shortenUrlService;
    }

    @GetMapping(path = "/")
    public String welcome() {
        return "Welcome to my demo";
    }

    /**
     * Converts long url to short url.
     *
     * @param urlRequest containing the url to convert to add.
     */
    @ApiOperation(value = "Convert new url", notes = "Converts long url to short url")
    @PostMapping(value = "/createshorturl", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> convertToShortUrl(@RequestBody UrlRequest urlRequest) {
        return new ResponseEntity<>(shortenUrlService.convertToShortUrlAndSave(urlRequest), HttpStatus.CREATED);
    }

    /**
     * Finds original url from short url and redirects.
     *
     * @param shortUrl to be find and redirected.
     */
    @ApiOperation(value = "Redirect", notes = "Finds original url from short url and redirects")
    @GetMapping(value = "/{shortUrl}")
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        var url = shortenUrlService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}
