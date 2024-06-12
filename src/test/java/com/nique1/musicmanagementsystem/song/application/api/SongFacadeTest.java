package com.nique1.musicmanagementsystem.song.application.api;

import com.nique1.musicmanagementsystem.song.application.api.dto.SongRspDto;
import com.nique1.musicmanagementsystem.song.domain.Song;
import com.nique1.musicmanagementsystem.song.domain.SongCriteria;
import com.nique1.musicmanagementsystem.song.domain.SongService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SongFacadeTest {
    private final UUID uuid = UUID.fromString("D6E80790-081A-4ABD-B5E7-1AC0CEDC9EBD");
    @Mock
    private SongService songService;
    private SongFacade songFacade;


    @BeforeEach
    void init() {
        songFacade = new SongFacade(songService, Mappers.getMapper(SongDtoMapper.class));
    }

    public Song testSong() {
        return new Song(uuid, "Adele", "Hello", 295, 2010);
    }

    public SongRspDto testSongRspDto() {
        return new SongRspDto(uuid, "Adele", "Hello", 295, 2010);
    }

    @Test
    void shouldReturnSongDataGivenSongUuidExists() {
        //given
        Song song = testSong();
        SongRspDto expectedSongRspDto = testSongRspDto();
        given(songService.getSongsByUuid(uuid)).willReturn(Optional.of(song));
        //when
        Optional<SongRspDto> actualSongRspDtoOptional = songFacade.getSongByUuid(uuid);
        //then
        assertThat(actualSongRspDtoOptional).contains(expectedSongRspDto);
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
        Song song = testSong();
        SongRspDto expectedSongRspDto = testSongRspDto();
        SongCriteria songCriteria = new SongCriteria("Adele", null, null, null);
        given(songService.getSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear("Adele", null, null, null);
        //then
        assertThat(actualSongRspDtoList).containsExactly(expectedSongRspDto);
    }

    @Test
    void shouldReturnSongDataGivenTrackExists() {
        //given
        Song song = testSong();
        SongRspDto expectedSongRspDto = testSongRspDto();
        SongCriteria songCriteria = new SongCriteria(null, "Hello", null, null);
        given(songService.getSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear(null, "Hello", null, null);
        //then
        assertThat(actualSongRspDtoList).containsExactly(expectedSongRspDto);
    }

    @Test
    void shouldReturnSongDataGivenYearExists() {
        //given
        Song song = testSong();
        SongRspDto expectedSongRspDto = testSongRspDto();
        SongCriteria songCriteria = new SongCriteria(null, null, 2010, 2010);
        given(songService.getSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear(null, null, 2010, 2010);
        //then
        assertThat(actualSongRspDtoList).containsExactly(expectedSongRspDto);
    }

    @Test
    void shouldReturnEmptyOptionalGivenNullParameters() {
        //given
        SongCriteria songCriteria = new SongCriteria(null, null, null, null);
        given(songService.getSongsByCriteria(songCriteria)).willReturn(Collections.emptyList());
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear(null, null, null, null);
        //then
        assertThat(actualSongRspDtoList).isEmpty();
    }

    @Test
    void shouldReturnSongDataGivenArtistAndTrackExists() {
        //given
        Song song = testSong();
        SongRspDto expectedSongRspDto = testSongRspDto();
        SongCriteria songCriteria = new SongCriteria("Adele", "Hello", null, null);
        given(songService.getSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear("Adele", "Hello", null, null);
        //then
        assertThat(actualSongRspDtoList).containsExactly(expectedSongRspDto);
    }

    @Test
    void shouldReturnSongDataGivenArtistAndYearExists() {
        //given
        Song song = testSong();
        SongRspDto expectedSongRspDto = testSongRspDto();
        SongCriteria songCriteria = new SongCriteria("Adele", null, 2009, 2010);
        given(songService.getSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear("Adele", null, 2009, 2010);
        //then
        assertThat(actualSongRspDtoList).containsExactly(expectedSongRspDto);
    }

    @Test
    void shouldReturnSongDataGivenTrackAndYearExists() {
        //given
        Song song = testSong();
        SongRspDto expectedSongRspDto = testSongRspDto();
        SongCriteria songCriteria = new SongCriteria(null, "Hello", 2009, 2010);
        given(songService.getSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear(null, "Hello", 2009, 2010);
        //then
        assertThat(actualSongRspDtoList).containsExactly(expectedSongRspDto);
    }

    @Test
    void shouldReturnEmptyOptionalGivenYearFromGreaterThanYearTo() {
        //given
        SongCriteria songCriteria = new SongCriteria(null, null, 2010, 2009);
        given(songService.getSongsByCriteria(songCriteria)).willReturn(Collections.emptyList());
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear(null, null, 2010, 2009);
        //then
        assertThat(actualSongRspDtoList).isEmpty();
    }
    @Test
    void shouldReturnSongDataGivenPartialLowercaseArtistName() {
        //given
        Song song = testSong();
        SongRspDto expectedSongRspDto = testSongRspDto();
        SongCriteria songCriteria = new SongCriteria("ade", null, null, null);
        given(songService.getSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear("ade", null, null, null);
        //then
        assertThat(actualSongRspDtoList).containsExactly(expectedSongRspDto);
    }

    @Test
    void shouldReturnSongDataGivenPartialLowercaseArtistNameAndYearFrom(){
        //given
        Song song = testSong();
        SongRspDto expectedSongRspDto = testSongRspDto();
        SongCriteria songCriteria = new SongCriteria("ade", null, 2010, null);
        given(songService.getSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear("ade", null, 2010, null);
        //then
        assertThat(actualSongRspDtoList).containsExactly(expectedSongRspDto);
    }

    @Test
    void shouldReturnSongDataGivenPartialUppercaseArtistName() {
        //given
        Song song = testSong();
        SongRspDto expectedSongRspDto = testSongRspDto();
        SongCriteria songCriteria = new SongCriteria("ADE", null, null, null);
        given(songService.getSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear("ADE", null, null, null);
        //then
        assertThat(actualSongRspDtoList).containsExactly(expectedSongRspDto);
    }
    @Test
    void shouldReturnSongDataGivenPartialUppercaseArtistNameAndYearTo(){
        //given
        Song song = testSong();
        SongRspDto expectedSongRspDto = testSongRspDto();
        SongCriteria songCriteria = new SongCriteria("ADE", null, null, 2010);
        given(songService.getSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear("ADE", null, null, 2010);
        //then
        assertThat(actualSongRspDtoList).containsExactly(expectedSongRspDto);
    }

    @Test
    void shouldReturnSongDataGIvenPartialLowercaseTrackName() {
        //given
        Song song = testSong();
        SongRspDto expectedSongRspDto = testSongRspDto();
        SongCriteria songCriteria = new SongCriteria(null, "hel", null, null);
        given(songService.getSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear(null, "hel", null, null);
        //then
        assertThat(actualSongRspDtoList).containsExactly(expectedSongRspDto);
    }

    @Test
    void shouldReturnSongDataGIvenPartialLowercaseTrackNameAndYearFrom() {
        //given
        Song song = testSong();
        SongRspDto expectedSongRspDto = testSongRspDto();
        SongCriteria songCriteria = new SongCriteria(null, "hel", 2010, null);
        given(songService.getSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear(null, "hel", 2010, null);
        //then
        assertThat(actualSongRspDtoList).containsExactly(expectedSongRspDto);
    }
    @Test
    void shouldReturnSongDataGIvenPartialUppercaseTrackName() {
        //given
        Song song = testSong();
        SongRspDto expectedSongRspDto = testSongRspDto();
        SongCriteria songCriteria = new SongCriteria(null, "HEL", null, null);
        given(songService.getSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear(null, "HEL", null, null);
        //then
        assertThat(actualSongRspDtoList).containsExactly(expectedSongRspDto);
    }
    @Test
    void shouldReturnSongDataGIvenPartialUppercaseTrackNameAndYearFrom() {
        //given
        Song song = testSong();
        SongRspDto expectedSongRspDto = testSongRspDto();
        SongCriteria songCriteria = new SongCriteria(null, "HEL", 2009, null);
        given(songService.getSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<SongRspDto> actualSongRspDtoList = songFacade.getSongByArtistTrackOrYear(null, "HEL", 2009, null);
        //then
        assertThat(actualSongRspDtoList).containsExactly(expectedSongRspDto);
    }
}