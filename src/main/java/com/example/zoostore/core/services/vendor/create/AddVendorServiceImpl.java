package com.example.zoostore.core.services.vendor.create;

import com.example.zoostore.api.operations.vendor.create.AddVendorService;
import com.example.zoostore.api.operations.vendor.create.CreateNewVendorRequest;
import com.example.zoostore.api.operations.vendor.create.CreateNewVendorResponse;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.VendorRepository;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddVendorServiceImpl implements AddVendorService {
    private final VendorRepository vendorRepository;

    @Override
    public CreateNewVendorResponse process(CreateNewVendorRequest createNewVendorRequest) throws VendorNotFoundInRepositoryException {
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
