package com.example.zoostore.core.processors.item;

import com.example.zoostore.api.operations.item.find.byid.FindItemByIdOperation;
import com.example.zoostore.api.operations.item.find.byid.FindItemByIdRequest;
import com.example.zoostore.api.operations.item.find.byid.FindItemByIdResponse;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindItemByIdOperationProcessor implements FindItemByIdOperation {
    private final ItemRepository itemRepository;

    @Override
    public FindItemByIdResponse process(FindItemByIdRequest findItemByIdRequest) {
        log.info("Starting find item by ID operation");

        Item item = this.itemRepository.findById(findItemByIdRequest.getId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);
        log.info("Found item with ID: {}", item.getId());

        FindItemByIdResponse response = FindItemByIdResponse.builder()
                .itemId(item.getId())
                .productName(item.getProductName())
                .isArchived(item.getArchived())
                .tagIds(item.getTags().stream()
                        .map(Tag::getId)
                        .toList())
                .vendorId(item.getVendor().getId())
                .multimediaIds(item.getMultimedia().stream()
                        .map(Multimedia::getId)
                        .toList())
                .description(item.getDescription())
                .build();
        log.info("Find item by ID operation completed");

        return response;
    }
}
