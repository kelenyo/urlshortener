package com.kelenyo.urlshortener.service;

import com.kelenyo.urlshortener.models.ShortenUrl;
import com.kelenyo.urlshortener.repositories.ShortenUrlRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ShortenUrlServiceTest {
    @Mock
    ShortenUrlRepository mockShortenUrlRepository;

    @Mock
    BaseConversion mockBaseConversion;

    @InjectMocks
    ShortenUrlService shortenUrlService;

    @Test
    public void convertToShortUrlAndSaveTest() {
        var url = new ShortenUrl();
        url.setUrl("https://www.jesus.de/artikel/");
        url.setCreated(LocalDateTime.now());
        url.setId(5L);

        when(mockShortenUrlRepository.save(any(ShortenUrl.class))).thenReturn(url);
        when(mockBaseConversion.encode(url.getId())).thenReturn("f");



        assertEquals("f", shortenUrlService.convertToShortUrlAndSave("f", ""));
    }

    @Test
    public void getOriginalUrlTest() {


    }
}
