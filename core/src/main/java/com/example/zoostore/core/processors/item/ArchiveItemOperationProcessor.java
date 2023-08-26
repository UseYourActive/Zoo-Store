package com.example.zoostore.core.processors.item;

import com.example.zoostore.api.operations.item.archive.ArchiveItemRequest;
import com.example.zoostore.api.operations.item.archive.ArchiveItemResponse;
import com.example.zoostore.api.operations.item.archive.ArchiveItemOperation;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class ArchiveItemOperationProcessor implements ArchiveItemOperation {
    private final ItemRepository itemRepository;

    @Override
    public ArchiveItemResponse process(ArchiveItemRequest request) {
        log.info("Starting archive item operation");

        Item found = itemRepository.findById(request.getItemId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);
        log.info("Found item to archive with id = {}", found.getId());

        found.setArchived(true);

        Item savedItem = itemRepository.save(found);
        log.info("Item archived successfully with id = {}", savedItem.getId());

        ArchiveItemResponse response = ArchiveItemResponse.builder()
                .itemId(savedItem.getId())
                .vendorId(savedItem.getVendor().getId())
                .description(savedItem.getDescription())
                .multimediaIds(savedItem.getMultimedia().stream()
                        .map(Multimedia::getId)
                        .toList())
                .productName(savedItem.getProductName())
                .tagIds(savedItem.getTags().stream()
                        .map(Tag::getId)
                        .toList())
                .isArchived(true)
                .build();
        log.info("Archive item operation completed");

        return response;
    }
}
