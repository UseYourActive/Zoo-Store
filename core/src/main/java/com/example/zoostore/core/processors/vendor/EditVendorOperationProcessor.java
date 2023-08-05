package com.example.zoostore.core.processors.vendor;

import com.example.zoostore.api.operations.vendor.full.EditVendorOperation;
import com.example.zoostore.api.operations.vendor.full.EditVendorRequest;
import com.example.zoostore.api.operations.vendor.full.EditVendorResponse;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EditVendorOperationProcessor implements EditVendorOperation {
    private final VendorRepository vendorRepository;
    private final ItemRepository itemRepository;

    @Override
    public EditVendorResponse process(EditVendorRequest editVendorRequest) {
        Vendor vendor = vendorRepository.findById(editVendorRequest.getId())
                .orElseThrow(VendorNotFoundInRepositoryException::new);

        if (editVendorRequest.getName() != null) {
            vendor.setName(editVendorRequest.getName());
        }

        if (editVendorRequest.getPhone() != null) {
            vendor.setPhone(editVendorRequest.getPhone());
        }

        if (editVendorRequest.getItems() != null) {

            List<UUID> itemIds = editVendorRequest.getItems().stream()
                    .toList();
            List<Item> items = this.itemRepository.findAllById(itemIds);

            if (items.size() != editVendorRequest.getItems().size()) {
                throw new ItemNotFoundInRepositoryException();
            }

            vendor.setItems(new HashSet<>(items));
        }

        Vendor saved = this.vendorRepository.save(vendor);

        return EditVendorResponse.builder()
                .id(saved.getId())
                .phone(saved.getPhone())
                .name(saved.getName())
                .itemIds(saved.getItems().stream()
                        .map(Item::getId)
                        .collect(Collectors.toSet()))
                .build();
    }
}
