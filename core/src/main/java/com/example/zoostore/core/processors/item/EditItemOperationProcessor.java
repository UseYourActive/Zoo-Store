package com.example.zoostore.core.processors.item;

import com.example.zoostore.api.operations.item.edit.full.EditItemOperation;
import com.example.zoostore.api.operations.item.edit.full.EditItemRequest;
import com.example.zoostore.api.operations.item.edit.full.EditItemResponse;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.MultimediaRepository;
import com.example.zoostore.persistence.repositories.TagRepository;
import com.example.zoostore.persistence.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EditItemOperationProcessor implements EditItemOperation {
    private final ItemRepository itemRepository;
    private final TagRepository tagRepository;
    private final VendorRepository vendorRepository;
    private final MultimediaRepository multimediaRepository;

    @Override
    public EditItemResponse process(final EditItemRequest editItemRequest) {
        Item item = this.itemRepository.findById(UUID.fromString(editItemRequest.getId()))
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        Optional.ofNullable(editItemRequest.getProductName())
                .ifPresent(item::setProductName);

        Optional.ofNullable(editItemRequest.getDescription())
                .ifPresent(item::setDescription);

        Optional.ofNullable(editItemRequest.getIsArchived())
                .ifPresent(item::setArchived);

        Optional.ofNullable(editItemRequest.getVendorId())
                .ifPresent(vendorId -> {
                    Vendor vendor = this.vendorRepository.findById(vendorId)
                            .orElseThrow(() -> new VendorNotFoundInRepositoryException("Vendor not found in repository!"));
                    item.setVendor(vendor);
                });

        Optional.ofNullable(editItemRequest.getMultimedia())
                .map(multimediaRepository::findAllByIdIn)
                .ifPresent(item::setMultimedia);

        Optional.ofNullable(editItemRequest.getTags())
                .map(tagRepository::findAllByIdIn)
                .ifPresent(item::setTags);

        Item save = this.itemRepository.save(item);

        return buildEditItemResponse(save);
    }

    private EditItemResponse buildEditItemResponse(Item item) {
        return EditItemResponse.builder()
                .itemId(item.getId())
                .productName(item.getProductName())
                .description(item.getDescription())
                .vendorId(item.getVendor().getId())
                .multimediaIds(item.getMultimedia().stream()
                        .map(Multimedia::getId)
                        .collect(Collectors.toSet()))
                .tagIds(item.getTags().stream()
                        .map(Tag::getId)
                        .collect(Collectors.toSet()))
                .isArchived(item.getArchived())
                .build();
    }
}
