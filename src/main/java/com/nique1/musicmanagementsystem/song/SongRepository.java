package com.nique1.musicmanagementsystem.song;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SongRepository extends JpaRepository<SongEntity, Integer> {

    List<SongEntity> findSongsBySongId(int id);
    List<SongEntity> findSongsByArtistName(String artistName);
    List<SongEntity> findSongsByTrackName(String trackName);
    List<SongEntity> findSongsByYear(int year);
}
