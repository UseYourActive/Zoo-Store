package com.example.zoostore.persistence.repositories;

import com.example.zoostore.persistence.entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VendorRepository  extends JpaRepository<Vendor, UUID> {
}
