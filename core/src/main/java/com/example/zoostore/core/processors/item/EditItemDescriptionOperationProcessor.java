package com.example.zoostore.core.processors.item;

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
        Item itemFoundInRepository = itemRepository.findById(editItemDescriptionRequest.getItemId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        itemFoundInRepository.setDescription(editItemDescriptionRequest.getDescription());

        Item save = itemRepository.save(itemFoundInRepository);

        return EditItemDescriptionResponse.builder()
                .itemId(save.getId())
                .vendorId(save.getVendor().getId())
                .description(save.getDescription())
                .multimediaIds(save.getMultimedia().stream()
                        .map(Multimedia::getId)
                        .toList())
                .productName(save.getProductName())
                .tagIds(save.getTags().stream()
                        .map(Tag::getId)
                        .toList())
                .isArchived(true)
                .build();
    }
}
