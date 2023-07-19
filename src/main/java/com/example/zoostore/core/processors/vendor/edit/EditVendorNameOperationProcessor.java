package com.example.zoostore.core.processors.vendor.edit;

import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameRequest;
import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameResponse;
import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameOperation;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.VendorRepository;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EditVendorNameOperationProcessor implements EditVendorNameOperation {
    private final VendorRepository vendorRepository;

    @Override
    public EditVendorNameResponse process(EditVendorNameRequest editVendorNameRequest) {
        Vendor foundInRepo = vendorRepository.findById(editVendorNameRequest.getVendorId())
                .orElseThrow(VendorNotFoundInRepositoryException::new);

        foundInRepo.setName(editVendorNameRequest.getName());

        Vendor save = vendorRepository.save(foundInRepo);

        return EditVendorNameResponse.builder()
                .vendorId(save.getId())
                .name(save.getName())
                .build();
    }
}
