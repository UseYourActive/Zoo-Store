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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class EditItemMultimediaURLOperationProcessor implements EditItemMultimediaURLOperation {
    private final ItemRepository itemRepository;
    private final MultimediaRepository multimediaRepository;

    @Override
    public EditItemMultimediaURLResponse process(EditItemMultimediaURLRequest editItemMultimediaURLRequest) {
        log.info("Starting edit item multimedia URL operation");

        Item itemFoundInRepository = itemRepository.findById(editItemMultimediaURLRequest.getItemId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);
        log.info("Found item for multimedia URL editing. ItemId: {}", itemFoundInRepository.getId());

        Set<Multimedia> multimedia = this.multimediaRepository.findAllByIdIn(editItemMultimediaURLRequest.getMultimediaIds());

        if (multimedia.size() != editItemMultimediaURLRequest.getMultimediaIds().size()) {
            log.error("Not all multimedia were found in the repository");
            throw new MultimediaNotFoundInRepositoryException();
        }
        log.info("All multimedia were found in the repository");

        itemFoundInRepository.setMultimedia(multimedia);

        Item savedItem = itemRepository.save(itemFoundInRepository);
        log.info("Multimedia URLs edited for item. ItemId: {}", savedItem.getId());

        EditItemMultimediaURLResponse response = EditItemMultimediaURLResponse.builder()
                .itemId(savedItem.getId())
                .vendorId(savedItem.getVendor().getId())
                .description(savedItem.getDescription())
                .multimediaIds(savedItem.getMultimedia().stream()
                        .map(Multimedia::getId)
                        .toList())
                .productName(savedItem.getProductName())
                .tagIds(savedItem.getTags().stream()
                        .map(Tag::getId)
                        .toList())
                .isArchived(true)
                .build();
        log.info("Edit item multimedia URL operation completed");

        return response;
    }
}
