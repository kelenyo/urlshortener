package com.kelenyo.urlshortener;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.kelenyo.urlshortener.controllers.ShortenUrlController;
import com.kelenyo.urlshortener.service.ShortenUrlService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ShortenUrlController.class)
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShortenUrlService shortenUrlService;


    @Test
    @WithMockUser(username = "user", password = "demo", roles = "USER")
    public void greetingShouldReturnMessageFromService() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void accessUnauthorized() throws Exception {
        this.mockMvc.perform(post("/createshorturl")
                .content("{\"url\": \"https://google.com\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin", password = "demo", roles = "ADMIN")
    public void shortnerShouldReturnMessageFromService() throws Exception {
        this.mockMvc.perform(post("/createshorturl")
                .content("{\"url\": \"https://www.test.de\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin", password = "demo", roles = "ADMIN")
    public void shortnerShouldHandleMethodNotAllowed() throws Exception {

        this.mockMvc.perform(post("/createshorturls")
                .content("{\"url\": \"https://google.com\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isMethodNotAllowed());
    }

    @Test
    @WithMockUser(username = "admin", password = "demo", roles = "ADMIN")
    public void shortnerShouldHandleBadRequests() throws Exception {
        this.mockMvc.perform(post("/createshorturl")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest());
    }
}