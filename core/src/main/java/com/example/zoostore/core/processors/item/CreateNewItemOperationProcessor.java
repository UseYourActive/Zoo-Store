package com.example.zoostore.core.processors.item;

import com.example.zoostore.api.operations.item.create.CreateNewItemOperation;
import com.example.zoostore.api.operations.item.create.CreateNewItemRequest;
import com.example.zoostore.api.operations.item.create.CreateNewItemResponse;
import com.example.zoostore.core.exceptions.tag.TagNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.TagRepository;
import com.example.zoostore.persistence.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class CreateNewItemOperationProcessor implements CreateNewItemOperation {
    private final ItemRepository itemRepository;
    private final TagRepository tagRepository;
    private final VendorRepository vendorRepository;

    @Override
    public CreateNewItemResponse process(final CreateNewItemRequest createNewItemRequest) {
        log.info("Starting create new item operation");

        Vendor vendor = vendorRepository.findById(UUID.fromString(createNewItemRequest.getVendorId()))
                .orElseThrow(() -> new VendorNotFoundInRepositoryException("Vendor not found while creating a new item!"));
        log.info("Found vendor for new item. VendorId: {}", vendor.getId());

        Set<Tag> tags = tagRepository.findAllByIdIn(createNewItemRequest.getTagIds().stream()
                .map(UUID::fromString)
                .collect(Collectors.toSet()));

        if(tags.size() != createNewItemRequest.getTagIds().size()) {
            log.error("Not all tags were found in the repository");
            throw new TagNotFoundInRepositoryException();
        }
        log.info("All tags were found in the repository");

        Item item = Item.builder()
                .tags(tags)
                .multimedia(new HashSet<>())
                .vendor(vendor)
                .description(createNewItemRequest.getDescription())
                .productName(createNewItemRequest.getProductName())
                .build();

        Item savedItem = itemRepository.save(item);
        log.info("New item created. ItemId: {}", savedItem.getId());

        List<String> tagIds = savedItem.getTags().stream()
                .map(tag -> String.valueOf(tag.getId()))
                .toList();

        CreateNewItemResponse response = CreateNewItemResponse.builder()
                .itemId(String.valueOf(savedItem.getId()))
                .vendorId(String.valueOf(savedItem.getVendor().getId()))
                .description(savedItem.getDescription())
                .productName(savedItem.getProductName())
                .tagIds(tagIds)
                .multimediaIds(new ArrayList<>())
                .isArchived(savedItem.getArchived())
                .build();
        log.info("Create new item operation completed");

        return response;
    }
}
