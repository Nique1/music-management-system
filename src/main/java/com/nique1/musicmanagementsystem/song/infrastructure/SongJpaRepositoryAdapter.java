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

    public SongJpaRepositoryAdapter(SongJpaRepository songJpaRepository) {
        this.songJpaRepository = songJpaRepository;
    }

    @Override
    public Optional<Song> findSongsBySongUuid(UUID uuid) {
        return songJpaRepository.findSongsByUuid(uuid)
                .map(SongJpaRepositoryAdapter::convertToSong);
    }

    @Override
    public List<Song> findSongsByArtistNameOrTrackNameOrYear(String artistName, String trackName, Integer year) {
        return songJpaRepository.findSongsByArtistNameOrTrackNameOrYear(artistName, trackName, year).stream()
                .map(SongJpaRepositoryAdapter::convertToSong)
                .toList();
    }

    private static Song convertToSong(SongEntity songEntity) {
        return new Song(songEntity.getUuid(), songEntity.getArtistName(), songEntity.getTrackName(), songEntity.getTrackLength(), songEntity.getYear());
    }
}
