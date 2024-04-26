package com.nique1.musicmanagementsystem.song;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SongControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SongService songService;

    private SongEntity song;

    @BeforeEach
    void setUp() {
        song = new SongEntity(1, "ArtistName", "TrackName", 300, 2020);
    }

    @Test
    void findSongBySongId() throws Exception {
        when(songService.getSongsById(1)).thenReturn(Arrays.asList(song));

        mockMvc.perform(get("/songs/song/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'artistName': 'ArtistName', 'trackName': 'TrackName', 'duration': 300, 'year': 2020}]"));
    }

    @Test
    void findSongByArtistName() throws Exception {
        when(songService.getSongsByArtistName("ArtistName")).thenReturn(Arrays.asList(song));

        mockMvc.perform(get("/songs/artist/ArtistName")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'artistName': 'ArtistName', 'trackName': 'TrackName', 'duration': 300, 'year': 2020}]"));
    }

    @Test
    void findSongByTrackName() throws Exception {
        when(songService.getSongsByTrackName("TrackName")).thenReturn(Arrays.asList(song));

        mockMvc.perform(get("/songs/track/TrackName")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'artistName': 'ArtistName', 'trackName': 'TrackName', 'duration': 300, 'year': 2020}]"));
    }

    @Test
    void findSongByYear() throws Exception {
        when(songService.getSongsByYear(2020)).thenReturn(Arrays.asList(song));

        mockMvc.perform(get("/songs/year/2020")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'artistName': 'ArtistName', 'trackName': 'TrackName', 'duration': 300, 'year': 2020}]"));
    }
}
