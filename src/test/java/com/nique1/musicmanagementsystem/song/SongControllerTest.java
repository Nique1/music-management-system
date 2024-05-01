package com.nique1.musicmanagementsystem.song;

import com.nique1.musicmanagementsystem.song.application.api.SongController;
import com.nique1.musicmanagementsystem.song.application.api.SongFacade;
import com.nique1.musicmanagementsystem.song.application.api.dto.SongRspDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SongController.class)
class SongControllerTest {

    @MockBean
    private SongFacade songFacade;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnSongDataGivenSongIdExists() throws Exception {
        //given
        //when-then
        given(songFacade.getSongByUuid(UUID.fromString("F0955F27-4404-49C7-B444-A5C3F1918B52"))).willReturn(Optional.of(
                new SongRspDto(UUID.fromString("F0955F27-4404-49C7-B444-A5C3F1918B52"), "Adele", "Hello", 295, 2010)));
        mockMvc.perform(get("/songs/{uuid}", "F0955F27-4404-49C7-B444-A5C3F1918B52")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value("f0955f27-4404-49c7-b444-a5c3f1918b52"))
                .andExpect(jsonPath("$.artistName").value("Adele"))
                .andExpect(jsonPath("$.trackName").value("Hello"))
                .andExpect(jsonPath("$.trackLength").value(295))
                .andExpect(jsonPath("$.year").value(2010));
    }

    @Test
    void shouldReturnStatus404GivenSongDoesNotExist() throws Exception {
        //given
        given(songFacade.getSongByUuid(UUID.fromString("F0955F27-4404-49C7-B444-A5C3F1918B52"))).willReturn(Optional.empty());
        //when-then
        mockMvc.perform(get("/songs/{uuid}", "F0955F27-4404-49C7-B444-A5C3F1918B52")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
//
//    @Test
//    void shouldReturnSongDataGivenSongDataExists() throws Exception {
//        //given
//        given(songFacade.getSongsByArtistTrackOrYear("Adele", "Hello", 2010)).willReturn(Optional.of(
//                List.of(new SongEntity(1, "Adele", "Hello", 295, 2010))));
//        //when-then
//        mockMvc.perform(get("/songs/search?artistName=Adele&trackName=Hello&year=2010")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].songId").value(1))
//                .andExpect(jsonPath("$[0].artistName").value("Adele"))
//                .andExpect(jsonPath("$[0].trackName").value("Hello"))
//                .andExpect(jsonPath("$[0].trackLength").value(295))
//                .andExpect(jsonPath("$[0].year").value(2010));
//    }
//    @Test
//    void shouldReturnStatus404GivenSongDataDoesNotExist() throws Exception {
//        //given
//        given(songFacade.getSongsByArtistTrackOrYear("Adele", "Hello", 2010)).willReturn(Optional.empty());
//        //when-then
//        mockMvc.perform(get("/songs/search?artistName=Adele&trackName=Hello&year=2010")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void shouldReturnSongDataGivenArtistExists() throws Exception {
//        //given
//        given(songFacade.getSongsByArtistTrackOrYear("Adele", null, null))
//                .willReturn(Optional.of(
//                List.of(new SongEntity(1, "Adele", "Hello", 295, 2010))));
//        //when-then
//        mockMvc.perform(get("/songs/search?artistName=Adele")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].songId").value(1))
//                .andExpect(jsonPath("$[0].artistName").value("Adele"))
//                .andExpect(jsonPath("$[0].trackName").value("Hello"))
//                .andExpect(jsonPath("$[0].trackLength").value(295))
//                .andExpect(jsonPath("$[0].year").value(2010));
//    }
//    @Test
//    void shouldReturnSongDataGivenTrackExists() throws Exception {
//        //given
//        given(songFacade.getSongsByArtistTrackOrYear(null, "Hello", null))
//                .willReturn(Optional.of(
//                List.of(new SongEntity(1, "Adele", "Hello", 295, 2010))));
//        //when-then
//        mockMvc.perform(get("/songs/search?trackName=Hello")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].songId").value(1))
//                .andExpect(jsonPath("$[0].artistName").value("Adele"))
//                .andExpect(jsonPath("$[0].trackName").value("Hello"))
//                .andExpect(jsonPath("$[0].trackLength").value(295))
//                .andExpect(jsonPath("$[0].year").value(2010));
//    }
//
//    @Test
//    void shouldReturnSongDataGivenYearExists() throws Exception {
//        //given
//        given(songFacade.getSongsByArtistTrackOrYear(null, null, 2010))
//                .willReturn(Optional.of(
//                List.of(new SongEntity(1, "Adele", "Hello", 295, 2010))));
//        //when-then
//        mockMvc.perform(get("/songs/search?year=2010")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].songId").value(1))
//                .andExpect(jsonPath("$[0].artistName").value("Adele"))
//                .andExpect(jsonPath("$[0].trackName").value("Hello"))
//                .andExpect(jsonPath("$[0].trackLength").value(295))
//                .andExpect(jsonPath("$[0].year").value(2010));
//    }
//
//    @Test
//    void shouldReturnSongDataGivenArtistAndTrackExists() throws Exception {
//        //given
//        given(songFacade.getSongsByArtistTrackOrYear("Adele", "Hello", null))
//                .willReturn(Optional.of(
//                List.of(new SongEntity(1, "Adele", "Hello", 295, 2010))));
//        //when-then
//        mockMvc.perform(get("/songs/search?artistName=Adele&trackName=Hello")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].songId").value(1))
//                .andExpect(jsonPath("$[0].artistName").value("Adele"))
//                .andExpect(jsonPath("$[0].trackName").value("Hello"))
//                .andExpect(jsonPath("$[0].trackLength").value(295))
//                .andExpect(jsonPath("$[0].year").value(2010));
//    }
//
//    @Test
//    void shouldReturnSongDataGivenArtistAndYearExists() throws Exception {
//        //given
//        given(songFacade.getSongsByArtistTrackOrYear("Adele", null, 2010))
//                .willReturn(Optional.of(
//                List.of(new SongEntity(1, "Adele", "Hello", 295, 2010))));
//        //when-then
//        mockMvc.perform(get("/songs/search?artistName=Adele&year=2010")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].songId").value(1))
//                .andExpect(jsonPath("$[0].artistName").value("Adele"))
//                .andExpect(jsonPath("$[0].trackName").value("Hello"))
//                .andExpect(jsonPath("$[0].trackLength").value(295))
//                .andExpect(jsonPath("$[0].year").value(2010));
//    }
//    @Test
//    void shouldReturnSongDataGivenTrackAndYearExists() throws Exception {
//        //given
//        given(songFacade.getSongsByArtistTrackOrYear(null, "Hello", 2010))
//                .willReturn(Optional.of(
//                List.of(new SongEntity(1, "Adele", "Hello", 295, 2010))));
//        //when-then
//        mockMvc.perform(get("/songs/search?trackName=Hello&year=2010")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].songId").value(1))
//                .andExpect(jsonPath("$[0].artistName").value("Adele"))
//                .andExpect(jsonPath("$[0].trackName").value("Hello"))
//                .andExpect(jsonPath("$[0].trackLength").value(295))
//                .andExpect(jsonPath("$[0].year").value(2010));
//    }
}
