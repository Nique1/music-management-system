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
        //when-then
        given(songFacade.getSongByUuid(uuid)).willReturn(Optional.of(
                new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
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
    void shouldReturnStatus404GivenUuidIsNull() throws Exception {
        //given
        //when-then
        mockMvc.perform(get("/songs/{uuid}", (Object) null)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    void shouldReturnSongDataGivenSongDataExists() throws Exception {
        //given
        given(songFacade.getSongByArtistTrackOrYear("Adele", "Hello", 2010)).willReturn(List.of(
                new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
        //when-then
        mockMvc.perform(get("/songs?artist-name=Adele&track-name=Hello&year=2010"))
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
        given(songFacade.getSongByArtistTrackOrYear("Adele", null, null)).willReturn(List.of(
                new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
        //when-then
        mockMvc.perform(get("/songs?artist-name=Adele"))
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
        given(songFacade.getSongByArtistTrackOrYear(null, "Hello", null)).willReturn(List.of(
                new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
        //when-then
        mockMvc.perform(get("/songs?track-name=Hello"))
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
        given(songFacade.getSongByArtistTrackOrYear(null, null, 2010)).willReturn(List.of(
                new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
        //when-then
        mockMvc.perform(get("/songs?year=2010"))
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
        given(songFacade.getSongByArtistTrackOrYear("Adele", "Hello", null)).willReturn(List.of(
                new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
        //when-then
        mockMvc.perform(get("/songs?artist-name=Adele&track-name=Hello"))
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
        given(songFacade.getSongByArtistTrackOrYear("Adele", null, 2010)).willReturn(List.of(
                new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
        //when-then
        mockMvc.perform(get("/songs?artist-name=Adele&year=2010"))
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
        given(songFacade.getSongByArtistTrackOrYear(null, "Hello", 2010)).willReturn(List.of(
                new SongRspDto(uuid, "Adele", "Hello", 295, 2010)));
        //when-then
        mockMvc.perform(get("/songs?track-name=Hello&year=2010"))
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
        given(songFacade.getSongByArtistTrackOrYear("Adele", "Hello", 2010)).willReturn(List.of());
        //when-then
        mockMvc.perform(get("/songs?artist-name=Adele&track-name=Hello&year=2010"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

}
