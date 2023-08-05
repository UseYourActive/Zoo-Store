package com.example.zoostore.persistence.repositories;

import com.example.zoostore.persistence.entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface VendorRepository  extends JpaRepository<Vendor, UUID> {
    Set<Vendor> findVendorsByIdIn(Set<UUID> id);
    List<Vendor> findVendorsByName(String name);
    Optional<Vendor> findVendorByPhone(String phone);
}
