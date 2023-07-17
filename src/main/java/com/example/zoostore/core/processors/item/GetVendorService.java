package com.example.zoostore.core.processors.item;

import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GetVendorService {
    private final VendorRepository vendorRepository;

    public Vendor getVendorsByID(UUID request){
        return this.vendorRepository.findById(request).get();
    }
}
