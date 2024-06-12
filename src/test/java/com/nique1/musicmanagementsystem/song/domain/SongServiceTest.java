package com.nique1.musicmanagementsystem.song.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SongServiceTest {

    private final UUID uuid = UUID.fromString("D6E80790-081A-4ABD-B5E7-1AC0CEDC9EBD");
    @Mock
    private SongRepository songRepository;
    private SongService songService;

    @BeforeEach
    void init() {
        songService = new SongService(songRepository);
    }

    public Song testSong() {
        return new Song(uuid, "Adele", "Hello", 295, 2010);
    }

    @Test
    void shouldReturnSongDataGivenSongUuidExists() {
        //given
        Song song = testSong();
        given(songRepository.findSongsBySongUuid(uuid)).willReturn(Optional.of(song));
        //when
        Optional<Song> actualSong = songService.getSongsByUuid(uuid);
        //then
        assertThat(actualSong).contains(song);
    }

    @Test
    void shouldReturnEmptyOptionalGivenSongUuidDoesNotExist() {
        //given
        UUID nonExistingUUID = UUID.randomUUID();
        given(songRepository.findSongsBySongUuid(nonExistingUUID)).willReturn(Optional.empty());
        //when
        Optional<Song> actualSong = songService.getSongsByUuid(nonExistingUUID);
        //then
        assertThat(actualSong).isEmpty();
    }

    @Test
    void shouldReturnEmptyOptionalWhenUuidIsNull() {
        //given
        //when
        Optional<Song> actualSong = songService.getSongsByUuid(null);
        //then
        assertThat(actualSong).isEmpty();
    }

    @Test
    void shouldReturnSongDataGivenArtistName() {
        //given
        Song song = testSong();
        SongCriteria songCriteria = new SongCriteria("Adele", null, null, null);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).containsExactly(song);

    }

    @Test
    void shouldReturnSongDataGivenTrackName() {
        //given
        Song song = testSong();
        SongCriteria songCriteria = new SongCriteria(null, "Hello", null, null);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).containsExactly(song);

    }

    @Test
    void shouldReturnSongDataGivenYear() {
        //given
        Song song = testSong();
        SongCriteria songCriteria = new SongCriteria(null, null, 2010, null);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).containsExactly(song);
    }

    @Test
    void shouldReturnEmptyListGivenNoSongFound() {
        //given
        SongCriteria songCriteria = new SongCriteria(null, null, null, null);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(List.of());
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).isEmpty();
    }

    @Test
    void shouldReturnEmptyListGivenArtistNameNotFound() {
        //given
        SongCriteria songCriteria = new SongCriteria("Some artist", null, null, null);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(List.of());
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).isEmpty();
    }

    @Test
    void shouldReturnEmptyListGivenTrackNameNotFound() {
        //given
        SongCriteria songCriteria = new SongCriteria(null, "Some track", null, null);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(List.of());
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).isEmpty();
    }


    @Test
    void shouldReturnSongDataGivenArtistNameAndYearFrom() {
        //given
        Song song = testSong();
        SongCriteria songCriteria = new SongCriteria("Adele", null, 2010, null);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).containsExactly(song);
    }

    @Test
    void shouldReturnSongDataGivenArtistNameAndYearTo() {
        //given
        Song song = testSong();
        SongCriteria songCriteria = new SongCriteria("Adele", null, null, 2015);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).containsExactly(song);

    }

    @Test
    void shouldReturnSongDataGivenArtistNameAndYearFromAndYearTo() {
        //given
        Song song = testSong();
        SongCriteria songCriteria = new SongCriteria("Adele", null, 2009, 2015);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).containsExactly(song);
    }

    @Test
    void shouldReturnSongDataGivenTrackNameAndYearFrom() {
        //given
        Song song = testSong();
        SongCriteria songCriteria = new SongCriteria(null, "Hello", 2009, null);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).containsExactly(song);
    }

    @Test
    void shouldReturnSongDataGivenTrackNameAndYearTo() {
        //given
        Song song = testSong();
        SongCriteria songCriteria = new SongCriteria(null, "Hello", null, 2015);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).containsExactly(song);
    }

    @Test
    void shouldReturnSongDataGivenArtistNameAndTrackName() {
        //given
        Song song = testSong();
        SongCriteria songCriteria = new SongCriteria("Adele", "Hello", null, null);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).containsExactly(song);
    }

    @Test
    void shouldReturnAllSongsGivenFullYearsRange() {
        //given
        Song song1 = new Song(UUID.fromString("F0955F27-4404-49C7-B444-A5C3F1918B52"), "The Beatles", "Come Together", 259, 1960);
        Song song2 = new Song(UUID.fromString("58745F5F-937F-412B-BEFD-CB55ACB8E70E"), "Led Zeppelin", "Stairway to Heaven", 482, 1970);
        Song song3 = new Song(UUID.fromString("8B477224-D930-402B-B284-CFD1B59BBA86"), "Michael Jackson", "Billie Jean", 294, 1980);
        Song song4 = new Song(UUID.fromString("82F81B56-AA8F-4850-8BCA-F75A12E6EA57"), "Nirvana", "Smells Like Teen Spirit", 301, 1990);
        Song song5 = new Song(UUID.fromString("D6E80790-081A-4ABD-B5E7-1AC0CEDC9EBD"), "Adele", "Hello", 295, 2010);
        List<Song> expectedSongs = List.of(song1, song2, song3, song4, song5);
        SongCriteria songCriteria = new SongCriteria(null, null, 1950, 2020);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(expectedSongs);
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).containsExactlyInAnyOrderElementsOf(expectedSongs);
    }

    @Test
    void shouldReturnEmptyListGivenYearFromGreaterThanYearTo() {
        //given
        SongCriteria songCriteria = new SongCriteria(null, null, 2015, 2009);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(List.of());
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).isEmpty();
    }

    @Test
    void shouldReturnSongDataGivenPartialLowercaseArtistName() {
        //given
        Song song = testSong();
        SongCriteria songCriteria = new SongCriteria("ade", null, null, null);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).containsExactly(song);
    }

    @Test
    void shouldReturnSongDataGivenPartialLowercaseArtistNameAndYearFrom() {
        //given
        Song song = testSong();
        SongCriteria songCriteria = new SongCriteria("ade", null, 2010, null);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).containsExactly(song);
    }

    @Test
    void shouldReturnSongDataGivenPartialLowercaseArtistNameAndYearTo() {
        //given
        Song song = testSong();
        SongCriteria songCriteria = new SongCriteria("ade", null, null, 2015);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).containsExactly(song);
    }

    @Test
    void shouldReturnSongDataGivenPartialUppercaseArtistName() {
        //given
        Song song = testSong();
        SongCriteria songCriteria = new SongCriteria("ADE", null, null, null);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).containsExactly(song);
    }

    @Test
    void shouldReturnSongDataGivenPartialLowercaseTrackName() {
        //given
        Song song = testSong();
        SongCriteria songCriteria = new SongCriteria(null, "hell", null, null);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).containsExactly(song);
    }

    @Test
    void shouldReturnSongDataGivenPartialUppercaseTrackName() {
        //given
        Song song = testSong();
        SongCriteria songCriteria = new SongCriteria(null, "HELL", null, null);
        given(songRepository.findSongsByCriteria(songCriteria)).willReturn(List.of(song));
        //when
        List<Song> actualSongList = songService.getSongsByCriteria(songCriteria);
        //then
        assertThat(actualSongList).containsExactly(song);
    }


}