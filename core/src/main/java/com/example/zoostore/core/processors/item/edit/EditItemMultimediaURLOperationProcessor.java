package com.example.zoostore.core.processors.item.edit;

import com.example.zoostore.api.operations.item.edit.multimedia.EditItemMultimediaURLRequest;
import com.example.zoostore.api.operations.item.edit.multimedia.EditItemMultimediaURLResponse;
import com.example.zoostore.api.operations.item.edit.multimedia.EditItemMultimediaURLOperation;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.multimedia.MultimediaNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.MultimediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EditItemMultimediaURLOperationProcessor implements EditItemMultimediaURLOperation {
    private final ItemRepository itemRepository;
    private final MultimediaRepository multimediaRepository;

    @Override
    public EditItemMultimediaURLResponse process(EditItemMultimediaURLRequest editItemMultimediaURLRequest) {
        Item itemFoundInRepository = itemRepository.findById(editItemMultimediaURLRequest.getItemId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        Set<Multimedia> multimedia = this.multimediaRepository.findAllByIdIn(editItemMultimediaURLRequest.getMultimediaIds());

        if (multimedia.size() != editItemMultimediaURLRequest.getMultimediaIds().size()) {
            throw new MultimediaNotFoundInRepositoryException();
        }

        itemFoundInRepository.setMultimedia(multimedia);

        Item savedItem = itemRepository.save(itemFoundInRepository);

        return EditItemMultimediaURLResponse.builder()
                .itemId(savedItem.getId())
                .title(savedItem.getProductName())
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
