package com.example.zoostore.core.processors.item.archive;

import com.example.zoostore.api.operations.item.archive.ArchiveItemRequest;
import com.example.zoostore.api.operations.item.archive.ArchiveItemResponse;
import com.example.zoostore.api.operations.item.archive.ArchiveItemOperation;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArchiveItemOperationProcessor implements ArchiveItemOperation {
    private final ItemRepository itemRepository;

    @Override
    public ArchiveItemResponse process(ArchiveItemRequest request) {
        Item found = itemRepository.findById(request.getItemId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        Item item = Item.builder()
                .id(found.getId())
                .tags(found.getTags())
                .productName(found.getProductName())
                .description(found.getDescription())
                .vendor(found.getVendor())
                .multimedia(found.getMultimedia()) // moje da nqma multimedii i da stane null pointer
                .archived(true)
                .build();

        Item save = itemRepository.save(item);

        return ArchiveItemResponse.builder()
                .itemId(save.getId())
                .vendorId(save.getVendor().getId())
                .description(save.getDescription())
                //.multimedia(save.getMultimedia().stream().map(Multimedia::getId).collect(Collectors.toSet()))
                .title(save.getProductName())
                .tagIds(save.getTags().stream().map(Tag::getId).collect(Collectors.toSet()))
                .isArchived(true)
                .build();
    }
}
