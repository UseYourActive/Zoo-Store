package com.example.zoostore.core.processors.item.create;

import com.example.zoostore.api.operations.item.create.CreateNewItemOperation;
import com.example.zoostore.api.operations.item.create.CreateNewItemRequest;
import com.example.zoostore.api.operations.item.create.CreateNewItemResponse;
import com.example.zoostore.core.processors.item.GetTagService;
import com.example.zoostore.core.processors.item.GetVendorService;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CreateNewItemOperationProcessor implements CreateNewItemOperation {
    private final ItemRepository itemRepository;
    private final GetTagService getTagService;
    private final GetVendorService getVendorService;

    @Override
    public CreateNewItemResponse process(CreateNewItemRequest createNewItemRequest) {
        Item item = Item.builder()
                .tags(getTagService.getTagsByID(createNewItemRequest.getTags()))
                .vendor(getVendorService.getVendorsByID(createNewItemRequest.getVendor()))
                .description(createNewItemRequest.getDescription())
                .productName(createNewItemRequest.getTitle())
                .build();

        Item save = itemRepository.save(item);

        return CreateNewItemResponse.builder()
                .id(save.getId())
                .vendor(save.getVendor().getId())
                .description(save.getDescription())
                //.multimedia(save.getMultimedia().stream().map(Multimedia::getId).collect(Collectors.toSet())) null pointer because URLs can't be attached to a new item immediately
                .title(save.getProductName())
                .tags(save.getTags().stream().map(Tag::getId).collect(Collectors.toSet()))
                .build();
    }
}
