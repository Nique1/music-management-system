package com.nique1.musicmanagementsystem.song;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Integer> {

    Optional<SongEntity> findSongsBySongId(int id);
    Optional<List<SongEntity>> findSongsByArtistNameOrTrackNameOrYear(String artistName, String trackName, Integer year);
    List<SongEntity> findSongsByTrackName(String trackName);

}
