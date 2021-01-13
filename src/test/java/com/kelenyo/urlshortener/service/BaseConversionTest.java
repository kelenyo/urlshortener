package com.kelenyo.urlshortener.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import static org.junit.Assert.assertEquals;

public class BaseConversionTest {

    private BaseConversion conversion;

    @Test
    public void encodingTest() {
        conversion = new BaseConversion();
        assertEquals(6, conversion.generateRandomAlphanumericString().length());
    }
}
