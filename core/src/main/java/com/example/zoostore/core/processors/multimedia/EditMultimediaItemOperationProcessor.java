package com.example.zoostore.core.processors.multimedia;

import com.example.zoostore.api.operations.multimedia.edit.item.EditMultimediaItemOperation;
import com.example.zoostore.api.operations.multimedia.edit.item.EditMultimediaItemRequest;
import com.example.zoostore.api.operations.multimedia.edit.item.EditMultimediaItemResponse;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.multimedia.MultimediaNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.MultimediaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class EditMultimediaItemOperationProcessor implements EditMultimediaItemOperation {
    private final MultimediaRepository multimediaRepository;
    private final ItemRepository itemRepository;

    @Override
    public EditMultimediaItemResponse process(EditMultimediaItemRequest editMultimediaItemRequest) {
        log.info("Starting edit multimedia item operation");

        Multimedia multimedia = multimediaRepository.findById(UUID.fromString(editMultimediaItemRequest.getMultimediaId()))
                .orElseThrow(MultimediaNotFoundInRepositoryException::new);
        log.info("Multimedia found in repository with ID: {}", multimedia.getId());

        Item item = itemRepository.findById(UUID.fromString(editMultimediaItemRequest.getItemId()))
                .orElseThrow(ItemNotFoundInRepositoryException::new);
        log.info("Item found in repository with ID: {}", item.getId());

        multimedia.setItem(item);

        Multimedia save = multimediaRepository.save(multimedia);
        log.info("Multimedia updated with ID: {}", save.getId());

        EditMultimediaItemResponse response = EditMultimediaItemResponse.builder()
                .id(String.valueOf(save.getId()))
                .itemId(String.valueOf(save.getItem().getId()))
                .url(save.getUrl())
                .build();
        log.info("Edit multimedia item operation completed");

        return response;
    }
}
