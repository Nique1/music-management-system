package com.nique1.musicmanagementsystem.song.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SongJpaRepository extends JpaRepository<SongEntity, Integer>, JpaSpecificationExecutor<SongEntity> {

    Optional<SongEntity> findSongsByUuid(UUID uuid);

}
