package com.example.zoostore.services.vendor;

import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameRequest;
import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameResponse;
import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneRequest;
import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneResponse;
import com.example.zoostore.api.operations.vendor.edit.EditVendorService;
import com.example.zoostore.data.entities.Vendor;
import com.example.zoostore.data.repositories.VendorRepository;
import com.example.zoostore.exceptions.vendor.VendorNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EditVendorServiceImpl implements EditVendorService {
    private final VendorRepository vendorRepository;

    @Override
    public EditVendorNameResponse editVendorName(EditVendorNameRequest request) throws VendorNotFoundInRepositoryException {
        Vendor foundInRepo = findById(request.getId());

        foundInRepo.setName(request.getName());

        Vendor save = vendorRepository.save(foundInRepo);

        return EditVendorNameResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .phone(save.getPhone())
                .build();
    }

    @Override
    public EditVendorPhoneResponse editVendorPhone(EditVendorPhoneRequest request) throws VendorNotFoundInRepositoryException {
        Vendor foundInRepo = findById(request.getId());

        foundInRepo.setPhone(request.getPhone());

        Vendor save = vendorRepository.save(foundInRepo);

        return EditVendorPhoneResponse.builder()
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
