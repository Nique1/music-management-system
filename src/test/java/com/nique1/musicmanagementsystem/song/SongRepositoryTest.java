package com.nique1.musicmanagementsystem.song;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Testcontainers
class SongRepositoryTest {
    @Autowired
    private SongRepository songRepository;
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withUsername("postgres")
            .withPassword("secret");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);

    }
    @Test
    void shouldFindTrackByName(){
        //when
        List<SongEntity> songs = songRepository.findSongsByTrackName("Hello");
        //then
        assertThat(songs).hasSize(1);
        assertThat(songs.get(0).getTrackName()).isEqualTo("Hello");
        assertThat(songs.get(0).getArtistName()).isEqualTo("Adele");


    }

}