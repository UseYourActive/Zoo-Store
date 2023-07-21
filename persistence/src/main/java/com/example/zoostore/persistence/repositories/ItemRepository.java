package com.example.zoostore.persistence.repositories;

import com.example.zoostore.persistence.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    Optional<Item> findItemById(UUID id);
    List<Item> findItemsByProductName(String productName);
}
