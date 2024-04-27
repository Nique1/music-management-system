package com.nique1.musicmanagementsystem.song;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "song_entity")
public class SongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="song_id")
    private int songId;

    @Column(name="artist_name")
    private String artistName;

    @Column(name="track_name")
    private String trackName;

    @Column(name="track_length")
    private int trackLength;

    @Column(name="year")
    private int year;



}
