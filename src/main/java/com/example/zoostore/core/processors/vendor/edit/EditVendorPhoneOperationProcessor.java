package com.example.zoostore.core.processors.vendor.edit;

import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneRequest;
import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneResponse;
import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneOperation;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.VendorRepository;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditVendorPhoneOperationProcessor implements EditVendorPhoneOperation {
    private final VendorRepository vendorRepository;
    @Override
    public EditVendorPhoneResponse process(EditVendorPhoneRequest editVendorPhoneRequest) {
        Vendor foundInRepo = vendorRepository.findById(editVendorPhoneRequest.getVendorId())
                .orElseThrow(VendorNotFoundInRepositoryException::new);

        foundInRepo.setPhone(editVendorPhoneRequest.getPhone());

        Vendor save = vendorRepository.save(foundInRepo);

        return EditVendorPhoneResponse.builder()
                .vendorId(save.getId())
                .phone(save.getPhone())
                .build();
    }
}
