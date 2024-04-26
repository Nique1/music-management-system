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

class SongServiceTest {
    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private SongServiceImpl songService;
    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void shouldReturnTrueWhenSongFoundById() {
        //given
        int songId = 1;
        SongEntity song = new SongEntity(songId, "ArtistName", "TrackName", 300, 2020);

        //when
        when(songRepository.findSongsBySongId(songId)).thenReturn(Arrays.asList(song));
        List<SongEntity> songsResult = songService.getSongsById(songId);
        //then
        assertThat(songsResult).isNotEmpty();

    }
    @Test
    void shouldReturnSongDataWhenSongFoundById(){
        //given
        int songId = 1;
        SongEntity song = new SongEntity(songId, "ArtistName", "TrackName", 300, 2020);
        //when
        when(songRepository.findSongsBySongId(songId)).thenReturn(Arrays.asList(song));
        List<SongEntity> songsResult = songService.getSongsById(songId);
        //then
        assertThat(songsResult).isEqualTo(Arrays.asList(song));
    }

    @Test
    void shouldReturnTrueWhenSongFoundByArtistName() {
        //given
        String artistName = "ArtistName";
        SongEntity song = new SongEntity(1, artistName, "TrackName", 300, 2020);

        //when
        when(songRepository.findSongsByArtistName(artistName)).thenReturn(Arrays.asList(song));
        List<SongEntity> songsResult = songService.getSongsByArtistName(artistName);
        //then
        assertThat(songsResult).isNotEmpty();

    }
    @Test
    void shouldReturnSongDataWhenSongFoundByArtistName(){
        //given
        String artistName = "ArtistName";
        SongEntity song = new SongEntity(1, artistName, "TrackName", 300, 2020);
        //when
        when(songRepository.findSongsByArtistName(artistName)).thenReturn(Arrays.asList(song));
        List<SongEntity> songsResult = songService.getSongsByArtistName(artistName);
        //then
        assertThat(songsResult).isEqualTo(Arrays.asList(song));
    }

    @Test
    void shouldReturnTrueWhenSongFoundByTrackName() {
        //given
        String trackName = "TrackName";
        SongEntity song = new SongEntity(1, "ArtistName", trackName, 300, 2020);

        //when
        when(songRepository.findSongsByTrackName(trackName)).thenReturn(Arrays.asList(song));
        List<SongEntity> songsResult = songService.getSongsByTrackName(trackName);
        //then
        assertThat(songsResult).isNotEmpty();

    }
    @Test
    void shouldReturnSongDataWhenSongFoundByTrackName(){
        //given
        String trackName = "TrackName";
        SongEntity song = new SongEntity(1, "ArtistName", trackName, 300, 2020);
        //when
        when(songRepository.findSongsByTrackName(trackName)).thenReturn(Arrays.asList(song));
        List<SongEntity> songsResult = songService.getSongsByTrackName(trackName);
        //then
        assertThat(songsResult).isEqualTo(Arrays.asList(song));
    }

    @Test
    void shouldReturnTrueWhenSongFoundByYear() {
        //given
        int year = 2020;
        SongEntity song = new SongEntity(1, "ArtistName", "TrackName", 300, year);

        //when
        when(songRepository.findSongsByYear(year)).thenReturn(Arrays.asList(song));
        List<SongEntity> songsResult = songService.getSongsByYear(year);
        //then
        assertThat(songsResult).isNotEmpty();

    }

    @Test
    void shouldReturnSongDataWhenSongFoundByYear(){
        //given
        int year = 2020;
        SongEntity song = new SongEntity(1, "ArtistName", "TrackName", 300, year);
        //when
        when(songRepository.findSongsByYear(year)).thenReturn(Arrays.asList(song));
        List<SongEntity> songsResult = songService.getSongsByYear(year);
        //then
        assertThat(songsResult).isEqualTo(Arrays.asList(song));
    }

}