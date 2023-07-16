package com.example.zoostore.data.repositories;

import com.example.zoostore.data.entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VendorRepository  extends JpaRepository<Vendor, UUID> {
}
