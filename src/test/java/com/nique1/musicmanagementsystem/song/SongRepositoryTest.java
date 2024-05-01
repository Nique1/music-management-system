package com.nique1.musicmanagementsystem.song;

import com.nique1.musicmanagementsystem.song.domain.Song;
import com.nique1.musicmanagementsystem.song.domain.SongRepository;
import com.nique1.musicmanagementsystem.song.infrastructure.SongEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        Optional<Song> song = songRepository.findSongsBySongUuid(UUID.fromString("D6E80790-081A-4ABD-B5E7-1AC0CEDC9EBD"));
        //then
        assertThat(song).isNotEmpty();
        assertThat(song).get().satisfies(s -> {
            assertThat(s.trackName()).isEqualTo("Hello");
            assertThat(s.artistName()).isEqualTo("Adele");
        });


    }

}