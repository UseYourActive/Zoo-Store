package com.example.zoostore.core.processors.item;

import com.example.zoostore.api.operations.item.edit.tag.EditItemTagRequest;
import com.example.zoostore.api.operations.item.edit.tag.EditItemTagResponse;
import com.example.zoostore.api.operations.item.edit.tag.EditItemTagOperation;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class EditItemTagOperationProcessor implements EditItemTagOperation {
    private final ItemRepository itemRepository;
    private final TagRepository tagRepository;

    @Override
    public EditItemTagResponse process(EditItemTagRequest editItemTagRequest) {
        log.info("Starting edit item tag operation");

        Item itemFoundInRepository = itemRepository.findById(UUID.fromString(editItemTagRequest.getItemId()))
                .orElseThrow(ItemNotFoundInRepositoryException::new);
        log.info("Found item for tag editing. ItemId: {}", itemFoundInRepository.getId());

        Set<Tag> tags = this.tagRepository.findAllByIdIn(editItemTagRequest.getTagIds().stream()
                .map(UUID::fromString)
                .collect(Collectors.toSet()));

        itemFoundInRepository.setTags(tags);

        Item savedItem = itemRepository.save(itemFoundInRepository);
        log.info("Tags edited for item. ItemId: {}", savedItem.getId());

        List<String> multimediaIds = savedItem.getMultimedia().stream()
                .map(multimedia -> String.valueOf(multimedia.getId()))
                .toList();

        List<String> tagIds = savedItem.getTags().stream()
                .map(tag -> String.valueOf(tag.getId()))
                .toList();

        EditItemTagResponse response = EditItemTagResponse.builder()
                .itemId(String.valueOf(savedItem.getId()))
                .productName(savedItem.getProductName())
                .isArchived(savedItem.getArchived())
                .tagIds(tagIds)
                .vendorId(String.valueOf(savedItem.getVendor().getId()))
                .multimediaIds(multimediaIds)
                .description(savedItem.getDescription())
                .build();
        log.info("Edit item tag operation completed");

        return response;
    }
}
