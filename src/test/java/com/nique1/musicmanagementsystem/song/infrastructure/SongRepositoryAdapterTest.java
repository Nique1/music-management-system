package com.nique1.musicmanagementsystem.song.infrastructure;

import com.nique1.musicmanagementsystem.song.domain.Song;
import com.nique1.musicmanagementsystem.song.domain.SongRepository;
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
class SongRepositoryAdapterTest {
    private final UUID uuid = UUID.fromString("D6E80790-081A-4ABD-B5E7-1AC0CEDC9EBD");
    @Autowired
    private SongJpaRepositoryAdapter songRepository;
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withUsername("postgres")
            .withPassword("secret");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);

    }

    @Test
    void shouldFindTrackGivenUuid() {
        //when
        Optional<Song> song = songRepository.findSongsBySongUuid(uuid);
        //then
        assertThat(song).isNotEmpty();
        assertThat(song).get().satisfies(s -> {
            assertThat(s.trackName()).isEqualTo("Hello");
            assertThat(s.artistName()).isEqualTo("Adele");
            assertThat(s.trackLength()).isEqualTo(295);
            assertThat(s.year()).isEqualTo(2010);
        });

    }

    @Test
    void shouldFindTrackGivenArtistName() {
        //when
        List<Song> songs = songRepository.findSongsByArtistNameOrTrackNameOrYear("Adele", null, null);
        //then
        assertThat(songs).isNotEmpty();
        assertThat(songs).hasSize(1);
        assertThat(songs.get(0)).satisfies(s -> {
            assertThat(s.trackName()).isEqualTo("Hello");
            assertThat(s.artistName()).isEqualTo("Adele");
            assertThat(s.trackLength()).isEqualTo(295);
            assertThat(s.year()).isEqualTo(2010);
        });

    }

    @Test
    void shouldFindTrackGivenTrackName() {
        //when
        List<Song> songs = songRepository.findSongsByArtistNameOrTrackNameOrYear(null, "Hello", null);
        //then
        assertThat(songs).isNotEmpty();
        assertThat(songs).hasSize(1);
        assertThat(songs.get(0)).satisfies(s -> {
            assertThat(s.trackName()).isEqualTo("Hello");
            assertThat(s.artistName()).isEqualTo("Adele");
            assertThat(s.trackLength()).isEqualTo(295);
            assertThat(s.year()).isEqualTo(2010);
        });

    }

    @Test
    void shouldFindTrackGivenYear() {
        //when
        List<Song> songs = songRepository.findSongsByArtistNameOrTrackNameOrYear(null, null, 2010);
        //then
        assertThat(songs).isNotEmpty();
        assertThat(songs).hasSize(1);
        assertThat(songs.get(0)).satisfies(s -> {
            assertThat(s.trackName()).isEqualTo("Hello");
            assertThat(s.artistName()).isEqualTo("Adele");
            assertThat(s.trackLength()).isEqualTo(295);
            assertThat(s.year()).isEqualTo(2010);
        });

    }

    @Test
    void shouldFindTrackGivenArtistNameAndTrackName() {
        //when
        List<Song> songs = songRepository.findSongsByArtistNameOrTrackNameOrYear("Adele", "Hello", null);
        //then
        assertThat(songs).isNotEmpty();
        assertThat(songs).hasSize(1);
        assertThat(songs.get(0)).satisfies(s -> {
            assertThat(s.trackName()).isEqualTo("Hello");
            assertThat(s.artistName()).isEqualTo("Adele");
            assertThat(s.trackLength()).isEqualTo(295);
            assertThat(s.year()).isEqualTo(2010);
        });

    }

    @Test
    void shouldReturnEmptyOptionalGivenUuidNotFound() {
        //given
        UUID nonExistingUuid = UUID.randomUUID();
        //when
        Optional<Song> song = songRepository.findSongsBySongUuid(nonExistingUuid);
        //then
        assertThat(song).isEmpty();
    }

    @Test
    void shouldReturnEmptyListGivenNullParameters() {
        //when
        List<Song> songs = songRepository.findSongsByArtistNameOrTrackNameOrYear(null, null, null);
        //then
        assertThat(songs).isEmpty();
    }


}