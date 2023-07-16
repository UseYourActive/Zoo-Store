package com.example.zoostore.data.repositories;

import com.example.zoostore.data.entities.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface MultimediaRepository extends JpaRepository<Multimedia, UUID> {
    Set<Multimedia> findAllByIdIn(Set<UUID> uuids);
}
