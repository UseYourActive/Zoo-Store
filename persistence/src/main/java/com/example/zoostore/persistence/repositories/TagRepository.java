package com.example.zoostore.persistence.repositories;

import com.example.zoostore.persistence.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {
    Set<Tag> findAllByIdIn(Set<UUID> uuids);

    Optional<Tag> findTagById(UUID id);
}
