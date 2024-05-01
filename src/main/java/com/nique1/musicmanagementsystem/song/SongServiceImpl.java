package com.nique1.musicmanagementsystem.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService{
    private final SongRepository songRepository;
    @Autowired
    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public Optional<SongEntity> getSongsById(int id) {
        return songRepository.findSongsBySongId(id);
    }

    @Override
    public Optional<List<SongEntity>> getSongsByArtistTrackOrYear(String artistName, String trackName, Integer year) {
        return songRepository.findSongsByArtistNameOrTrackNameOrYear(artistName, trackName, year);
    }

}
