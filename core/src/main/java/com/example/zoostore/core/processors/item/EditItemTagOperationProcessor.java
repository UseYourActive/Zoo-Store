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

import java.util.Set;
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

        Item itemFoundInRepository = itemRepository.findById(editItemTagRequest.getItemId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);
        log.info("Found item for tag editing. ItemId: {}", itemFoundInRepository.getId());

        Set<Tag> tags = tagRepository.findAllByIdIn(editItemTagRequest.getTagIds());

        itemFoundInRepository.setTags(tags);

        Item savedItem = itemRepository.save(itemFoundInRepository);
        log.info("Tags edited for item. ItemId: {}", savedItem.getId());

        EditItemTagResponse response = EditItemTagResponse.builder()
                .itemId(savedItem.getId())
                .productName(savedItem.getProductName())
                .isArchived(savedItem.getArchived())
                .tagIds(savedItem.getTags().stream()
                        .map(Tag::getId)
                        .toList())
                .vendorId(savedItem.getVendor().getId())
                .multimediaIds(savedItem.getMultimedia().stream()
                        .map(Multimedia::getId)
                        .toList())
                .description(savedItem.getDescription())
                .build();
        log.info("Edit item tag operation completed");

        return response;
    }
}
