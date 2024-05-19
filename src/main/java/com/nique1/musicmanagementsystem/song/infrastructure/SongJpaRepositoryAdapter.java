package com.nique1.musicmanagementsystem.song.infrastructure;

import com.nique1.musicmanagementsystem.song.domain.Song;
import com.nique1.musicmanagementsystem.song.domain.SongRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public class SongJpaRepositoryAdapter implements SongRepository {
    private final SongJpaRepository songJpaRepository;
    private final SongEntityMapper songEntityMapper;

    public SongJpaRepositoryAdapter(SongJpaRepository songJpaRepository, SongEntityMapper songEntityMapper) {
        this.songJpaRepository = songJpaRepository;
        this.songEntityMapper = songEntityMapper;
    }

    @Override
    public Optional<Song> findSongsBySongUuid(UUID uuid) {
        return songJpaRepository.findSongsByUuid(uuid)
                .map(songEntityMapper::convertToSong);
    }

    @Override
    public List<Song> findSongsByArtistNameOrTrackNameOrYear(String artistName, String trackName, Integer year) {
        return songJpaRepository.findSongsByArtistNameOrTrackNameOrYear(artistName, trackName, year).stream()
                .map(songEntityMapper::convertToSong)
                .toList();
    }

}
