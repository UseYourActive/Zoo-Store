package com.example.zoostore.core.processors.vendor;

import com.example.zoostore.api.operations.vendor.find.byid.FindVendorByIdOperation;
import com.example.zoostore.api.operations.vendor.find.byid.FindVendorByIdRequest;
import com.example.zoostore.api.operations.vendor.find.byid.FindVendorByIdResponse;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindVendorByIdOperationProcessor implements FindVendorByIdOperation {
    private final VendorRepository vendorRepository;

    @Override
    public FindVendorByIdResponse process(final FindVendorByIdRequest findVendorByIdRequest) {
        log.info("Starting find vendor by ID operation for vendor ID: {}", findVendorByIdRequest.getId());

        Vendor vendor = vendorRepository.findById(UUID.fromString(findVendorByIdRequest.getId()))
                .orElseThrow(VendorNotFoundInRepositoryException::new);
        log.info("Find vendor by ID operation completed for vendor ID: {}", findVendorByIdRequest.getId());

        List<String> itemIds = vendor.getItems().stream()
                .map(i -> String.valueOf(i.getId()))
                .toList();

        return FindVendorByIdResponse.builder()
                .id(String.valueOf(vendor.getId()))
                .name(vendor.getName())
                .phone(vendor.getPhone())
                .itemIds(itemIds)
                .build();
    }
}
