package com.kelenyo.urlshortener.controllers;


import com.kelenyo.urlshortener.dto.UrlRequest;
import com.kelenyo.urlshortener.service.ShortenUrlService;
import com.kelenyo.urlshortener.service.representation.ShortenUrlResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;


@RestController
@RequestMapping("/api")
public class ShortenUrlController {

    private final ShortenUrlService shortenUrlService;

    public ShortenUrlController(ShortenUrlService shortenUrlService) {
        this.shortenUrlService = shortenUrlService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String welcome() {
        return "Welcome to my demo";
    }


    @ApiOperation(value = "Convert new url", notes = "Converts long url to short url")
    @RequestMapping(value = "/createshorturl", method=RequestMethod.POST)
    public String convertToShortUrl(@RequestBody UrlRequest urlRequest, HttpServletRequest request) {
        return shortenUrlService.convertToShortUrlAndSave(urlRequest);
    }

    @ApiOperation(value = "Redirect", notes = "Finds original url from short url and redirects")
    @RequestMapping(value = "/{shortUrl}", method=RequestMethod.GET)
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        var url = shortenUrlService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}
