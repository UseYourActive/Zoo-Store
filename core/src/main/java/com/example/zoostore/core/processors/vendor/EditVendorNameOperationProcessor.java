package com.example.zoostore.core.processors.vendor;

import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameRequest;
import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameResponse;
import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameOperation;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.VendorRepository;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class EditVendorNameOperationProcessor implements EditVendorNameOperation {
    private final VendorRepository vendorRepository;

    @Override
    public EditVendorNameResponse process(EditVendorNameRequest editVendorNameRequest) {
        log.info("Starting edit vendor name operation for vendor with ID: {}", editVendorNameRequest.getVendorId());

        Vendor vendor = vendorRepository.findById(editVendorNameRequest.getVendorId())
                .orElseThrow(VendorNotFoundInRepositoryException::new);

        vendor.setName(editVendorNameRequest.getName());
        vendor.setPhone(vendor.getPhone());

        Vendor save = vendorRepository.save(vendor);
        log.info("Vendor name edited for vendor with ID: {}", save.getId());

        return EditVendorNameResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .phone(save.getPhone())
                .itemIds(save.getItems().stream()
                        .map(Item::getId)
                        .toList())
                .build();
    }
}
