package com.nique1.musicmanagementsystem.song;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("{id}")
    public SongEntity findSongBySongId(@PathVariable int id) {
        return songService.getSongsById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("search")
    public List<SongEntity> findSongByArtistTrackOrYear(@RequestParam(required = false) String artistName,
                                                        @RequestParam(required = false) String trackName,
                                                        @RequestParam(required = false) Integer year) {
        return songService.getSongsByArtistTrackOrYear(artistName, trackName, year)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
