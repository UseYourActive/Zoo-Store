package com.example.zoostore.core.processors.item.edit;

import com.example.zoostore.api.operations.item.edit.tag.EditItemTagRequest;
import com.example.zoostore.api.operations.item.edit.tag.EditItemTagResponse;
import com.example.zoostore.api.operations.item.edit.tag.EditItemTagOperation;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.tag.TagNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class EditItemTagOperationProcessor implements EditItemTagOperation {
    private final ItemRepository itemRepository;
    private final TagRepository tagRepository;

    @Override
    public EditItemTagResponse process(EditItemTagRequest editItemTagRequest) {
        Item itemFoundInRepository = itemRepository.findById(editItemTagRequest.getId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        Set<Tag> tags = tagRepository.findAllByIdIn(Collections.singleton(editItemTagRequest.getId()))
                .orElseThrow(TagNotFoundInRepositoryException::new);

        itemFoundInRepository.setTags(tags);

        Item savedItem = itemRepository.save(itemFoundInRepository);

        return EditItemTagResponse.builder()
                .id(savedItem.getId())
                .title(savedItem.getTags().toString())
                .build();

        //TODO opravi returnovete da pravqt proverki dali kolekciqta e prazna
    }
}
