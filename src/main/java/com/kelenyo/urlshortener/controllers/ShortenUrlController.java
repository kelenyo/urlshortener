package com.kelenyo.urlshortener.controllers;

import com.kelenyo.urlshortener.dto.UrlRequest;
import com.kelenyo.urlshortener.models.ShortenUrl;
import com.kelenyo.urlshortener.service.ShortenUrlService;
import org.springframework.http.HttpStatus;
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

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String welcome() {
        return "Welcome to my demo";
    }


    @ApiOperation(value = "Convert new url", notes = "Converts long url to short url")
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/createshorturl", method=RequestMethod.POST)
    public ShortenUrl convertToShortUrl(@RequestBody UrlRequest urlRequest) {
        return shortenUrlService.convertToShortUrlAndSave(urlRequest);
    }

    @ApiOperation(value = "Redirect", notes = "Finds original url from short url and redirects")
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/{shortUrl}", method=RequestMethod.GET)
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        var url = shortenUrlService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}
