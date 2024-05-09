package com.nique1.musicmanagementsystem.song.application.api;

import com.nique1.musicmanagementsystem.song.application.api.dto.SongRspDto;
import com.nique1.musicmanagementsystem.song.domain.Song;
import com.nique1.musicmanagementsystem.song.domain.SongService;
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
class SongFacadeTest {
    @Mock
    private SongService songService;
    private SongFacade songFacade;

    @BeforeEach
    void init() {
        songFacade = new SongFacade(songService);
    }

    @Test
    void shouldReturnSongDataGivenSongUuidExists() {
        //given
        Song song = new Song(UUID.fromString("D6E80790-081A-4ABD-B5E7-1AC0CEDC9EBD"), "Adele", "Hello", 295, 2010);
        SongRspDto expectedSongRspDto = new SongRspDto(UUID.fromString("D6E80790-081A-4ABD-B5E7-1AC0CEDC9EBD"), "Adele", "Hello", 295, 2010);
        given(songService.getSongsByUuid(UUID.fromString("D6E80790-081A-4ABD-B5E7-1AC0CEDC9EBD"))).willReturn(Optional.of(song));
        //when
        Optional<SongRspDto> actualSongRspDtoOptional = songFacade.getSongByUuid(UUID.fromString("D6E80790-081A-4ABD-B5E7-1AC0CEDC9EBD"));
        //then
        assertThat(actualSongRspDtoOptional).isEqualTo(Optional.of(expectedSongRspDto));
    }

    @Test
    void shouldReturnEmptyOptionalGivenSongUuidDoesNotExist() {

        //given
        UUID nonExistingUUID = UUID.randomUUID();
        given(songService.getSongsByUuid(nonExistingUUID)).willReturn(Optional.empty());
        //when
        Optional<SongRspDto> actualSongRspDtoOptional = songFacade.getSongByUuid(nonExistingUUID);
        //then
        assertThat(actualSongRspDtoOptional).isEmpty();
    }

    @Test
    void shouldReturnEmptyOptionalWhenUuidIsNull() {
        //given
        //when
        Optional<SongRspDto> actualSongRspDtoOptional = songFacade.getSongByUuid(null);
        //then
        assertThat(actualSongRspDtoOptional).isEmpty();

    }


    @Test
    void shouldReturnSongDataGivenArtistExists() {
        //given
        Song song = new Song(UUID.fromString("D6E80790-081A-4ABD-B5E7-1AC0CEDC9EBD"), "Adele", "Hello", 295, 2010);
        SongRspDto expectedSongRspDto = new SongRspDto(UUID.fromString("D6E80790-081A-4ABD-B5E7-1AC0CEDC9EBD"), "Adele", "Hello", 295, 2010);
        given(songService.getSongsByArtistTrackOrYear("Adele", null, null)).willReturn(List.of(song));
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear("Adele", null, null);
        //then
        assertThat(actualSongRspDtoList).isEqualTo(List.of(expectedSongRspDto));
    }

    @Test
    void shouldReturnSongDataGivenTrackExists() {
        //given
        Song song = new Song(UUID.fromString("D6E80790-081A-4ABD-B5E7-1AC0CEDC9EBD"), "Adele", "Hello", 295, 2010);
        SongRspDto expectedSongRspDto = new SongRspDto(UUID.fromString("D6E80790-081A-4ABD-B5E7-1AC0CEDC9EBD"), "Adele", "Hello", 295, 2010);
        given(songService.getSongsByArtistTrackOrYear(null, "Hello", null)).willReturn(List.of(song));
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear(null, "Hello", null);
        //then
        assertThat(actualSongRspDtoList).isEqualTo(List.of(expectedSongRspDto));
    }

    @Test
    void shouldReturnSongDataGivenYearExists() {
        //given
        Song song = new Song(UUID.fromString("D6E80790-081A-4ABD-B5E7-1AC0CEDC9EBD"), "Adele", "Hello", 295, 2010);
        SongRspDto expectedSongRspDto = new SongRspDto(UUID.fromString("D6E80790-081A-4ABD-B5E7-1AC0CEDC9EBD"), "Adele", "Hello", 295, 2010);
        given(songService.getSongsByArtistTrackOrYear(null, null, 2010)).willReturn(List.of(song));
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear(null, null, 2010);
        //then
        assertThat(actualSongRspDtoList).isEqualTo(List.of(expectedSongRspDto));
    }

    @Test
    void shouldReturnEmptyOptionalGivenNullParameters() {
        //given
        given(songFacade.getSongByArtistTrackOrYear(null, null, null)).willReturn(List.of());
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear(null, null, null);
        //then
        assertThat(actualSongRspDtoList).isEmpty();
    }


}