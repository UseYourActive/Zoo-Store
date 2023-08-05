package com.example.zoostore.core.processors.item;

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

import java.util.HashSet;
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

        Item save = itemRepository.save(itemFoundInRepository);

        return EditItemMultimediaURLResponse.builder()
                .itemId(save.getId())
                .vendorId(save.getVendor().getId())
                .description(save.getDescription())
                .multimediaIds(save.getMultimedia().stream()
                        .map(Multimedia::getId)
                        .collect(Collectors.toSet()))
                .productName(save.getProductName())
                .tagIds(save.getTags().stream()
                        .map(Tag::getId)
                        .collect(Collectors.toSet()))
                .isArchived(true)
                .build();
    }
}
