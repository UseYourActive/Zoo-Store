package com.example.zoostore.data.repositories;

import com.example.zoostore.data.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {
    Set<Tag> findAllByIdIn(Set<UUID> uuids);
}
