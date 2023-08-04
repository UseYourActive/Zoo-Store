package com.example.zoostore.core.processors.vendor;

import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameRequest;
import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameResponse;
import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameOperation;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.VendorRepository;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EditVendorNameOperationProcessor implements EditVendorNameOperation {
    private final VendorRepository vendorRepository;

    @Override
    public EditVendorNameResponse process(EditVendorNameRequest editVendorNameRequest) {
        Vendor vendor = vendorRepository.findVendorById(editVendorNameRequest.getVendorId())
                .orElseThrow(VendorNotFoundInRepositoryException::new);

        vendor.setName(editVendorNameRequest.getName());
        vendor.setPhone(vendor.getPhone());

        Vendor save = vendorRepository.save(vendor);

        return EditVendorNameResponse.builder()
                .vendorId(save.getId())
                .vendorName(save.getName())
                .phone(save.getPhone())
                .build();
    }
}
