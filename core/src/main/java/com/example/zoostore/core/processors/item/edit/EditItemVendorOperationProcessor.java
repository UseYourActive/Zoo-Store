package com.example.zoostore.core.processors.item.edit;

import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorRequest;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorResponse;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorOperation;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EditItemVendorOperationProcessor implements EditItemVendorOperation {
    private final ItemRepository itemRepository;
    private final VendorRepository vendorRepository;

    @Override
    public EditItemVendorResponse process(EditItemVendorRequest editItemVendorRequest) {
        Item itemFoundInRepository = itemRepository.findItemById(editItemVendorRequest.getVendorId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        Optional<Vendor> vendorOptional = vendorRepository.findVendorById(editItemVendorRequest.getItemId());

        Vendor vendor = vendorOptional
                .orElseThrow(VendorNotFoundInRepositoryException::new);

        itemFoundInRepository.setVendor(vendor);

        Item savedItem = itemRepository.save(itemFoundInRepository);

        return EditItemVendorResponse.builder()
                .itemId(savedItem.getId())
                .vendorId(savedItem.getVendor().getId())
                .build();
    }
}