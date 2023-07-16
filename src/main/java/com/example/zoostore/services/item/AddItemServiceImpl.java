package com.example.zoostore.services.item;

import com.example.zoostore.api.operations.item.create.CreateItemService;
import com.example.zoostore.api.operations.item.create.CreateNewItemRequest;
import com.example.zoostore.api.operations.item.create.CreateNewItemResponse;
import com.example.zoostore.data.entities.Item;
import com.example.zoostore.data.entities.Tag;
import com.example.zoostore.data.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AddItemServiceImpl implements CreateItemService {
    private final ItemRepository itemRepository;
    private final GetTagService getTagService;
    private final GetVendorService getVendorService;

    @Override
    public CreateNewItemResponse createItem(CreateNewItemRequest request) {
        Item item = Item.builder()
                .tags(getTagService.getTagsByID(request.getTags()))
                .vendor(getVendorService.getVendorsByID(request.getVendor()))
                .description(request.getDescription())
                .productName(request.getTitle())
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
