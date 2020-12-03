package com.kelenyo.urlshortener.service;

import com.kelenyo.urlshortener.dto.UrlRequest;
import com.kelenyo.urlshortener.models.ShortenUrl;
import com.kelenyo.urlshortener.repositories.ShortenUrlRepository;
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

    public String convertToShortUrlAndSave(UrlRequest urlRequest) {
        System.out.println("URL: " + urlRequest.getUrl());
        System.out.println("CODE: " + urlRequest.getCode());
        String url;

        if(validateURL(urlRequest.getUrl())) {
            var shortenUrl = new ShortenUrl();
            url = sanitizeURL(urlRequest.getUrl());
            // Check if the url is already entered in the db.
            Optional<ShortenUrl> exitURL = Optional.ofNullable(shortenUrlRepository.findByUrl(url));
            if(exitURL.isPresent()) {

            } else {
                shortenUrl.setUrl(url);
                shortenUrl.setCreated(LocalDateTime.now());
                shortenUrl.setCode(urlRequest.getCode());
                var entity = shortenUrlRepository.save(shortenUrl);
                //shortenUrl.setCode(conversion.encode(entity.getId()));
                System.out.println(entity.getId());
                return conversion.encode(entity.getId());
            }

        }
        return "A";
    }

    public String getOriginalUrl(String shortUrlCode) {
        var entity = shortenUrlRepository.findByCode(shortUrlCode);

        return entity.getUrl();
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
        if (url.substring(0, 7).equals("http://"))
            url = url.substring(7);

        if (url.substring(0, 8).equals("https://"))
            url = url.substring(8);

        if (url.charAt(url.length() - 1) == '/')
            url = url.substring(0, url.length() - 1);
        return url;
    }

}
