package com.example.zoostore.core.processors.vendor.create;

import com.example.zoostore.api.operations.vendor.create.CreateNewVendorOperation;
import com.example.zoostore.api.operations.vendor.create.CreateNewVendorRequest;
import com.example.zoostore.api.operations.vendor.create.CreateNewVendorResponse;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateNewVendorOperationProcessor implements CreateNewVendorOperation {
    private final VendorRepository vendorRepository;

    @Override
    public CreateNewVendorResponse process(CreateNewVendorRequest createNewVendorRequest) {
        Vendor vendor = Vendor.builder()
                .name(createNewVendorRequest.getName())
                .phone(createNewVendorRequest.getPhone())
                .build();

        Vendor save = vendorRepository.save(vendor);

        return CreateNewVendorResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .phone(save.getPhone())
                .build();
    }
}
