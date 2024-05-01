package com.nique1.musicmanagementsystem.song.infrastructure;

import com.nique1.musicmanagementsystem.song.domain.SongRepository;
import com.nique1.musicmanagementsystem.song.domain.SongService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SongServiceFactory {
    @Bean
    public SongService createSongService(SongRepository songRepository) {
        return new SongService(songRepository);
    }
}
