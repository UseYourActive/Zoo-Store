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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class EditVendorOperationProcessor implements EditVendorOperation {
    private final VendorRepository vendorRepository;
    private final ItemRepository itemRepository;

    @Override
    public EditVendorResponse process(final EditVendorRequest editVendorRequest) {
        log.info("Starting edit vendor operation for vendor with ID: {}", editVendorRequest.getId());

        Vendor vendor = vendorRepository.findById(UUID.fromString(editVendorRequest.getId()))
                .orElseThrow(VendorNotFoundInRepositoryException::new);

        Optional.ofNullable(editVendorRequest.getName())
                .ifPresent(name -> {
                    vendor.setName(name);
                    log.info("Vendor name updated for vendor with ID: {}", vendor.getId());
                });

        Optional.ofNullable(editVendorRequest.getPhone())
                .ifPresent(phone -> {
                    vendor.setPhone(phone);
                    log.info("Vendor phone updated for vendor with ID: {}", vendor.getId());
                });

        Optional.ofNullable(editVendorRequest.getItems())
                .ifPresent(items -> updateVendorItems(vendor, items));

        Vendor saved = this.vendorRepository.save(vendor);
        log.info("Edit vendor operation completed for vendor with ID: {}", saved.getId());

        List<String> itemIds = saved.getItems().stream()
                .map(i -> String.valueOf(i.getId()))
                .toList();

        return EditVendorResponse.builder()
                .id(String.valueOf(saved.getId()))
                .phone(saved.getPhone())
                .name(saved.getName())
                .itemIds(itemIds)
                .build();
    }

    private void updateVendorItems(Vendor vendor, final List<String> items) {
        List<UUID> itemUUIDs = items.stream()
                .map(UUID::fromString)
                .toList();

        List<Item> fetchedItems = this.itemRepository.findAllByIdIn(itemUUIDs);

        if (fetchedItems.size() != items.size()) {
            throw new ItemNotFoundInRepositoryException();
        }

        vendor.setItems(new HashSet<>(fetchedItems));
    }
}
