package com.example.zoostore.core.services.vendor.edit;

import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameRequest;
import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameResponse;
import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameService;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.VendorRepository;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EditVendorNameServiceImpl implements EditVendorNameService {
    private final VendorRepository vendorRepository;

    @Override
    public EditVendorNameResponse process(EditVendorNameRequest editVendorNameRequest) throws VendorNotFoundInRepositoryException {
        Vendor foundInRepo = findById(editVendorNameRequest.getId());

        foundInRepo.setName(editVendorNameRequest.getName());

        Vendor save = vendorRepository.save(foundInRepo);

        return EditVendorNameResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .phone(save.getPhone())
                .build();
    }

    private Vendor findById(UUID id) throws VendorNotFoundInRepositoryException {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new VendorNotFoundInRepositoryException(HttpStatus.NOT_FOUND));
    }
}
