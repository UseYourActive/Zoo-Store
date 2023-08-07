package com.example.zoostore.core.processors.item;

import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionResponse;
import com.example.zoostore.api.operations.item.edit.product.name.EditItemProductNameRequest;
import com.example.zoostore.api.operations.item.edit.product.name.EditItemProductNameResponse;
import com.example.zoostore.api.operations.item.edit.product.name.EditItemProductNameOperation;
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
public class EditItemProductNameOperationProcessor implements EditItemProductNameOperation {
    private final ItemRepository itemRepository;

    @Override
    public EditItemProductNameResponse process(EditItemProductNameRequest editItemProductNameRequest) {
        Item itemFoundInRepository = itemRepository.findById(editItemProductNameRequest.getItemId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        itemFoundInRepository.setProductName(editItemProductNameRequest.getProductName());

        Item savedItem = itemRepository.save(itemFoundInRepository);

        return EditItemProductNameResponse.builder()
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
    }
}
