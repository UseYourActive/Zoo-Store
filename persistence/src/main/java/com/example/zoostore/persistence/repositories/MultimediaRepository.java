package com.example.zoostore.persistence.repositories;

import com.example.zoostore.persistence.entities.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface MultimediaRepository extends JpaRepository<Multimedia, UUID> {
    Set<Multimedia> findAllMultimediaByIdIn(Set<UUID> uuids);
    Optional<Multimedia> findMultimediaById(UUID id);
    Set<Multimedia> findAllByIdIn(Set<UUID> uuids);

}
