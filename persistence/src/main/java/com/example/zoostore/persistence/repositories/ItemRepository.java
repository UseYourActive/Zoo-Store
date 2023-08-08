package com.example.zoostore.persistence.repositories;

import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    Page<Item> findAllByArchivedAndTagsContaining(Boolean archived, Tag tag, Pageable pageable);
    Page<Item> findAllByTagsContaining(Tag tag, Pageable pageable);
    Page<Item> findItemsByProductNameContainingAndArchived(String productName, Boolean archived, Pageable pageable);
    Page<Item> findItemsByTagsContainingAndArchived(Tag tag, Boolean archived, Pageable pageable);
    @Query(value = """
            SELECT *
            FROM items i
            WHERE i.productName REGEXP :regex
            ORDER BY title ASC
            """, nativeQuery = true)
    Page<Item> findAllByPartialProductName(String regex, Pageable pageable);
}
