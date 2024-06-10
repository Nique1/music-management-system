package com.nique1.musicmanagementsystem.song.application.api;

import com.nique1.musicmanagementsystem.song.application.api.dto.SongRspDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SongController.class)
class SongControllerTest {
    private final UUID uuid = UUID.fromString("D6E80790-081A-4ABD-B5E7-1AC0CEDC9EBD");

    @MockBean
    private SongFacade songFacade;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnSongDataGivenSongUuidExists() throws Exception {
        //given
        given(songFacade.getSongByUuid(uuid)).willReturn(Optional.of(
                new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
        //when-then
        mockMvc.perform(get("/songs/{uuid}", "D6E80790-081A-4ABD-B5E7-1AC0CEDC9EBD")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.uuid").value("d6e80790-081a-4abd-b5e7-1ac0cedc9ebd"))
                .andExpect(jsonPath("$.artistName").value("Adele"))
                .andExpect(jsonPath("$.trackName").value("Hello"))
                .andExpect(jsonPath("$.trackLength").value(295))
                .andExpect(jsonPath("$.year").value(2010));
    }

    @Test
    void shouldReturnStatus404GivenSongUuidDoesNotExist() throws Exception {
        //given
        UUID nonExistingUUID = UUID.randomUUID();
        given(songFacade.getSongByUuid(nonExistingUUID)).willReturn(Optional.empty());
        //when-then
        mockMvc.perform(get("/songs/{uuid}", nonExistingUUID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    void shouldReturnSongDataGivenSongDataExists() throws Exception {
        //given
        given(songFacade.getSongByArtistTrackOrYear("Adele", "Hello", 2010, 2010)).willReturn(List.of(
                new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
        //when-then
        mockMvc.perform(get("/songs")
                        .queryParam("artist-name", "Adele")
                        .queryParam("track-name", "Hello")
                        .queryParam("year-from", "2010")
                        .queryParam("year-to", "2010"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value("d6e80790-081a-4abd-b5e7-1ac0cedc9ebd"))
                .andExpect(jsonPath("$[0].artistName").value("Adele"))
                .andExpect(jsonPath("$[0].trackName").value("Hello"))
                .andExpect(jsonPath("$[0].trackLength").value(295))
                .andExpect(jsonPath("$[0].year").value(2010));

    }

    @Test
    void shouldReturnSongDataGivenArtistExists() throws Exception {
        //given
        given(songFacade.getSongByArtistTrackOrYear("Adele", null, null, null)).willReturn(List.of(
                new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
        //when-then
        mockMvc.perform(get("/songs")
                        .queryParam("artist-name", "Adele"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value("d6e80790-081a-4abd-b5e7-1ac0cedc9ebd"))
                .andExpect(jsonPath("$[0].artistName").value("Adele"))
                .andExpect(jsonPath("$[0].trackName").value("Hello"))
                .andExpect(jsonPath("$[0].trackLength").value(295))
                .andExpect(jsonPath("$[0].year").value(2010));
    }

    @Test
    void shouldReturnSOngDataGivenTrackExists() throws Exception {
        //given
        given(songFacade.getSongByArtistTrackOrYear(null, "Hello", null, null)).willReturn(List.of(
                new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
        //when-then
        mockMvc.perform(get("/songs")
                        .queryParam("track-name", "Hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value("d6e80790-081a-4abd-b5e7-1ac0cedc9ebd"))
                .andExpect(jsonPath("$[0].artistName").value("Adele"))
                .andExpect(jsonPath("$[0].trackName").value("Hello"))
                .andExpect(jsonPath("$[0].trackLength").value(295))
                .andExpect(jsonPath("$[0].year").value(2010));
    }

    @Test
    void shouldReturnSongDataGivenYearExists() throws Exception {
        //given
        given(songFacade.getSongByArtistTrackOrYear(null, null, 2010, 2010)).willReturn(List.of(
                new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
        //when-then
        mockMvc.perform(get("/songs")
                        .queryParam("year-from", "2010")
                        .queryParam("year-to", "2010"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value("d6e80790-081a-4abd-b5e7-1ac0cedc9ebd"))
                .andExpect(jsonPath("$[0].artistName").value("Adele"))
                .andExpect(jsonPath("$[0].trackName").value("Hello"))
                .andExpect(jsonPath("$[0].trackLength").value(295))
                .andExpect(jsonPath("$[0].year").value(2010));
    }

    @Test
    void shouldReturnSongDataGivenArtistAndTrackExists() throws Exception {
        //given
        given(songFacade.getSongByArtistTrackOrYear("Adele", "Hello", null, null)).willReturn(List.of(
                new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
        //when-then
        mockMvc.perform(get("/songs")
                        .queryParam("artist-name", "Adele")
                        .queryParam("track-name", "Hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value("d6e80790-081a-4abd-b5e7-1ac0cedc9ebd"))
                .andExpect(jsonPath("$[0].artistName").value("Adele"))
                .andExpect(jsonPath("$[0].trackName").value("Hello"))
                .andExpect(jsonPath("$[0].trackLength").value(295))
                .andExpect(jsonPath("$[0].year").value(2010));
    }

    @Test
    void shouldReturnSongDataGivenArtistAndYearExists() throws Exception {
        //given
        given(songFacade.getSongByArtistTrackOrYear("Adele", null, 2010, 2010)).willReturn(List.of(
                new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
        //when-then
        mockMvc.perform(get("/songs")
                        .queryParam("artist-name", "Adele")
                        .queryParam("year-from", "2010")
                        .queryParam("year-to", "2010"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value("d6e80790-081a-4abd-b5e7-1ac0cedc9ebd"))
                .andExpect(jsonPath("$[0].artistName").value("Adele"))
                .andExpect(jsonPath("$[0].trackName").value("Hello"))
                .andExpect(jsonPath("$[0].trackLength").value(295))
                .andExpect(jsonPath("$[0].year").value(2010));
    }

    @Test
    void shouldReturnSongDataGivenTrackAndYearExists() throws Exception {
        //given
        given(songFacade.getSongByArtistTrackOrYear(null, "Hello", 2010, 2010)).willReturn(List.of(
                new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
        //when-then
        mockMvc.perform(get("/songs")
                        .queryParam("track-name", "Hello")
                        .queryParam("year-from", "2010")
                        .queryParam("year-to", "2010"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value("d6e80790-081a-4abd-b5e7-1ac0cedc9ebd"))
                .andExpect(jsonPath("$[0].artistName").value("Adele"))
                .andExpect(jsonPath("$[0].trackName").value("Hello"))
                .andExpect(jsonPath("$[0].trackLength").value(295))
                .andExpect(jsonPath("$[0].year").value(2010));
    }

    @Test
    void shouldReturnEmptyListGivenSongFacadeReturnsEmptyList() throws Exception {
        //given
        given(songFacade.getSongByArtistTrackOrYear("Adele", "Hello", 2010, 2010)).willReturn(List.of());
        //when-then
        mockMvc.perform(get("/songs")
                        .queryParam("artist-name", "Adele")
                        .queryParam("track-name", "Hello")
                        .queryParam("year-from", "2010")
                        .queryParam("year-to", "2010"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void shouldReturnSongDataGivenNullYearFrom() throws Exception {
        //given
        given(songFacade.getSongByArtistTrackOrYear("Adele", "Hello", null, 2010)).willReturn(List.of(
                new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
        //when-then
        mockMvc.perform(get("/songs")
                        .queryParam("artist-name", "Adele")
                        .queryParam("track-name", "Hello")
                        .queryParam("year-to", "2010"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value("d6e80790-081a-4abd-b5e7-1ac0cedc9ebd"))
                .andExpect(jsonPath("$[0].artistName").value("Adele"))
                .andExpect(jsonPath("$[0].trackName").value("Hello"))
                .andExpect(jsonPath("$[0].trackLength").value(295))
                .andExpect(jsonPath("$[0].year").value(2010));
    }

    @Test
    void shouldReturnSongDataGivenNullYearTo() throws Exception {
        //given
        given(songFacade.getSongByArtistTrackOrYear("Adele", "Hello", 2010, null)).willReturn(List.of(
                new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
        //when-then
        mockMvc.perform(get("/songs")
                        .queryParam("artist-name", "Adele")
                        .queryParam("track-name", "Hello")
                        .queryParam("year-from", "2010"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value("d6e80790-081a-4abd-b5e7-1ac0cedc9ebd"))
                .andExpect(jsonPath("$[0].artistName").value("Adele"))
                .andExpect(jsonPath("$[0].trackName").value("Hello"))
                .andExpect(jsonPath("$[0].trackLength").value(295))
                .andExpect(jsonPath("$[0].year").value(2010));
    }

    @Test
    void shouldReturnEmptyListGivenYearFromGreaterThanYearTo() throws Exception {
        //given
        given(songFacade.getSongByArtistTrackOrYear("Adele", "Hello", 2015, 2010)).willReturn(List.of());
        //when-then
        mockMvc.perform(get("/songs")
                        .queryParam("artist-name", "Adele")
                        .queryParam("track-name", "Hello")
                        .queryParam("year-from", "2015")
                        .queryParam("year-to", "2010"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void shouldReturnListOfAllSongsGivenFullYearsRange() throws Exception {
        //given
        given(songFacade.getSongByArtistTrackOrYear(null, null, 1960, 2010)).willReturn(List.of(
                new SongRspDto(uuid, "Adele", "Hello", 295, 2010),
                new SongRspDto( UUID.fromString("82F81B56-AA8F-4850-8BCA-F75A12E6EA57"), "Nirvana","Smells Like Teen Spirit", 301, 1990),
                new SongRspDto( UUID.fromString("8B477224-D930-402B-B284-CFD1B59BBA86"), "Michael Jackson","Billie Jean" ,  294, 1980),
                new SongRspDto( UUID.fromString("58745F5F-937F-412B-BEFD-CB55ACB8E70E"), "Led Zeppelin","Stairway to Heaven" , 482, 1970),
                new SongRspDto(UUID.fromString("F0955F27-4404-49C7-B444-A5C3F1918B52"), "The Beatles", "Come Together", 259, 1960)
        ));
        //when-then
        mockMvc.perform(get("/songs")
                        .queryParam("year-from", "1960")
                        .queryParam("year-to", "2010"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value("d6e80790-081a-4abd-b5e7-1ac0cedc9ebd"))
                .andExpect(jsonPath("$[0].artistName").value("Adele"))
                .andExpect(jsonPath("$[0].trackName").value("Hello"))
                .andExpect(jsonPath("$[0].trackLength").value(295))
                .andExpect(jsonPath("$[0].year").value(2010))
                .andExpect(jsonPath("$[1].uuid").value("82f81b56-aa8f-4850-8bca-f75a12e6ea57"))
                .andExpect(jsonPath("$[1].artistName").value("Nirvana"))
                .andExpect(jsonPath("$[1].trackName").value("Smells Like Teen Spirit"))
                .andExpect(jsonPath("$[1].trackLength").value(301))
                .andExpect(jsonPath("$[1].year").value(1990))
                .andExpect(jsonPath("$[2].uuid").value("8b477224-d930-402b-b284-cfd1b59bba86"))
                .andExpect(jsonPath("$[2].artistName").value("Michael Jackson"))
                .andExpect(jsonPath("$[2].trackName").value("Billie Jean"))
                .andExpect(jsonPath("$[2].trackLength").value(294))
                .andExpect(jsonPath("$[2].year").value(1980))
                .andExpect(jsonPath("$[3].uuid").value("58745f5f-937f-412b-befd-cb55acb8e70e"))
                .andExpect(jsonPath("$[3].artistName").value("Led Zeppelin"))
                .andExpect(jsonPath("$[3].trackName").value("Stairway to Heaven"))
                .andExpect(jsonPath("$[3].trackLength").value(482))
                .andExpect(jsonPath("$[3].year").value(1970))
                .andExpect(jsonPath("$[4].uuid").value("f0955f27-4404-49c7-b444-a5c3f1918b52"))
                .andExpect(jsonPath("$[4].artistName").value("The Beatles"))
                .andExpect(jsonPath("$[4].trackName").value("Come Together"))
                .andExpect(jsonPath("$[4].trackLength").value(259))
                .andExpect(jsonPath("$[4].year").value(1960));

    }


    @Test
    void shouldReturnEmptyListGivenNullParameters() throws Exception {
        //given
        given(songFacade.getSongByArtistTrackOrYear(null, null, null, null)).willReturn(List.of());
        //when-then
        mockMvc.perform(get("/songs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void shouldReturnEmptyListGivenArtistNameNotFound() throws Exception {
        //given
        given(songFacade.getSongByArtistTrackOrYear("Some artist", null, null, null)).willReturn(List.of());
        //when-then
        mockMvc.perform(get("/songs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void shouldReturnEmptyListGivenTrackNameNotFound() throws Exception {
        //given
        given(songFacade.getSongByArtistTrackOrYear(null, "Some track", null, null)).willReturn(List.of());
        //when-then
        mockMvc.perform(get("/songs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void shouldReturnSongDataGivenPartialLowercaseArtistName() throws Exception {
        //given
        given(songFacade.getSongByArtistTrackOrYear("ade", null, null, null))
                .willReturn(List.of(new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
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
        //given
        given(songFacade.getSongByArtistTrackOrYear(null, "hell", null, null))
                .willReturn(List.of(new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
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
        //given
        given(songFacade.getSongByArtistTrackOrYear(null, "hell", 2010, null))
                .willReturn(List.of(new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
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
        //given
        given(songFacade.getSongByArtistTrackOrYear("ADE", null, null, null))
                .willReturn(List.of(new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
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
