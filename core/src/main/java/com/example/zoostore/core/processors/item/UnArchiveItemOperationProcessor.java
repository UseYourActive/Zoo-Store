package com.example.zoostore.core.processors.item;

import com.example.zoostore.api.operations.item.unarchive.UnArchiveItemOperation;
import com.example.zoostore.api.operations.item.unarchive.UnArchiveItemRequest;
import com.example.zoostore.api.operations.item.unarchive.UnArchiveItemResponse;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class UnArchiveItemOperationProcessor implements UnArchiveItemOperation {
    private final ItemRepository itemRepository;

    @Override
    public UnArchiveItemResponse process(UnArchiveItemRequest unArchiveItemRequest) {
        log.info("Starting unarchive item operation");

        Item item = itemRepository.findById(unArchiveItemRequest.getId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);
        log.info("Item found in repository with ID: {}", item.getId());

        item.setArchived(false);

        Item save = itemRepository.save(item);
        log.info("Item successfully unarchived with ID: {}", save.getId());

        UnArchiveItemResponse response = UnArchiveItemResponse.builder()
                .itemId(save.getId())
                .vendorId(save.getVendor().getId())
                .description(save.getDescription())
                .multimediaIds(save.getMultimedia().stream()
                        .map(Multimedia::getId)
                        .toList())
                .productName(save.getProductName())
                .tagIds(save.getTags().stream()
                        .map(Tag::getId)
                        .toList())
                .isArchived(false)
                .build();
        log.info("Unarchive item operation completed");

        return response;
    }
}
