package com.example.zoostore.data.repositories;

import com.example.zoostore.data.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
}
