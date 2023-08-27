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
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class EditItemMultimediaURLOperationProcessor implements EditItemMultimediaURLOperation {
    private final ItemRepository itemRepository;
    private final MultimediaRepository multimediaRepository;

    @Override
    public EditItemMultimediaURLResponse process(final EditItemMultimediaURLRequest editItemMultimediaURLRequest) {
        log.info("Starting edit item multimedia URL operation");

        Item itemFoundInRepository = itemRepository.findById(UUID.fromString(editItemMultimediaURLRequest.getItemId()))
                .orElseThrow(ItemNotFoundInRepositoryException::new);
        log.info("Found item for multimedia URL editing. ItemId: {}", itemFoundInRepository.getId());

        Set<Multimedia> multimedia = this.multimediaRepository.findAllByIdIn(editItemMultimediaURLRequest.getMultimediaIds().stream()
                .map(UUID::fromString)
                .collect(Collectors.toSet()));

        if (multimedia.size() != editItemMultimediaURLRequest.getMultimediaIds().size()) {
            log.error("Not all multimedia were found in the repository");
            throw new MultimediaNotFoundInRepositoryException();
        }

        log.info("All multimedia were found in the repository");

        itemFoundInRepository.setMultimedia(multimedia);

        Item savedItem = itemRepository.save(itemFoundInRepository);
        log.info("Multimedia URLs edited for item. ItemId: {}", savedItem.getId());

        List<String> multimediaIds = savedItem.getMultimedia().stream()
                .map(i -> String.valueOf(i.getId()))
                .toList();

        List<String> tagIds = savedItem.getTags().stream()
                .map(tag -> String.valueOf(tag.getId()))
                .toList();

        EditItemMultimediaURLResponse response = EditItemMultimediaURLResponse.builder()
                .itemId(String.valueOf(savedItem.getId()))
                .vendorId(String.valueOf(savedItem.getVendor().getId()))
                .description(savedItem.getDescription())
                .multimediaIds(multimediaIds)
                .productName(savedItem.getProductName())
                .tagIds(tagIds)
                .isArchived(savedItem.getArchived())
                .build();
        log.info("Edit item multimedia URL operation completed");

        return response;
    }
}
