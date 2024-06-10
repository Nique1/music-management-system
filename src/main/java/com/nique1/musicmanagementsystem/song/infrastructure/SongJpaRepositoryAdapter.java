package com.nique1.musicmanagementsystem.song.infrastructure;

import com.nique1.musicmanagementsystem.song.domain.Song;
import com.nique1.musicmanagementsystem.song.domain.SongCriteria;
import com.nique1.musicmanagementsystem.song.domain.SongRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SongJpaRepositoryAdapter implements SongRepository {
    private final SongJpaRepository songJpaRepository;
    private final SongEntityMapper songEntityMapper;

    public SongJpaRepositoryAdapter(SongJpaRepository songJpaRepository, SongEntityMapper songEntityMapper) {
        this.songJpaRepository = songJpaRepository;
        this.songEntityMapper = songEntityMapper;
    }

    @Override
    public Optional<Song> findSongsBySongUuid(UUID uuid) {
        return songJpaRepository.findSongsByUuid(uuid)
                .map(songEntityMapper::convertToSong);
    }


    @Override
    public List<Song> findSongsByCriteria(SongCriteria songCriteria) {
        Specification<SongEntity> songSpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();
            if (songCriteria.artistName() != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(SongEntity_.artistName)), "%" + songCriteria.artistName().toLowerCase() + "%"));
            }
            if (songCriteria.yearFrom() != null) {
                predicates.add(criteriaBuilder.ge(root.get(SongEntity_.year), songCriteria.yearFrom()));
            }
            if (songCriteria.yearTo() != null) {
                predicates.add(criteriaBuilder.le(root.get(SongEntity_.year), songCriteria.yearTo()));
            }
            if (songCriteria.trackName() != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(SongEntity_.trackName)), "%" + songCriteria.trackName().toLowerCase() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return songJpaRepository.findAll(songSpecification).stream()
                .map(songEntityMapper::convertToSong)
                .toList();
    }

}
