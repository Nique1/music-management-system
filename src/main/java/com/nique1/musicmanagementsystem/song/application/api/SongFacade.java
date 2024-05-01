package com.nique1.musicmanagementsystem.song.application.api;

import com.nique1.musicmanagementsystem.song.domain.SongService;
import com.nique1.musicmanagementsystem.song.application.api.dto.SongRspDto;
import com.nique1.musicmanagementsystem.song.domain.Song;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SongFacade {
    private final SongService songService;

    public SongFacade(SongService songService) {
        this.songService = songService;
    }

    public Optional<SongRspDto> getSongByUuid(UUID uuid) {
        return songService.getSongsByUuid(uuid)
                .map(SongFacade::convertToSongRspDto);
    }

    public List<SongRspDto> getSongByArtistTrackOrYear(String artistName, String trackName, Integer year) {
        return songService.getSongsByArtistTrackOrYear(artistName, trackName, year).stream()
                .map(SongFacade::convertToSongRspDto)
                .toList();
    }
    private static SongRspDto convertToSongRspDto(Song song) {
        return new SongRspDto(
                song.uuid(),
                song.artistName(),
                song.trackName(),
                song.trackLength(),
                song.year()
        );
    }

}
