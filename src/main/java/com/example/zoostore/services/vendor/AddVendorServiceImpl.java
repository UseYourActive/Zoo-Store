package com.example.zoostore.services.vendor;

import com.example.zoostore.api.operations.vendor.create.AddVendorService;
import com.example.zoostore.api.operations.vendor.create.CreateNewVendorRequest;
import com.example.zoostore.api.operations.vendor.create.CreateNewVendorResponse;
import com.example.zoostore.data.entities.Vendor;
import com.example.zoostore.data.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddVendorServiceImpl implements AddVendorService {
    private final VendorRepository vendorRepository;

    @Override
    public CreateNewVendorResponse addVendor(CreateNewVendorRequest request) {
        Vendor vendor = Vendor.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .build();

        Vendor save = vendorRepository.save(vendor);

        return CreateNewVendorResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .phone(save.getPhone())
                .build();
    }
}
