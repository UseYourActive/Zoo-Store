package com.example.zoostore.core.processors.vendor.edit;

import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneRequest;
import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneResponse;
import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneOperation;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.VendorRepository;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EditVendorPhoneOperationProcessor implements EditVendorPhoneOperation {
    private final VendorRepository vendorRepository;
    @Override
    public EditVendorPhoneResponse process(EditVendorPhoneRequest editVendorPhoneRequest) {
        Vendor foundInRepo = vendorRepository.findById(editVendorPhoneRequest.getId())
                .orElseThrow(VendorNotFoundInRepositoryException::new);

        foundInRepo.setPhone(editVendorPhoneRequest.getPhone());

        Vendor save = vendorRepository.save(foundInRepo);

        return EditVendorPhoneResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .phone(save.getPhone())
                .build();
    }
}
