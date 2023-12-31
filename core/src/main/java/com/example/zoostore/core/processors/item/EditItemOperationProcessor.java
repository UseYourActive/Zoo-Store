package com.example.zoostore.core.processors.item;

import com.example.zoostore.api.operations.item.edit.full.EditItemOperation;
import com.example.zoostore.api.operations.item.edit.full.EditItemRequest;
import com.example.zoostore.api.operations.item.edit.full.EditItemResponse;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.MultimediaRepository;
import com.example.zoostore.persistence.repositories.TagRepository;
import com.example.zoostore.persistence.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class EditItemOperationProcessor implements EditItemOperation {
    private final ItemRepository itemRepository;
    private final TagRepository tagRepository;
    private final VendorRepository vendorRepository;
    private final MultimediaRepository multimediaRepository;

    @Override
    public EditItemResponse process(final EditItemRequest editItemRequest) {
        log.info("Starting edit item operation");

        Item item = this.itemRepository.findById(UUID.fromString(editItemRequest.getId()))
                .orElseThrow(ItemNotFoundInRepositoryException::new);
        log.info("Found item to edit. ItemId: {}", item.getId());

        Optional.ofNullable(editItemRequest.getProductName())
                .ifPresent(productName -> {
                    log.info("Updating product name to: {}", productName);
                    item.setProductName(productName);
                });

        Optional.ofNullable(editItemRequest.getDescription())
                .ifPresent(description -> {
                    log.info("Updating description to: {}", description);
                    item.setDescription(description);
                });

        Optional.ofNullable(editItemRequest.getIsArchived())
                .ifPresent(isArchived -> {
                    log.info("Updating archived status to: {}", isArchived);
                    item.setArchived(isArchived);
                });

        Optional.ofNullable(editItemRequest.getVendorId())
                .ifPresent(vendorId -> {
                    log.info("Updating vendor to VendorId: {}", vendorId);
                    Vendor vendor = this.vendorRepository.findById(UUID.fromString(vendorId))
                            .orElseThrow(() -> new VendorNotFoundInRepositoryException("Vendor not found in repository!"));
                    item.setVendor(vendor);
                });

        Optional.ofNullable(editItemRequest.getMultimedia())
                .ifPresent(multimediaIds -> {
                    Set<Multimedia> multimediaSet = multimediaIds.stream()
                            .map(UUID::fromString)
                            .map(multimediaRepository::findMultimediaById)
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .collect(Collectors.toSet());
                    log.info("Updating multimedia");
                    item.setMultimedia(multimediaSet);
                });

        Optional.ofNullable(editItemRequest.getTags())
                .ifPresent(tagIds -> {
                    Set<Tag> tagSet = tagIds.stream()
                            .map(UUID::fromString)
                            .map(tagRepository::findTagById)
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .collect(Collectors.toSet());
                    log.info("Updating tags");
                    item.setTags(tagSet);
                });

        Item savedItem = this.itemRepository.save(item);
        log.info("Item edited successfully. ItemId: {}", savedItem.getId());

        EditItemResponse response = buildEditItemResponse(savedItem);
        log.info("Edit item operation completed");

        return response;
    }

    private EditItemResponse buildEditItemResponse(Item item) {
        List<String> multimediaIds = item.getMultimedia().stream()
                .map(multimedia -> String.valueOf(multimedia.getId()))
                .toList();

        List<String> tagIds = item.getTags().stream()
                .map(tag -> String.valueOf(tag.getId()))
                .toList();

        return EditItemResponse.builder()
                .itemId(String.valueOf(item.getId()))
                .productName(item.getProductName())
                .description(item.getDescription())
                .vendorId(String.valueOf(item.getVendor().getId()))
                .multimediaIds(multimediaIds)
                .tagIds(tagIds)
                .isArchived(item.getArchived())
                .build();
    }
}
