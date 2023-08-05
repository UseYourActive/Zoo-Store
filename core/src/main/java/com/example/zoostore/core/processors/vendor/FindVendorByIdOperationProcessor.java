package com.example.zoostore.core.processors.vendor;

import com.example.zoostore.api.operations.vendor.find.byid.FindVendorByIdOperation;
import com.example.zoostore.api.operations.vendor.find.byid.FindVendorByIdRequest;
import com.example.zoostore.api.operations.vendor.find.byid.FindVendorByIdResponse;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindVendorByIdOperationProcessor implements FindVendorByIdOperation {
    private final VendorRepository vendorRepository;

    @Override
    public FindVendorByIdResponse process(FindVendorByIdRequest findVendorByIdRequest) {
        Vendor vendor = vendorRepository.findById(findVendorByIdRequest.getId())
                .orElseThrow(VendorNotFoundInRepositoryException::new);

        return FindVendorByIdResponse.builder()
                .id(vendor.getId())
                .name(vendor.getName())
                .phone(vendor.getPhone())
                .itemIds(vendor.getItems().stream()
                        .map(Item::getId)
                        .collect(Collectors.toSet()))
                .build();
    }
}
