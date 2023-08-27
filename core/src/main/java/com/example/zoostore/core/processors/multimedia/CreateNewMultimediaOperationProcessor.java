package com.example.zoostore.core.processors.multimedia;

import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaOperation;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaResponse;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.MultimediaRepository;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class CreateNewMultimediaOperationProcessor implements CreateNewMultimediaOperation {
    private final ItemRepository itemRepository;
    private final MultimediaRepository multimediaRepository;

    @Override
    public CreateNewMultimediaResponse process(CreateNewMultimediaRequest createNewMultimediaRequest) {
        log.info("Starting create new multimedia operation");

        Item item = itemRepository.findById(UUID.fromString(createNewMultimediaRequest.getItemId()))
                .orElseThrow(ItemNotFoundInRepositoryException::new);
        log.info("Item found in repository with ID: {}", item.getId());

        Multimedia multimedia = Multimedia.builder()
                .item(item)
                .url(createNewMultimediaRequest.getUrl())
                .build();

        Multimedia save = multimediaRepository.save(multimedia);
        log.info("Multimedia created with ID: {}", save.getId());

        CreateNewMultimediaResponse response = CreateNewMultimediaResponse.builder()
                .id(String.valueOf(save.getId()))
                .itemId(String.valueOf(save.getItem().getId()))
                .url(save.getUrl())
                .build();
        log.info("Create new multimedia operation completed");

        return response;
    }
}
