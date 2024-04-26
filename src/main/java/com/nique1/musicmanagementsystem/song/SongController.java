package com.nique1.musicmanagementsystem.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("song/{id}")
    public List<SongEntity> findSongBySongId(@PathVariable int id) {
        return songService.getSongsById(id);
    }

    @GetMapping("artist/{artistName}")
    public List<SongEntity> findSongByArtistName(@PathVariable String artistName) {
        return songService.getSongsByArtistName(artistName);
    }

    @GetMapping("track/{trackName}")
    public List<SongEntity> findSongByTrackName(@PathVariable String trackName) {
        return songService.getSongsByTrackName(trackName);
    }

    @GetMapping("year/{year}")
    public List<SongEntity> findSongByYear(@PathVariable int year) {
        return songService.getSongsByYear(year);
    }
}
