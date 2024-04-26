package com.nique1.musicmanagementsystem.song;

import java.util.List;

public interface SongService {

    List<SongEntity> getSongsById(int id);
    List<SongEntity> getSongsByArtistName(String artistName);
    List<SongEntity> getSongsByTrackName(String songName);
    List<SongEntity> getSongsByYear(int year);
}
