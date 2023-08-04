package com.example.zoostore.persistence.repositories;

import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    Page<Item> findAllByArchivedAndTagsContaining(Boolean archived, Tag tag, Pageable pageable);
    Page<Item> findAllByTagsContaining(Tag tag, Pageable pageable);
}
