package com.example.zoostore.core.processors.vendor;

import com.example.zoostore.api.operations.vendor.create.CreateNewVendorOperation;
import com.example.zoostore.api.operations.vendor.create.CreateNewVendorRequest;
import com.example.zoostore.api.operations.vendor.create.CreateNewVendorResponse;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CreateNewVendorOperationProcessor implements CreateNewVendorOperation {
    private final VendorRepository vendorRepository;

    @Override
    public CreateNewVendorResponse process(CreateNewVendorRequest createNewVendorRequest) {
        Vendor vendor = Vendor.builder()
                .name(createNewVendorRequest.getName())
                .phone(createNewVendorRequest.getPhone())
                .items(new HashSet<>())
                .build();

        Vendor save = vendorRepository.save(vendor);

        return CreateNewVendorResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .phone(save.getPhone())
                .itemIds(save.getItems().stream()
                        .map(Item::getId)
                        .collect(Collectors.toSet()))
                .build();
    }
}
