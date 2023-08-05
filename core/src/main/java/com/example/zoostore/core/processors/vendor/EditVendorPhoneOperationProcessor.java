package com.example.zoostore.core.processors.vendor;

import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneRequest;
import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneResponse;
import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneOperation;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.VendorRepository;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EditVendorPhoneOperationProcessor implements EditVendorPhoneOperation {
    private final VendorRepository vendorRepository;

    @Override
    public EditVendorPhoneResponse process(EditVendorPhoneRequest editVendorPhoneRequest) {
        Vendor vendor = vendorRepository.findById(editVendorPhoneRequest.getVendorId())
                .orElseThrow(VendorNotFoundInRepositoryException::new);

        vendor.setPhone(editVendorPhoneRequest.getPhone());
        vendor.setName(vendor.getName());

        Vendor save = vendorRepository.save(vendor);

        return EditVendorPhoneResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .phone(save.getPhone())
                .itemIds(save.getItems().stream()
                        .map(Item::getId)
                        .collect(Collectors.toSet()))
                .build();
    }
}
