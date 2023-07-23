package com.example.zoostore.core.processors.item.edit;

import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionRequest;
import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionResponse;
import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionOperation;
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
public class EditItemDescriptionOperationProcessor implements EditItemDescriptionOperation {
    private final ItemRepository itemRepository;

    @Override
    public EditItemDescriptionResponse process(EditItemDescriptionRequest editItemDescriptionRequest) {
        Item itemFoundInRepository = itemRepository.findItemById(editItemDescriptionRequest.getItemId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        itemFoundInRepository.setDescription(editItemDescriptionRequest.getDescription());

        Item savedItem = itemRepository.save(itemFoundInRepository);

        if(savedItem.getMultimedia().isEmpty()){
            return EditItemDescriptionResponse.builder()
                    .itemId(savedItem.getId())
                    .productName(savedItem.getProductName())
                    .isArchived(savedItem.isArchived())
                    .tagIds(savedItem.getTags().stream()
                            .map(Tag::getId)
                            .collect(Collectors.toSet()))
                    .vendorId(savedItem.getVendor().getId())
                    .description(savedItem.getDescription())
                    .build();
        }

        return EditItemDescriptionResponse.builder()
                .itemId(savedItem.getId())
                .productName(savedItem.getProductName())
                .isArchived(savedItem.isArchived())
                .tagIds(savedItem.getTags().stream()
                        .map(Tag::getId)
                        .collect(Collectors.toSet()))
                .vendorId(savedItem.getVendor().getId())
                .multimediaIds(savedItem.getMultimedia().stream()
                        .map(Multimedia::getId)
                        .collect(Collectors.toSet()))
                .description(savedItem.getDescription())
                .build();
    }
}
