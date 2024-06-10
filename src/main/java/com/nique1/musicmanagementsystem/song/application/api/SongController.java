package com.nique1.musicmanagementsystem.song.application.api;

import com.nique1.musicmanagementsystem.song.application.api.dto.SongRspDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongFacade songFacade;

    public SongController(SongFacade songFacade) {
        this.songFacade = songFacade;
    }

    @GetMapping("{uuid}")
    public SongRspDto getSongByUuid(@PathVariable UUID uuid) {
        return songFacade.getSongByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<SongRspDto> getSongByArtistTrackOrYear(
        @RequestParam(name = "artist-name", required = false) String artistName,
        @RequestParam(name = "track-name", required = false) String trackName,
        @RequestParam(name = "year-from", required = false) Integer yearFrom,
        @RequestParam(name = "year-to", required = false) Integer yearTo
    ) {
        return songFacade.getSongByArtistTrackOrYear(artistName, trackName, yearFrom, yearTo);
    }

}
