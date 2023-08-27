package com.example.zoostore.core.processors.multimedia;

import com.example.zoostore.api.operations.multimedia.find.byid.FindMultimediaByIdOperation;
import com.example.zoostore.api.operations.multimedia.find.byid.FindMultimediaByIdRequest;
import com.example.zoostore.api.operations.multimedia.find.byid.FindMultimediaByIdResponse;
import com.example.zoostore.core.exceptions.multimedia.MultimediaNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.repositories.MultimediaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindMultimediaByIdOperationProcessor implements FindMultimediaByIdOperation {
    private final MultimediaRepository multimediaRepository;

    @Override
    public FindMultimediaByIdResponse process(FindMultimediaByIdRequest findMultimediaByIdRequest) {
        log.info("Starting find multimedia by ID operation for ID: {}", findMultimediaByIdRequest.getId());

        Multimedia multimedia = multimediaRepository.findById(UUID.fromString(findMultimediaByIdRequest.getId()))
                .orElseThrow(MultimediaNotFoundInRepositoryException::new);
        log.info("Found multimedia with ID: {}", multimedia.getId());

        FindMultimediaByIdResponse build = FindMultimediaByIdResponse.builder()
                .id(String.valueOf(multimedia.getId()))
                .url(multimedia.getUrl())
                .itemId(String.valueOf(multimedia.getItem().getId()))
                .build();
        log.info("Find multimedia by ID operation completed");

        return build;
    }
}
