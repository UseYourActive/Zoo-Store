package com.example.zoostore.core.processors.item.create;

import com.example.zoostore.api.operations.item.create.CreateNewItemOperation;
import com.example.zoostore.api.operations.item.create.CreateNewItemRequest;
import com.example.zoostore.api.operations.item.create.CreateNewItemResponse;
import com.example.zoostore.core.exceptions.tag.TagNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.TagRepository;
import com.example.zoostore.persistence.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CreateNewItemOperationProcessor implements CreateNewItemOperation {
    private final ItemRepository itemRepository;
    private final TagRepository tagRepository;
    private final VendorRepository vendorRepository;

    @Override
    public CreateNewItemResponse process(CreateNewItemRequest createNewItemRequest) {
        Vendor vendor = vendorRepository.findVendorById(createNewItemRequest.getVendorId())
                .orElseThrow(() -> new VendorNotFoundInRepositoryException("Vendor not found while creating a new item!"));

        Set<Tag> tags = tagRepository.findAllByIdIn(createNewItemRequest.getTagIds());

        if(tags.size() != createNewItemRequest.getTagIds().size()){ //TODO ruchen fetch ot bazata findbyid na vseki tak i da se natrupat nesushtestvuvashtite ID-ta v list
            throw new TagNotFoundInRepositoryException();
        }

        Item item = Item.builder()
                .tags(tags)
                .vendor(vendor)
                .description(createNewItemRequest.getDescription())
                .productName(createNewItemRequest.getProductName())
                .build();

        Item save = itemRepository.save(item);

        return CreateNewItemResponse.builder()
                .itemId(save.getId())
                .vendorId(save.getVendor().getId())
                .description(save.getDescription())
                //.multimedia(save.getMultimedia().stream().map(Multimedia::getId).collect(Collectors.toSet())) null pointer because URLs can't be attached to a new item immediately
                .title(save.getProductName())
                .tagIds(save.getTags().stream().map(Tag::getId).collect(Collectors.toSet()))
                .build();
    }
}
