package com.example.zoostore.core.processors.item.unarchive;

import com.example.zoostore.api.operations.item.unarchive.UnArchiveItemOperation;
import com.example.zoostore.api.operations.item.unarchive.UnArchiveItemRequest;
import com.example.zoostore.api.operations.item.unarchive.UnArchiveItemResponse;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UnArchiveItemOperationProcessor implements UnArchiveItemOperation {
    private final ItemRepository itemRepository;

    @Override
    public UnArchiveItemResponse process(UnArchiveItemRequest unArchiveItemRequest) {
        Item item = itemRepository.findById(unArchiveItemRequest.getId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        item.setArchived(false);

        Item savedItem = itemRepository.save(item);

        return UnArchiveItemResponse.builder()
                .id(savedItem.getId())
                .tagIds(savedItem.getTags().stream()
                        .map(Tag::getId)
                        .collect(Collectors.toSet()))
                .description(savedItem.getDescription())
                .multimediaIds(savedItem.getMultimedia().stream()
                        .map(Multimedia::getId)
                        .collect(Collectors.toSet()))
                .isArchived(savedItem.isArchived())
                .productName(savedItem.getProductName())
                .vendorId(savedItem.getVendor().getId())
                .build();
    }
}
