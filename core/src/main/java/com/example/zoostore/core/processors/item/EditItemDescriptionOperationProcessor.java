package com.example.zoostore.core.processors.item;

import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionRequest;
import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionResponse;
import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionOperation;
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
public class EditItemDescriptionOperationProcessor implements EditItemDescriptionOperation {
    private final ItemRepository itemRepository;

    @Override
    public EditItemDescriptionResponse process(EditItemDescriptionRequest editItemDescriptionRequest) {
        log.info("Starting edit item description operation");

        Item itemFoundInRepository = itemRepository.findById(editItemDescriptionRequest.getItemId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);
        log.info("Found item to edit description. ItemId: {}", itemFoundInRepository.getId());

        itemFoundInRepository.setDescription(editItemDescriptionRequest.getDescription());

        Item savedItem = itemRepository.save(itemFoundInRepository);
        log.info("Item description edited successfully. ItemId: {}", savedItem.getId());

        EditItemDescriptionResponse response = EditItemDescriptionResponse.builder()
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
        log.info("Edit item description operation completed");

        return response;
    }
}
