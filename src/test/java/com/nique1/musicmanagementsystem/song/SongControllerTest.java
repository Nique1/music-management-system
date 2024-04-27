package com.nique1.musicmanagementsystem.song;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class SongControllerTest {

    private MockMvc mockMvc;
    @Mock
    private SongService songService;
    @InjectMocks
    private SongController songController;
    private SongEntity song;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(songController).build();

        song = new SongEntity(1, "ArtistName", "TrackName", 300, 2020);
    }

    @Test
    void findSongBySongId() throws Exception {
        when(songService.getSongsById(1)).thenReturn(Collections.singletonList(song));

        mockMvc.perform(get("/songs/song/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'songId': 1, 'artistName': 'ArtistName', 'trackName': 'TrackName', 'trackLength': 300, 'year': 2020}]"));
    }

    @Test
    void findSongByArtistName() throws Exception {
        when(songService.getSongsByArtistName("ArtistName")).thenReturn(Arrays.asList(song));

        mockMvc.perform(get("/songs/artist/ArtistName")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'songId': 1, 'artistName': 'ArtistName', 'trackName': 'TrackName', 'trackLength': 300, 'year': 2020}]"));
    }

    @Test
    void findSongByTrackName() throws Exception {
        when(songService.getSongsByTrackName("TrackName")).thenReturn(Arrays.asList(song));

        mockMvc.perform(get("/songs/track/TrackName")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'songId': 1, 'artistName': 'ArtistName', 'trackName': 'TrackName', 'trackLength': 300, 'year': 2020}]"));
    }

    @Test
    void findSongByYear() throws Exception {
        when(songService.getSongsByYear(2020)).thenReturn(Arrays.asList(song));

        mockMvc.perform(get("/songs/year/2020")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'songId': 1, 'artistName': 'ArtistName', 'trackName': 'TrackName', 'trackLength': 300, 'year': 2020}]"));
    }
}
