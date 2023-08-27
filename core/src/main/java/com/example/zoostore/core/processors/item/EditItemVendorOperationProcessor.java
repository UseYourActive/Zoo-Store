package com.example.zoostore.core.processors.item;

import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorRequest;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorResponse;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorOperation;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class EditItemVendorOperationProcessor implements EditItemVendorOperation {
    private final ItemRepository itemRepository;
    private final VendorRepository vendorRepository;

    @Override
    public EditItemVendorResponse process(final EditItemVendorRequest editItemVendorRequest) {
        log.info("Starting edit item vendor operation");

        Item itemFoundInRepository = itemRepository.findById(UUID.fromString(editItemVendorRequest.getItemId()))
                .orElseThrow(ItemNotFoundInRepositoryException::new);
        log.info("Found item for vendor editing. ItemId: {}", itemFoundInRepository.getId());

        Vendor vendor = vendorRepository.findById(UUID.fromString(editItemVendorRequest.getVendorId()))
                .orElseThrow(VendorNotFoundInRepositoryException::new);
        log.info("Updating vendor to VendorId: {}", vendor.getId());

        itemFoundInRepository.setVendor(vendor);

        Item savedItem = itemRepository.save(itemFoundInRepository);
        log.info("Vendor edited for item. ItemId: {}", savedItem.getId());

        List<String> multimediaIds = savedItem.getMultimedia().stream()
                .map(multimedia -> String.valueOf(multimedia.getId()))
                .toList();

        List<String> tagIds = savedItem.getTags().stream()
                .map(tag -> String.valueOf(tag.getId()))
                .toList();

        EditItemVendorResponse response = EditItemVendorResponse.builder()
                .itemId(String.valueOf(savedItem.getId()))
                .productName(savedItem.getProductName())
                .isArchived(savedItem.getArchived())
                .tagIds(tagIds)
                .vendorId(String.valueOf(savedItem.getVendor().getId()))
                .multimediaIds(multimediaIds)
                .description(savedItem.getDescription())
                .build();
        log.info("Edit item vendor operation completed");

        return response;
    }
}
