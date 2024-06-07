package com.nique1.musicmanagementsystem.song.application.api.dto;

import java.util.UUID;

public record SongRspDto(
        UUID uuid,
        String artistName,
        String trackName,
        int trackLength,
        int year
) {


}
