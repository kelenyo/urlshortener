package com.kelenyo.urlshortener.service;

import com.kelenyo.urlshortener.dto.UrlRequest;
import com.kelenyo.urlshortener.models.ShortenUrl;
import com.kelenyo.urlshortener.repositories.ShortenUrlRepository;
import com.kelenyo.urlshortener.service.exception.AlreadyExistingCodeException;
import com.kelenyo.urlshortener.service.exception.AlreadyExistingUrlException;
import com.kelenyo.urlshortener.service.exception.UnknownCodeException;
import com.kelenyo.urlshortener.service.exception.UnknownUrlException;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ShortenUrlService {

    private final ShortenUrlRepository shortenUrlRepository;
    private final BaseConversion conversion;

    public ShortenUrlService(ShortenUrlRepository shortenUrlRepository, BaseConversion baseConversion) {
        this.shortenUrlRepository = shortenUrlRepository;
        this.conversion = baseConversion;
    }

    public ShortenUrl convertToShortUrlAndSave(UrlRequest urlRequest) {
        if(validateURL(urlRequest.getUrl())) {
            var shortenUrl = new ShortenUrl();
            String url = sanitizeURL(urlRequest.getUrl());

            // Check if the url is already entered in the db.
            Optional<ShortenUrl> exitURL = Optional.ofNullable(shortenUrlRepository.findByUrl(url));
            Optional<ShortenUrl> exitCode = Optional.ofNullable(shortenUrlRepository.findByCode(urlRequest.getCode()));

            if(exitURL.isPresent()) {
                throw new AlreadyExistingUrlException();
            } else if(exitCode.isPresent()) {
                throw new AlreadyExistingCodeException();
            } else {
                shortenUrl.setUrl(url);
                shortenUrl.setCreated(LocalDateTime.now());
                shortenUrl.setCode(conversion.generateRandomAlphanumericString());
                shortenUrlRepository.save(shortenUrl);

                return shortenUrl;
            }

        }

        throw new UnknownUrlException();
    }

    public String getOriginalUrl(String shortUrlCode) {
        Optional<ShortenUrl> entity = Optional.ofNullable(shortenUrlRepository.findByCode(shortUrlCode));
        if(entity.isEmpty()) {
            throw new UnknownCodeException();
        }

        return entity.get().getUrl();
    }

    private boolean validateURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    private String sanitizeURL(String url) {
        if (url.charAt(url.length() - 1) == '/')
            url = url.substring(0, url.length() - 1);
        return url;
    }

}
