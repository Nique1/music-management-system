package com.nique1.musicmanagementsystem.song;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class SongServiceImplTest {

    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private SongServiceImpl songService;
    private SongEntity song;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        song = new SongEntity(1, "ArtistName", "TrackName", 300, 2020);
    }

    @Test
    void shouldReturnTrueWhenSongFoundById() {
        int songId = 1;
        when(songRepository.findSongsBySongId(songId)).thenReturn(Arrays.asList(song));
        List<SongEntity> songsResult = songService.getSongsById(songId);
        assertThat(songsResult).isNotEmpty();

    }

    @Test
    void shouldReturnSongDataWhenSongFoundById() {
        int songId = 1;
        when(songRepository.findSongsBySongId(songId)).thenReturn(Arrays.asList(song));
        List<SongEntity> songsResult = songService.getSongsById(songId);
        assertThat(songsResult).isEqualTo(Arrays.asList(song));
    }

    @Test
    void shouldReturnTrueWhenSongFoundByArtistName() {
        String artistName = "ArtistName";
        when(songRepository.findSongsByArtistName(artistName)).thenReturn(Arrays.asList(song));
        List<SongEntity> songsResult = songService.getSongsByArtistName(artistName);
        assertThat(songsResult).isNotEmpty();

    }

    @Test
    void shouldReturnSongDataWhenSongFoundByArtistName() {
        String artistName = "ArtistName";
        when(songRepository.findSongsByArtistName(artistName)).thenReturn(Arrays.asList(song));
        List<SongEntity> songsResult = songService.getSongsByArtistName(artistName);
        assertThat(songsResult).isEqualTo(Arrays.asList(song));
    }

    @Test
    void shouldReturnTrueWhenSongFoundByTrackName() {
        String trackName = "TrackName";
        when(songRepository.findSongsByTrackName(trackName)).thenReturn(Arrays.asList(song));
        List<SongEntity> songsResult = songService.getSongsByTrackName(trackName);
        assertThat(songsResult).isNotEmpty();

    }

    @Test
    void shouldReturnSongDataWhenSongFoundByTrackName() {
        String trackName = "TrackName";
        when(songRepository.findSongsByTrackName(trackName)).thenReturn(Arrays.asList(song));
        List<SongEntity> songsResult = songService.getSongsByTrackName(trackName);
        assertThat(songsResult).isEqualTo(Arrays.asList(song));
    }

    @Test
    void shouldReturnTrueWhenSongFoundByYear() {
        int year = 2020;
        when(songRepository.findSongsByYear(year)).thenReturn(Arrays.asList(song));
        List<SongEntity> songsResult = songService.getSongsByYear(year);
        assertThat(songsResult).isNotEmpty();

    }

    @Test
    void shouldReturnSongDataWhenSongFoundByYear() {
        int year = 2020;
        when(songRepository.findSongsByYear(year)).thenReturn(Arrays.asList(song));
        List<SongEntity> songsResult = songService.getSongsByYear(year);
        assertThat(songsResult).isEqualTo(Arrays.asList(song));
    }

}