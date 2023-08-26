package com.example.zoostore.core.processors.vendor;

import com.example.zoostore.api.operations.vendor.edit.items.EditVendorItemsOperation;
import com.example.zoostore.api.operations.vendor.edit.items.EditVendorItemsRequest;
import com.example.zoostore.api.operations.vendor.edit.items.EditVendorItemsResponse;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class EditVendorItemsOperationProcessor implements EditVendorItemsOperation {
    private final VendorRepository vendorRepository;
    private final ItemRepository itemRepository;

    @Override
    public EditVendorItemsResponse process(EditVendorItemsRequest editVendorItemsRequest) {
        log.info("Starting edit vendor items operation for vendor with ID: {}", editVendorItemsRequest.getVendorId());

        Vendor vendor = vendorRepository.findById(editVendorItemsRequest.getVendorId())
                .orElseThrow(VendorNotFoundInRepositoryException::new);

        List<Item> items = itemRepository.findAllById(editVendorItemsRequest.getItemIds());

        vendor.setItems(new HashSet<>(items));

        Vendor save = vendorRepository.save(vendor);
        log.info("Vendor items edited for vendor with ID: {}", save.getId());

        return EditVendorItemsResponse.builder()
                .id(save.getId())
                .phone(save.getPhone())
                .name(save.getName())
                .itemIds(save.getItems().stream()
                        .map(Item::getId)
                        .toList())
                .build();
    }
}
