package com.nique1.musicmanagementsystem.song.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SongServiceTest {

    private final UUID uuid = UUID.fromString("D6E80790-081A-4ABD-B5E7-1AC0CEDC9EBD");
    @Mock
    private SongRepository songRepository;
    private SongService songService;

    @BeforeEach
    void init() {
        songService = new SongService(songRepository);
    }

    public Song testSong() {
        return new Song(uuid, "Adele", "Hello", 295, 2010);
    }

    @Test
    void shouldReturnSongDataGivenSongUuidExists() {
        //given
        Song song = testSong();
        given(songRepository.findSongsBySongUuid(uuid)).willReturn(Optional.of(song));
        //when
        Optional<Song> actualSong = songService.getSongsByUuid(uuid);
        //then
        //TODO change Optional
        assertThat(actualSong).contains(song);
    }

    @Test
    void shouldReturnEmptyOptionalGivenSongUuidDoesNotExist() {
        //given
        UUID nonExistingUUID = UUID.randomUUID();
        given(songRepository.findSongsBySongUuid(nonExistingUUID)).willReturn(Optional.empty());
        //when
        Optional<Song> actualSong = songService.getSongsByUuid(nonExistingUUID);
        //then
        assertThat(actualSong).isEmpty();
    }

    @Test
    void shouldReturnEmptyOptionalWhenUuidIsNull() {
        //given
        //when
        Optional<Song> actualSong = songService.getSongsByUuid(null);
        //then
        assertThat(actualSong).isEmpty();
    }

    @Test
    void shouldReturnSongDataGivenArtistName() {
        //given
        Song song = testSong();
        given(songRepository.findSongsByArtistNameOrTrackNameOrYear("Adele", null, null)).willReturn(List.of(song));
        //when
        List<Song> actualSongList = songService.getSongsByArtistTrackOrYear("Adele", null, null);
        //then
        assertThat(actualSongList).containsExactly(song);

    }

    @Test
    void shouldReturnSongDataGivenTrackName() {
        //given
        Song song = testSong();
        given(songRepository.findSongsByArtistNameOrTrackNameOrYear(null, "Hello", null)).willReturn(List.of(song));
        //when
        List<Song> actualSongList = songService.getSongsByArtistTrackOrYear(null, "Hello", null);
        //then
        assertThat(actualSongList).containsExactly(song);

    }

    @Test
    void shouldReturnSongDataGivenYear() {
        //given
        Song song = testSong();
        given(songRepository.findSongsByArtistNameOrTrackNameOrYear(null, null, 2010)).willReturn(List.of(song));
        //when
        List<Song> actualSongList = songService.getSongsByArtistTrackOrYear(null, null, 2010);
        //then
        assertThat(actualSongList).containsExactly(song);
    }

    @Test
    void shouldReturnEmptyListGivenNoSongFound() {
        //given
        given(songRepository.findSongsByArtistNameOrTrackNameOrYear(null, null, null)).willReturn(List.of());
        //when
        List<Song> actualSongList = songService.getSongsByArtistTrackOrYear(null, null, null);
        //then
        assertThat(actualSongList).isEmpty();
    }


}