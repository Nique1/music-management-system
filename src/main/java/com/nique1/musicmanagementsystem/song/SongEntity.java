package com.nique1.musicmanagementsystem.song;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "songs")
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
