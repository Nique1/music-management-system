package com.nique1.musicmanagementsystem.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService{
    private final SongRepository songRepository;
    @Autowired
    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<SongEntity> getSongsById(int id) {
        List<SongEntity> songs =  songRepository.findSongsBySongId(id).stream().toList();
        if(songs.isEmpty()){
            throw new IllegalArgumentException("Invalid song id" + id);
        }
        return songs;
    }

    @Override
    public List<SongEntity> getSongsByArtistName(String artistName) {
        List<SongEntity> songs =  songRepository.findSongsByArtistName(artistName).stream().toList();
        if(songs.isEmpty()){
            throw new IllegalArgumentException("Invalid artist name" + artistName );
        }
        return songs;
    }

    @Override
    public List<SongEntity> getSongsByTrackName(String trackName) {
        List<SongEntity> songs =  songRepository.findSongsByTrackName(trackName).stream().toList();
        if(songs.isEmpty()){
            throw new IllegalArgumentException("Invalid track name" + trackName);
        }
        return songs;
    }


    @Override
    public List<SongEntity> getSongsByYear(int year) {
        List<SongEntity> songs =  songRepository.findSongsByYear(year).stream().toList();
        if(songs.isEmpty()){
            throw new IllegalArgumentException("Invalid year" + year);
        }
        return songs;
    }
}
