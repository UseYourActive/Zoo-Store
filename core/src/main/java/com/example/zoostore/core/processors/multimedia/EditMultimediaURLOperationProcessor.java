package com.example.zoostore.core.processors.multimedia;

import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLOperation;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLRequest;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLResponse;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.repositories.MultimediaRepository;
import com.example.zoostore.core.exceptions.multimedia.MultimediaNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class EditMultimediaURLOperationProcessor implements EditMultimediaURLOperation {
    private final MultimediaRepository multimediaRepository;

    @Override
    public EditMultimediaURLResponse process(EditMultimediaURLRequest editMultimediaURLRequest) {
        log.info("Starting edit multimedia URL operation");

        Multimedia multimedia = multimediaRepository.findMultimediaById(editMultimediaURLRequest.getMultimediaId())
                .orElseThrow(MultimediaNotFoundInRepositoryException::new);
        log.info("Multimedia found in repository with ID: {}", multimedia.getId());

        multimedia.setUrl(editMultimediaURLRequest.getUrl());

        Multimedia save = multimediaRepository.save(multimedia);
        log.info("Multimedia URL updated with ID: {}", save.getId());

        EditMultimediaURLResponse response = EditMultimediaURLResponse.builder()
                .id(save.getId())
                .itemId(save.getId()) // This should probably be "itemId" instead of "id"
                .url(save.getUrl())
                .build();
        log.info("Edit multimedia URL operation completed");

        return response;
    }
}
