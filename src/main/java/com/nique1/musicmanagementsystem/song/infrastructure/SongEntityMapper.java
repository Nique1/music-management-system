package com.nique1.musicmanagementsystem.song.infrastructure;

import com.nique1.musicmanagementsystem.song.domain.Song;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SongEntityMapper {
    Song convertToSong(SongEntity songEntity);
}
