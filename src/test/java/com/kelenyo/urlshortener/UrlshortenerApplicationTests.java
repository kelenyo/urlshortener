package com.kelenyo.urlshortener;

import com.kelenyo.urlshortener.controllers.ShortenUrlController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UrlshortenerApplicationTests {

	@Autowired
	private ShortenUrlController controller;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
