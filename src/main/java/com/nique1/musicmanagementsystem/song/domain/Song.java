package com.nique1.musicmanagementsystem.song.domain;

import java.util.UUID;

public record Song(
        UUID uuid,
        String artistName,
        String trackName,
        int trackLength,
        int year

) {

}
