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
            String url = sanitizeChunk(urlRequest.getUrl());
            String code = sanitizeChunk(urlRequest.getCode());

            checkCode(code);

            // Check if the url is already entered in the db.
            Optional<ShortenUrl> dbURL = Optional.ofNullable(shortenUrlRepository.findByUrl(url));
            Optional<ShortenUrl> dbCode = Optional.ofNullable(shortenUrlRepository.findByCode(code));

            if(dbURL.isPresent()) {
                throw new AlreadyExistingUrlException();
            } else if(dbCode.isPresent()) {
                throw new AlreadyExistingCodeException();
            } else {
                if (code.isEmpty()) {
                    shortenUrl.setCode(conversion.generateRandomAlphanumericString());
                } else {
                    shortenUrl.setCode(code);
                }

                shortenUrl.setUrl(url);
                shortenUrl.setCreated(LocalDateTime.now());
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

    private String sanitizeChunk(String chunk) {
        if (chunk.charAt(chunk.length() - 1) == '/')
            chunk = chunk.substring(0, chunk.length() - 1);

        return chunk;
    }

    private void checkCode(String code) {
        String ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9]+$";
        if (code.length() > 6 || code.matches(ALPHANUMERIC_PATTERN))
            throw new UnknownCodeException();
    }
}
