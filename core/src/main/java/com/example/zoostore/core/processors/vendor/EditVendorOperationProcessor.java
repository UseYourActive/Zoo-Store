package com.example.zoostore.core.processors.vendor;

import com.example.zoostore.api.operations.vendor.edit.full.EditVendorOperation;
import com.example.zoostore.api.operations.vendor.edit.full.EditVendorRequest;
import com.example.zoostore.api.operations.vendor.edit.full.EditVendorResponse;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EditVendorOperationProcessor implements EditVendorOperation {
    private final VendorRepository vendorRepository;
    private final ItemRepository itemRepository;

    @Override
    public EditVendorResponse process(final EditVendorRequest editVendorRequest) {
        Vendor vendor = vendorRepository.findById(editVendorRequest.getId())
                .orElseThrow(VendorNotFoundInRepositoryException::new);

        Optional.ofNullable(editVendorRequest.getName())
                .ifPresent(vendor::setName);

        Optional.ofNullable(editVendorRequest.getPhone())
                .ifPresent(vendor::setPhone);

        Optional.ofNullable(editVendorRequest.getItems())
                .ifPresent(items -> updateVendorItems(vendor, editVendorRequest.getItems()));

        Vendor saved = this.vendorRepository.save(vendor);

        return EditVendorResponse.builder()
                .id(saved.getId())
                .phone(saved.getPhone())
                .name(saved.getName())
                .itemIds(saved.getItems().stream()
                        .map(Item::getId)
                        .toList())
                .build();
    }

    private void updateVendorItems(Vendor vendor, final Set<UUID> items) {
        List<Item> fetchedItems = this.itemRepository.findAllById(items);

        if (fetchedItems.size() != items.size()) {
            throw new ItemNotFoundInRepositoryException();
        }

        vendor.setItems(new HashSet<>(fetchedItems));
    }
}
