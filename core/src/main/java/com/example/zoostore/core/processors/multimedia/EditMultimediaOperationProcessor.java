package com.example.zoostore.core.processors.multimedia;

import com.example.zoostore.api.operations.multimedia.edit.full.EditMultimediaOperation;
import com.example.zoostore.api.operations.multimedia.edit.full.EditMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.edit.full.EditMultimediaResponse;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.multimedia.MultimediaNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.MultimediaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class EditMultimediaOperationProcessor implements EditMultimediaOperation {
    private final MultimediaRepository multimediaRepository;
    private final ItemRepository itemRepository;

    @Override
    public EditMultimediaResponse process(final EditMultimediaRequest editMultimediaRequest) {
        log.info("Starting edit multimedia operation");

        Multimedia multimedia = multimediaRepository.findById(UUID.fromString(editMultimediaRequest.getMultimediaId()))
                .orElseThrow(MultimediaNotFoundInRepositoryException::new);
        log.info("Multimedia found in repository with ID: {}", multimedia.getId());

        Optional.ofNullable(editMultimediaRequest.getItemId())
                .ifPresent(itemId -> {
                    Item item = itemRepository.findById(UUID.fromString(itemId))
                            .orElseThrow(ItemNotFoundInRepositoryException::new);
                    multimedia.setItem(item);
                    log.info("Associated multimedia with item: {}", item.getId());
                });

        Optional.ofNullable(editMultimediaRequest.getUrl())
                .ifPresent(url -> {
                    multimedia.setUrl(url);
                    log.info("Updated multimedia URL: {}", url);
                });

        Multimedia save = this.multimediaRepository.save(multimedia);
        log.info("Multimedia updated with ID: {}", save.getId());

        EditMultimediaResponse response = EditMultimediaResponse.builder()
                .id(String.valueOf(save.getId()))
                .url(save.getUrl())
                .itemId(String.valueOf(save.getItem().getId()))
                .build();
        log.info("Edit multimedia operation completed");

        return response;
    }
}
