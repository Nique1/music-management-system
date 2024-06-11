package com.nique1.musicmanagementsystem.song.application.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class SongControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withUsername("postgres")
            .withPassword("secret");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);

    }


    @Test
    void shouldReturnSongDataGivenPartialLowercaseArtistName() throws Exception {
        //when-then
        mockMvc.perform(get("/songs")
                        .queryParam("artist-name", "ade"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value("d6e80790-081a-4abd-b5e7-1ac0cedc9ebd"))
                .andExpect(jsonPath("$[0].artistName").value("Adele"))
                .andExpect(jsonPath("$[0].trackName").value("Hello"))
                .andExpect(jsonPath("$[0].trackLength").value(295))
                .andExpect(jsonPath("$[0].year").value(2010));
    }


    @Test
    void shouldReturnSongDataGivenPartialLowercaseTrackName() throws Exception {

        //when-then
        mockMvc.perform(get("/songs")
                        .queryParam("track-name", "hell"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value("d6e80790-081a-4abd-b5e7-1ac0cedc9ebd"))
                .andExpect(jsonPath("$[0].artistName").value("Adele"))
                .andExpect(jsonPath("$[0].trackName").value("Hello"))
                .andExpect(jsonPath("$[0].trackLength").value(295))
                .andExpect(jsonPath("$[0].year").value(2010));
    }

    @Test
    void shouldReturnSongDataGivenPartialLowercaseTrackNameAndYearFrom() throws Exception {
        //when-then
        mockMvc.perform(get("/songs")
                        .queryParam("track-name", "hell")
                        .queryParam("year-from", "2010"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value("d6e80790-081a-4abd-b5e7-1ac0cedc9ebd"))
                .andExpect(jsonPath("$[0].artistName").value("Adele"))
                .andExpect(jsonPath("$[0].trackName").value("Hello"))
                .andExpect(jsonPath("$[0].trackLength").value(295))
                .andExpect(jsonPath("$[0].year").value(2010));
    }

    @Test
    void shouldReturnSongDataGivenPartialUpperCaseArtistName() throws Exception {
        //when-then
        mockMvc.perform(get("/songs")
                        .queryParam("artist-name", "ADE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value("d6e80790-081a-4abd-b5e7-1ac0cedc9ebd"))
                .andExpect(jsonPath("$[0].artistName").value("Adele"))
                .andExpect(jsonPath("$[0].trackName").value("Hello"))
                .andExpect(jsonPath("$[0].trackLength").value(295))
                .andExpect(jsonPath("$[0].year").value(2010));
    }
}

