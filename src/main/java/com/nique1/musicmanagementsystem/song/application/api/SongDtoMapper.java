package com.nique1.musicmanagementsystem.song.application.api;

import com.nique1.musicmanagementsystem.song.application.api.dto.SongRspDto;
import com.nique1.musicmanagementsystem.song.domain.Song;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SongDtoMapper {

    SongRspDto convertToSongRspDto(Song song);
}
