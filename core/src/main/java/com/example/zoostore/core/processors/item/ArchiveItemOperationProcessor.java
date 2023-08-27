package com.example.zoostore.core.processors.item;

import com.example.zoostore.api.operations.item.archive.ArchiveItemRequest;
import com.example.zoostore.api.operations.item.archive.ArchiveItemResponse;
import com.example.zoostore.api.operations.item.archive.ArchiveItemOperation;
import com.example.zoostore.core.exceptions.item.ItemIsAlreadyArchivedException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class ArchiveItemOperationProcessor implements ArchiveItemOperation {
    private final ItemRepository itemRepository;

    @Override
    public ArchiveItemResponse process(final ArchiveItemRequest request) {
        log.info("Starting archive item operation");

        Item found = itemRepository.findById(UUID.fromString(request.getItemId()))
                .orElseThrow(ItemNotFoundInRepositoryException::new);
        log.info("Found item to archive with id = {}", found.getId());

        if(found.getArchived()){
            throw new ItemIsAlreadyArchivedException();
        }

        found.setArchived(Boolean.TRUE);

        Item savedItem = itemRepository.save(found);
        log.info("Item archived successfully with id = {}", savedItem.getId());

        List<String> multimediaIds = savedItem.getMultimedia().stream()
                .map(multimedia -> String.valueOf(multimedia.getId()))
                .toList();

        List<String> tagIds = savedItem.getTags().stream()
                .map(tag -> String.valueOf(tag.getId()))
                .toList();

        ArchiveItemResponse response = ArchiveItemResponse.builder()
                .itemId(String.valueOf(savedItem.getId()))
                .vendorId(String.valueOf(savedItem.getVendor().getId()))
                .description(savedItem.getDescription())
                .multimediaIds(multimediaIds)
                .productName(savedItem.getProductName())
                .tagIds(tagIds)
                .isArchived(Boolean.TRUE)
                .build();
        log.info("Archive item operation completed");

        return response;
    }
}
