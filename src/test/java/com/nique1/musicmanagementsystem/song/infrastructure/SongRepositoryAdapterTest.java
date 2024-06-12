package com.nique1.musicmanagementsystem.song.infrastructure;

import com.nique1.musicmanagementsystem.song.domain.Song;
import com.nique1.musicmanagementsystem.song.domain.SongCriteria;
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
    void shouldReturnEmptyOptionalGivenUuidNotFound() {
        //given
        UUID nonExistingUuid = UUID.randomUUID();
        //when
        Optional<Song> song = songRepository.findSongsBySongUuid(nonExistingUuid);
        //then
        assertThat(song).isEmpty();
    }

    @Test
    void shouldFindTrackGivenArtistName() {
        //when
        SongCriteria songCriteria = new SongCriteria("Adele", null, null, null);
        List<Song> songs = songRepository.findSongsByCriteria(songCriteria);
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
    void shouldReturnEmptyListGivenArtistNameNotFound() {
        //when
        SongCriteria songCriteria = new SongCriteria("Some artist", null, null, null);
        List<Song> songs = songRepository.findSongsByCriteria(songCriteria);
        //then
        assertThat(songs).isEmpty();
    }

    @Test
    void shouldFindTrackGivenTrackName() {
        //when
        SongCriteria songCriteria = new SongCriteria(null, "Hello", null, null);
        List<Song> songs = songRepository.findSongsByCriteria(songCriteria);
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
    void shouldReturnEmptyListGivenTrackNameNotFound() {
        //when
        SongCriteria songCriteria = new SongCriteria(null, "Some track", null, null);
        List<Song> songs = songRepository.findSongsByCriteria(songCriteria);
        //then
        assertThat(songs).isEmpty();
    }


    @Test
    void shouldFindTrackGivenYear() {
        //when
        SongCriteria songCriteria = new SongCriteria(null, null, 2010, 2010);
        List<Song> songs = songRepository.findSongsByCriteria(songCriteria);
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
        SongCriteria songCriteria = new SongCriteria("Adele", "Hello", null, null);
        List<Song> songs = songRepository.findSongsByCriteria(songCriteria);
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
    void shouldFindTrackGivenArtistNameAndYearFrom() {
        //when
        SongCriteria songCriteria = new SongCriteria("Adele", null, 2010, null);
        List<Song> songs = songRepository.findSongsByCriteria(songCriteria);
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
    void shouldFindTrackGivenArtistNameAndYearTo() {
        //when
        SongCriteria songCriteria = new SongCriteria("Adele", null, null, 2010);
        List<Song> songs = songRepository.findSongsByCriteria(songCriteria);
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
    void shouldFindTrackGivenTrackNameAndYearFrom() {
        //when
        SongCriteria songCriteria = new SongCriteria(null, "Hello", 2010, null);
        List<Song> songs = songRepository.findSongsByCriteria(songCriteria);
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
    void shouldReturnTrackGivenTrackNameAndYearTo() {
        //when
        SongCriteria songCriteria = new SongCriteria(null, "Hello", null, 2010);
        List<Song> songs = songRepository.findSongsByCriteria(songCriteria);
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
    void shouldReturnSongsGivenRangeOfYears() {
        //when
        List<Song> songs = songRepository.findSongsByCriteria(new SongCriteria(null, null, 1970, 1985));
        //then
        assertThat(songs).isNotEmpty();
        assertThat(songs).hasSize(2);
        assertThat(songs.get(0)).satisfies(s -> {
            assertThat(s.trackName()).isEqualTo("Stairway to Heaven");
            assertThat(s.artistName()).isEqualTo("Led Zeppelin");
            assertThat(s.trackLength()).isEqualTo(482);
            assertThat(s.year()).isEqualTo(1970);

        });
        assertThat(songs.get(1)).satisfies(s -> {
            assertThat(s.trackName()).isEqualTo("Billie Jean");
            assertThat(s.artistName()).isEqualTo("Michael Jackson");
            assertThat(s.trackLength()).isEqualTo(294);
            assertThat(s.year()).isEqualTo(1980);

        });

    }

    @Test
    void shouldReturnEmptyListGivenGreaterYearFromThanYearTo() {
        //when
        List<Song> songs = songRepository.findSongsByCriteria(new SongCriteria(null, null, 1985, 1970));
        //then
        assertThat(songs).isEmpty();

    }

    @Test
    void shouldReturnTrackGivenTheSameYearFromAndYearTo() {
        //when
        List<Song> songs = songRepository.findSongsByCriteria(new SongCriteria(null, null, 1970, 1970));
        //then
        assertThat(songs).isNotEmpty();
        assertThat(songs).hasSize(1);
        assertThat(songs.get(0)).satisfies(s -> {
            assertThat(s.trackName()).isEqualTo("Stairway to Heaven");
            assertThat(s.artistName()).isEqualTo("Led Zeppelin");
            assertThat(s.trackLength()).isEqualTo(482);
            assertThat(s.year()).isEqualTo(1970);

        });

    }

    @Test
    void shouldReturnEmptyListGivenNoDatabaseMatchHavingYearFromAndYearTo() {
        //when
        List<Song> songs = songRepository.findSongsByCriteria(new SongCriteria(null, null, 2030, 2050));
        //then
        assertThat(songs).isEmpty();
    }

    @Test
    void shouldReturnAllTracksGivenFullYearsRange() {
        //when
        List<Song> songs = songRepository.findSongsByCriteria(new SongCriteria(null, null, 1960, 2010));
        //then
        assertThat(songs).isNotEmpty();
        assertThat(songs).hasSize(5);
        assertThat(songs.get(0)).satisfies(s -> {
            assertThat(s.trackName()).isEqualTo("Come Together");
            assertThat(s.artistName()).isEqualTo("The Beatles");
            assertThat(s.trackLength()).isEqualTo(259);
            assertThat(s.year()).isEqualTo(1960);

        });
        assertThat(songs.get(1)).satisfies(s -> {
            assertThat(s.trackName()).isEqualTo("Stairway to Heaven");
            assertThat(s.artistName()).isEqualTo("Led Zeppelin");
            assertThat(s.trackLength()).isEqualTo(482);
            assertThat(s.year()).isEqualTo(1970);

        });
        assertThat(songs.get(2)).satisfies(s -> {
            assertThat(s.trackName()).isEqualTo("Billie Jean");
            assertThat(s.artistName()).isEqualTo("Michael Jackson");
            assertThat(s.trackLength()).isEqualTo(294);
            assertThat(s.year()).isEqualTo(1980);

        });
        assertThat(songs.get(3)).satisfies(s -> {
            assertThat(s.trackName()).isEqualTo("Smells Like Teen Spirit");
            assertThat(s.artistName()).isEqualTo("Nirvana");
            assertThat(s.trackLength()).isEqualTo(301);
            assertThat(s.year()).isEqualTo(1990);

        });
        assertThat(songs.get(4)).satisfies(s -> {
            assertThat(s.trackName()).isEqualTo("Hello");
            assertThat(s.artistName()).isEqualTo("Adele");
            assertThat(s.trackLength()).isEqualTo(295);
            assertThat(s.year()).isEqualTo(2010);

        });
    }

    @Test
    void shouldReturnAllSongsGivenNullParameters() {
        //when
        List<Song> songs = songRepository.findSongsByCriteria(new SongCriteria(null, null, null, null));
        //then
        assertThat(songs).isNotEmpty();
        assertThat(songs).hasSize(5);
        assertThat(songs.get(0)).satisfies(s -> {
            assertThat(s.trackName()).isEqualTo("Come Together");
            assertThat(s.artistName()).isEqualTo("The Beatles");
            assertThat(s.trackLength()).isEqualTo(259);
            assertThat(s.year()).isEqualTo(1960);

        });
        assertThat(songs.get(1)).satisfies(s -> {
            assertThat(s.trackName()).isEqualTo("Stairway to Heaven");
            assertThat(s.artistName()).isEqualTo("Led Zeppelin");
            assertThat(s.trackLength()).isEqualTo(482);
            assertThat(s.year()).isEqualTo(1970);

        });
        assertThat(songs.get(2)).satisfies(s -> {
            assertThat(s.trackName()).isEqualTo("Billie Jean");
            assertThat(s.artistName()).isEqualTo("Michael Jackson");
            assertThat(s.trackLength()).isEqualTo(294);
            assertThat(s.year()).isEqualTo(1980);

        });
        assertThat(songs.get(3)).satisfies(s -> {
            assertThat(s.trackName()).isEqualTo("Smells Like Teen Spirit");
            assertThat(s.artistName()).isEqualTo("Nirvana");
            assertThat(s.trackLength()).isEqualTo(301);
            assertThat(s.year()).isEqualTo(1990);

        });
        assertThat(songs.get(4)).satisfies(s -> {
            assertThat(s.trackName()).isEqualTo("Hello");
            assertThat(s.artistName()).isEqualTo("Adele");
            assertThat(s.trackLength()).isEqualTo(295);
            assertThat(s.year()).isEqualTo(2010);

        });
    }

    @Test
    void shouldReturnTrackGivenPartialLowerCaseArtistName() {
        //when
        List<Song> songs = songRepository.findSongsByCriteria(new SongCriteria("ade", null, null, null));
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
    void shouldReturnTrackGivenPartialLowerCaseTrackName() {
        //when
        List<Song> songs = songRepository.findSongsByCriteria(new SongCriteria(null, "hel", null, null));
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
    void shouldReturnTrackGivenPartialLowerCaseTrackNameAndYearFrom() {
        //when
        List<Song> songs = songRepository.findSongsByCriteria(new SongCriteria(null, "hel", 2009, null));
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
    void shouldReturnTrackGivenPartialUpperCaseArtistName() {
        //when
        List<Song> songs = songRepository.findSongsByCriteria(new SongCriteria("ADE", null, null, null));
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
    void shouldReturnTrackGivenPartialUpperCaseArtistNameAndYearTo() {
        //when
        List<Song> songs = songRepository.findSongsByCriteria(new SongCriteria("ADE", null, null, 2011));
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
    void shouldReturnTrackGivenPartialUpperCaseTrackName() {
        //when
        List<Song> songs = songRepository.findSongsByCriteria(new SongCriteria(null, "HEL", null, null));
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


}