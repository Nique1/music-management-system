package com.nique1.musicmanagementsystem.song.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SongJpaRepository extends JpaRepository<SongEntity, Integer> {

    Optional<SongEntity> findSongsByUuid(UUID uuid);
    List<SongEntity> findSongsByArtistNameOrTrackNameOrYear(String artistName, String trackName, Integer year);

}
