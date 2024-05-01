package com.nique1.musicmanagementsystem.song.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SongService {

    private final SongRepository songRepository;
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Optional<Song> getSongsByUuid(UUID uuid) {
        return songRepository.findSongsBySongUuid(uuid);
    }

    public List<Song> getSongsByArtistTrackOrYear(String artistName, String trackName, Integer year) {
        return songRepository.findSongsByArtistNameOrTrackNameOrYear(artistName, trackName, year);    }


}
