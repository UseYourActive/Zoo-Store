package com.example.zoostore.core.processors.vendor;

import com.example.zoostore.api.operations.vendor.create.CreateNewVendorOperation;
import com.example.zoostore.api.operations.vendor.create.CreateNewVendorRequest;
import com.example.zoostore.api.operations.vendor.create.CreateNewVendorResponse;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CreateNewVendorOperationProcessor implements CreateNewVendorOperation {
    private final VendorRepository vendorRepository;

    @Override
    public CreateNewVendorResponse process(final CreateNewVendorRequest createNewVendorRequest) {
        log.info("Starting create new vendor operation for vendor: {}", createNewVendorRequest.getName());

        Vendor vendor = Vendor.builder()
                .name(createNewVendorRequest.getName())
                .phone(createNewVendorRequest.getPhone())
                .items(new HashSet<>())
                .build();

        Vendor save = vendorRepository.save(vendor);
        log.info("Vendor created with ID: {}, Name: {}", save.getId(), save.getName());

        List<String> itemIds = save.getItems().stream()
                .map(i -> String.valueOf(i.getId()))
                .toList();

        return CreateNewVendorResponse.builder()
                .id(String.valueOf(save.getId()))
                .name(save.getName())
                .phone(save.getPhone())
                .itemIds(itemIds)
                .build();
    }
}
