package com.example.zoostore.core.processors.item.edit;

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
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EditItemTagOperationProcessor implements EditItemTagOperation {
    private final ItemRepository itemRepository;
    private final TagRepository tagRepository;

    @Override
    public EditItemTagResponse process(EditItemTagRequest editItemTagRequest) {
        Item itemFoundInRepository = itemRepository.findById(editItemTagRequest.getItemId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        Set<Tag> tags = tagRepository.findAllByIdIn(editItemTagRequest.getTagIds());

        itemFoundInRepository.setTags(tags);

        Item savedItem = itemRepository.save(itemFoundInRepository);

        if(savedItem.getMultimedia().isEmpty()){
            return EditItemTagResponse.builder()
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

        return EditItemTagResponse.builder()
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

        //TODO opravi returnovete da pravqt proverki dali kolekciqta e prazna
    }
}
