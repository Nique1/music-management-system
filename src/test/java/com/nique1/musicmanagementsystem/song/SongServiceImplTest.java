//package com.nique1.musicmanagementsystem.song;
//
//import com.nique1.musicmanagementsystem.song.infrastructure.SongEntity;
//import com.nique1.musicmanagementsystem.song.infrastructure.SongJpaRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.BDDMockito.given;
//@ExtendWith(MockitoExtension.class)
//class SongServiceImplTest {
//
//    @Mock
//    private SongJpaRepository songJpaRepository;
//
//    private SongServiceImpl songService;
//
//
//    @BeforeEach
//    void init() {
//        songService = new SongServiceImpl(songJpaRepository);
//    }
//
//    public SongEntity testSong(){
//        return new SongEntity(1, "Adele", "Hello", 295, 2010);
//    }
//
//    @Test
//    void shouldReturnSongDataWhenSongFoundById() {
//        //given
//        SongEntity song = testSong();
//        int songId = 1;
//        given(songJpaRepository.findSongsBySongId(songId)).willReturn(Optional.ofNullable(song));
//        //when
//        Optional<SongEntity> songResult = songService.getSongsByUuid(songId);
//        //then
//        assertThat(songResult).containsSame(song);
//    }
//
////    @Test
////    void shouldReturnTrueWhenSongFoundByArtistName() {
////        String artistName = "ArtistName";
////        when(songRepository.findSongsByArtistName(artistName)).thenReturn(List.of(testSong()));
////        List<SongEntity> songsResult = songService.getSongsByArtistName(artistName);
////        assertThat(songsResult).isNotEmpty();
////
////    }
////
////    @Test
////    void shouldReturnSongDataWhenSongFoundByArtistName() {
////        String artistName = "ArtistName";
////        when(songRepository.findSongsByArtistName(artistName)).thenReturn(List.of(testSong()));
////        List<SongEntity> songsResult = songService.getSongsByArtistName(artistName);
////        assertThat(songsResult).isEqualTo(List.of(testSong()));
////    }
////
////    @Test
////    void shouldReturnTrueWhenSongFoundByTrackName() {
////        String trackName = "TrackName";
////        when(songRepository.findSongsByTrackName(trackName)).thenReturn(List.of(testSong()));
////        List<SongEntity> songsResult = songService.getSongsByTrackName(trackName);
////        assertThat(songsResult).isNotEmpty();
////
////    }
////
////    @Test
////    void shouldReturnSongDataWhenSongFoundByTrackName() {
////        String trackName = "TrackName";
////        when(songRepository.findSongsByTrackName(trackName)).thenReturn(List.of(testSong()));
////        List<SongEntity> songsResult = songService.getSongsByTrackName(trackName);
////        assertThat(songsResult).isEqualTo(List.of(testSong()));
////    }
////
////    @Test
////    void shouldReturnTrueWhenSongFoundByYear() {
////        int year = 2020;
////        when(songRepository.findSongsByYear(year)).thenReturn(List.of(testSong()));
////        List<SongEntity> songsResult = songService.getSongsByYear(year);
////        assertThat(songsResult).isNotEmpty();
////
////    }
////
////    @Test
////    void shouldReturnSongDataWhenSongFoundByYear() {
////        int year = 2020;
////        when(songRepository.findSongsByYear(year)).thenReturn(List.of(testSong()));
////        List<SongEntity> songsResult = songService.getSongsByYear(year);
////        assertThat(songsResult).isEqualTo(List.of(testSong()));
////    }
//
//}