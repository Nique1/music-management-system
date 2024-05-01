package com.nique1.musicmanagementsystem.song;

import java.util.List;
import java.util.Optional;

public interface SongService {

    Optional<SongEntity> getSongsById(int id);
    Optional<List<SongEntity>> getSongsByArtistTrackOrYear(String artistName, String trackName, Integer year);
}
