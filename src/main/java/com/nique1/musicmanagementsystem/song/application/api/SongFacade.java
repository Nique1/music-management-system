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
    private final SongDtoMapper songDtoMapper;

    public SongFacade(SongService songService, SongDtoMapper songDtoMapper) {
        this.songService = songService;
        this.songDtoMapper = songDtoMapper;
    }

    public Optional<SongRspDto> getSongByUuid(UUID uuid) {
        return songService.getSongsByUuid(uuid)
                .map(songDtoMapper::convertToSongRspDto);
    }

    public List<SongRspDto> getSongByArtistTrackOrYear(String artistName, String trackName, Integer year) {
        return songService.getSongsByArtistTrackOrYear(artistName, trackName, year).stream()
                .map(songDtoMapper::convertToSongRspDto)
                .toList();
    }

}
