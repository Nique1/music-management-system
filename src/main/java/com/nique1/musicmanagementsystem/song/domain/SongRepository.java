package com.nique1.musicmanagementsystem.song.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SongRepository {
    Optional<Song> findSongsBySongUuid(UUID uuid);

    List<Song> findSongsByCriteria(SongCriteria songCriteria);
}
